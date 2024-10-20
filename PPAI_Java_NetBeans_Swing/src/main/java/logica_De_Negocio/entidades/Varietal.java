package logica_De_Negocio.entidades;

public class Varietal {
    private String descripcion;
    private double porcentajeComposicion;
    private TipoUva tipoUva;

    public Varietal() {
    }

    public Varietal(String descripcion, double porcentajeComposicion, TipoUva tipoUva) {
        this.descripcion = descripcion;
        this.porcentajeComposicion = porcentajeComposicion;
        this.tipoUva = tipoUva;
    }
    
    public void conocerTipoUva(){}
    
    public void esDeTipoUva(){}
    
    public void mostrarPorcentaje(){}
        
    public void crear(){}
}
