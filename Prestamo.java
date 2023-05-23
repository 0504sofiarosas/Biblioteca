import java.io.Serializable;

/**
 * Clase para alamacenar la información de los prestamos.
 *
 * @author Equipo
 */
public class Prestamo implements Serializable {

  private int idlibro;
  private String fecha_prestamo;
  private String fecha_devolucion;
  private int prorroga;

  /**
   * Construye un objeto Préstamo con todos sus atributos.
   *
   * @param idlibro El identificador interno del libro.
   * @param fecha_prestamo La fecha en que se pidio el préstamo.
   * @param fecha_devolucion La fecha en que se devuelve el libro.   
   */

  public Prestamo(int idlibro, String fecha_prestamo, String fecha_devolucion, int prorroga) {
    this.idlibro = idlibro;
    this.fecha_prestamo = fecha_prestamo;
    this.fecha_devolucion = fecha_devolucion;
    this.prorroga = prorroga;
  }

  /**
   * Regresa el id del libro.
   *
   * @return El id del libro.
   */
  public int getIdlibro() {
    return idlibro;
  }

  /**
   * Regresa la fecha de cuando se realizó el préstamo.
   *
   * @return La fecha del préstamo.
   */
  public String getFecha_prestamo() {
    return fecha_prestamo;
  }

  /**
   * Regresa la fecha de cuando se termina el préstamo.
   *
   * @return La fecha del termino del préstamo.
   */
  public String getFecha_devolucion() {
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
    System.out.println(String.format("\t\tId del libro:          %07d", idlibro));
    System.out.println(String.format("\t\tFecha del prestamo:       %s", fecha_prestamo));
    System.out.println(String.format("\t\tFecha de la devolución:      %s", fecha_devolucion));
    System.out.println(String.format("\t\tProrroga: %s", prorroga));
  }

}