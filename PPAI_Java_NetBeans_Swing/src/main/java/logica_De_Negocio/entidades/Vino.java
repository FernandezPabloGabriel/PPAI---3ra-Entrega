package logica_De_Negocio.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vinos")
public class Vino implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private int aniada;
    @Column(name = "fecha_actualizacion", nullable = true)
    private LocalDateTime fechaActualizacion;
    @Column(name = "imagen_etiqueta")
    private String imagenEtiqueta;
    @Column(name = "nota_de_cata_bodega")
    private String notaDeCataBodega;
    @Column(name = "precio_ARS")
    private double precioArs;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bodega_id")
    private Bodega bodega;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vinos_maridajes",
            joinColumns = @JoinColumn(name = "vino_id"),
            inverseJoinColumns = @JoinColumn(name = "maridaje_id")
    )
    private List<Maridaje> maridajes;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER
    )
    @JoinColumn(name = "vino_id")
    private List<Varietal> varietales;

    public Vino() {
    }

    public Vino(String nombre, int aniada, LocalDateTime fechaActualizacion, String imagenEtiqueta, String notaDeCataBodega, double precioArs, Bodega bodega, List<Maridaje> maridajes, List<Varietal> varietales) {
        this.nombre = nombre;
        this.aniada = aniada;
        this.fechaActualizacion = fechaActualizacion;
        this.imagenEtiqueta = imagenEtiqueta;
        this.notaDeCataBodega = notaDeCataBodega;
        this.precioArs = precioArs;
        this.bodega = bodega;
        this.maridajes = maridajes;
        this.varietales = varietales;
        //varietales.forEach(varietal->varietal.setVino(this));
    }
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAniada() {
        return aniada;
    }

    public void setAniada(int aniada) {
        this.aniada = aniada;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getImagenEtiqueta() {
        return imagenEtiqueta;
    }

    public void setImagenEtiqueta(String imagenEtiqueta) {
        this.imagenEtiqueta = imagenEtiqueta;
    }

    public String getNotaDeCataBodega() {
        return notaDeCataBodega;
    }

    public void setNotaDeCataBodega(String notaDeCataBodega) {
        this.notaDeCataBodega = notaDeCataBodega;
    }

    public double getPrecioArs() {
        return precioArs;
    }

    public void setPrecioArs(double precioArs) {
        this.precioArs = precioArs;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public List<Maridaje> getMaridajes() {
        return maridajes;
    }

    public void setMaridajes(List<Maridaje> maridajes) {
        this.maridajes = maridajes;
    }

    public List<Varietal> getVarietales() {
        return varietales;
    }

    public void setVarietales(List<Varietal> varietales) {
        this.varietales = varietales;
    }
    
    public void compararEtiqueta(){}
    
    public void esDeBodega(){}
    
    public void esDeRegionVitivinicola(){}
    
    public void sosVinoParaActualizar(){}
    
    public void setPrecio(){}
    
    public void setNotaCata(){}
    
    public void setImagenEtiqueta(){}
    
    public void crear(){}

    @Override
    public String toString() {
        return "Vino{" + "id=" + id + ", nombre=" + nombre + ", aniada=" + aniada + ", fechaActualizacion=" + fechaActualizacion + ", imagenEtiqueta=" + imagenEtiqueta + ", notaDeCataBodega=" + notaDeCataBodega + ", precioArs=" + precioArs + ", bodega=" + bodega + ", maridajes=" + maridajes + ", varietales=" + varietales + '}';
    }
    
    
}
