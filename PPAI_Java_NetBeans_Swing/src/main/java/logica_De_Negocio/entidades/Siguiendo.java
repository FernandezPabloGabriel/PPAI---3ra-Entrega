package logica_De_Negocio.entidades;

import java.time.LocalDateTime;

public class Siguiendo {
    private LocalDateTime fechaFin;
    private LocalDateTime fechaInicio;
    private Bodega bodega;

    public Siguiendo() {
    }

    public Siguiendo(LocalDateTime fechaFin, LocalDateTime fechaInicio, Bodega bodega) {
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.bodega = bodega;
    }
    
    public void sosDeBodega(){}
}
