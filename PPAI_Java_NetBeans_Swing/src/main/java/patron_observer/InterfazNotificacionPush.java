package patron_observer;

import java.util.List;

public class InterfazNotificacionPush implements IObservadorNotificacion{
    
    
    @Override
    public void notificarNovedadesVinosParaBodega(List<List<String>> novedadesVinos, List<String> nombresUsuariosList, String nombreBodegaSeleccionada) {
        int contCreados = 0;
        int contActualizados = 0;
        
        for(List<String> novedadVino: novedadesVinos){
            if(novedadVino.get(1).equalsIgnoreCase("Actualizado")){
                contActualizados++;
            } else{
                contCreados++;  
            }
        }
        notificarUsuarios(nombresUsuariosList, nombreBodegaSeleccionada, contActualizados, contCreados);
    }
    
    public void notificarUsuarios(
            List<String> nombresUsuarios, 
            String nombreBodegaSeleccionada, 
            int contActualizados, 
            int contCreados){
        //Simulación de envío de notificación a cada usuario seguidor de la bodega
        for(String nombreUsuario: nombresUsuarios){
            String notificacionPush = String.format(
                    "¡La bodega %s tiene novedades!\nVinos Actualizados: %d | Vinos Creados: %d",
                    nombreBodegaSeleccionada,
                    contActualizados,
                    contCreados);
            System.out.println(notificacionPush);
        }
    }
}
