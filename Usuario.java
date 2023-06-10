import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.time.LocalDateTime;

/**
 * Clase para alamacenar la información de los usuarios.
 *
 * @author Equipo
 */
public class Usuario implements Serializable {
  
  private int id;
  private String nombre;
  private String primer_apellido;
  private String segundo_apellido;
  private String direccion;
  private String telefono;
  private String correo_electronico;
  private Libro[] libros;
  private Prestamo[] prestamos;
  private String contrasena;
  private String user;

  /**
   * Construye un objeto usuario con los datos del dueño.
   *
   * @param id El identificador del usuario.
   * @param nombre Nombre del usuario.
   * @param primer_apellido Primer apellido del usuario.
   * @param segundo_apellido Segundo apellido del usuario.
   * @param direccion Dirección del usuario.
   * @param telefono Teléfono para comunicarse con el usuario.
   * @param correo_electronico Correo electrónico del usuario.
   */
  public Usuario(int id_usuario, String nombre, String primer_apellido, String segundo_apellido, String user, String direccion, String telefono, String correo_electronico, String contrasena){

    this.id = id;
    this.nombre = nombre;
    this.primer_apellido = primer_apellido;
    this.segundo_apellido = segundo_apellido;
    this.direccion = direccion;
    this.telefono = telefono;
    this.correo_electronico = correo_electronico;
    this.libros = new Libro[3];
    this.prestamos = new Prestamo[3];
    this.contrasena = contrasena;
    this.user = user;
  }

  /**
   * Regresa el identificador interno del usuario.
   *
   * @return El id del usuario.
   */
  public int getId() {
    return id;
  }

  /**
   * Regresa el nombre del usuario.
   *
   * @return El nombre del usuario.
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Actualiza el nombre del usuario.
   *
   * @param nombre El nombre del usuario.
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Regresa el primer apellido del usuario.
   *
   * @return El primer apellido del usuario.
   */
  public String getPrimer_apellido() {
    return primer_apellido;
  }

  /**
   * Actualiza el primer apellido del usuario.
   *
   * @param primer_apellido El priemr apellido del usuario.
   */
  public void setPrimer_apellido(String primer_apellido) {
    this.primer_apellido = primer_apellido;
  }

  /**
   * Regresa el segundo apellido del usuario.
   *
   * @return El segundo apellido del usuario.
   */
  public String getSegundo_apellido() {
    return segundo_apellido;
  }

  /**
   * Actualiza el segundo apellido del usuario.
   *
   * @param segundo_apellido El segundo apellido del usuario.
   */
  public void setSegundo_apellido(String segundo_apellido) {
    this.segundo_apellido = segundo_apellido;
  }

  /**
   * Regresa la dirección del usuario.
   *
   * @return La dirección del usuario.
   */
  public String getDireccion() {
    return direccion;
  }

  /**
   * Actualiza la dirección del usuario.
   *
   * @param direccion La dirección del usuario.
   */
  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  /**
   * Regresa el teléfono de contacto del usuario.
   *
   * @return El teléfono del usuario.
   */
  public String getTelefono() {
    return telefono;
  }

  /**
   * Actualiza el primer teléfono de contacto del usuario.
   *
   * @param telefono El teléfono del usuario.
   */
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  /**
   * Regresa el correo electrónico del usuario.
   *
   * @return El correo electrónico del usuario.
   */
  public String getCorreo_electronico() {
    return correo_electronico;
  }

  public void setCorreo_electronico(String correo_electronico) {
    this.correo_electronico = correo_electronico;
  }

  public Prestamo[] getPrestamos() {
    return prestamos;
  }

