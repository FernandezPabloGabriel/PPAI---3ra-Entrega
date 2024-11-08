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

    //Cargamos todos los datos pertinentes que utilizará el gestor al inicializarce
    public GestorImportadorBodega() {
        //Creamos instancia del controlador de persistencia, quien se comunicará
        //con las clases de persistencia asignadas a cada clase entidad
        ControladorPersistencia controladorPersistencia = new ControladorPersistencia();
        //Populación de la BD
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
    
    public void estaParaActualizarNovedadesVino(){
    }
    
    public void getNombre(){
    }
    
    public void tomarSeleccionBodega(Set<String> bodegasSeleccionadas){
        Set<Bodega> bodegasSeleccionadasSet;
        
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
    }
    
    public HashMap<Bodega, List<List<String>>> obtenerActualizacionVinosBodega(Set<String> bodegasSeleccionadas){
        //Faltaría asociarla a su correspondiente bodega y extraer maridajes, varietales y tipos de uva
        HashMap<Bodega, List<List<String>>> bodegaVinosMap = new HashMap<>();
        
//        for(String bodegaSeleccionada:bodegasSeleccionadas){
//            InterfazAPIBodega interfazAPIBodega = new InterfazAPIBodega();
//            
//            //Vinos extraidos
//            List<List<String>> listaVinos = interfazAPIBodega.obtenerActualizacionVinos(bodegaSeleccionada);
//            
//            //Recuperamos el objeto de bodega correspondiente al nombre seleccionado
//            Bodega bodega = (bodegasSet.stream()
//                    .filter(bode->bode
//                            .getNombre()
//                            .equalsIgnoreCase(bodegaSeleccionada))
//                    .collect(Collectors
//                            .toList()))
//                    .get(0);
//            bodegaVinosMap.put(bodega, listaVinos);
//        }
        return bodegaVinosMap;
    }
    
    public void actualizarCaracteristicasVinoExistente(Bodega bodega, List<String> vino){
        bodega.actualizarDatosVinos(vino);
    }
    
    public void buscarMaridaje(){     
    }
    
    public void buscarTipoUva(){}
    
    public void crearVinos(){}
    
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
