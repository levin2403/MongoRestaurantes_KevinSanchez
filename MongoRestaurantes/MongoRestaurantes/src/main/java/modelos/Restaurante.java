package modelos;

import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Clase que representa un restaurante.
 * 
 * Esta clase contiene información sobre un restaurante, como su nombre, 
 * calificación, fecha de inauguración y categorías de comida. Está diseñada
 * para ser utilizada como parte de un sistema de gestión de restaurantes,
 * donde los restaurantes se pueden almacenar, consultar y actualizar según
 * sus características.
 * 
 * @author skevi
 */
public class Restaurante {

    /**
     * Identificador único del restaurante.
     * Este campo utiliza el tipo ObjectId, que es comúnmente utilizado en 
     * MongoDB para representar identificadores únicos de documentos en la 
     * base de datos.
     */
    private ObjectId id;

    /**
     * Nombre del restaurante.
     * Este campo almacena el nombre comercial del restaurante.
     */
    private String nombre;

    /**
     * Calificación del restaurante.
     * Este campo almacena la calificación del restaurante, generalmente 
     * de 1 a 5, representando la calidad percibida del restaurante.
     */
    private int rating;

    /**
     * Fecha de inauguración del restaurante.
     * Este campo almacena la fecha en la que el restaurante fue inaugurado.
     */
    private Date fechaInauguracion;

    /**
     * Categorías de comida ofrecidas en el restaurante.
     * Este campo es un arreglo de cadenas que representa las diferentes 
     * categorías de platos o estilos culinarios que el restaurante ofrece.
     */
    private String categorias[];

    /**
     * Constructor vacío de la clase Restaurante.
     * Inicializa un objeto Restaurante sin valores específicos.
     */
    public Restaurante() {
    }

    /**
     * Constructor con el identificador único del restaurante.
     * Este constructor permite crear un restaurante solo con su identificador.
     * 
     * @param id El identificador único del restaurante.
     */
    public Restaurante(ObjectId id) {
        this.id = id;
    }

    /**
     * Constructor completo para crear un restaurante con todos sus campos.
     * 
     * @param nombre El nombre del restaurante.
     * @param rating La calificación del restaurante.
     * @param fechaInauguracion La fecha en que el restaurante fue inaugurado.
     * @param categorias Las categorías de comida del restaurante.
     */
    public Restaurante(String nombre, int rating, Date fechaInauguracion, 
            String[] categorias) {
        this.nombre = nombre;
        this.rating = rating;
        this.fechaInauguracion = fechaInauguracion;
        this.categorias = categorias;
    }

    /**
     * Constructor completo con identificador y todos los campos del 
     * restaurante.
     * 
     * @param id El identificador único del restaurante.
     * @param nombre El nombre del restaurante.
     * @param rating La calificación del restaurante.
     * @param fechaInauguracion La fecha de inauguración del restaurante.
     * @param categorias Las categorías de comida del restaurante.
     */
    public Restaurante(ObjectId id, String nombre, int rating, 
            Date fechaInauguracion, String[] categorias) {
        this.id = id;
        this.nombre = nombre;
        this.rating = rating;
        this.fechaInauguracion = fechaInauguracion;
        this.categorias = categorias;
    }

    /**
     * Obtiene el identificador único del restaurante.
     * 
     * @return El identificador del restaurante.
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Establece el identificador único del restaurante.
     * 
     * @param id El nuevo identificador del restaurante.
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del restaurante.
     * 
     * @return El nombre del restaurante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del restaurante.
     * 
     * @param nombre El nuevo nombre del restaurante.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la calificación del restaurante.
     * 
     * @return La calificación del restaurante.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Establece la calificación del restaurante.
     * 
     * @param rating La nueva calificación del restaurante.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Obtiene la fecha de inauguración del restaurante.
     * 
     * @return La fecha de inauguración del restaurante.
     */
    public Date getFechaInauguracion() {
        return fechaInauguracion;
    }

    /**
     * Establece la fecha de inauguración del restaurante.
     * 
     * @param fechaInauguracion La nueva fecha de inauguración del 
     * restaurante.
     */
    public void setFechaInauguracion(Date fechaInauguracion) {
        this.fechaInauguracion = fechaInauguracion;
    }

    /**
     * Obtiene las categorías de comida del restaurante.
     * 
     * @return Un arreglo de cadenas que representan las categorías del 
     * restaurante.
     */
    public String[] getCategorias() {
        return categorias;
    }

    /**
     * Establece las categorías de comida del restaurante.
     * 
     * @param categorias El nuevo arreglo de categorías del restaurante.
     */
    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }

    /**
     * Devuelve una representación en cadena del restaurante.
     * Este método sobrescribe el método toString para proporcionar una 
     * representación legible de la clase Restaurante.
     * 
     * @return Una cadena que describe al restaurante.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Restaurante{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", rating=").append(rating);
        sb.append(", fechaInauguracion=").append(fechaInauguracion);
        sb.append(", categorias=").append(categorias);
        sb.append('}');
        return sb.toString();
    }

}

