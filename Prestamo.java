import java.io.Serializable;
import java.time.LocalDate;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Clase para alamacenar la información de los prestamos.
 *
 * @author Equipo
 */
public class Prestamo implements Serializable {

  private int id;
  private String usuario;
  private String titulo;
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

  public Prestamo(String usuario, int id, String titulo, LocalDate fecha_prestamo, LocalDate fecha_devolucion) {
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
  
  private void guardar() {
		FileOutputStream pres = null;
		ObjectOutputStream retor = null;

		try {
			pres = new FileOutputStream("labiblio.dat", false);
			retor = new ObjectOutputStream (pres);

			retor.writeObject(this);
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} finally {
			try {
				if (pres != null) {
					pres.close();
				}
				if (retor != null) {
					retor.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		}
	}



  /**
   * Muestra la información del préstamo.
   */
  public void muestra_prestamo() {
    System.out.println(String.format("\n\nUsuario:                %s", usuario));
    System.out.println(String.format("\t\tId del libro:           %07d", id));
    System.out.println(String.format("\t\tTitulo del libro:       %s", titulo));
    System.out.println(String.format("\t\tFecha del prestamo:     %s", fecha_prestamo));
    System.out.println(String.format("\t\tFecha de la devolucion: %s", fecha_devolucion));
    
  }

  /**
   * Muestra la información de la prorroga.
   */
  public void muestra_prorroga() {
    System.out.println(String.format("\n\nUsuario:      %s", usuario));
    System.out.println(String.format("\t\tId del libro:          %07d", id));
    System.out.println(String.format("\t\tTitulo del libro:     %s", titulo));
    System.out.println(String.format("\t\tFecha de solicitud de la prorroga:       %s", fecha_prestamo));
    System.out.println(String.format("\t\tFecha de la devolucion:      %s", fecha_devolucion));
    
  }

  


}
