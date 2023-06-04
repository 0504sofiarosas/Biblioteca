import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase para alamacenar la información de los prestamos.
 *
 * @author Equipo
 */
public class Prestamo implements Serializable {

  private int id;
  private LocalDate fecha_prestamo;
  private LocalDate fecha_devolucion;
  private int prorroga;

  /**
   * Construye un objeto Préstamo con todos sus atributos.
   *
   * @param id El identificador interno del libro.
   * @param fecha_prestamo La fecha en que se pidio el préstamo.
   * @param fecha_devolucion La fecha en que se devuelve el libro.   
   */

  public Prestamo(int id, LocalDate fecha_prestamo, LocalDate fecha_devolucion, int prorroga) {
    this.id = id;
    this.fecha_prestamo = fecha_prestamo;
    this.fecha_devolucion = fecha_devolucion;
    this.prorroga = prorroga;
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
  public LocalDate getFecha_prestamo() {
    return fecha_prestamo;
  }

  /**
   * Regresa la fecha de cuando se termina el préstamo.
   *
   * @return La fecha del termino del préstamo.
   */
  public LocalDate getFecha_devolucion() {
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
    System.out.println(String.format("\t\tId del libro:          %07d", id));
    System.out.println(String.format("\t\tFecha del prestamo:       %s", fecha_prestamo));
    System.out.println(String.format("\t\tFecha de la devolución:      %s", fecha_devolucion));
    System.out.println(String.format("\t\tProrroga: %s", prorroga));
  }

}