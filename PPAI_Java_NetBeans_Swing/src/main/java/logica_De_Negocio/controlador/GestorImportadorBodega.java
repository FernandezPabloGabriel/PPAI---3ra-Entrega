/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package logica_De_Negocio.controlador;

import java.time.LocalDateTime;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import logica_De_Negocio.entidades.Bodega;
import presentacion.PantallaImportarBodega;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import javax.swing.JDialog;
import logica_De_Negocio.entidades.Enofilo;
import logica_De_Negocio.entidades.Maridaje;
import logica_De_Negocio.entidades.Siguiendo;
import logica_De_Negocio.entidades.TipoUva;
import logica_De_Negocio.entidades.Usuario;
import logica_De_Negocio.entidades.Vino;
import logica_De_Negocio.gestion_interfaces.InterfazAPIBodega;
import patron_observer.IObservadorNotificacion;
import patron_observer.ISujeto;
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
    private List<HashMap<String, Object>> resumenNovedadesVino = new ArrayList();
    
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

        //Por cada bodega seleccionada actualizo y/o creo los vinos importados
        //correspondientes con los vinos asociados a esta
        for(String nombreBodegaSeleccionada: nombreBodegasSeleccionadas){
            Bodega bodegaSeleccionada = buscarBodegaSeleccionada(nombreBodegaSeleccionada);
            
            //Lista de HashMaps que representan los vinos extraidos desde la interfazApiBodega
            List<HashMap<String,Object>> listaVinosImportados = obtenerActualizacionVinosBodega(nombreBodegaSeleccionada);
            
            //Filtramos los vinos de la lista cargada del gestor para obtener los vinos correspondientes a la bodega
            Set<Vino> vinosBodegaSeleccionada = filtrarVinosDeBodega(bodegaSeleccionada);
            
            //Actualizar vinos existentes y reasignar valor de "listaVinosImportados"
            //con la lista de vinos importados que no se encontró en el sistema y que
            //por lo tanto se creará
            listaVinosImportados = actualizarCaracteristicasVinosExistentes(bodegaSeleccionada, listaVinosImportados, vinosBodegaSeleccionada);
            
            //Se realiza la búsqueda de los maridajes y tipos de uva cargados al gestor 
            //que se encuentren en la lista de vinos importados para sí crearlos a posteriori
            HashMap<String, Maridaje> maridajesMap = buscarMaridaje(listaVinosImportados);
            HashMap<String, TipoUva> tiposUvasMap = buscarTipoUva(listaVinosImportados);
            
            //Crear vinos importados y persistirlos
            crearVinos(listaVinosImportados, bodegaSeleccionada, maridajesMap, tiposUvasMap);
            
            //Persisto la fecha de actualización de la bodega
            bodegaSeleccionada.setFechaUltimaActualizacion(LocalDateTime.now());
            controladorPersistencia.actualizarFechaBodega(bodegaSeleccionada);
        }
        
        //Mostramos resumen
        PantallaResumenNovedadesVino pantallaResumenVinosImportados = new PantallaResumenNovedadesVino();
        pantallaResumenVinosImportados.setVisible(true);
        pantallaResumenVinosImportados.mostrarResumenVinosImportados(resumenNovedadesVino);
        
        //Enviamos notifición push a todos los seguidores de las bodegas elegidas
        //notificarUsuariosSeguidores();
        
        resumenNovedadesVino = new ArrayList();
    }

    
    public Bodega buscarBodegaSeleccionada(String nombreBodegaSeleccionada){
        Bodega bodegaSeleccionada;
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
        //Creamos una lista de HashMaps a la que se agregarán aquellos vinos
        //que no fueron encontrados en el sistema y que serán creados
        List<HashMap<String,Object>> vinosParaCrear = new ArrayList<>();
        
        for(HashMap<String,Object> vinoImportado: vinosImportadosData){
            Vino vinoActualizado = bodega.actualizarDatosVino(vinosBodegaSeleccionada, vinoImportado);
            if(vinoActualizado != null){
                //Persistir cambios
                controladorPersistencia.actualizarVinos(vinoActualizado);
                //Agrego vino actualizado al resumen
                HashMap<String, Object> resumenVinoActualizado = new HashMap<>();
                resumenVinoActualizado.put("nombre", vinoActualizado.getNombre());
                resumenVinoActualizado.put("precioArs", vinoActualizado.getPrecioArs());
                resumenVinoActualizado.put("notaDeCataBodega", vinoActualizado.getNotaDeCataBodega());
                resumenVinoActualizado.put("imagenEtiqueta", vinoActualizado.getImagenEtiqueta());
                resumenVinoActualizado.put("tipo", "Actualizado");
                resumenVinoActualizado.put("bodega", vinoActualizado.getBodega().getNombre());
                resumenNovedadesVino.add(resumenVinoActualizado);
            } else{
                vinosParaCrear.add(vinoImportado);
            }
        }
        return vinosParaCrear; 
    }
    
    
    public HashMap<String, Maridaje> buscarMaridaje(List<HashMap<String,Object>> listaVinosImportados){     
        HashMap<String,Maridaje> maridajesMap = new HashMap<>();
        
        //Filtro los maridajes de los vinos importados
        List<String> listaMaridajes = listaVinosImportados
                .stream()
                .flatMap(vinoImportado->((List<String>) vinoImportado.get("maridajes"))
                        .stream())
                .distinct()
                .collect(Collectors.toList());
        
        //Añado al HashMap los maridajes que se encuentren en el sistema
        maridajesSet
                .stream()
                .forEach(marida->{
                        if(marida.maridaConVino(listaMaridajes)){
                            maridajesMap.put(marida.getNombre(), marida);
                        };
                    });
                
        return maridajesMap;
    }
    
    
    public HashMap<String,TipoUva> buscarTipoUva(List<HashMap<String,Object>> listaVinosImportados){
        HashMap<String,TipoUva> tiposUvasMap = new HashMap<>();
        
        //Filtro los tipos de uva de los vinos importados
        List<String> listaTiposUvas = listaVinosImportados
                .stream()
                .flatMap(vino->((List<HashMap<String,String>>) vino.get("varietales"))
                        .stream()
                        .map(vari->vari.get("tipoUva")))
                .distinct()
                .collect(Collectors.toList());
        
        //Añado al HashMap los tipos de uva que se encuentren en el sistema
        tiposUvasSet
                .stream()
                .forEach(tipoUva->{
                    if(tipoUva.esTipoUva(listaTiposUvas)){
                        tiposUvasMap.put(tipoUva.getNombre(), tipoUva);
                    };
                });
        return tiposUvasMap;
    }
    
    
    public void crearVinos(List<HashMap<String,Object>> listaVinosImportados, 
            Bodega bodegaSeleccionada, 
            HashMap<String, Maridaje> maridajesMap, 
            HashMap<String, TipoUva> tiposUvasMap){
        
        //Implementar algún tipo de excepción en caso de que suceda algún error con las relaciones al cargarlas
        for(HashMap<String, Object> vinoImportado: listaVinosImportados){
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
                    
            Long anioAux = (long) vinoImportado.get("aniada");
            Integer aniada = anioAux.intValue();
            
            Double precioArs = (double) vinoImportado.get("precioArs");
            
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
            
            controladorPersistencia.desmaterializarVino(nuevoVino);
            vinoImportado.put("tipo", "Creado");
            resumenNovedadesVino.add(vinoImportado);
        }
    }
    
    
