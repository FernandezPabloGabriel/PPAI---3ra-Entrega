package logica_de_negocio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "varietales")
public class Varietal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String descripcion;
    @Column(name = "porcentaje_composicion", nullable = false)
    private double porcentajeComposicion;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_uva_id", nullable = false)
    private TipoUva tipoUva;

    public Varietal() {
    }
    
    public Varietal(String descripcion, double porcentajeComposicion, TipoUva tipoUva) {
        this.descripcion = descripcion;
        this.porcentajeComposicion = porcentajeComposicion;
        this.tipoUva = tipoUva;
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

    @Override
    public String toString() {
        return "Varietal{descripcion=" + descripcion + ", porcentajeComposicion=" + porcentajeComposicion + ", tipoUva=" + tipoUva + '}';
    }
}
