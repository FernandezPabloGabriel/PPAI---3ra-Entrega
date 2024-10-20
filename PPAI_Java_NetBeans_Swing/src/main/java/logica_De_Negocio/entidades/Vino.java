package logica_De_Negocio.entidades;

import java.time.LocalDateTime;
import java.util.List;

public class Vino {
    private String nombre;
    private int aniada;
    private LocalDateTime fechaActualizacion;
    private String imagenEtiqueta;
    private String notaDeCataBodega;
    private double precioArs;
    private Bodega bodega;
    private List<Maridaje> maridajes;
    private List<Varietal> varietales;

    public Vino() {
    }


    
    public void compararEtiqueta(){}
    
    public void esDeBodega(){}
    
    public void esDeRegionVitivinicola(){}
    
    public void sosVinoParaActualizar(){}
    
    public void setPrecio(){}
    
    public void setNotaCata(){}
    
    public void setImagenEtiqueta(){}
    
    public void crear(){}
}
