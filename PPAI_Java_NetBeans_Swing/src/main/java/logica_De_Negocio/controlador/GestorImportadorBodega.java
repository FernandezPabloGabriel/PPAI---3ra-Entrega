/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package logica_De_Negocio.controlador;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import logica_De_Negocio.entidades.Bodega;
import presentacion.PantallaImportarBodega;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import logica_De_Negocio.entidades.Maridaje;
import logica_De_Negocio.entidades.TipoUva;
import logica_De_Negocio.entidades.Vino;
import logica_De_Negocio.gestion_interfaces.InterfazAPIBodega;
import persistencia.ControladorPersistencia;

/**
 *
 * @author USUARIO
 */
public class GestorImportadorBodega {
    private Set<Bodega> bodegasSet = new HashSet<>();
    private Set<Vino> vinosSet = new HashSet<>();
    private Set<Maridaje> maridajesSet = new HashSet<>();
    private Set<TipoUva> tiposUvasSet = new HashSet<>();
    private PantallaImportarBodega pantallaImportarBodega;
    private InterfazAPIBodega interfazAPIBodega;
    private ControladorPersistencia controladorPersistencia = new ControladorPersistencia();

    //Cargamos todos los datos pertinentes que utilizará el gestor al inicializarce
    public GestorImportadorBodega() {
        //Creamos instancia del controlador de persistencia, quien se comunicará
        //con las clases de persistencia asignadas a cada clase entidad
        
        //Populación de la BD - SOLO PARA MOTIVOS DE PRUEBA
        controladorPersistencia.popularBDMock();
        
        //Carga de los objetos que utilizaremos en este programa
        //Utilizamos eager.FETCH para ello con el objetivo de simplificar, aunque
        //es una forma costosa en cuanto a memoria
        cargarVinos(controladorPersistencia);
        //A partir del Set de vinos, buscamos de cada vino sus relaciones correspondientes
        //y hacemos un Set de ellas, para evitar tener objetos duplicados en memoria
        cargarBodegas();
        cargarMaridajes(controladorPersistencia);
        cargarTiposUvas(controladorPersistencia);
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
    
    public void tomarSeleccionBodega(Set<String> bodegasSeleccionadas){
        List<Bodega> listaBodegasSeleccionadas = buscarBodegasSeleccionadas(bodegasSeleccionadas);
        
        for(Bodega bodegaSeleccionada: listaBodegasSeleccionadas){
            //Lista de String de vinos extraidos desde la interfazApiBodega
            List<HashMap<String,Object>> listaVinosImportados = obtenerActualizacionVinosBodega(bodegaSeleccionada.getNombre());
            //Filtramos los vinos de la lista cargada del gestor para obtener los vinos correspondientes a la bodega
            Set<Vino> vinosBodegaSeleccionada = filtrarVinosDeBodega(bodegaSeleccionada);
            
            //Actualizar vinos existentes
            listaVinosImportados = actualizarCaracteristicasVinoExistente(bodegaSeleccionada, listaVinosImportados, vinosBodegaSeleccionada);
            HashMap<String, Maridaje> maridajesMap = buscarMaridaje(listaVinosImportados);
            HashMap<String, TipoUva> tiposUvasMap = buscarTipoUva(listaVinosImportados);
            
            System.out.println(maridajesMap);
            System.out.println(tiposUvasMap);
            
            crearVinos(listaVinosImportados, bodegaSeleccionada, maridajesMap, tiposUvasMap);
            }
        }

//        for(Bodega bodegaSeleccionada:bodegaVinosMap.keySet()){
//            bodegaVinosMap.get(bodegaSeleccionada)
//                    .stream()
//                    .forEach(vino->{
//                        vinosSet.forEach(vinoS->{
//                            if(vinoS.getNombre().equalsIgnoreCase(vino.get(0))){
//                                actualizarCaracteristicasVinoExistente(bodegaSeleccionada, vinoS);
//                            }
//                        });
//                    });
//        }
    
    public List<Bodega> buscarBodegasSeleccionadas(Set<String> bodegasSeleccionadas){
        List<Bodega> listaBodegasSeleccionadas = new ArrayList<>();
        
        for(String bodegaSeleccionada: bodegasSeleccionadas){
            bodegasSet.forEach(bode->{
                if(bode.getNombre().equalsIgnoreCase(bodegaSeleccionada)){
                    listaBodegasSeleccionadas.add(bode);
                }
            });
        }   
        return listaBodegasSeleccionadas;
    }
    
    public List<HashMap<String,Object>> obtenerActualizacionVinosBodega(String bodegaSeleccionadaNombre){
        //List<List<String>> listaVinos = interfazAPIBodega.obtenerActualizacionVinos(bodegaSeleccionadaNombre);
        //Faltaría asociarla a su correspondiente bodega y extraer maridajes, varietales y tipos de uva
        return interfazAPIBodega.obtenerActualizacionVinos(bodegaSeleccionadaNombre);
    }
    
    public Set<Vino> filtrarVinosDeBodega(Bodega bodegaSeleccionada){
        return bodegaSeleccionada.filtrarVinosDeBodega(vinosSet);
    }
    
    public List<HashMap<String,Object>> actualizarCaracteristicasVinoExistente(Bodega bodega, 
            List<HashMap<String,Object>> vinosImportadosData, 
            Set<Vino> vinosBodegaSeleccionada){
        System.out.println("-------------------------------------------------");
        List<HashMap<String,Object>> vinosParaCrear = new ArrayList<>();
        
        for(HashMap<String,Object> vinoImportado: vinosImportadosData){
            Vino vinoActualizado = bodega.actualizarDatosVino(vinosBodegaSeleccionada, vinoImportado);
            if(vinoActualizado != null){
                controladorPersistencia.actualizarVinos(vinoActualizado);
            } else{
                vinosParaCrear.add(vinoImportado);
            }
        }
        return vinosParaCrear;
        //Vino vinoActualizado = bodega.actualizarDatosVino(vinosBodegaSeleccionada, );
//        if(vinoActualizado != null){
//            controladorPersistencia.actualizarVinos(vinoActualizado);
//        }
        
        
    }
    
    public void getNombre(){
    }
    
    public HashMap<String, Maridaje> buscarMaridaje(List<HashMap<String,Object>> listaVinosImportados){     
        HashMap<String,Maridaje> maridajesMap = new HashMap<>();
        //Filtro los maridajes importados
        List<String> listaMaridajes = listaVinosImportados
                .stream()
                .flatMap(vinoImportado->((List<String>) vinoImportado.get("maridajes"))
                        .stream())
                .distinct()
                .collect(Collectors.toList());
        
        maridajesSet
                .stream()
                .forEach(marida->{
                    for(String nombreMaridaje: listaMaridajes){
                        if(marida.maridaConVino(nombreMaridaje)){
                            maridajesMap.put(nombreMaridaje, marida);
                        };
                    }
                });
                
        return maridajesMap;
    }
    
    public HashMap<String,TipoUva> buscarTipoUva(List<HashMap<String,Object>> listaVinosImportados){
        HashMap<String,TipoUva> tiposUvasMap = new HashMap<>();
        
        List<String> listaTiposUvas = listaVinosImportados
                .stream()
                .flatMap(vino->((List<HashMap<String,String>>) vino.get("varietales"))
                        .stream()
                        .map(vari->vari.get("tipoUva")))
                .distinct()
                .collect(Collectors.toList());
        
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
        
        for(HashMap<String, Object> vinoImportado: listaVinosImportados){
            List<HashMap<String,String>> varietalesParaCrear = 
                    ((List<HashMap<String,String>>) vinoImportado.get("varietales"))
                        .stream()
                        .filter(vari->tiposUvasMap.containsKey(vari.get("tipoUva")))
                    .collect(Collectors.toList());
                    
            Long anioAux = (long) vinoImportado.get("aniada");
            Integer anio = anioAux.intValue();
            
            Double precioArs = (double) vinoImportado.get("precioArs");
            
            Vino nuevoVino = new Vino(
                    (String) vinoImportado.get("nombre"),
                    anio,
                    (String) vinoImportado.get("imagenEtiqueta"),
                    (String) vinoImportado.get("notaDeCataBodega"),
                    precioArs,
                    bodegaSeleccionada,
                    ((List<String>) vinoImportado.get("maridajes"))
                        .stream()
                        .map(marida->maridajesMap.get(marida))
                        .distinct()
                        .collect(Collectors.toList()),
                    varietalesParaCrear,
                    tiposUvasMap
            );
            
            controladorPersistencia.desmaterializarVino(nuevoVino);
        }
        
//        public Vino(String nombre, 
//            int aniada, 
//            String imagenEtiqueta, 
//            String notaDeCataBodega, 
//            double precioArs, 
//            Bodega bodega, 
//            List<Maridaje> maridajes, 
//            List<HashMap<String,String>> varietales) 
    }
    
    public void buscarSeguidoresDeBodega(){}
    
    public void finCU(){}



    private void cargarEnofilos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void cargarSiguiendos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    private void cargarUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void cargarVinos(ControladorPersistencia controladorPersistencia) {
        vinosSet = new HashSet<>(controladorPersistencia.materializarVinos());
        
    }
    
    private void cargarBodegas() {
        bodegasSet = vinosSet
                .stream()
                .map(vino->vino.getBodega())
                .collect(Collectors.toSet());
        
    }

    private void cargarMaridajes(ControladorPersistencia controladorPersistencia) {
        maridajesSet = vinosSet
                .stream()
                .flatMap(vino->vino.getMaridajes().stream())
                .collect(Collectors.toSet());
        
        Set<Maridaje> maridajesTodos = new HashSet<>(controladorPersistencia.materializarMaridajes());
        maridajesTodos.forEach(marida->maridajesSet.add(marida));
        maridajesTodos = null;
    }
    
    private void cargarTiposUvas(ControladorPersistencia controladorPersistencia) {
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
}