//    private void notificarUsuariosSeguidores() {
//        IObservadorNotificacion observador = new InterfazNotificacionPush();
//        suscribir(observador);
//        for(Bodega bodegaSeleccionada: bodegasSeleccionadas){
//            bodegaActual = bodegaSeleccionada.getNombre();
//            //Busco los nombres de los usuarios que siguen a la bodega seleccionada
//            nombresUsuariosBodegaSeleccionadaActual = buscarSeguidoresDeBodega(bodegaActual);
//            
//            //Filtra del resumen todas las actualizaciones y creaciones hechas para
//            //tal bodega
//            List<HashMap<String, Object>> novedadesBodegaSeleccionada = resumenNovedadesVino
//                    .stream()
//                    .filter(novedades->((String) novedades.get("bodega"))
//                            .equalsIgnoreCase(bodegaActual.getNombre()))
//                    .collect(Collectors.toList());
//            
//            //Extraer datos pertinentes del filtro
//            
//            
//            
//            notificar();
//        }
//    }
    
    
    //MÉTODOS DEL PATRÓN OBSERVER
    @Override
    public void suscribir(IObservadorNotificacion observador) {
        observadores.add(observador);
    }

    
    @Override
    public void quitar(IObservadorNotificacion observador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    @Override
    public void notificar(){
//        for(IObservadorNotificacion observador: observadores){
//            observador.notificarNovedadesVinosParaBodega(novedadesBodegaSeleccionadaActual, nombresUsuariosBodegaSeleccionadaActual, bodegaActual.getNombre());
//        }
    }
    
    
    
    public Set<String> buscarSeguidoresDeBodega(Bodega bodegaSeleccionada){
         return enofilosSet
                .stream()
                .filter(enofilo->enofilo.seguisABodega(bodegaSeleccionada))
                .map(enofilo->enofilo.getNombreUsuario())
                .collect(Collectors.toSet());
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
