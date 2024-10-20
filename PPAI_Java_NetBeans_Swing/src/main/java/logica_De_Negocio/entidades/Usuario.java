package logica_De_Negocio.entidades;

public class Usuario {
    private String contrasenia;
    private String nombre;

    public Usuario() {
    }

    public Usuario(String contrasenia, String nombre) {
        this.contrasenia = contrasenia;
        this.nombre = nombre;
    }
    
    public void esTuUsuario(){}
    
    public void getNombre(){}
}
