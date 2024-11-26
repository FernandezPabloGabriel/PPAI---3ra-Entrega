/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package logica_de_negocio.controlador;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import logica_de_negocio.entidades.Bodega;
import presentacion.PantallaImportarBodega;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import logica_de_negocio.entidades.Enofilo;
import logica_de_negocio.entidades.Maridaje;
import logica_de_negocio.entidades.TipoUva;
import logica_de_negocio.entidades.Vino;
import logica_De_Negocio.interfaz_externa.InterfazAPIBodega;
import logica_De_Negocio.patron_observer.IObservadorNotificacion;
import logica_De_Negocio.patron_observer.ISujeto;
import logica_De_Negocio.patron_observer.InterfazNotificacionPush;
import persistencia.ControladorPersistencia;

public class GestorImportadorBodega implements ISujeto{
    private Set<Bodega> bodegasSet = new HashSet<>();
    private Set<Vino> vinosSet = new HashSet<>();
    private Set<Maridaje> maridajesSet = new HashSet<>();
    private Set<TipoUva> tiposUvasSet = new HashSet<>();
    private Set<Enofilo> enofilosSet = new HashSet<>();
    private PantallaImportarBodega pantallaImportarBodega;
    private InterfazAPIBodega interfazAPIBodega;
    private ControladorPersistencia controladorPersistencia = new ControladorPersistencia();
    private List<String> nombreBodegasSeleccionadas;
    private List<HashMap<String, Object>> resumenNovedadesVino = new ArrayList();
    private List<HashMap<String, Object>> datosANotificarPorBodegas = new ArrayList<>();
    
    

