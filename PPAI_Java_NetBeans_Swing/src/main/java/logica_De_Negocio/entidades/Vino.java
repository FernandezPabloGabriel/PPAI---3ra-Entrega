package logica_de_negocio.entidades;

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
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vinos")
public class Vino implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private int aniada;
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    @Column(name = "imagen_etiqueta")
    private String imagenEtiqueta;
    @Column(name = "nota_de_cata_bodega")
    private String notaDeCataBodega;
    @Column(name = "precio_ARS", nullable = false)
    private double precioArs;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bodega_id", nullable = false)
    private Bodega bodega;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vinos_maridajes",
            joinColumns = @JoinColumn(name = "vino_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "maridaje_id", nullable = false)
    )
    private List<Maridaje> maridajes;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "vino_id", nullable = false)
    private List<Varietal> varietales = new ArrayList<>();

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
    }

    public Vino(String nombre, 
            int aniada, 
            String imagenEtiqueta, 
            String notaDeCataBodega, 
            double precioArs, 
            Bodega bodega, 
            List<Maridaje> maridajes, 
            List<HashMap<String,String>> varietales,
            HashMap<String, TipoUva> tiposUvasMap) {
        this.nombre = nombre;
        this.aniada = aniada;
        this.imagenEtiqueta = imagenEtiqueta;
        this.notaDeCataBodega = notaDeCataBodega;
        this.precioArs = precioArs;
        this.bodega = bodega;
        this.maridajes = maridajes;
        setFechaActualizacion();
        crearVarietales(varietales, tiposUvasMap);
    }

    public void crearVarietales(List<HashMap<String,String>> varietales, HashMap<String, TipoUva> tiposUvasMap){
        for(HashMap<String,String> varietal: varietales){
            Varietal varietalParaCrear = new Varietal(
                    varietal.get("descripcion"),
                    Double.parseDouble(varietal.get("porcentajeComposicion")), 
                    tiposUvasMap.get(varietal.get("tipoUva")));
            if(varietalParaCrear != null){
                this.varietales.add(varietalParaCrear);
            }
        }
    }
    
    //Solo para cargar MOCK de datos
    public long getId() {
        return id;
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

    public void setFechaActualizacion() {
        this.fechaActualizacion = LocalDateTime.now();
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
    
    public Boolean esDeBodega(Bodega bodegaSeleccionada){
        return this.bodega.equals(bodegaSeleccionada);
    }
    
    public Boolean sosVinoParaActualizar(String vinoImportado){
        return vinoImportado.equalsIgnoreCase(this.nombre);
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vino other = (Vino) obj;
        return Objects.equals(this.nombre, other.nombre);
    }
    
    @Override
    public String toString() {
        return "Vino{nombre=" + nombre + ", aniada=" + aniada + ", fechaActualizacion=" + fechaActualizacion + ", imagenEtiqueta=" + imagenEtiqueta + ", notaDeCataBodega=" + notaDeCataBodega + ", precioArs=" + precioArs + ", bodega=" + bodega + ", maridajes=" + maridajes + ", varietales=" + varietales + '}';
    }
}
