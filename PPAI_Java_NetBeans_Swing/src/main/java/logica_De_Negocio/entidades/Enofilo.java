package logica_De_Negocio.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "enofilos")
public class Enofilo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String apellido;
    @Column(name = "imagen_perfil")
    private String imagenPerfil;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enofilo_id")
    private List<Siguiendo> seguidores;

    public Enofilo() {
    }

    public Enofilo(String nombre, String apellido, String imagenPerfil, Usuario usuario, List<Siguiendo> seguidores) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.imagenPerfil = imagenPerfil;
        this.usuario = usuario;
        this.seguidores = seguidores;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Siguiendo> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Siguiendo> seguidores) {
        this.seguidores = seguidores;
    }

    
    
    public void seguisABodega(){}
    
    public void getNombreUsuario(){}
    
    
}
