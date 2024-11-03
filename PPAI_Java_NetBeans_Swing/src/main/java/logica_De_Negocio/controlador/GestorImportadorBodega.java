/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package logica_De_Negocio.controlador;

import java.awt.List;
import java.util.HashSet;
import java.util.Set;
import logica_De_Negocio.entidades.Bodega;
import presentacion.PantallaImportarBodega;
import java.time.LocalDateTime;
import persistencia.ControladorPersistencia;

/**
 *
 * @author USUARIO
 */
public class GestorImportadorBodega {
    private Set<Bodega> bodegasSet = new HashSet<>();
    private PantallaImportarBodega pantallaImportarBodega;

    public GestorImportadorBodega() {
        ControladorPersistencia controladorPersistencia = new ControladorPersistencia();
//        cargarBodegas();
//        cargarEnofilos();
//        cargarMaridajes();
//        cargarSiguiendos();
//        cargarTiposUvas();
//        cargarUsuarios();
//        cargarVarietales();
//        cargarVinos();
    }
    
    
    
    //---Debe ser eliminado cuando se implemente la conexión con BD
    public void cargarBodegasMock(){
        Bodega bodega1 = new Bodega("Bodega San Pedro", "-33.4167, -70.6667", "Bodega tradicional chilena con una amplia variedad de vinos tintos.",
                                        "Fundada en 1865, es una de las más antiguas de Chile.", LocalDateTime.of(2023, 11, 15, 0, 0), 3);
       Bodega bodega2 = new Bodega("Château Margaux", "44.9778, -0.3333", "Una de las bodegas más prestigiosas de Burdeos, Francia.", 
                                    "Clasificada como Premier Grand Cru en 1855.", LocalDateTime.of(2023, 12, 1, 0, 0), 6);
       Bodega bodega3 = new Bodega("Bodega Colomé", "-24.6167, -65.5333", "La bodega más alta del mundo, ubicada en los Andes argentinos.", 
                                    "Con una vista impresionante de la montaña.", LocalDateTime.of(2024, 1, 15, 0, 0), 9);
       Bodega bodega4 = new Bodega("Bodega Terrazas de los Andes", "-32.9500, -68.8333", "Bodega argentina con vinos de alta calidad y un enfoque en la sustentabilidad.", 
                                    "Ubicada en Mendoza.", LocalDateTime.of(2024, 2, 1, 0, 0), 3);
       Bodega bodega5 = new Bodega("Bodega Penfolds", "-34.9500, 138.5833", "Una de las bodegas más famosas de Australia, conocida por sus vinos tintos concentrados.", 
                                    "Con una larga historia de innovación enológica.", LocalDateTime.of(2024, 2, 15, 0, 0), 6);
       Bodega bodega6 = new Bodega("Bodega Vega Sicilia", "41.7833, -4.5167", "Una de las bodegas más emblemáticas de España, con vinos de crianza prolongada.", 
                                    "Ubicada en Ribera del Duero.", LocalDateTime.of(2024, 3, 1, 0, 0), 90);
       Bodega bodega7 = new Bodega("Bodega Tenuta dell'Ornellaia", "43.4500, 10.6833", "Bodega italiana productora de vinos Super Tuscans de alta gama.", 
                                    "Conocida por sus vinos elegantes y complejos.", LocalDateTime.of(2024, 3, 15, 0, 0), 30);
       Bodega bodega8 = new Bodega("Bodega Klein Constantia", "-34.0500, 18.4500", "Una de las bodegas más antiguas de Sudáfrica, con una larga tradición vitivinícola.", 
                                    "Ubicada en las laderas de la Montaña de la Mesa.", LocalDateTime.of(2024, 4, 1, 0, 0), 60);
       Bodega bodega9 = new Bodega("Bodega Cloudy Bay", "-41.3000, 174.1833", "Bodega neozelandesa reconocida por sus Sauvignon Blancs frescos y aromáticos.", 
                                    "Ubicada en Marlborough.", LocalDateTime.of(2024, 4, 15, 0, 0), 90);
       Bodega bodega10 = new Bodega("Bodega Quinta do Noval", "41.0833, -8.1667", "Bodega portuguesa especializada en vinos de Porto, con una larga historia.", 
                                 "Ubicada en el Valle del Duero.", LocalDateTime.of(2024, 5, 1, 0, 0), 30);
       bodegasSet.add(bodega1);
       bodegasSet.add(bodega2);
       bodegasSet.add(bodega3);
       bodegasSet.add(bodega4);
       bodegasSet.add(bodega5);
       bodegasSet.add(bodega6);
       bodegasSet.add(bodega7);
       bodegasSet.add(bodega8);
       bodegasSet.add(bodega9);
       bodegasSet.add(bodega10);
    }
    

    public void setPantallaImportarBodega(PantallaImportarBodega pantallaImportarBodega) {
        this.pantallaImportarBodega = pantallaImportarBodega;
    }

    public void opcionImportarActualizacionVino() {
        cargarBodegasMock();
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
        bodegasSeleccionadas.forEach(bod->System.out.println(bod)); //log
        obtenerActualizacionVinosBodega(bodegasSeleccionadas);
    }
    
    public void obtenerActualizacionVinosBodega(Set<String> bodegasSeleccionadas){
        obtenerActualizacionVinos();
    }
    
    public void obtenerActualizacionVinos(){System.out.println("Todavía no implementado...");}
    
    public void actualizarCaracteristicasVinoExistente(){}
    
    public void buscarMaridaje(){     
    }
    
    public void buscarTipoUva(){}
    
    public void crearVinos(){}
    
    public void buscarSeguidoresDeBodega(){}
    
    public void finCU(){}

    private void cargarBodegas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void cargarEnofilos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void cargarSiguiendos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void cargarTiposUvas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void cargarUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void cargarVinos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void cargarMaridajes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void cargarVarietales() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
