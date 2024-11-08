package com.mycompany.mongorestaurantes_235633;

import datos.RestaurantesDAO;
import java.util.ArrayList;
import java.util.Date;
import modelos.Restaurante;
import org.bson.types.ObjectId;

/**
 *
 * @author skevin
 */
public class MongoRestaurantes_235633 {

    public static void main(String[] args) {
        
        RestaurantesDAO dao = new RestaurantesDAO();
        ArrayList<Restaurante> lista = dao.obtenerRestaurantes();
        
//        //Insertar 3 documentos (restaurantes) más con al menos 2 categorías cada uno.
//        Restaurante insertar = new Restaurante("sushilito", 5, 
//               new Date(), new String[]{"Sushi", "Asiatica"});
//        Restaurante insertar2 = new Restaurante(new ObjectId(), "Prision Pizza", 4, 
//                new Date(), new String[]{"Pizza", "Italiana"});
//        Restaurante insertar3 = new Restaurante(new ObjectId(), "Sushi Van", 3, 
//                new Date(), new String[]{"Sushi", "Asiática"});
//        
//        dao.agregarRestaurante(insertar);
//        dao.agregarRestaurante(insertar2);
//        dao.agregarRestaurante(insertar3);
        
        //Consultar los restaurantes con más de 4 estrellas de rating.
        //dao.consultarRestaurantesRating();
        
        //Consultar los restaurantes que incluyan la categoría pizza.
        //dao.consultarRestaurantesCategoria();
        
        //Consultar los restaurantes que incluyan sushi en su nombre.
        //dao.consultarRestaurantesNombre();
        
        //Agregar una categoría extra al restaurant sushilito.
        //dao.agregarCategoria();
        
//        //Eliminar un restaurante por su identificador.        
//        boolean eliminado = dao.eliminarId("672e84f979ed443c0a3bafe3");
//
//        if (eliminado) {
//            System.out.println("Restaurante eliminado correctamente.");
//        } else {
//            System.out.println("No se pudo eliminar el restaurante.");
//        }
        
        //Eliminar los restaurantes con 3 estrellas o menos.
        boolean eliminados = dao.eliminarRating();
        
        if(eliminados){
            System.out.println("Se han eliminado los restaurantes con 3 estrellas o menos.");
        }
        else{
            System.out.println("No se ha eliminado ningún restaurante o no hay ninguno con 3 estrellas o menos");
        }

          for (Restaurante restaurante : lista) {
              System.out.println(restaurante.toString());
          }
        
          
    }
}
