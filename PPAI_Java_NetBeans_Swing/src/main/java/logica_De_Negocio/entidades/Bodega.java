package logica_De_Negocio.entidades;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "bodegas")
public class Bodega implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(name = "coordenadas_ubicacion")
    private String coordenadasUbicacion;
    private String descripcion;
    private String historia;
    @Column (name = "fecha_ultima_actualizacion")
    private LocalDateTime fechaUltimaActualizacion;
    @Column (name = "periodo_actualizacion")
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
    
    public void actualizarDatosVinos(List<String> vino){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoordenadasUbicacion() {
        return coordenadasUbicacion;
    }

    public void setCoordenadasUbicacion(String coordenadasUbicacion) {
        this.coordenadasUbicacion = coordenadasUbicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public LocalDateTime getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(LocalDateTime fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public int getPeriodoActualizacion() {
        return periodoActualizacion;
    }

    public void setPeriodoActualizacion(int periodoActualizacion) {
        this.periodoActualizacion = periodoActualizacion;
    }

    @Override
    public String toString() {
        return "Bodega{" + "id=" + id + ", nombre=" + nombre + ", coordenadasUbicacion=" + coordenadasUbicacion + ", descripcion=" + descripcion + ", historia=" + historia + ", fechaUltimaActualizacion=" + fechaUltimaActualizacion + ", periodoActualizacion=" + periodoActualizacion + '}';
    }


}