    //Cargamos todos los datos pertinentes que utilizará el gestor al inicializarce
    public GestorImportadorBodega() {
        //Creamos instancia del controlador de persistencia, quien se comunicará
        //con las clases de persistencia asignadas a cada clase entidad
        //Populación de la BD - SOLO PARA MOTIVOS DE PRUEBA
        controladorPersistencia.popularBDMock();
        
        //Carga de los objetos que utilizaremos en este programa
        //Utilizamos eager.FETCH para ello con el objetivo de simplificar, aunque
        //es una forma costosa en cuanto a memoria
        cargarVinos();
        //A partir del Set de vinos, buscamos de cada vino sus relaciones correspondientes
        //y hacemos un Set de ellas, para evitar tener objetos duplicados en memoria
        cargarBodegas();
        cargarMaridajes();
        cargarTiposUvas();
        cargarEnofilos();
    }

    
    public void setPantallaImportarBodega(PantallaImportarBodega pantallaImportarBodega) {
        this.pantallaImportarBodega = pantallaImportarBodega;
    }

    
    public void setInterfazAPIBodega(InterfazAPIBodega interfazAPIBodega) {
        this.interfazAPIBodega = interfazAPIBodega;
    }
    
    
    public void opcionImportarActualizacionVino() {
        //cargarBodegasMock();
        Set<String> nombresBodegasSet = buscarBodegaParaActualizar(this.bodegasSet);
        pantallaImportarBodega.mostrarBodegaParaActualizar(nombresBodegasSet);
        
    }
    
    
    private Set<String> buscarBodegaParaActualizar(Set<Bodega> bodegas) {
        Set<String> nombresBodegasSet = new HashSet<>();
        for(Bodega bodega:bodegas){
            boolean bodegaParaActualizar = bodega.estaParaActualizarNovedadesVino();
            if(bodegaParaActualizar){
                nombresBodegasSet.add(bodega.getNombre());
            }
        }
        return nombresBodegasSet;
    }
    
    
    public void tomarSeleccionBodega(List<String> nombreBodegasSeleccionadasParam){
        nombreBodegasSeleccionadas = nombreBodegasSeleccionadasParam;
        boolean fallaConexion = false;
        
        //Por cada bodega seleccionada actualizo y/o creo los vinos importados
        //correspondientes con los vinos asociados a esta
        for(int i = nombreBodegasSeleccionadas.size() - 1; i >= 0; i--){
            String nombreBodegaSeleccionada = nombreBodegasSeleccionadas.get(i);
            
            //Buscamos el objeto Bodega referente al nombre de la bodega
            Bodega bodegaSeleccionada = buscarBodegaSeleccionada(nombreBodegaSeleccionada);
            
            //Lista de HashMaps que representan los vinos extraidos desde la interfazApiBodega
            List<HashMap<String,Object>> listaVinosImportados = obtenerActualizacionVinosBodega(nombreBodegaSeleccionada);
            
            //Validación para evitar actualizar bodega en caso de que no tenga importaciones disponibles
            if(listaVinosImportados != null){
                //Filtramos los vinos de la lista cargada del gestor para obtener los vinos correspondientes a la bodega
                Set<Vino> vinosBodegaSeleccionada = filtrarVinosDeBodega(bodegaSeleccionada);

                //Actualizamos la lista de la información vinos importados, agregandole
                //a cada indice de la lista un HashMap que indica si será para actualizar,
                //para crear, o si el vino no se actualizó
                listaVinosImportados = actualizarCaracteristicasVinosExistentes(bodegaSeleccionada, listaVinosImportados, vinosBodegaSeleccionada);

                //Se realiza la búsqueda de los maridajes y tipos de uva cargados al gestor 
                //que se encuentren en la lista de vinos importados para sí crearlos a posteriori
                HashMap<String, Maridaje> maridajesMap = buscarMaridaje(listaVinosImportados);
                HashMap<String, TipoUva> tiposUvasMap = buscarTipoUva(listaVinosImportados);

                //Crear vinos importados y persistirlos
                crearVinos(listaVinosImportados, bodegaSeleccionada, maridajesMap, tiposUvasMap);

                //Persisto la fecha de actualización de la bodega
                bodegaSeleccionada.setFechaUltimaActualizacion();
                controladorPersistencia.actualizarFechaBodega(bodegaSeleccionada);

                //Agrego la lista de vinos importados al resumen de novedades, incluyendo solo
                //aquellos vinos que han sido actualizados o creados
                listaVinosImportados
                        .stream()
                        .filter(vino->!((String) vino.get("tipo")).equalsIgnoreCase("noActualizado"))
                        .forEach(vino->resumenNovedadesVino.add(vino));
            } else{
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con '" + nombreBodegaSeleccionada + "'", null, JOptionPane.INFORMATION_MESSAGE);
                //Si no se encuentra la bodega buscada la eliminamos de la lista de strings
                nombreBodegasSeleccionadas.remove(i);
                fallaConexion = true;
            }
        }
        
        //Pequeña condición para evitar mostrar ventanas vacías
        if(!resumenNovedadesVino.isEmpty()){
            //Mostramos resumen
            pantallaImportarBodega.mostrarResumenVinosImportados(resumenNovedadesVino);
            //Enviamos notifición push a todos los seguidores de las bodegas elegidas
            notificarUsuariosSeguidores();
        } else if(!fallaConexion){
            JOptionPane.showMessageDialog(null, "No se han añadido nuevas novedades", null, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    
    public Bodega buscarBodegaSeleccionada(String nombreBodegaSeleccionada){
        //Busca y agrega a la lista todas las bodegas cuyo nombre coincidan con
        //el de las bodegas seleccionadas
        for(Bodega bodega: bodegasSet){
            if(bodega.sosBodegaSeleccionada(nombreBodegaSeleccionada)){
                return bodega;
            }
        }
        return null;
    }
    
    
    public List<HashMap<String,Object>> obtenerActualizacionVinosBodega(String bodegaSeleccionadaNombre){
        return interfazAPIBodega.obtenerActualizacionVinos(bodegaSeleccionadaNombre);
    }
    
    
    public Set<Vino> filtrarVinosDeBodega(Bodega bodegaSeleccionada){
        return bodegaSeleccionada.filtrarVinosDeBodega(vinosSet);
    }

    
    public List<HashMap<String,Object>> actualizarCaracteristicasVinosExistentes(
            Bodega bodega, 
            List<HashMap<String,Object>> vinosImportadosData, 
            Set<Vino> vinosBodegaSeleccionada){
        for(int i = 0; i < vinosImportadosData.size(); i++){
            String estadoVino = bodega.actualizarDatosVino(vinosBodegaSeleccionada, vinosImportadosData.get(i), controladorPersistencia);
            vinosImportadosData.get(i).put("tipo", estadoVino);
        }
        return vinosImportadosData; 
    }
    
    
    public HashMap<String, Maridaje> buscarMaridaje(List<HashMap<String,Object>> listaVinosImportados){     
        HashMap<String,Maridaje> maridajesMap = new HashMap<>();
        
        maridajeLoop:
        for(Maridaje marida: maridajesSet){
            for(HashMap<String,Object> vinoImportado: listaVinosImportados){
                if(((String) vinoImportado.get("tipo")).equalsIgnoreCase("creado")){
                    //Guardamos en listaMaridaje una lista de Strings correspondiente
                    //a los nombres de los mardiajes extraidos del json importado
                    List<String> listaMaridaje = (List<String>) vinoImportado.get("maridajes");
                    for(String nombreMarida: listaMaridaje){
                        if(marida.maridaConVino(nombreMarida)){
                            maridajesMap.put(nombreMarida, marida);
                            continue maridajeLoop;
                        }
                    }
                }
            }
        }
        return maridajesMap;  
    }
    
    
    public HashMap<String,TipoUva> buscarTipoUva(List<HashMap<String,Object>> listaVinosImportados){
        HashMap<String,TipoUva> tiposUvasMap = new HashMap<>();
        tipoUvaLoop:
        for(TipoUva tipoUva: tiposUvasSet){
            for(HashMap<String,Object> vinoImportado: listaVinosImportados){
                if(((String) vinoImportado.get("tipo")).equalsIgnoreCase("creado")){
                    //Guardamos HashMap de varietales que tendría las siguientes claves:
                    //"descripcion", "porcentaje", "tipoUva"
                    List<HashMap<String,String>> listaVarietales = (List<HashMap<String,String>>) vinoImportado.get("varietales");
                    for(HashMap<String,String> varietal: listaVarietales){
                        String nombreTipoUva = varietal.get("tipoUva");
                        if(tipoUva.esTipoUva(nombreTipoUva)){
                            tiposUvasMap.put(nombreTipoUva, tipoUva);
                            continue tipoUvaLoop;
                        }
                    }
                }
            }
        }
        return tiposUvasMap;
    }
    
    
    public void crearVinos(
            List<HashMap<String,Object>> listaVinosImportados, 
            Bodega bodegaSeleccionada, 
            HashMap<String, Maridaje> maridajesMap, 
            HashMap<String, TipoUva> tiposUvasMap){      
        for(HashMap<String, Object> vinoImportado: listaVinosImportados){
            //Filtramos los vinos con el valor "Creado" de la clave "tipo"
            if(((String) vinoImportado.get("tipo")).equalsIgnoreCase("Creado")){
                Long anioAux = (long) vinoImportado.get("aniada");
                Integer aniada = anioAux.intValue();

                Double precioArs = (double) vinoImportado.get("precioArs");
                
                //Extraigo los maridajes que tengan la misma clave que los del vino importado
                List<Maridaje> maridajesExistentes = 
                        ((List<String>) vinoImportado.get("maridajes"))
                            .stream()
                            .map(marida->maridajesMap.get(marida))
                            .collect(Collectors.toList());

                //Extraigo varietales del HashMap del vino importado que tengan los
                //mismos tipos de uva del sistema
                List<HashMap<String,String>> varietalesParaCrear = 
                        ((List<HashMap<String,String>>) vinoImportado.get("varietales"))
                            .stream()
                            .filter(vari->tiposUvasMap.containsKey(vari.get("tipoUva")))
                        .collect(Collectors.toList());
                
                //Creo el vino con todos los valores obtenidos anteriormente
                Vino nuevoVino = new Vino(
                        (String) vinoImportado.get("nombre"),
                        aniada,
                        (String) vinoImportado.get("imagenEtiqueta"),
                        (String) vinoImportado.get("notaDeCataBodega"),
                        precioArs,
                        bodegaSeleccionada,
                        maridajesExistentes,
                        varietalesParaCrear,
                        tiposUvasMap
                );
                //Una vez creado el vino, lo persisto en la BD
                controladorPersistencia.desmaterializarVino(nuevoVino);
            }
        }
    }
    
    
    private void notificarUsuariosSeguidores() {
        //Creamos instancia de observador concreto "InterfazNotificacionPush" como la cual
        //es referenciada por la interfaz "IObservadorNotificacion" y los sucribimos al gestor
        //que sería el sujeto concreto
        IObservadorNotificacion observador = new InterfazNotificacionPush();
        suscribir(observador);
        
        for(String nombreBodegaActualizada: nombreBodegasSeleccionadas){
            HashMap<String,Object> datosBodegaMap = new HashMap<>();
            
            //Busco los nombres de los usuarios que siguen a la bodega seleccionada
            List<String> nombresUsuariosSeguidoresDeBodega = buscarSeguidoresDeBodega(nombreBodegaActualizada);
            
            //Validación para solo "notificar" a las bodegas que tengan seguidores
            if(!nombresUsuariosSeguidoresDeBodega.isEmpty()){
                //Filtra del resumen todas las actualizaciones y creaciones hechas para tal bodega
                List<HashMap<String, Object>> novedadesBodegaSeleccionada = resumenNovedadesVino
                        .stream()
                        .filter(novedades->((String) novedades.get("bodega"))
                                .equalsIgnoreCase(nombreBodegaActualizada))
                        .collect(Collectors.toList());

                //Extraemos los datos que nos pueden servir para la notificación a los usuarios
                List<List<String>> novedadesANotificar = new ArrayList();
                for(HashMap<String,Object> novedadBodegaSeleccionada: novedadesBodegaSeleccionada){
                    List<String> novedadesAux = new ArrayList();
                    novedadesAux.add((String) novedadBodegaSeleccionada.get("nombre"));
                    novedadesAux.add((String) novedadBodegaSeleccionada.get("tipo"));
                    novedadesANotificar.add(novedadesAux);
                }
                
                datosBodegaMap.put("bodega", nombreBodegaActualizada);
                datosBodegaMap.put("usuarios", nombresUsuariosSeguidoresDeBodega);
                datosBodegaMap.put("vinos", novedadesANotificar);
                datosANotificarPorBodegas.add(datosBodegaMap);
            } else{
                JOptionPane.showMessageDialog(null, "La bodega '" + nombreBodegaActualizada + "' no tiene seguidores.\nNo se han realizado notificaciones...", null, JOptionPane.INFORMATION_MESSAGE);
            }
        }    
        
        notificar();
        pantallaImportarBodega.mostrarResumenNotificaciones(datosANotificarPorBodegas);
    }
    
    
    //MÉTODOS DEL PATRÓN OBSERVER
    @Override
    public void suscribir(IObservadorNotificacion observador) {
        observadores.add(observador);
    }

    
    @Override
    public void quitar(IObservadorNotificacion observador) {
        observadores.remove(observador);
    }

    
    @Override
    public void notificar(){
        //Aplicamos polimorfismo al llamar al mismo método que realizan todos los observadores
        //suscritos al gestor, ejecutando cada uno de estos su propia implementación del método.
        //En nuestro caso solo tendríamos un observador.
        for(IObservadorNotificacion observador: observadores){
            observador.notificarNovedadesVinosParaBodega(datosANotificarPorBodegas);
        }
    }
    
    
    public List<String> buscarSeguidoresDeBodega(String nombreBodegaActualizada){
         return enofilosSet
                .stream()
                .filter(enofilo->enofilo.seguisABodega(nombreBodegaActualizada))
                .map(enofilo->enofilo.getNombreUsuario())
                .collect(Collectors.toList());
    }

    
    private void cargarVinos() {
        vinosSet = new HashSet<>(controladorPersistencia.materializarVinos());
    }
    
    
    private void cargarBodegas() {
        bodegasSet = vinosSet
                .stream()
                .map(vino->vino.getBodega())
                .collect(Collectors.toSet());
    }

    
    private void cargarMaridajes() {
        maridajesSet = vinosSet
                .stream()
                .flatMap(vino->vino.getMaridajes().stream())
                .collect(Collectors.toSet());
        
        Set<Maridaje> maridajesTodos = new HashSet<>(controladorPersistencia.materializarMaridajes());
        maridajesTodos.forEach(marida->maridajesSet.add(marida));
        maridajesTodos = null;
    }
    
    
    private void cargarTiposUvas() {
        tiposUvasSet = vinosSet
                .stream()
                .flatMap(vino->vino.getVarietales()
                        .stream()
                        .map(varietal->varietal.getTipoUva()))
                .collect(Collectors.toSet());
        
        Set<TipoUva> tipoUvasTodos = new HashSet<>(controladorPersistencia.materializarTipoUvas());
        tipoUvasTodos.forEach(tipoUva->tiposUvasSet.add(tipoUva));
        tipoUvasTodos = null;
    }
    
    
    private void cargarEnofilos() {
        enofilosSet = new HashSet<>(controladorPersistencia.materializarEnofilos());
    }
}
