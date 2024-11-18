package logica_de_negocio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Entity
@Table(name = "siguiendos")
public class Siguiendo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "fecha_fin", nullable = true)
    private LocalDateTime fechaFin;
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;
    @ManyToOne
    @JoinColumn(name = "bodega_id", nullable = true)
    private Bodega bodega;

    public Siguiendo() {
    }

    public Siguiendo(LocalDateTime fechaInicio, LocalDateTime fechaFin, Bodega bodega) {
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.bodega = bodega;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }
    
    
    public boolean sosDeBodega(String nombreBodegaSeleccionada){
        return fechaFin == null && this.bodega.getNombre().equalsIgnoreCase(nombreBodegaSeleccionada);
    }
    
    @Override
    public String toString() {
        return "Siguiendo{" + "fechaFin=" + fechaFin + ", fechaInicio=" + fechaInicio + ", bodega=" + bodega + '}';
    }
    
    
}
