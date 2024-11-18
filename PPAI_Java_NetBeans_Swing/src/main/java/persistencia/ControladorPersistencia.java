package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica_de_negocio.entidades.Bodega;
import logica_de_negocio.entidades.Enofilo;
import logica_de_negocio.entidades.Maridaje;
import logica_de_negocio.entidades.Siguiendo;
import logica_de_negocio.entidades.TipoUva;
import logica_de_negocio.entidades.Usuario;
import logica_de_negocio.entidades.Varietal;
import logica_de_negocio.entidades.Vino;
import presentacion.otros.PantallaCarga;

public class ControladorPersistencia {
    BodegaJpaController bodegaJpaController;
    EnofiloJpaController enofiloJpaController;
    MaridajeJpaController maridajeJpaController;
    TipoUvaJpaController tipoUvaJpaController;
    UsuarioJpaController usuarioJpaController;
    VinoJpaController vinoJpaController;

    public ControladorPersistencia() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        this.bodegaJpaController = new BodegaJpaController(emf);
        this.enofiloJpaController = new EnofiloJpaController(emf);
        this.maridajeJpaController = new MaridajeJpaController(emf);
        this.tipoUvaJpaController = new TipoUvaJpaController(emf);
        this.usuarioJpaController = new UsuarioJpaController(emf);
        this.vinoJpaController = new VinoJpaController(emf);
    }

    public List<Vino> materializarVinos(){
        return vinoJpaController.findVinoEntities();
    }
    
    public List<TipoUva> materializarTipoUvas(){
        return tipoUvaJpaController.findTipoUvaEntities();
    }
    
    public List<Maridaje> materializarMaridajes(){
        return maridajeJpaController.findMaridajeEntities();
    }
    
    public List<Enofilo> materializarEnofilos(){
        return enofiloJpaController.findEnofiloEntities();
    }
    
    public void actualizarVino(Vino vinoActualizado){
        try {
            vinoJpaController.edit(vinoActualizado);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desmaterializarVino(Vino vino){
        vinoJpaController.create(vino);
    }
    
    public void actualizarFechaBodega(Bodega bodega){
        try {
            bodegaJpaController.edit(bodega);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Populación de la BD para que se borren los cambios cada vez que corramos el programa 
    public void popularBDMock(){ 
        //Instancias de Bodega
        List<Bodega> bodegasList = new ArrayList<>();
        Bodega bodega1 = new Bodega("Bodega El Valle", "34.567,-117.123", 
                "Bodega ubicada en el corazón del valle con vinos premiados.", 
                "Fundada en 1920, El Valle ha sido pionera en la región.", 
                LocalDateTime.of(2024, 11, 3, 14, 23, 45), 3);
        
        Bodega bodega2 = new Bodega("Viñedos San Pedro", "35.123,-118.456", 
                "Viñedos familiares con una larga tradición.", 
                "La familia San Pedro ha cultivado uvas desde 1880.", 
                LocalDateTime.of(2024, 10, 30, 9, 15, 12), 3);
        
        Bodega bodega3 = new Bodega("Cavas de Sol", "36.789,-119.789", 
                "Bodega boutique especializada en vinos de autor.", 
                "Con sus raíces en los años 80, Cavas de Sol destaca por su calidad.", 
                LocalDateTime.of(2024, 9, 15, 18, 42, 30), 2);
        
        Bodega bodega4 = new Bodega("Vinos de Altura", "33.890,-117.456", 
                "Bodega de montaña conocida por sus tintos intensos.", 
                "Nació en 1950 y se ha convertido en un emblema de la región.", 
                LocalDateTime.of(2024, 8, 21, 13, 0, 0), 1);
        
        Bodega bodega5 = new Bodega("Terra Alta", "37.654,-120.345", 
                "Bodega sostenible que produce vinos orgánicos.", 
                "Fundada en 2005, Terra Alta es líder en agricultura orgánica.", 
                LocalDateTime.of(2024, 7, 18, 10, 5, 55), 4);
        
        Bodega bodega6 = new Bodega("Bodega Nueva Esperanza", "32.987,-115.432", 
                "Una bodega joven con propuestas innovadoras.", 
                "Creada en 2010, busca desafiar las tradiciones del vino.", 
                LocalDateTime.of(2024, 6, 10, 15, 30, 25), 6);
        
        Bodega bodega7 = new Bodega("Viñas del Mar", "34.567,-116.789", 
                "Ubicada cerca del océano, famosa por sus vinos frescos.", 
                "Fundada en 1975, destaca por sus vinos blancos.", 
                LocalDateTime.of(2024, 6, 8, 17, 55, 10), 4);
        
        Bodega bodega8 = new Bodega("Los Altos", "31.876,-118.123", 
                "Bodega de altura con vinos exclusivos de pequeñas producciones.", 
                "Desde 1960, produce vinos únicos para el mercado premium.", 
                LocalDateTime.of(2024, 4, 11, 16, 30, 0), 3);
        
        Bodega bodega9 = new Bodega("Estancia Vieja", "35.345,-119.876", 
                "Una bodega con una larga trayectoria en la región.", 
                "Establecida en 1910, mantiene técnicas de vinificación tradicionales.", 
                LocalDateTime.of(2024, 7, 5, 14, 10, 20), 9);
        
        Bodega bodega10 = new Bodega("Rincones del Vino", "33.123,-117.654", 
                "Con una selección amplia y variada de vinos.", 
                "Desde 1945, esta bodega ofrece una experiencia única de degustación.", 
                LocalDateTime.of(2024, 8, 14, 20, 45, 50), 4);
       bodegasList.add(bodega1);
       bodegasList.add(bodega2);
       bodegasList.add(bodega3);
       bodegasList.add(bodega4);
       bodegasList.add(bodega5);
       bodegasList.add(bodega6);
       bodegasList.add(bodega7);
       bodegasList.add(bodega8);
       bodegasList.add(bodega9);
       bodegasList.add(bodega10);
       bodegasList.forEach(bodega->bodegaJpaController.create(bodega));
       
       //Instancias de TiposUva
       List<TipoUva> tiposUvasList = new ArrayList<>();
       TipoUva tipoUva1 = new TipoUva("Uva de piel negra, ideal para tintos.", "Cabernet Sauvignon");
        TipoUva tipoUva2 = new TipoUva("Uva blanca, conocida por su frescura.", "Chardonnay");
        TipoUva tipoUva3 = new TipoUva("Variedad de uva tinto con cuerpo suave.", "Merlot");
        TipoUva tipoUva4 = new TipoUva("Uva de piel roja, aromática y frutal.", "Pinot Noir");
        TipoUva tipoUva5 = new TipoUva("Uva versátil, usada en vinos tintos y rosados.", "Syrah");
        TipoUva tipoUva6 = new TipoUva("Uva blanca que produce vinos dulces y frescos.", "Riesling");
        TipoUva tipoUva7 = new TipoUva("Variedad de uva tinto, con notas especiadas.", "Zinfandel");
        TipoUva tipoUva8 = new TipoUva("Uva dulce, comúnmente utilizada en vinos de postre.", "Muscat");
        TipoUva tipoUva9 = new TipoUva("Uva roja, ideal para vinos complejos y elegantes.", "Tempranillo");
        TipoUva tipoUva10 = new TipoUva("Uva blanca con una acidez vibrante.", "Sauvignon Blanc");
        tiposUvasList.add(tipoUva1);
        tiposUvasList.add(tipoUva2);
        tiposUvasList.add(tipoUva3);
        tiposUvasList.add(tipoUva4);
        tiposUvasList.add(tipoUva5);
        tiposUvasList.add(tipoUva6);
        tiposUvasList.add(tipoUva7);
        tiposUvasList.add(tipoUva8);
        tiposUvasList.add(tipoUva9);
        tiposUvasList.add(tipoUva10);
        tiposUvasList.forEach(tipo->tipoUvaJpaController.create(tipo));
        
        //Instancias de Maridaje
        List<Maridaje> maridajesList = new ArrayList<>();
        maridajesList.add(new Maridaje("Cabernet Sauvignon y carne roja", "Un tinto robusto que complementa los sabores de la carne a la parrilla."));
        maridajesList.add(new Maridaje("Chardonnay y mariscos", "Un blanco fresco que realza la dulzura natural de los mariscos."));
        maridajesList.add(new Maridaje("Merlot y pasta con salsa de tomate", "El cuerpo suave del Merlot equilibra la acidez del tomate."));
        maridajesList.add(new Maridaje("Pinot Noir y pato", "Un tinto elegante que combina bien con la riqueza del pato."));
        maridajesList.add(new Maridaje("Syrah y costillas a la barbacoa", "El Syrah complementa las especias y el ahumado de las costillas."));
        maridajesList.add(new Maridaje("Riesling y comida picante", "Un blanco dulce que ayuda a mitigar el picante de los platos."));
        maridajesList.add(new Maridaje("Zinfandel y pizza", "La fruta del Zinfandel complementa los sabores de la pizza perfectamente."));
        maridajesList.add(new Maridaje("Muscat y postres", "Un vino dulce que es ideal para acompañar postres de frutas."));
        maridajesList.add(new Maridaje("Tempranillo y tapas", "Un tinto español que marida bien con una variedad de tapas."));
        maridajesList.add(new Maridaje("Sauvignon Blanc y ensaladas", "Un blanco crujiente que realza los sabores frescos de las ensaladas."));
        maridajesList.add(new Maridaje("Malbec y empanadas de carne", "Un tinto con cuerpo que realza el sabor de las empanadas argentinas."));
        maridajesList.add(new Maridaje("Carmenere y cordero asado", "Un vino suave que complementa los sabores fuertes del cordero."));
        maridajesList.add(new Maridaje("Gewürztraminer y comida asiática", "Un blanco floral que equilibra los sabores especiados de la comida asiática."));
        maridajesList.add(new Maridaje("Grenache y quesos semicurados", "Suave y afrutado, ideal para quesos de intensidad media."));
        maridajesList.add(new Maridaje("Verdejo y sushi", "Un blanco refrescante que marida bien con pescados crudos y algas."));
        maridajesList.add(new Maridaje("Grüner Veltliner y espárragos", "Un vino austriaco que combina perfectamente con el sabor único de los espárragos."));
        maridajesList.add(new Maridaje("Rosé y ensalada de frutas", "Refrescante y ligero, ideal para acompañar una ensalada de frutas."));
        maridajesList.add(new Maridaje("Porto y queso azul", "El dulzor del Porto contrasta a la perfección con el sabor intenso del queso azul."));
        maridajesList.add(new Maridaje("Barolo y estofado de carne", "Un tinto potente y complejo, perfecto para platos de carne largos en cocción."));
        maridajesList.add(new Maridaje("Albariño y mariscos a la plancha", "Un vino blanco español que resalta los sabores del marisco."));
        maridajesList.add(new Maridaje("Viognier y pollo al curry", "Un blanco que complementa los sabores especiados y cremosos del curry."));
        maridajesList.add(new Maridaje("Chenin Blanc y sushi", "Un blanco versátil que resalta los sabores delicados del sushi."));
        maridajesList.add(new Maridaje("Sangiovese y pizza Margarita", "Su acidez y frescura lo hacen ideal para la clásica pizza Margarita."));
        maridajesList.add(new Maridaje("Tempranillo y jamón ibérico", "Un tinto español que se complementa a la perfección con jamón ibérico."));
        maridajesList.add(new Maridaje("Rosado de Provence y tartar de atún", "Ligero y fresco, ideal para platos de atún crudo."));
        maridajesList.add(new Maridaje("Chardonnay y langosta", "Un blanco robusto que realza la riqueza de la langosta."));
        maridajesList.add(new Maridaje("Beaujolais y quesos suaves", "Ligero y afrutado, ideal para acompañar quesos suaves."));
        maridajesList.add(new Maridaje("Cabernet Franc y platos de setas", "La terrosidad del Cabernet Franc combina bien con platos de setas."));
        maridajesList.add(new Maridaje("Pinot Grigio y carpaccio de salmón", "Un blanco fresco que resalta la delicadeza del salmón."));
        maridajesList.add(new Maridaje("Syrah y chili con carne", "El Syrah equilibra y resalta los sabores especiados del chili con carne."));
        maridajesList.forEach(maridaje->maridajeJpaController.create(maridaje));
        
        //Instancias de Varietal
        tipoUva1 = tipoUvaJpaController.findTipoUva(1L);
        tipoUva2 = tipoUvaJpaController.findTipoUva(2L);
        tipoUva3 = tipoUvaJpaController.findTipoUva(3L);
        tipoUva4 = tipoUvaJpaController.findTipoUva(4L);
        tipoUva5 = tipoUvaJpaController.findTipoUva(5L);
        tipoUva6 = tipoUvaJpaController.findTipoUva(6L);
        tipoUva7 = tipoUvaJpaController.findTipoUva(7L);
        tipoUva8 = tipoUvaJpaController.findTipoUva(8L);
        tipoUva9 = tipoUvaJpaController.findTipoUva(9L);
        tipoUva10 = tipoUvaJpaController.findTipoUva(10L);
        Varietal varietal1 = new Varietal("Varietal con alta concentración de taninos.", 70.0, tipoUva1);
        Varietal varietal2 = new Varietal("Varietal fresco y frutal.", 85.0, tipoUva2);
        Varietal varietal3 = new Varietal("Varietal suave con notas a frutas rojas.", 60.0, tipoUva3);
        Varietal varietal4 = new Varietal("Varietal elegante con aroma floral.", 75.0, tipoUva4);
        Varietal varietal5 = new Varietal("Varietal equilibrado y versátil.", 65.0, tipoUva5);
        Varietal varietal6 = new Varietal("Varietal aromático y dulce.", 90.0, tipoUva6);
        Varietal varietal7 = new Varietal("Varietal especiado y robusto.", 80.0, tipoUva7);
        Varietal varietal8 = new Varietal("Varietal dulce ideal para postres.", 95.0, tipoUva8);
        Varietal varietal9 = new Varietal("Varietal complejo con cuerpo.", 70.0, tipoUva9);
        Varietal varietal10 = new Varietal("Varietal refrescante con buena acidez.", 80.0, tipoUva10);
        Varietal varietal11 = new Varietal("Varietal ligero y refrescante.", 65.0, tipoUva1);
        Varietal varietal12 = new Varietal("Varietal con notas a cítricos y flores blancas.", 75.0, tipoUva2);
        Varietal varietal13 = new Varietal("Varietal intenso y persistente.", 85.0, tipoUva3);
        Varietal varietal14 = new Varietal("Varietal con taninos suaves y sabores frutales.", 60.0, tipoUva4);
        Varietal varietal15 = new Varietal("Varietal estructurado con notas de vainilla.", 90.0, tipoUva5);
        Varietal varietal16 = new Varietal("Varietal con alta acidez, ideal para mariscos.", 80.0, tipoUva6);
        Varietal varietal17 = new Varietal("Varietal con cuerpo y sabores a frutas oscuras.", 70.0, tipoUva7);
        Varietal varietal18 = new Varietal("Varietal exótico con aromas a especias.", 85.0, tipoUva8);
        Varietal varietal19 = new Varietal("Varietal fresco con notas de melón y pera.", 78.0, tipoUva9);
        Varietal varietal20 = new Varietal("Varietal con una mezcla única de sabores tropicales.", 95.0, tipoUva10);
        Varietal varietal21 = new Varietal("Varietal seco y delicado, ideal para pescado.", 67.0, tipoUva1);
        Varietal varietal22 = new Varietal("Varietal rico en minerales y sabores terrosos.", 88.0, tipoUva2);
        Varietal varietal23 = new Varietal("Varietal robusto con notas de cuero y tabaco.", 75.0, tipoUva3);
        Varietal varietal24 = new Varietal("Varietal suave con matices herbales.", 60.0, tipoUva4);
        Varietal varietal25 = new Varietal("Varietal con un toque especiado y picante.", 80.0, tipoUva5);
        Varietal varietal26 = new Varietal("Varietal afrutado con notas a fresa y cereza.", 82.0, tipoUva6);
        Varietal varietal27 = new Varietal("Varietal con alta intensidad y aromas tostados.", 73.0, tipoUva7);
        Varietal varietal28 = new Varietal("Varietal fresco, perfecto para días calurosos.", 77.0, tipoUva8);
        Varietal varietal29 = new Varietal("Varietal frutal con notas de durazno y albaricoque.", 65.0, tipoUva9);
        Varietal varietal30 = new Varietal("Varietal elegante con sabores a frutas rojas.", 90.0, tipoUva10);
        

        //varietalesList.forEach(varietal->varietalJpaController.create(varietal));
        
        //Instancias de Vino
        bodega1 = bodegaJpaController.findBodega(1L);
        bodega2 = bodegaJpaController.findBodega(2L);
        bodega3 = bodegaJpaController.findBodega(3L);
        bodega4 = bodegaJpaController.findBodega(4L);
        bodega5 = bodegaJpaController.findBodega(5L);
        bodega6 = bodegaJpaController.findBodega(6L);
        bodega7 = bodegaJpaController.findBodega(7L);
        bodega8 = bodegaJpaController.findBodega(8L);
        bodega9 = bodegaJpaController.findBodega(9L);
        bodega10 = bodegaJpaController.findBodega(10L);
        
        Maridaje maridaje1 = maridajeJpaController.findMaridaje(1);
        Maridaje maridaje2 = maridajeJpaController.findMaridaje(2);
        Maridaje maridaje3 = maridajeJpaController.findMaridaje(3);
        Maridaje maridaje4 = maridajeJpaController.findMaridaje(4);
        Maridaje maridaje5 = maridajeJpaController.findMaridaje(5);
        Maridaje maridaje6 = maridajeJpaController.findMaridaje(6);
        Maridaje maridaje7 = maridajeJpaController.findMaridaje(7);
        Maridaje maridaje8 = maridajeJpaController.findMaridaje(8);
        Maridaje maridaje9 = maridajeJpaController.findMaridaje(9);
        Maridaje maridaje10 = maridajeJpaController.findMaridaje(10);
//        Maridaje maridaje11 = maridajeJpaController.findMaridaje(11);
//        Maridaje maridaje12 = maridajeJpaController.findMaridaje(12);
//        Maridaje maridaje13 = maridajeJpaController.findMaridaje(13);
//        Maridaje maridaje14 = maridajeJpaController.findMaridaje(14);
//        Maridaje maridaje15 = maridajeJpaController.findMaridaje(15);
//        Maridaje maridaje16 = maridajeJpaController.findMaridaje(16);
        Maridaje maridaje17 = maridajeJpaController.findMaridaje(17);
        Maridaje maridaje18 = maridajeJpaController.findMaridaje(18);
        Maridaje maridaje19 = maridajeJpaController.findMaridaje(19);
        Maridaje maridaje20 = maridajeJpaController.findMaridaje(20);
//        Maridaje maridaje21 = maridajeJpaController.findMaridaje(21);
//        Maridaje maridaje22 = maridajeJpaController.findMaridaje(22);
//        Maridaje maridaje23 = maridajeJpaController.findMaridaje(23);
//        Maridaje maridaje24 = maridajeJpaController.findMaridaje(24);
        Maridaje maridaje25 = maridajeJpaController.findMaridaje(25);
//        Maridaje maridaje26 = maridajeJpaController.findMaridaje(26);
//        Maridaje maridaje27 = maridajeJpaController.findMaridaje(27);
//        Maridaje maridaje28 = maridajeJpaController.findMaridaje(28);
        Maridaje maridaje29 = maridajeJpaController.findMaridaje(29);
        Maridaje maridaje30 = maridajeJpaController.findMaridaje(30);
        
        
        Vino vino1 = new Vino("Gran Reserva El Valle", 2018,
                LocalDateTime.of(2024, 11, 3, 14, 23, 45),
                "gran_reserva_el_valle.jpg",
                "Notas de frutos rojos y especias, cuerpo robusto.",
                1500.50, bodega1,
                Arrays.asList(maridaje1),
                Arrays.asList(varietal1, varietal2, varietal30));

        Vino vino2 = new Vino("San Pedro Malbec", 2020,
                LocalDateTime.of(2024, 10, 30, 9, 15, 12),
                "san_pedro_malbec.jpg",
                "Aroma a ciruelas y taninos equilibrados.",
                1200.00, bodega2,
                null,
                Arrays.asList(varietal3, varietal4));
//        varietal3.setVino(vino2);
//        varietal4.setVino(vino2);

        Vino vino3 = new Vino("Cavas de Sol Blend", 2019,
                LocalDateTime.of(2024, 9, 15, 18, 42, 30),
                "cavas_de_sol_blend.jpg",
                "Exquisito blend con notas florales y final largo.",
                1800.75, bodega3,
                Arrays.asList(maridaje2, maridaje3, maridaje4),
                Arrays.asList(varietal5, varietal6));
//        varietal5.setVino(vino3);
//        varietal6.setVino(vino3);

        Vino vino4 = new Vino("Altura Pinot Noir", 2017,
                LocalDateTime.of(2024, 8, 21, 13, 0, 0),
                "altura_pinot_noir.jpg",
                "Elegante con notas a frutos del bosque.",
                2100.99, bodega4,
                Arrays.asList(maridaje5, maridaje6, maridaje7, maridaje8),
                Arrays.asList(varietal7, varietal8, varietal27, varietal28, varietal29));
//        varietal7.setVino(vino4);
//        varietal8.setVino(vino4);
//        varietal27.setVino(vino4);
//        varietal28.setVino(vino4);
//        varietal29.setVino(vino4);
        

        Vino vino5 = new Vino("Terra Alta Orgánico", 2021,
                LocalDateTime.of(2024, 7, 18, 10, 5, 55),
                "terra_alta_organico.jpg",
                "Vino orgánico, fresco y frutado.",
                950.00, bodega5,
                Arrays.asList(maridaje1, maridaje3, maridaje5, maridaje10),
                Arrays.asList(varietal9, varietal10,varietal26));
//        varietal9.setVino(vino5);
//        varietal10.setVino(vino5);
//        varietal26.setVino(vino5);

        Vino vino6 = new Vino("Esperanza Tannat", 2016,
                LocalDateTime.of(2024, 6, 10, 15, 30, 25),
                "esperanza_tannat.jpg",
                "Taninos firmes y estructura compleja.",
                1700.45, bodega6,
                Arrays.asList(maridaje3, maridaje20, maridaje19, maridaje18, maridaje17),
                Arrays.asList(varietal11, varietal12));

        Vino vino7 = new Vino("Viñas del Mar Sauvignon Blanc", 2022,
                LocalDateTime.of(2024, 5, 8, 17, 55, 10),
                "vinas_del_mar_sauvignon.jpg",
                "Fresco y vibrante, ideal para mariscos.",
                1350.60, bodega7,
                Arrays.asList(maridaje1, maridaje3, maridaje5, maridaje25),
                Arrays.asList(varietal13, varietal14,varietal24,varietal25));

        Vino vino8 = new Vino("Los Altos Merlot", 2018,
                LocalDateTime.of(2024, 4, 11, 16, 30, 0),
                "los_altos_merlot.jpg",
                "Suave, con notas a frambuesa y toques de roble.",
                1600.30, bodega8,
                Arrays.asList(maridaje4),
                Arrays.asList(varietal15, varietal16));

        Vino vino9 = new Vino("Vieja Tradición Cabernet", 2015,
                LocalDateTime.of(2024, 3, 5, 14, 10, 20),
                "vieja_tradicion_cabernet.jpg",
                "Clásico cabernet con notas de tabaco y cuero.",
                1950.85, bodega9,
                Arrays.asList(maridaje9, maridaje19),
                Arrays.asList(varietal17, varietal18,varietal23));

        Vino vino10 = new Vino("Rincones del Vino Rosé", 2023,
                LocalDateTime.of(2024, 2, 14, 20, 45, 50),
                "rincones_vino_rose.jpg",
                "Refrescante, con suaves notas de frutillas.",
                1100.90, bodega10,
                Arrays.asList(maridaje30, maridaje18, maridaje29),
                Arrays.asList(varietal19, varietal20,varietal21,varietal22));
        List<Vino> vinos = new ArrayList<>();
        vinos.add(vino1);
        vinos.add(vino2);
        vinos.add(vino3);
        vinos.add(vino4);
        vinos.add(vino5);
        vinos.add(vino6);
        vinos.add(vino7);
        vinos.add(vino8);
        vinos.add(vino9);
        vinos.add(vino10);        
        vinos.forEach(vino->vinoJpaController.create(vino));

        //Instancias de Usuario
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Laura97_pro", "D8fN&@2hUqB5RmCz!KsP9YbXWAvG3oTJpQkLF6ZeVcHnM7#xOwUyEt*ad"));  
        usuarios.add(new Usuario("john_doe88", "qCk9@VXnM#oTYRb2p6LF3Ad8fUJh!Ks5WZveGxHtO7*PbUYacQoEtNLz"));  
        usuarios.add(new Usuario("alex_2022", "Uo6WtYLF9&KpHf3@NsJAdXZePbQ#hRV4Yx7Cm9LnMw8OcG2TJkEt*oa"));  
        usuarios.add(new Usuario("vinoPatero3000", "HtXZe7*UYVYxLp3Ad8o6@PbQoTJpQksCMnWZvcFLJNm#9YRH4M2KbfoA"));  
        usuarios.add(new Usuario("superMario90", "9fULXc7RRYTkJXZePb9QoMtWt6bZe3bMoN&aPoEt#@HxLp6AdvoTJ!N2"));  
        usuarios.add(new Usuario("laraX_top20", "Jv@LYtQckNbNxHtMpT8o6@Pbnk9X*VoTJ6UyPJL#ob9QoHtZpbNbH"));  
        usuarios.add(new Usuario("chloeVinotera88pt", "KpUYFL8*5o9QoTJLPtXVcHb#@HnJLNRFLXtTJXZeVYMoTxZeTJm"));  
        usuarios.add(new Usuario("mojo196", "W74YLPoJNTJOpUY9f!*TJMCNn*F6&.JtJxtZ"));
        usuarios.add(new Usuario("vinoMaster66", "NYx8MoXt10+qw34fw34fq4gqq34gMO}!@"));
        usuarios.add(new Usuario("catadorsupremo309", "D8fN&@2hUqB5RmCz!PD98Q2B3DIQÑ23F23923}Q2BFQ34RQ029H3")); 

        usuarios.forEach(usuario->usuarioJpaController.create(usuario));
        
        //Instancias de Siguiendo
        List<Siguiendo> siguiendoList = new ArrayList();
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2023, 1, 15, 12, 0, 0), null, bodega4));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2023, 2, 10, 15, 30, 0), null, bodega4));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2022, 12, 1, 9, 45, 0), null, bodega7));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2023, 5, 18, 14, 0, 0), LocalDateTime.of(2023, 11, 18, 14, 0, 0), bodega7));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2023, 8, 5, 17, 20, 0), null, bodega3));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2024, 1, 1, 10, 0, 0), null, bodega3));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2022, 7, 20, 13, 15, 0), null, bodega4));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2023, 10, 10, 18, 0, 0), null, bodega4));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2023, 4, 2, 16, 0, 0), null, bodega5));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2022, 9, 15, 9, 30, 0), null, bodega7));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2024, 2, 20, 14, 30, 0), null, bodega6));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2023, 3, 10, 10, 0, 0), null, bodega8));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2023, 7, 25, 12, 30, 0), null, bodega7));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2022, 11, 1, 8, 0, 0), null, bodega7));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2024, 4, 8, 10, 45, 0), null, bodega8));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2023, 12, 12, 12, 0, 0), null, bodega8));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2023, 6, 17, 15, 15, 0), LocalDateTime.of(2024, 6, 17, 15, 15, 0), bodega7));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2022, 10, 5, 16, 0, 0), null, bodega8));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2024, 3, 1, 11, 30, 0), null, bodega10));
        siguiendoList.add(new Siguiendo(LocalDateTime.of(2023, 5, 23, 9, 0, 0), null, bodega8));
        

        //Instancias de Enofilo
        List<Enofilo> enofilos = new ArrayList();
        usuarios = usuarioJpaController.findUsuarioEntities();
        enofilos.add(new Enofilo("Carlos", "Mendez", "perfil1.jpg", usuarios.get(0), Arrays.asList(siguiendoList.get(0), siguiendoList.get(1))));
        enofilos.add(new Enofilo("Lucia", "Ramirez", "perfil2.jpg", usuarios.get(1), Arrays.asList(siguiendoList.get(2), siguiendoList.get(3), siguiendoList.get(4))));
        enofilos.add(new Enofilo("Daniel", "Gutierrez", "perfil3.jpg", usuarios.get(2), Arrays.asList(siguiendoList.get(5))));
        enofilos.add(new Enofilo("Elena", "Lopez", "perfil4.jpg", usuarios.get(3), Arrays.asList(siguiendoList.get(6), siguiendoList.get(7))));
        enofilos.add(new Enofilo("Martin", "Perez", "perfil5.jpg", usuarios.get(4), Arrays.asList(siguiendoList.get(8), siguiendoList.get(9), siguiendoList.get(10))));
        enofilos.add(new Enofilo("Ana", "Sanchez", "perfil6.jpg", usuarios.get(5), null));
        enofilos.add(new Enofilo("Diego", "Ortiz", "perfil7.jpg", usuarios.get(6), Arrays.asList(siguiendoList.get(11))));
        enofilos.add(new Enofilo("Sofia", "Morales", "perfil8.jpg", usuarios.get(7), Arrays.asList(siguiendoList.get(12), siguiendoList.get(13), siguiendoList.get(14), siguiendoList.get(15))));
        enofilos.add(new Enofilo("Julian", "Vargas", "perfil9.jpg", usuarios.get(8), Arrays.asList(siguiendoList.get(16), siguiendoList.get(17), siguiendoList.get(18))));
        enofilos.add(new Enofilo("Camila", "Ruiz", "perfil10.jpg", usuarios.get(9), Arrays.asList(siguiendoList.get(19))));
        enofilos.forEach(enofilo->enofiloJpaController.create(enofilo));
        
        List<Vino> vinitos = vinoJpaController.findVinoEntities();
        vinitos.forEach(vino->System.out.println(vino));
    }
}
