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

import javax.annotation.processing.Generated;

@Entity
@Table(name = "varietales")
public class Varietal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descripcion;
    @Column(name = "porcentaje_composicion")
    private double porcentajeComposicion;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_uva_id")
    private TipoUva tipoUva;

    //RELACIÓN SOLO PARA MAPEO INICIAL
//    @ManyToOne
//    @JoinColumn(name = "vino_id")
//    private Vino vino;
//    public Varietal() {
//    }

    public Varietal() {
    }
    

    public Varietal(String descripcion, double porcentajeComposicion, TipoUva tipoUva) {
        this.descripcion = descripcion;
        this.porcentajeComposicion = porcentajeComposicion;
        this.tipoUva = tipoUva;
//        this.vino = null;
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

//    public Vino getVino() {
//        return vino;
//    }
//
//    public void setVino(Vino vino) {
//        this.vino = vino;
//    }
    
    
    
    
    
    public void conocerTipoUva(){}
    
    public void esDeTipoUva(){}
    
    public void mostrarPorcentaje(){}
        
    public void crear(){}

    @Override
    public String toString() {
        return "Varietal{" + "id=" + id + ", descripcion=" + descripcion + ", porcentajeComposicion=" + porcentajeComposicion + ", tipoUva=" + tipoUva + '}';
    }
    
    
}
