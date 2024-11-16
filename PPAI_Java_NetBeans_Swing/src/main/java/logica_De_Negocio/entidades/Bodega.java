package logica_de_negocio.entidades;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import persistencia.ControladorPersistencia;

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
//    @OneToMany(mappedBy = "bodega")
//    private List<Vino> vinos; 

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
    
//    public void agregarVino(Vino vino){
//        this.vinos.add(vino);
//    }
    
    public boolean estaParaActualizarNovedadesVino(){
        LocalDateTime fechaActual = LocalDateTime.now();
        //Se suma la cantidad de meses especificados en el periodoActualizacion a la fechaUltimaActualizacion
        //y se pregunta si es posterior a la fecha actual. Si la fechaActual es posterior
        //entonces devuelve true, ya que está listo para actualizar
        return fechaActual.isAfter(this.fechaUltimaActualizacion.plusMonths(periodoActualizacion));
    }
    
    
    public Set<Vino> filtrarVinosDeBodega(Set<Vino> vinos){
        return vinos
                .stream()
                .filter(vino->vino.esDeBodega(this))
                .collect(Collectors.toSet());
                
    }
    
    
    public String actualizarDatosVino(
            Set<Vino> vinosBodega, 
            HashMap<String,Object> vinoImportado,
            ControladorPersistencia controladorPersistencia){
        boolean paraActualizar = false;
        for(Vino vino: vinosBodega){
            //Faltaría hacer algún tipo de validación de si el vino ya tenía el mismo parámetro, aunque se me hace lo mismo
            if(vino.sosVinoParaActualizar((String) vinoImportado.get("nombre"))){
                double precioArs = (double) vinoImportado.get("precioArs");
                String notaDeCataBodega = (String) vinoImportado.get("notaDeCataBodega");
                String imagenEtiqueta = (String) vinoImportado.get("imagenEtiqueta");
                
                if(precioArs != vino.getPrecioArs()){
                    vino.setPrecioArs(precioArs);
                    paraActualizar = true;
                }
                if(notaDeCataBodega.equals(notaDeCataBodega)){
                    vino.setNotaDeCataBodega(notaDeCataBodega);
                    paraActualizar = true;
                }
                if(imagenEtiqueta.equals(imagenEtiqueta)){
                    vino.setImagenEtiqueta(imagenEtiqueta);
                    paraActualizar = true;
                }
                
                if(paraActualizar){
                    vino.setFechaActualizacion();
                    controladorPersistencia.actualizarVino(vino);
                    return "actualizado";
                }
                return "sinActualizar";
            }
        }
        return "creado";
    }
    
    
    public boolean sosBodegaSeleccionada(String bodegaNombre){
        return this.nombre.equalsIgnoreCase(bodegaNombre);
    }
    
    
    public String getNombre() {
        return nombre;
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

    public void setFechaUltimaActualizacion() {
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

    public int getPeriodoActualizacion() {
        return periodoActualizacion;
    }

    public void setPeriodoActualizacion(int periodoActualizacion) {
        this.periodoActualizacion = periodoActualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.nombre);
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
        final Bodega other = (Bodega) obj;
        return Objects.equals(this.nombre, other.nombre);
    }

    
    @Override
    public String toString() {
        return "Bodega{" + "id=" + id + ", nombre=" + nombre + ", coordenadasUbicacion=" + coordenadasUbicacion + ", descripcion=" + descripcion + ", historia=" + historia + ", fechaUltimaActualizacion=" + fechaUltimaActualizacion + ", periodoActualizacion=" + periodoActualizacion + '}';
    }


}
