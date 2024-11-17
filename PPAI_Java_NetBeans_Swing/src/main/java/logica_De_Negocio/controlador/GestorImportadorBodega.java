/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package logica_de_negocio.controlador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import logica_de_negocio.entidades.Bodega;
import presentacion.PantallaImportarBodega;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import logica_de_negocio.entidades.Enofilo;
import logica_de_negocio.entidades.Maridaje;
import logica_de_negocio.entidades.Siguiendo;
import logica_de_negocio.entidades.TipoUva;
import logica_de_negocio.entidades.Usuario;
import logica_de_negocio.entidades.Vino;
import logica_de_negocio.gestion_interfaces.InterfazAPIBodega;
import patron_observer.IObservadorNotificacion;
import patron_observer.ISujeto;
import patron_observer.InterfazNotificacionPush;
import patron_observer.InterfazNotificacionPush;
import persistencia.ControladorPersistencia;
import presentacion.PantallaResumenNovedadesVino;

/**
 *
 * @author USUARIO
 */
public class GestorImportadorBodega implements ISujeto{
    private Set<Bodega> bodegasSet = new HashSet<>();
    private Set<Vino> vinosSet = new HashSet<>();
    private Set<Maridaje> maridajesSet = new HashSet<>();
    private Set<TipoUva> tiposUvasSet = new HashSet<>();
    private Set<Enofilo> enofilosSet = new HashSet<>();
    private PantallaImportarBodega pantallaImportarBodega;
    private InterfazAPIBodega interfazAPIBodega;
    private ControladorPersistencia controladorPersistencia = new ControladorPersistencia();
    
    
//    private Bodega bodegaActual;
//    private Set<String> nombresUsuariosBodegaSeleccionadaActual;
//    private List<List<String>> novedadesBodegaSeleccionadaActual;

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
    
    
    //---Faltaría validación para cuando se devuelve un Set vacío
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
    
    
    public void tomarSeleccionBodega(Set<String> nombreBodegasSeleccionadas){
        List<HashMap<String, Object>> resumenNovedadesVino = new ArrayList();
        List<String> nombreBodegasActualizadas = new ArrayList();
        boolean fallaConexion = false;
        
        //Por cada bodega seleccionada actualizo y/o creo los vinos importados
        //correspondientes con los vinos asociados a esta
        for(String nombreBodegaSeleccionada: nombreBodegasSeleccionadas){
            Bodega bodegaSeleccionada = buscarBodegaSeleccionada(nombreBodegaSeleccionada);
            
            //Lista de HashMaps que representan los vinos extraidos desde la interfazApiBodega
            List<HashMap<String,Object>> listaVinosImportados = obtenerActualizacionVinosBodega(nombreBodegaSeleccionada);
            
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

                //Agrego la lista de vinos importados al resumen de novedades
                listaVinosImportados
                        .stream()
                        .filter(vino->!((String) vino.get("tipo")).equalsIgnoreCase("noActualizado"))
                        .forEach(vino->resumenNovedadesVino.add(vino));
                
                //Agrego la bodega a la lista de bodegas seleccionadas para realizar la notificacion a posteriori
                nombreBodegasActualizadas.add(nombreBodegaSeleccionada);
                
            } else{
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con '" + nombreBodegaSeleccionada + "'", null, JOptionPane.INFORMATION_MESSAGE);
                fallaConexion = true;
            }
        }
        
