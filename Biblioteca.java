import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase donde se definen todas las operaciones que están disponibles dentro de
 * la biblioteca.
 *
 * @author Equipo
 */
public class Biblioteca implements Serializable {

	private static final long serialVersionUID = 85930233957019525L;
	private Bibliotecario[] bibliotecarios;
	private Usuario[] usuarios;
	private Libro[] libros;
  private Prestamo[] prestamos;

   /**
   	* Construye un objeto Biblioteca con todas las acciones que pueden realizar los
   	* empleados.
   	*/
  public Biblioteca(){
  	this.bibliotecarios = new Bibliotecario[4];
  	this.usuarios = new Usuario[100];
  	this.libros = new Libro[100];
    
  }

  /**
   * Regresa la lista de bibliotecarios.
   *
   * @return
   */
  public Bibliotecario[] getBibliotecarios() {
    return bibliotecarios;
  }

  /**
   * Regresa la lista de los usuarios.
   *
   * @return
   */
  public Usuario[] getUsuarios() {
    return usuarios;
  }

  /**
   * Regresa la lista de los libros.
   *
   * @return
   */
  public Libro[] getLibros() {
    return libros;
  }

  /**
   * Regresa la lista de los préstamos.
   *
   * @return
   */
  public Prestamo[] getPrestamos() {
    return prestamos;
  }

  /*
    Método auxiliar que permite guardar los cambios en el sistema y de esta forma
    hace que la información sea persistente.
   */
  private void guardar() {

  	FileOutputStream fos = null;
    ObjectOutputStream salida = null;

    try {
    	fos = new FileOutputStream("labiblio.dat", false);
      salida = new ObjectOutputStream(fos);

      salida.writeObject(this);
    } catch (FileNotFoundException fnfe) {
    	System.out.println(fnfe.getMessage());
    } catch (IOException ioe) {
    	System.out.println(ioe.getMessage());
    } finally {
    	try {
    		if (fos != null) {
    			fos.close();
    		}

    		if (salida != null) {
    			salida.close();
    		}
    	} catch (IOException ioe) {
    		System.out.println(ioe.getMessage());
    	}
    }
  }

  /*
    Método auxiliar que permite cargar la información que se haya guardado.
   */
  public Biblioteca cargar() {
  	FileInputStream fis = null;
    ObjectInputStream entrada = null;
    Biblioteca c = null;

    try {
    	fis = new FileInputStream("labiblio.dat");
      	entrada = new ObjectInputStream(fis);

      	c = (Biblioteca) entrada.readObject();

    } catch (FileNotFoundException fnfe) {
    	System.out.println(fnfe.getMessage());
    } catch (ClassNotFoundException cnfe) {
    	System.out.println(cnfe.getMessage());
    } catch (IOException ioe) {
    	System.out.println(ioe.getMessage());
    } finally {
    	try {
    		if (fis != null) {
    			fis.close();
    		}
    		if (entrada != null) {
    			entrada.close();
    		}
    	} catch (IOException ioe) {
    		System.out.println(ioe.getMessage());
    	}

    	return c;
    }
  }

  /* Método auxiliar encargado de generar un identificador. */
  private int genera_id() {
    Random rnd = new Random();
    return rnd.nextInt(1000000); // Regresa un núero entre 0 y 1000000.
  }

  /*
    Método auxiliar encargado de buscar un espacio vacio dentro del arreglo de 
    usuarios.
   */
  private int usuarios_busca_espacio() {

    for (int i = 0; i < usuarios.length; i++) {
      if (usuarios[i] == null) {  // Si es null es un espacio disponible.
        return i;
      }
    }

    return -1;
  }

  /*
    Método auxiliar encargado de buscar un espacio vacio dentro del arreglo de 
    libros del usuario dado.
   */
  private int libros_busca_espacio() {

    Libro[] libros = getLibros();

    for (int i = 0; i < libros.length; i++) { // Recorre el arreglo de libros del usuario
      if (libros[i] == null) {  // Si es null es un espacio disponible.
        return i;
      }
    }

    return -1;
  }

  /*
    Método auxiliar encargado de buscar un espacio vacio dentro del arreglo de 
    prestámos del usuario dado.
   */
  private int prestamos_busca_espacio() {

    Prestamo[] prestamos = getPrestamos();

    for (int i = 0; i < prestamos.length; i++) { // Recorre el arreglo de prestamos del usuario
      if (prestamos[i] == null) {  // Si es null es un espacio disponible.
        return i;
      }
    }

    return -1;
  }

  /* Método auxiliar encargado de darle formato a las cadenas que recibe. */
  private String da_formato(String s) {
    return s.trim() // Eliminar espacios al inicio y final
            .toUpperCase() // Convertir a mayúsculas
            .replaceAll("%s(,2)", " "); // Eliminar espacios intermedios
  }

