package logica_De_Negocio.entidades;

public class Bodega {
    private String nombre;
    private String coordenadasUbicacion;
    private String descripcion;
    private String historia;
    private int periodoActualizacion;

    public Bodega() {
    }

    public Bodega(String nombre, String coordenadasUbicacion, String descripcion, String historia, int periodoActualizacion) {
        this.nombre = nombre;
        this.coordenadasUbicacion = coordenadasUbicacion;
        this.descripcion = descripcion;
        this.historia = historia;
        this.periodoActualizacion = periodoActualizacion;
    }
    
    public void mostrarTodosVinos(){}
    
    public void estaParaActualizarNovedadesVino(){}
    
    public void getNombre(){}
    
    public void actualizarDatosVinos(){}
}
