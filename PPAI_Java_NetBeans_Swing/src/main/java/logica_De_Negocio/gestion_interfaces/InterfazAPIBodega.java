package logica_De_Negocio.gestion_interfaces;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class InterfazAPIBodega {
    public List<List<String>> obtenerActualizacionVinos(String bodegaSeleccionada){
        String _rutaBodega = String.format("C:\\Users\\USUARIO\\Desktop\\PPAI\\PPAI-3ra-Entrega\\PPAI_Java_NetBeans_Swing\\src\\main\\java\\resources_extra\\mock_bodega_sitio_web\\%s.json", bodegaSeleccionada);
        
        //Cargar string de vinos desde archivo JSON
        JSONParser jp = new JSONParser();
        List<List<String>> listaVinos = new ArrayList<>();
        try(FileReader fr = new FileReader(_rutaBodega)){
            JSONArray arrayVinosJson = (JSONArray) jp.parse(fr);
            for(Object vinoObj : arrayVinosJson){
                JSONObject vinoJSONObj = (JSONObject) vinoObj;
                
                List<String> aux = new ArrayList<>();
                aux.add((String) vinoJSONObj.get("nombre"));
                aux.add(Long.toString((long) vinoJSONObj.get("aniada")));
                aux.add((String) vinoJSONObj.get("fechaActualizacion"));
                aux.add((String) vinoJSONObj.get("imagenEtiqueta"));
                aux.add((String) vinoJSONObj.get("notaDeCataBodega"));
                aux.add(Double.toString((double) vinoJSONObj.get("precioArs")));
                
                listaVinos.add(aux);
            }
        } catch (Exception e){
            System.err.println("Error al cargar el archivo " + e.getMessage());
            e.printStackTrace();
        }
        
        listaVinos.forEach(vino->System.out.println(vino));
        return listaVinos;
    }
}
