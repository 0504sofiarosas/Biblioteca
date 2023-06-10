import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Clase para alamacenar la información de los prestamos.
 *
 * @author Equipo
 */
public class Prestamo implements Serializable {

  private int id;
  private String usuario;
  private String titulo;
  private LocalDateTime fecha_prestamo;
  private LocalDateTime fecha_devolucion;
  private int prorroga;

  /**
   * Construye un objeto Préstamo con todos sus atributos.
   *
   * @param id El identificador interno del libro.
   * @param fecha_prestamo La fecha en que se pidio el préstamo.
   * @param fecha_devolucion La fecha en que se devuelve el libro.   
   */

  public Prestamo(String usuario, int id, String titulo, LocalDateTime fecha_prestamo, LocalDateTime fecha_devolucion) {
    this.id = id;
    this.usuario = usuario;
    this.titulo = titulo;
    this.fecha_prestamo = fecha_prestamo;
    this.fecha_devolucion = fecha_devolucion;
    this.prorroga = prorroga;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getUsuario() {
    return usuario;
  }

  /**
   * Regresa el id del libro.
   *
   * @return El id del libro.
   */
  public int getId() {
    return id;
  }

  /**
   * Regresa la fecha de cuando se realizó el préstamo.
   *
   * @return La fecha del préstamo.
   */
  public LocalDateTime getFecha_prestamo() {
    return fecha_prestamo;
  }

  /**
   * Regresa la fecha de cuando se termina el préstamo.
   *
   * @return La fecha del termino del préstamo.
   */
  public LocalDateTime getFecha_devolucion() {
    return fecha_devolucion;
  }

  /**
   * Regresa la extensión de días en el préstamo.
   *
   * @return La fecha del día de la prorroga.
   */
  public int getProrroga() {
    return prorroga;
  }

  /**
   * Muestra la información del préstamo.
   */
  public void muestra_prestamo() {
    System.out.println(String.format("\n\nUsuario:      %s", usuario));
    System.out.println(String.format("\t\tId del libro:          %07d", id));
    System.out.println(String.format("\t\tTitulo del libro:     %s", titulo));
    System.out.println(String.format("\t\tFecha del prestamo:       %s", fecha_prestamo));
    System.out.println(String.format("\t\tFecha de la devolución:      %s", fecha_devolucion));
    
  }

  public void reporte_prestamos() {
    System.out.println(String.format("%-20s | %07d | %-50s | %-10s | %-10s", usuario, id, titulo, fecha_prestamo, fecha_devolucion));
  }


}