package logica_De_Negocio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "varietales")
public class Varietal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descripcion;
    @Column(name = "porcentaje_composicion")
    private double porcentajeComposicion;
    @OneToOne
    @JoinColumn(name = "tipo_uva_id")
    private TipoUva tipoUva;

    public Varietal() {
    }

    public Varietal(String descripcion, double porcentajeComposicion, TipoUva tipoUva) {
        this.descripcion = descripcion;
        this.porcentajeComposicion = porcentajeComposicion;
        this.tipoUva = tipoUva;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPorcentajeComposicion() {
        return porcentajeComposicion;
    }

    public void setPorcentajeComposicion(double porcentajeComposicion) {
        this.porcentajeComposicion = porcentajeComposicion;
    }

    public TipoUva getTipoUva() {
        return tipoUva;
    }

    public void setTipoUva(TipoUva tipoUva) {
        this.tipoUva = tipoUva;
    }
    
    
    
    public void conocerTipoUva(){}
    
    public void esDeTipoUva(){}
    
    public void mostrarPorcentaje(){}
        
    public void crear(){}
}
