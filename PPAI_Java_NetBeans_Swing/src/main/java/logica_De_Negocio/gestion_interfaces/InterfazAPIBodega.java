package logica_de_negocio.gestion_interfaces;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                
                //List<String> aux = new ArrayList<>();
                vinoData.put("nombre", vinoJSONObj.get("nombre"));
                vinoData.put("aniada", vinoJSONObj.get("aniada"));
                //vinoData.put("fechaActualizacion", vinoJSONObj.get("fechaActualizacion"));
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
        
        //ESTRUCTURA INCOMPLETA CON MULTIPLES LISTAS DE STRING ANIDADOS
//    public List<List<String>> obtenerActualizacionVinos(String bodegaSeleccionada){
//        String _rutaBodega = String.format("C:\\Users\\USUARIO\\Desktop\\PPAI\\PPAI-3ra-Entrega\\PPAI_Java_NetBeans_Swing\\src\\main\\java\\resources_extra\\mock_bodega_sitio_web\\%s.json", bodegaSeleccionada);
//        
//        //Cargar string de vinos desde archivo JSON
//        JSONParser jp = new JSONParser();
//        List<List<List<List<String>>>> listaVinos = new ArrayList<>();
//        
//        try(FileReader fr = new FileReader(_rutaBodega)){
//            JSONArray arrayVinosJson = (JSONArray) jp.parse(fr);
//            for(Object vinoObj : arrayVinosJson){
//                JSONObject vinoJSONObj = (JSONObject) vinoObj;
//                
//                List<String> aux = new ArrayList<>();
//                aux.add((String) vinoJSONObj.get("nombre")); //#0
//                aux.add(Long.toString((long) vinoJSONObj.get("aniada"))); //#1
//                aux.add((String) vinoJSONObj.get("fechaActualizacion")); //#2
//                aux.add((String) vinoJSONObj.get("imagenEtiqueta")); //#3
//                aux.add((String) vinoJSONObj.get("notaDeCataBodega")); //#4
//                aux.add(Double.toString((double) vinoJSONObj.get("precioArs"))); //#5
//                
//                JSONArray arrayMaridaJson = (JSONArray) vinoJSONObj.get("maridajes");
//                List<String> listaMarida = new ArrayList<>();
//                for(Object maridaObj: arrayMaridaJson){
//                    JSONObject maridaJSONobj = (JSONObject) maridaObj;
//                    //Solo necesitaremos el nombre del Maridaje para consultar su existencia
//                    listaMarida.add((String) maridaJSONobj.get("nombre"));
//                }
//                
//                JSONArray arrayVarietalJson = (JSONArray) vinoJSONObj.get("varietales");
//                List<List<String>> listaVarietal = new ArrayList<>();
//                for(Object varietalObj: arrayVarietalJson){
//                    JSONObject varietalJSONObj = (JSONObject) varietalObj;
//                    List<String> varietalAux = new ArrayList<>();
//                    varietalAux.add((String) varietalJSONObj.get("descripcion"));
//                    varietalAux.add(Double.toString((double) varietalJSONObj.get("porcentajeComposicion")));
//                    
//                    JSONObject tipoUvaJSONObj = (JSONObject) varietalJSONObj.get("tipoUva");
//                    varietalAux.add((String) tipoUvaJSONObj.get("nombre"));
//                }
//                
//                
//            }
//        } catch (Exception e){
//            System.err.println("Error al cargar el archivo " + e.getMessage());
//            e.printStackTrace();
//        }
//        
//        listaVinos.forEach(vino->System.out.println(vino));
//        return listaVinos;
//    }
}
