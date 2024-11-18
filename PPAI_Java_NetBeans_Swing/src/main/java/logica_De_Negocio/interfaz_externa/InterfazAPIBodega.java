package logica_De_Negocio.interfaz_externa;

import java.util.List;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class InterfazAPIBodega {
        public List<HashMap<String, Object>> obtenerActualizacionVinos(String bodegaSeleccionada){
        String _rutaBodega = String.format("C:\\Users\\USUARIO\\Desktop\\PPAI\\PPAI-3ra-Entrega\\PPAI_Java_NetBeans_Swing\\src\\main\\java\\resources_extra\\mock_bodega_sitio_web\\%s.json", bodegaSeleccionada);
        
        JSONParser jp = new JSONParser();
        //Creamos una lista que contendrá un HashMap cuya clave será el nombre del vino
        //y como valor el cuerpo del vino, sus atributos
        List<HashMap<String, Object>> vinosDataMap = new ArrayList();
        
        try(FileReader fr = new FileReader(_rutaBodega)){
            JSONArray arrayVinosJson = (JSONArray) jp.parse(fr);
            for(Object vinoObj : arrayVinosJson){
                HashMap<String, Object> vinoData = new HashMap<>();
                JSONObject vinoJSONObj = (JSONObject) vinoObj;
                
                vinoData.put("nombre", vinoJSONObj.get("nombre"));
                vinoData.put("aniada", vinoJSONObj.get("aniada"));
                vinoData.put("imagenEtiqueta", vinoJSONObj.get("imagenEtiqueta"));
                vinoData.put("notaDeCataBodega", vinoJSONObj.get("notaDeCataBodega"));
                vinoData.put("precioArs", vinoJSONObj.get("precioArs"));
                
                //Composición de maridajes
                JSONArray arrayMaridaJson = (JSONArray) vinoJSONObj.get("maridajes");
                List<String> listaMarida = new ArrayList<>();
                for(Object maridaObj: arrayMaridaJson){
                    JSONObject maridaJSONobj = (JSONObject) maridaObj;
                    //Solo necesitaremos el nombre del Maridaje para consultar su existencia
                    listaMarida.add((String) maridaJSONobj.get("nombre"));
                }
                vinoData.put("maridajes", listaMarida);
                
                //Composición de varietales, de los tipos de uva solo nos interesa el nombre
                //para luego buscarlos desde el gestor
                JSONArray arrayVarietalJson = (JSONArray) vinoJSONObj.get("varietales");
                List<HashMap<String,String>> listaVarietal = new ArrayList<>();
                for(Object varietalObj: arrayVarietalJson){
                    JSONObject varietalJSONObj = (JSONObject) varietalObj;
                    HashMap<String,String> varietalMap = new HashMap<>();
                    varietalMap.put("descripcion", (String) varietalJSONObj.get("descripcion"));
                    varietalMap.put("porcentajeComposicion", Double.toString((double) varietalJSONObj.get("porcentajeComposicion")));
                    
                    JSONObject tipoUvaJSONObj = (JSONObject) varietalJSONObj.get("tipoUva");
                    varietalMap.put("tipoUva", (String) tipoUvaJSONObj.get("nombre"));
                    listaVarietal.add(varietalMap);
                }
                vinoData.put("varietales", listaVarietal);
                
                vinoData.put("bodega", bodegaSeleccionada);
                vinosDataMap.add(vinoData);
            }
        } catch (Exception e){
            System.err.println("Error al cargar el archivo " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        
        vinosDataMap.forEach(vino->System.out.println(vino));
        return vinosDataMap;
    }
}