        //Pequeña condición para evitar mostrar ventanas vacías
        if(resumenNovedadesVino.size() > 0){
            //Mostramos resumen
            pantallaImportarBodega.mostrarResumenVinosImportados(resumenNovedadesVino);
            //Enviamos notifición push a todos los seguidores de las bodegas elegidas
            notificarUsuariosSeguidores(nombreBodegasActualizadas, resumenNovedadesVino);
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
//            if(estadoVino.equalsIgnoreCase("actualizado")){
//                //Agrego tipo de actualización a cada elemento de la lista de vinosImportadosData
//                vinosImportadosData.get(i).put("tipo", "Actualizado");
//            } else{
//                vinosImportadosData.get(i).put("tipo", "Creado");
//            }
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
//        //Filtro los maridajes de los vinos importados
//        List<String> listaMaridajes = listaVinosImportados
//                .stream()
//                .filter(vinoImportado->((String) vinoImportado.get("tipo")).equalsIgnoreCase("creado"))
//                .flatMap(vinoImportado->((List<String>) vinoImportado.get("maridajes"))
//                        .stream())
//                .distinct()
//                .collect(Collectors.toList());
//        
//        //Añado al HashMap los maridajes que se encuentren en el sistema
//        maridajesSet
//                .stream()
//                .forEach(marida->{
//                        if(marida.maridaConVino(listaMaridajes)){
//                            maridajesMap.put(marida.getNombre(), marida);
//                        };
//                    });     
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
//        List<String> listaTiposUvas = listaVinosImportados
//                .stream()
//                .filter(vinoImportado->((String) vinoImportado.get("tipo")).equalsIgnoreCase("creado"))
//                .flatMap(vino->((List<HashMap<String,String>>) vino.get("varietales"))
//                        .stream()
//                        .map(vari->vari.get("tipoUva")))
//                .distinct()
//                .collect(Collectors.toList());
//        
//        //Añado al HashMap los tipos de uva que se encuentren en el sistema
//        tiposUvasSet
//                .stream()
//                .forEach(tipoUva->{
//                    if(tipoUva.esTipoUva(listaTiposUvas)){
//                        tiposUvasMap.put(tipoUva.getNombre(), tipoUva);
//                    };
//                });
    }
    
    
    public void crearVinos(
            List<HashMap<String,Object>> listaVinosImportados, 
            Bodega bodegaSeleccionada, 
            HashMap<String, Maridaje> maridajesMap, 
            HashMap<String, TipoUva> tiposUvasMap){      
        //--Implementar algún tipo de excepción en caso de que suceda algún error con las relaciones al cargarlas
        for(HashMap<String, Object> vinoImportado: listaVinosImportados){
            if(((String) vinoImportado.get("tipo")).equalsIgnoreCase("Creado")){
                Long anioAux = (long) vinoImportado.get("aniada");
                Integer aniada = anioAux.intValue();

                Double precioArs = (double) vinoImportado.get("precioArs");
                
                //Extraigo los maridajes que tengan la misma clave que los del vino importado
                //--Implementar condición de que no se cree el vino en caso de que no se encuentre maridaje
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
    
    
    private void notificarUsuariosSeguidores(
            List<String> nombreBodegasActualizadas, 
            List<HashMap<String,Object>> listaVinosImportadosTotales) {
        //Guardamos todos los datos que se notificaron a los usuarios para así
        //mostrar un informe de ello posteriormente
        List<List<List<String>>> resumenNotificaciones = new ArrayList<>();

        //Creamos instancia de observador concreto "InterfazNotificacionPush" como la cual
        //es referenciada por la interfaz "IObservadorNotificacion" y los sucribimos al gestor
        //que sería el sujeto concreto
        IObservadorNotificacion observador = new InterfazNotificacionPush();
        suscribir(observador);
        
        for(String nombreBodegaActualizada: nombreBodegasActualizadas){
            //Busco los nombres de los usuarios que siguen a la bodega seleccionada
            List<String> nombresUsuariosSeguidoresDeBodega = buscarSeguidoresDeBodega(nombreBodegaActualizada);
            
            if(!nombresUsuariosSeguidoresDeBodega.isEmpty()){
                //Filtra del resumen todas las actualizaciones y creaciones hechas para tal bodega
                List<HashMap<String, Object>> novedadesBodegaSeleccionada = listaVinosImportadosTotales
                        .stream()
                        .filter(novedades->((String) novedades.get("bodega"))
                                .equalsIgnoreCase(nombreBodegaActualizada))
                        .collect(Collectors.toList());

                List<List<String>> novedadesANotificar = new ArrayList();
                for(HashMap<String,Object> novedadBodegaSeleccionada: novedadesBodegaSeleccionada){
                    List<String> novedadesAux = new ArrayList();
                    novedadesAux.add((String) novedadBodegaSeleccionada.get("nombre"));
                    novedadesAux.add((String) novedadBodegaSeleccionada.get("tipo"));
                    novedadesANotificar.add(novedadesAux);
                }

                notificar(novedadesANotificar, nombresUsuariosSeguidoresDeBodega, nombreBodegaActualizada);

                List<String> auxBodega = Arrays.asList(nombreBodegaActualizada);
                resumenNotificaciones.add(Arrays.asList(nombresUsuariosSeguidoresDeBodega, auxBodega));
            } else{
                JOptionPane.showMessageDialog(null, "La bodega '" + nombreBodegaActualizada + "' no tiene seguidores.\nNo se han realizado notificaciones...", null, JOptionPane.INFORMATION_MESSAGE);
            }
        }    
        
        pantallaImportarBodega.mostrarResumenNotificaciones(resumenNotificaciones);
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
    public void notificar(
            List<List<String>> novedadesVinos,
            List<String> nombresUsuarios,
            String nombreBodegaSeleccionada){
        for(IObservadorNotificacion observador: observadores){
            observador.notificarNovedadesVinosParaBodega(novedadesVinos, nombresUsuarios, nombreBodegaSeleccionada);
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
        enofilosSet.forEach(e->System.out.println(e));
    }
}
