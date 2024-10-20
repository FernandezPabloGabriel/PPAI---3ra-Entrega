package logica_De_Negocio.entidades;

import java.util.List;

public class Enofilo {
    private String nombre;
    private String apellido;
    private String imagenPerfil;
    private Usuario usuario;
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

    
    
    public void seguisABodega(){}
    
    public void getNombreUsuario(){}
}