  public String getContrasena() {
    return contrasena;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  /**
   * Regresa la lista de libros que el usuario pidió prestados.
   *
   * @return La lista de libros del usuario.
   */
  //public Libro[] getLibros() {
   // return libros;
  //}
  
  /* Método auxiliar encargado de contar el total de libros del usuario. */
  private int cuenta_libros() {
    int count = 0;  // Variable que servirá de contador.
 // REVISAR PARA VER SI NO FALTA UNA CONDICIÓN EN EL CONTADOR.
    for(int i = 0; i < libros.length; i++) {
      if(libros[i] != null) // Si encontramos un li bro que no es null aumenta el contador
        count++;
    }
    
    return count;
  }

  /**
   * Muestra la información del usuario y los libros.
   */
  public void muestra_usuario_con_libros() {
    System.out.println(String.format("Id:        %07d", id));
    System.out.println(String.format("Nombre:    %s", nombre + " " + primer_apellido + " " + segundo_apellido));
    System.out.println(String.format("Nombre de usuario:  %s", user));
    System.out.println(String.format("Dirección: %s", direccion));
    System.out.println(String.format("Tel. Prin: %s", telefono));
    System.out.println(String.format("Correo:    %s", correo_electronico));
    
    int count = 1;
    for(int i = 0; i < libros.length; i++) {
      if(libros[i] != null) {
        System.out.println("Libro " + count++ + ": ");
        libros[i].muestra_libro();
      }
    }
  }



  public void muestra_usuario_sin_libros() {
    System.out.println(String.format("Id:        %07d", id));
    System.out.println(String.format("Nombre:    %s", nombre + " " + primer_apellido + " " + segundo_apellido));
    System.out.println(String.format("Nombre de usuario:  %s", user));
    System.out.println(String.format("Dirección: %s", direccion));
    System.out.println(String.format("Tel. Prin: %s", telefono));
    System.out.println(String.format("Correo:    %s", correo_electronico));
    
  }

  public int prestamo_espacio_usuario() {
    for (int i = 0; i < prestamos.length; i++) {
      if (prestamos[i] == null) {
        return i;
      }
    }

    return -1;
  }



  public int libro_espacio_usuario() {
    for (int i = 0; i < libros.length; i++) {
      if (libros[i] == null) {
        return i;
      }
    }

    return -1;
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

  public void registrar_prestamo(int id, String titulo, String usuario) {
    Scanner teclado = new Scanner(System.in);
    try {
      int index = prestamo_espacio_usuario();
      if (index == -1) {
        System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
        System.out.println("ERROR. No hay prestamos disponibles");
        System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
      } else {
        LocalDateTime prestamo = LocalDateTime.now();
        LocalDateTime devolucion = prestamo.plusDays(5);

        Prestamo nuevo_prestamo = new Prestamo(usuario, id, titulo, prestamo, devolucion);
        prestamos[index] = nuevo_prestamo;

        System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
        System.out.println("Prestamo registrado");
        System.out.println(String.format("%050d", 0).replace("0", "-"));
        nuevo_prestamo.muestra_prestamo();
        System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
        guardar();
      }
    } catch (Exception e) {
      System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
      System.out.println("ERROR. Ha ocurrido un error. Vuelve a intentarlo");
      System.out.println(String.format("%050\n\n", 0).replace("0", "-"));
    }
  }

  /*public void reporte_prestamos() {
    System.out.println(String.format("\n%050d", 0).replace("0","-"));
    for (int i = 0; i < prestamos.length; i++) {
      if (prestamos[i] != null) {
        prestamos[i].reporte_prestamos();
      }
    }
  }*/

  public void reporte_prestamos() {
    for (int i = 0; i < prestamos.length; i++) {
      if (prestamos[i] != null) {
        String usuario = prestamos[i].getUsuario();
        int id = prestamos[i].getId();
        String titulo = prestamos[i].getTitulo();
        LocalDateTime fecha_prestamo = prestamos[i].getFecha_prestamo();
        LocalDateTime fecha_devolucion = prestamos[i].getFecha_devolucion();
        System.out.println(String.format("%07d | %-20s | %-50s | %-10s | %-10s", id, usuario, titulo, fecha_prestamo, fecha_devolucion));
      }
    }
  }

 
}