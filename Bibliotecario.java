import java.io.Serializable;

/**
 *
 * @author Equipo
 */
public class Bibliotecario implements Serializable{

    private int idbibliotecario;
    private String nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private String telefono;
    private String correo_electronico;
    private String direccion_biblioteca;
    private String nombre_biblioteca;

    public Bibliotecario(int idbibliotecario, String nombre, String primer_apellido, String segundo_apellido, String direccion_biblioteca, String nombre_biblioteca) {
        this.idbibliotecario = idbibliotecario;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.telefono = telefono;
        this.correo_electronico = correo_electronico;
        this.direccion_biblioteca = direccion_biblioteca;
        this.nombre_biblioteca = nombre_biblioteca;
    }
    
    /**
     * Regresa el identificador interno del bibliotecario.
     * @return El id del bibliotecario. 
     */
    public int getIdbibliotecario() {
        return id;
    }

    /**
     * Regresa el nombre del bibliotecario.
     * @return El nombre del bibliotecario.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Actualiza el nombre del bibliotecario
     * @param nombre El nombre del bibliotecario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Regresa el primer apellido del bibliotecario.
     * @return El primer apellido del bibliotecario.
     */
    public String getPrimer_apellido() {
        return primer_apellido;
    }

    /**
     * Actualiza el primer apellido del bibliotecario.
     * @param primer_apellido El primer apellido del bibliotecario.
     */
    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }
    
    /**
     * Regresa el segundo apellido del bibliotecario.
     * @return El segundo apellido del bibliotecario.
     */
    public String getSegundo_apellido() {
        return segundo_apellido;
    }
    
    /**
     * Actualiza el segundo apellido del bibliotecario.
     * @param segundo_apellido El segundo apellido del bibliotecario.
     */
    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    /**
   * Regresa el teléfono de contacto del bibliotecario.
   *
   * @return El teléfono del bibliotecario.
   */
  public String getTelefono() {
    return telefono;
    }

  /**
   * Actualiza el teléfono de contacto del bibliotecario.
   *
   * @param telefono El teléfono del usuario.
   */
  public void setTelefono(String telefono) {
    this.telefono = telefono;
    }

  /**
   * Regresa el correo electrónico del bibliotecario.
   *
   * @return El correo electrónico del bibliotecario.
   */
  public String getCorreo_electronico() {
    return correo_electronico;
    }

    /**
     * Regresa la dirección de la biblioteca.
     * @return La dirección de la biblioteca.
     */
    public String getDireccion_Biblioteca() {
        return direccion_biblioteca;
    }
    
    /**
     * Actualiza la dirección de la biblioteca.
     * @param direccion_biblioteca La dirección de la biblioteca.
     */
    public void setDireccion_biblioteca(String direccion_biblioteca) {
        this.direccion_biblioteca = direccion_biblioteca;
    }
    
    /**
     * Regresa el nombre de la biblioteca.
     * @return El nombre de la biblioteca.
     */
    public String getNombre_biblioteca() {
        return nombre_biblioteca;
    }
    
    /**
     * Actualiza el nombre de la biblioteca.
     * @param nombre_biblioteca El nombre de la biblioteca.
     */
    public void setNombre_biblioteca(String nombre_biblioteca) {
        this.nombre_biblioteca = nombre_biblioteca;
    }

}