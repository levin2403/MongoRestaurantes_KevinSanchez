package datos;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Filters.regex;
import com.mongodb.client.result.DeleteResult;
import java.util.ArrayList;
import java.util.Arrays;
import modelos.Restaurante;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Clase encargada de gestionar las operaciones relacionadas con la colección
 * de restaurantes en la base de datos MongoDB.
 * 
 * Esta clase ofrece métodos para agregar, consultar, y eliminar restaurantes,
 * así como para realizar operaciones sobre las categorías y ratings de los 
 * restaurantes.
 * 
 * @author skevi
 */
public class RestaurantesDAO {

    /**
     * Cliente de MongoDB para establecer la conexión con el servidor.
     */
    MongoClient mongoClient;

    /**
     * Base de datos de MongoDB donde se encuentra la colección de 
     * restaurantes.
     */
    MongoDatabase database;

    /**
     * Colección de MongoDB que contiene los documentos de restaurantes.
     */
    MongoCollection<Document> collection;

    /**
     * Constructor de la clase RestaurantesDAO.
     * Inicializa el cliente de MongoDB, la base de datos y la colección 
     * de restaurantes.
     */
    public RestaurantesDAO() {
        mongoClient = new MongoClient();
        database = mongoClient.getDatabase("ejercicios");
        collection = database.getCollection("restaurantes");
    }

    /**
     * Agrega un nuevo restaurante a la colección.
     * 
     * @param r El objeto Restaurante que se va a agregar a la base de datos.
     * Se crea un documento a partir de los atributos del restaurante y se 
     * inserta en la colección de restaurantes.
     */
    public void agregarRestaurante(Restaurante r) {
        Document d = new Document("nombre", r.getNombre())
                .append("rating", r.getRating())
                .append("fechainauguracion", r.getFechaInauguracion())
                .append("categorias", Arrays.asList(r.getCategorias()));
        collection.insertOne(d);
    }

    /**
     * Obtiene todos los restaurantes de la colección y los devuelve 
     * como una lista.
     * 
     * @return Una lista de objetos Restaurante que representan todos 
     * los restaurantes en la base de datos.
     */
    public ArrayList<Restaurante> obtenerRestaurantes() {
        ArrayList<Restaurante> restaurantes = new ArrayList();
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document d = cursor.next();
                Restaurante r = new Restaurante();
                r.setNombre(d.getString("nombre"));
                r.setRating(d.getInteger("rating"));
                r.setFechaInauguracion(d.getDate("fechainauguracion"));
                ArrayList<String> categorias = 
                        (ArrayList<String>) d.get("categorias");
                r.setCategorias(categorias.toArray(
                        new String[categorias.size()]));
                restaurantes.add(r);
            }
        } finally {
            cursor.close();
        }
        return restaurantes;
    }

    /**
     * Consulta los restaurantes cuyo rating es mayor a 4.
     * Este método no devuelve una lista de restaurantes, sino que 
     * simplemente imprime en consola los restaurantes que cumplen 
     * con el criterio.
     * 
     * @return Una lista vacía de restaurantes, ya que los resultados se 
     * imprimen directamente.
     */
    public ArrayList<Restaurante> consultarRestaurantesRating() {
        ArrayList<Restaurante> restaurantes = new ArrayList();
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };
        collection.find(gt("rating", 4)).forEach(printBlock);
        return restaurantes;
    }

    /**
     * Consulta los restaurantes que pertenecen a la categoría "Pizza".
     * Similar al método anterior, este imprime en consola los restaurantes
     * que tienen la categoría especificada.
     * 
     * @return Una lista vacía de restaurantes, ya que los resultados se 
     * imprimen directamente.
     */
    public ArrayList<Restaurante> consultarRestaurantesCategoria() {
        ArrayList<Restaurante> restaurantes = new ArrayList();
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };
        collection.find(eq("categorias", "Pizza")).forEach(printBlock);
        return restaurantes;
    }

    /**
     * Consulta los restaurantes cuyo nombre contiene la palabra "sushi".
     * Utiliza una expresión regular para realizar la búsqueda insensible 
     * a mayúsculas y minúsculas. Imprime los resultados en consola.
     * 
     * @return Una lista vacía de restaurantes, ya que los resultados se 
     * imprimen directamente.
     */
    public ArrayList<Restaurante> consultarRestaurantesNombre() {
        ArrayList<Restaurante> restaurantes = new ArrayList();
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };
        collection.find(regex("nombre", "sushi", "i")).forEach(printBlock);
        return restaurantes;
    }

    /**
     * Agrega una nueva categoría al restaurante llamado "sushilito".
     * Utiliza la operación $addToSet para asegurarse de que no se agregue 
     * una categoría duplicada.
     */
    public void agregarCategoria() {
        collection.updateOne(eq("nombre", "sushilito"),
                new Document("$addToSet", new Document("categorias", 
                        "Familiar")));
        Document doc = collection.find(eq("nombre", "sushilito")).first();
        System.out.println(doc.toJson());
    }

    /**
     * Elimina un restaurante por su identificador único (_id).
     * 
     * @param id El identificador único del restaurante que se va a eliminar.
     * @return true si el restaurante fue eliminado correctamente, false si 
     * ocurrió un error.
     */
    public boolean eliminarId(String id) {
        try {
            DeleteResult result = collection.deleteOne(eq("_id", 
                    new ObjectId(id)));
            return result.getDeletedCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina todos los restaurantes con un rating menor o igual a 3.
     * 
     * @return true si se eliminaron restaurantes, false si ocurrió un error 
     * o no se eliminaron restaurantes.
     */
    public boolean eliminarRating() {
        try {
            DeleteResult result = collection.deleteMany(lte("rating", 3));
            System.out.println(result.getDeletedCount());
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

