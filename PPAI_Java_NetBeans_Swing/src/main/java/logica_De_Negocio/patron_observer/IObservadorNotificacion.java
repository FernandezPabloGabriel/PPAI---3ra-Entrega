/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica_De_Negocio.patron_observer;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public interface IObservadorNotificacion {
    void notificarNovedadesVinosParaBodega(List<HashMap<String,Object>> datosANotificarPorBodega);
}