  /**
   * Agrega un nuevo usuario al sistema. Dentro del método se solita la
   * información del usuario.
   */
  public void agregar_usuario() {
  	Scanner teclado = new Scanner(System.in);

  	try {
  		int index = usuarios_busca_espacio();

      if (index == -1) {  // Revisa si hay espacio en el arreglo de usuarios.
        System.out.println("Error: Ya no hay espacio para agregar más información.");
  	} else {
  		// Tomamos la información del usuario.
        System.out.print("Nombre(s):           ");
        String nombre = da_formato(teclado.nextLine());
        System.out.print("Primer apellido:     ");
        String primer_apellido = da_formato(teclado.nextLine());
        System.out.print("Segundo apellido:    ");
        String segundo_apellido = da_formato(teclado.nextLine());
        System.out.print("Dirección:           ");
        String direccion = da_formato(teclado.nextLine());
        System.out.print("Telefono:  ");
        String telefono = da_formato(teclado.nextLine());
        System.out.print("Correo electrónico:  ");
        String correo_electronico = da_formato(teclado.nextLine());

        // Creamos el usuario
        Usuario nuevo_usuario = new Usuario(genera_id(), nombre, primer_apellido, segundo_apellido, direccion,
        	telefono, correo_electronico);

        // Se agrega al arreglo correspondiente.
        usuarios[index] = nuevo_usuario;

        // Muestra la información que se registro.
        System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
        System.out.println("INFO: Registro creado exitosamente.");
        System.out.println(String.format("%050d", 0).replace("0", "-"));
        nuevo_usuario.muestra_usuario_con_libros();
        System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));

        guardar();  // Guardamos los cambios en el sistema.
  	}

  } catch (Exception e) {
  	// En caso de error muestra un mensaje en lugar de terminar el programa.
      System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
      System.out.println("ERROR: Ha ocurrido un error inesperado. Vuelve a intentarlo más tarde.");
      System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
  	}
  }

  /**
   * Muesta a todos los usuarios en forma de lista y con la información de
   * los libros en préstamo.
   */
  public void muestra_usuario_con_libros() {
  	// Línea que sirve de separador.
    System.out.println(String.format("\n%050d", 0).replace("0", "-"));

    // Recorre el arreglo de usuarios y los va mostrando.
    for (int i = 0; i < usuarios.length; i++) {
    	if (usuarios[i] != null) {
    		 usuarios[i].muestra_usuario_con_libros();
        System.out.println(String.format("%050d", 0).replace("0", "-"));
    	}
    }
  }

  /**
   * Busca un usuario por su identificador asignado.
   *
   * @param id El identificador del usuario.
   * @return La posición donde se encontró al usuario. Si no se encontró
   * regresa -1.
   */
  public int busca_usuario(int id) {
    for (int i = 0; i < usuarios.length; i++) {
      if (usuarios[i] != null && usuarios[i].getId() == id) {
        // Si lo encuentra regresa su posición.
        return i;
      }
    }

    return -1;
  }

  // ===========================================================
  // LIBROS
  // ===========================================================
  /**
   * Agrega un nuevo libro al sistema. Dentro del método se solita la
   * información del libro.
   */
  public void agregar_libro() {
  	Scanner teclado = new Scanner(System.in);
  
  	try {
  		int index = libros_busca_espacio();

  		if (index == -1) {
  			System.out.println("Error: Ya no hay espacio para agregar más información.");
  		} else {
  			// Tomamos la información del libro.
        System.out.print("Titulo:           ");
        String titulo = da_formato(teclado.nextLine());
        System.out.print("Autor:     ");
        String autor = da_formato(teclado.nextLine());
        System.out.print("Editorial:    ");
        String editorial = da_formato(teclado.nextLine());
        System.out.print("´Número de paginas:           ");
        int no_paginas = Integer.parseInt(teclado.nextLine());
        System.out.print("Número de ejemplares:  ");
        int no_ejemplares = Integer.parseInt(teclado.nextLine());

        // Creamos el libro
        Libro nuevo_libro = new Libro(titulo,
                autor, editorial, no_paginas, genera_id(), no_ejemplares);

        // Se agrega al arreglo correspondiente.
        libros[index] = nuevo_libro;

        // Muestra la información que se registro.
        System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
        System.out.println("INFO: Registro creado exitosamente.");
        System.out.println(String.format("%050d", 0).replace("0", "-"));
        nuevo_libro.muestra_libro();
        System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));

        guardar();  // Guardamos los cambios en el sistema.
  		}

  	}catch (Exception e){
  		// En caso de error muestra un mensaje en lugar de terminar el programa.
      System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
      System.out.println("ERROR: Ha ocurrido un error inesperado. Vuelve a intentarlo más tarde.");
      System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
  	}
  }

  /**
   * Elimina un libro a partir de su identificador.
   *
   * @param id El identificador del libro.
   */
  public void eliminar_libro(int id) {
  	int index = busca_libro(id);

    if (index == -1) {
    	System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
      	System.out.println("ERROR: El libro no existe.");
      	System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
    } else {
    	libros[index] = null;
      	System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
      	System.out.println("INFO: El libro ha sido eliminado.");
      	System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));

      	guardar();  // Guardamos los cambios en el sistema.
    }
  }

  /**
   * Busca una libro por su nombre.
   *
   * @param id El identificador del libro.
   * @param título El título del libro.
   */
  public int busca_libro(int id) {
  	for (int i = 0; i < libros.length; i++) {
  		if (libros[i] != null && libros[i].getId() == id) {
  			// Si lo encuentra regresa su posición.
        	return i;
  		}
  	}
  	return -1;
  }

  /**
   * Muesta a todos los libros en forma de tabla.
   */
  public void existencia_libros() {
  	// Encabezados de la tabla.
    System.out.println(String.format("\n%-7s | %-40s | %-30s | %-30s | %-10s | %-10s",
            "Id", "Titulo", "Autor", "Editorial", "No. paginas", "# ejemplares"));

    // Línea que sirve de separador.
    System.out.println(String.format("%0165d", 0).replace("0", "-"));

    // Recorre el arreglo de libros y los va mostrando.
    for (int i = 0; i < libros.length; i++) {
    	if (libros[i] != null) {  // Valida que no este vacío el espacio.
    		libros[i].muestra_libro();
        System.out.println(String.format("%0165d", 0).replace("0", "-"));
    	}
    }
  }

