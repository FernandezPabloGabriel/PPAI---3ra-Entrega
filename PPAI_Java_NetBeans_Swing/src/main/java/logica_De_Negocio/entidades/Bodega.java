package logica_De_Negocio.entidades;
import java.time.Duration;
import java.time.LocalDateTime;

public class Bodega {
    private String nombre;
    private String coordenadasUbicacion;
    private String descripcion;
    private String historia;
    private LocalDateTime fechaUltimaActualizacion;
    private int periodoActualizacion;

    public Bodega() {
    }

    public Bodega(String nombre, String coordenadasUbicacion, String descripcion, String historia, LocalDateTime fechaUltimaActualizacion, int periodoActualizacion) {
        this.nombre = nombre;
        this.coordenadasUbicacion = coordenadasUbicacion;
        this.descripcion = descripcion;
        this.historia = historia;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
        this.periodoActualizacion = periodoActualizacion;
    }

    
    public void mostrarTodosVinos(){}
    
    public boolean estaParaActualizarNovedadesVino(){
        LocalDateTime fechaActual = LocalDateTime.now();
        //Se suma la cantidad de meses especificados en el periodoActualizacion a la fechaUltimaActualizacion
        //y se pregunta si es posterior a la fecha actual. Si la fechaActual es posterior
        //entonces devuelve true, ya que está listo para actualizar
        return fechaActual.isAfter(this.fechaUltimaActualizacion.plusMonths(periodoActualizacion));
    }

    public String getNombre() {
        return nombre;
    }
    
    public void actualizarDatosVinos(){}
}
