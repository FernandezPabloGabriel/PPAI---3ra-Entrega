package logica_De_Negocio.patron_observer;

import java.util.HashMap;
import java.util.List;

public class InterfazNotificacionPush implements IObservadorNotificacion{
    
    @Override
    public void notificarNovedadesVinosParaBodega(List<HashMap<String,Object>> datosANotificarPorBodega) {
        for(HashMap<String,Object> novedadBodega: datosANotificarPorBodega){
            String nombreBodega = (String) novedadBodega.get("bodega");
            List<String> nombresUsuarios = (List<String>) novedadBodega.get("usuarios");
            List<List<String>> novedadesVinos = (List<List<String>>) novedadBodega.get("vinos");
            
            int contCreados = 0;
            int contActualizados = 0;
            
            for(List<String> novedadVino: novedadesVinos){
                if(novedadVino.get(1).equalsIgnoreCase("Actualizado")){
                    contActualizados++;
                } else{
                    contCreados++;  
                }
            }

            //Simulación de envío de notificación a cada usuario seguidor de la bodega
            System.out.println("------------------------------------------");
            for(String nombreUsuario: nombresUsuarios){
                String notificacionPush = String.format(
                        "¡La bodega %s tiene novedades!\nVinos Actualizados: %d | Vinos Creados: %d",
                        nombreBodega,
                        contActualizados,
                        contCreados);
                System.out.println("Se ha enviado al usuario '" + nombreUsuario + "' la siguiente notificación:");
                System.out.println(notificacionPush);
            }
            System.out.println("------------------------------------------");
        }
    }
}