/**
   * Agenda una cita para una mascota.
   *
   * @param id El identificador del propietario, dueño de la mascota.
   */
  public void agregar_prestamo() {
    Scanner teclado = new Scanner(System.in);
    System.out.print("\n\nIngresa el ID del usuario:");
      int idu = Integer.parseInt(teclado.nextLine());
    System.out.print("\n\nIngresa el ID del libro:");
        int idl = Integer.parseInt(teclado.nextLine());
  
    try {
      int index_usuario = busca_usuario(idu);
      int index_prestamo = prestamos_busca_espacio();
      int index_libros = libros_busca_espacio();
      int index_libro = busca_libro(idl);

      if (index_usuario == -1) {
      System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
      System.out.println("ERROR: El usuario no existe.");
      System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
    } else 
        Usuario u = usuarios[index_usuario];
      
      if (index_libro == -1) {  // Revisa si existe esa mascota.
        System.out.println("Error: No existe mascota con ese nombre");
      } else {
        Libro l = u.getLibros()[index_libro];

       // Biblioteca c = new Biblioteca();

        // Tomamos los datos de la cita
        System.out.print("Fecha del prestamo (aaaa-mm-dd):           ");
        /* IMPORTANTE: La fecha ingresada debe estar en formato yyyy-MM-dd */
        String fchp = da_formato(teclado.nextLine());
        // Toma la fecha en forma de cadena y lo convierte a un objeto de tipo
        // LocalDate.
        DateTimeFormatter formatterp = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha_prestamo = LocalDate.parse(fchp, formatterp);

        System.out.print("Fecha de devolucion (aaaa-mm-dd):           ");
        String fchd = da_formato(teclado.nextLine());
        DateTimeFormatter formatterd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha_devolucion = LocalDate.parse(fchd, formatterd);

        System.out.print("Numero de dias de prorroga:           ");
        int prorroga = Integer.parseInt(teclado.nextLine());

        // Creamos el registro de la prorroga
        Prestamo nuevo_prestamo = new Prestamo(idl, fecha_prestamo, fecha_devolucion, prorroga);
        
        // Se agrega al arreglo correspondiente.
        prestamos[index_prestamo] = nuevo_prestamo;

        // Muestra la información que se registro.
        System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
        System.out.println("INFO: Registro creado exitosamente.");
        System.out.println(String.format("%050d", 0).replace("0", "-"));
        nuevo_prestamo.muestra_prestamo();
        System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
        
        guardar();  // Guardamos los cambios en el sistema.
      }

    }catch (Exception e){
      // En caso de error muestra un mensaje en lugar de terminar el programa.
      System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
      System.out.println("ERROR: Ha ocurrido un error inesperado. Vuelve a intentarlo más tarde.");
      System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
    }
  }



}