import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDate;

public class Biblioteca implements Serializable {
	
	private static final long serialVersionUID = 2L;
	private Bibliotecario [] bibliotecarios;
	private Libro [] libros;
	private Usuario [] usuarios;
	private Prestamo [] prestamos;

    //Aciones que pueden realizar los bibliotecarios

	public Biblioteca() {
		this.bibliotecarios = new Bibliotecario[100];
		this.usuarios = new Usuario[100];
		this.libros = new Libro[100];
		this.prestamos = new Prestamo[100];

	}

	public Bibliotecario [] getBibliotecarios() {
		return bibliotecarios;
	}

	public Libro [] getLibros() {
		return libros;
	}

	public Usuario [] getUsuarios() {
		return usuarios;
	}

	public Prestamo [] getPrestamos() {
		return prestamos;
	}


	// Método para guardar los cambios 

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

	// Metodo que permite cargar información

	public Biblioteca cargar() {
		FileInputStream pros = null;
		ObjectInputStream retiro = null;
		Biblioteca b = null;

		try {
			pros = new FileInputStream("labiblio.dat");
			retiro = new ObjectInputStream(pros);

			b = (Biblioteca) retiro.readObject();
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} finally {
			try {
				if (pros != null) {
					pros.close();
				}
				if (retiro != null) {
					retiro.close();
				}
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}

			return b;
		}
	}

	// Metodo encargado de generar un identificador

	private int genera_id() {
		Random rnd = new Random();
		return rnd.nextInt(1000000);
	}

	// Método para buscar un espacio del arreglo de Usuarios

	private int usuarios_busca_espacio() {

		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] == null) {
				return i;
			}
		}

		return -1;
	}

	// Metodo para buscar un espacio en el arreglo de bibliotecarios

	private int bibliotecarios_busca_espacio() {

		for (int i = 0; i < bibliotecarios.length; i++) {
			if (bibliotecarios[i] == null) {
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
	// Método encargado de dar formato a las cadenas que recibe

	private String da_formato(String s) {
		return s.trim().toUpperCase().replaceAll("%s(,2)", " ");
	}

	// Dar de alta Bibliotecario

	public void agregar_bibliotecario() {
		Scanner teclado = new Scanner(System.in);

		try {
			int index = bibliotecarios_busca_espacio();
			if(index == -1) {
				System.out.println("ERROR. Ya no queda espacio para agregar otro bibliotecario");
			} else {
				System.out.println("Ingrese los siguientes datos:");
				System.out.println("Nombre(s): ");
				String nombre_biblio = da_formato(teclado.nextLine());
				System.out.println("Primer apellido: ");
				String primer_apellido_biblio = da_formato(teclado.nextLine());
				System.out.println("Segundo apellido: ");
				String segundo_apellido_biblio = da_formato(teclado.nextLine());
				System.out.println("Nombre de usuario: ");
				String user_biblio = teclado.nextLine();
				System.out.println("Direccion: ");
				String direccion_biblio = da_formato(teclado.nextLine());
				System.out.println("Telefono: ");
				String telefono_biblio = da_formato(teclado.nextLine());
				System.out.println("Correo electronico: ");
				String correo_biblio = da_formato(teclado.nextLine());
				System.out.println("Ingresa tu contraseña:");
				String contrasena_biblio = teclado.nextLine();

				Bibliotecario nuevo_bibliotecario = new Bibliotecario (genera_id(), nombre_biblio, primer_apellido_biblio, segundo_apellido_biblio, user_biblio, direccion_biblio, telefono_biblio, correo_biblio, contrasena_biblio);
				bibliotecarios[index] = nuevo_bibliotecario;

				System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
				System.out.println("Registro realizado exitosamente");
				System.out.println(String.format("%050d", 0).replace("0", "-"));
				nuevo_bibliotecario.muestra_bibliotecarios();
				System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
				guardar();
			}
		} catch (Exception e) {
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("ERROR. Ha ocurrido un error. Vuelve a intentarlo");
			System.out.println(String.format("%050\n\n", 0).replace("0", "-"));
		}
	}

	// Agrega un nuevo usuario al sistema

	public void agregar_usuario() {
		Scanner teclado = new Scanner(System.in);

		try {
			int index = usuarios_busca_espacio();

			if (index == -1) {

				System.out.println("ERROR. Ya que queda espacio para agregar otro usuario");

			} else {

				System.out.println("Ingrese los siguientes datos:");
				System.out.println("Nombre(s):");
				String nombre_usuario = da_formato(teclado.nextLine());
				System.out.println("Primer apellido:");
				String primer_apellido_usuario = da_formato(teclado.nextLine());
				System.out.println("Segundo apellido:");
				String segundo_apellido_usuario = da_formato(teclado.nextLine());
				System.out.println("Nombre de usuario:");
				String user_usuario = teclado.nextLine();
				System.out.println("Direccion:");
				String direccion_usuario = da_formato(teclado.nextLine());
				System.out.println("Telefono:");
				String telefono_usuario = da_formato(teclado.nextLine());
				System.out.println("Correo electronico:");
				String correo_usuario = da_formato(teclado.nextLine());
				System.out.println("Ingresa tu contraseña:");
				String contrasena_usuario = teclado.nextLine();

				// Se crea el usuario

				Usuario nuevo_usuario = new Usuario(genera_id(), nombre_usuario, primer_apellido_usuario, segundo_apellido_usuario, user_usuario, direccion_usuario, telefono_usuario, correo_usuario, contrasena_usuario);
				usuarios[index] = nuevo_usuario;

				System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
				System.out.println("Registro realizado exitosamente");
				System.out.println(String.format("%050d", 0).replace("0", "-"));
				nuevo_usuario.muestra_usuario_con_libros();
				System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
				guardar();
			}

		} catch (Exception e) {
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("ERROR. Ha ocurrido un error. Vuelve a intentarlo");
			System.out.println(String.format("%050\n\n", 0).replace("0", "-"));
		}
	}

	// Muestra todos los usuarios en forma de tabla

	public void muestra_usuario_sin_libros() {
		System.out.println(String.format("\n%-7s | %-40s | %-40s | %-10s | %-40s | %-10s", "Id", "Nombre Completo", "Direccion", "Telefono", "E-mail", "Libros"));
		System.out.println(String.format("%0165d", 0).replace("0","-"));
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null) {
				usuarios[i].muestra_usuario_sin_libros();
				System.out.println(String.format("%0165d", 0).replace("0", "-"));
			}
		}
	}

	// Muestra todos los usuarios con informacion de sus libros

	public void muestra_usuario_con_libros() {
		System.out.println(String.format("\n%050d", 0).replace("0","-"));
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null) {
				usuarios[i].muestra_usuario_con_libros();
				System.out.println(String.format("%050d", 0).replace("0", "-"));
			}
		}
	}

	// Buscar usuario por id

	public int buscar_usuario(int id_usuario) {
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null && usuarios[i].getId() == id_usuario) {
				return i;
			}
		}

		return -1;
	}

	// Buscar usuario por nombre completo

	public int buscar_usuario(String nombre_usuario, String primer_apellido_usuario, String segundo_apellido_usuario) {
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null && usuarios[i].getNombre().equals(da_formato(nombre_usuario)) && usuarios[i].getPrimer_apellido().equals(da_formato(primer_apellido_usuario)) && usuarios[i].getSegundo_apellido().equals(da_formato(segundo_apellido_usuario))) {
				return i;
			}
		}

		return -1;
	}

	// Buscar bibliotecario por nombre de usuario

	public int buscar_biblio(String user_biblio) {
		for (int i = 0; i < bibliotecarios.length; i++) {
			if (bibliotecarios[i] != null && bibliotecarios[i].getNombre().equals(da_formato(user_biblio))) {
				return i;
			}
		}

		return -1;
	}

	// Buscar bibliotecario por su id

	public int buscar_biblio(int idbibliotecario) {
		for (int i = 0; i < bibliotecarios.length; i++) {
			if (bibliotecarios[i] != null && bibliotecarios[i].getId() == idbibliotecario) {
				return i;
			}
		}

		return -1;
	}

	// Verificar contraseña de bibliotecario a partir de user

	public int verificar_contrasena_biblio(String user_biblio, String contrasena_biblio) {
		String user_prin = "MelaniMucino";
		String contrasena_prin = "mel0104";
		for (int i = 0; i < bibliotecarios.length; i++) {
			if (user_biblio.equals(user_prin) && contrasena_biblio.equals(contrasena_prin)) {
				return i;
			} else if (bibliotecarios[i] != null && bibliotecarios[i].getContrasena().equals(contrasena_biblio) && bibliotecarios[i].getUser().equals(user_biblio)) {
				return i;
			}
		}

		return -1;
	}

	// Verificar contraseña de usuario 

	public int verificar_contrasena_usu(String user_usuario, String contrasena_usuario) {
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null && usuarios[i].getContrasena().equals(contrasena_usuario) && usuarios[i].getUser().equals(user_usuario)) {
				return i;
			}
		}

		return -1;
	}


	// Elimina un usuario a partir de id

	public void eliminar_usuario(int idusuario) {
		int index = buscar_usuario(idusuario);
		if (index == -1) {
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("ERROR. Este usuario no existe");
			System.out.println(String.format("%050\n\n", 0).replace("0", "-"));
		} else {
			usuarios[index] = null;
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("El usuario ha sido eliminado");
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			guardar();
		}
	}

	// Elimina un usuario a partir del nombre completo

	public void eliminar_usuario(String nombre_usuario, String primer_apellido_usuario, String segundo_apellido_usuario) {
		int index = buscar_usuario(da_formato(nombre_usuario), da_formato(primer_apellido_usuario), da_formato(segundo_apellido_usuario));
		if (index == -1) {
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("ERROR. El usuario no existe");
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
		} else {
			usuarios[index] = null;
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("El usuario ha sido eliminado");
			System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
			guardar();
		}
	}

	//====================== Libros ============================== //

	// Buscar un espacio en libros

	private int libro_busca_espacio() {
		for (int i = 0; i < libros.length; i++) {
			if (libros[i] == null) {
				return i;
			}
		}

		return -1;
	}

	// Agregar un libro 

	public void agregar_libro() {
		Scanner teclado = new Scanner(System.in);
		try {
			int index = libro_busca_espacio();
			if (index == -1) {
				System.out.println("ERROR. Ya no hay espacio para registrar este libro");
			} else {
				System.out.println("Ingresa la siguiente informacion:");
				System.out.println("Titulo:");
				String titulo_libro = da_formato(teclado.nextLine());
				System.out.println("Autor: ");
				String autor_libro = da_formato(teclado.nextLine());
				System.out.println("Editorial: ");
				String editorial_libro = da_formato(teclado.nextLine());
				System.out.println("Numero de paginas:");
			    int no_paginas_libro = teclado.nextInt();
				System.out.println("Numero de ejemplares:");
				int no_ejemplares_libro = teclado.nextInt();
				int ejemplares_disp_libro = no_ejemplares_libro;

				Libro nuevo_libro = new Libro(genera_id(), titulo_libro, autor_libro, editorial_libro, no_paginas_libro, no_ejemplares_libro, ejemplares_disp_libro);
				libros[index] = nuevo_libro;

				System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
				System.out.println("Registro de libro exitoso");
				System.out.println(String.format("%050d", 0).replace("0", "-"));
				nuevo_libro.muestra_libro();
				System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
				guardar();
			}

		} catch (Exception e) {
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("ERROR. Ha ocurrido un error. Vuelve a intentarlo");
			System.out.println(e.getMessage());
			System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
		}
	}

	// Muestra todos los libros

	public void muestra_todos_libros() {
		System.out.println(String.format("\n%-7s | %-50s | %-30s | %-20s | %-5s | %-3s", "Id", "Titulo", "Autor", "Editorial", "Numero de paginas", "Numero de ejemplares"));
		System.out.println(String.format("%0165d", 0).replace("0", "-"));
		for (int i = 0; i < libros.length; i++) {
			if (libros[i] != null) {
				libros[i].muestra_todos_libros();
				System.out.println(String.format("%0165d", 0).replace("0", "-"));
			}
		}
	}

	// Busca libro por su titulo

	public int buscar_libro(String titulo_libro) {
		for (int i = 0; i < libros.length; i++) {
			if (libros[i] != null && libros[i].getTitulo().equals(da_formato(titulo_libro))) {
				return i;
			}
		}

		return -1;
	}


	// Busca libro por su identificador

	public int buscar_libro(int id_libro) {
		for (int i = 0; i < libros.length; i++) {
			if (libros[i] != null && libros[i].getId() == id_libro) {
				return i;
			}
		}

		return -1;
	}

	// Eliminar un libro por su identificador

	public void eliminar_libro(int id_libro) {
		int index = buscar_libro(id_libro);
		if (index == -1) {
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("ERROR. Este libro no existe");
			System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
		} else {
			libros[index] = null;
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("El libro ha sido eliminado");
			System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
			guardar();
		}
	}

	// Eliminar un libro por su titulo

	public void eliminar_libro(String titulo_libro) {
		int index = buscar_libro(da_formato(titulo_libro));
		if (index == -1) {
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("ERROR. Este libro no existe");
			System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
		} else {
			libros[index] = null;
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("El libro ha sido eliminado");
			System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
			guardar();
		}
	}

	// Modificar numero de ejemplares disponibles por préstamo por id

	public void reducir_ejemplares(int titulo_libro) {
		int index = buscar_libro(titulo_libro);
		if (index == -1) {
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("ERROR. Este libro no existe");
			System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
		} else {
			libros[index].reducir_ejemplares_prestamo();
			guardar();
		}
	}


	// Registrar un prestamo por id

	public void agregar_prestamo(int id_usuario) {
		int index_usuario = buscar_usuario(id_usuario);
		if (index_usuario == -1) {
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("ERROR. El usuario no existe");
			System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
		} else {
			int index_prestamo = usuarios[index_usuario].prestamo_espacio_usuario();
			if (index_prestamo == -1) {
				System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
				System.out.println("ERROR. Ya no quedan mas prestamos disponibles para este usuario");
				System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
			} else {
				Usuario s = usuarios[index_usuario];
			    Scanner teclado = new Scanner(System.in);
			    System.out.println("Ingresa el titulo del libro que se desea tomar prestamo");
			    String tit_libro = da_formato(teclado.nextLine());
			    int index_libro = buscar_libro(tit_libro);
			    if (index_libro == -1) {
				    System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
				    System.out.println("ERROR. Este libro no existe");
				    System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
			    } else {
				    LocalDate prestamo = LocalDate.now();
				    LocalDate devolucion = prestamo.plusDays(5);
				    int id_libro = libros[index_libro].getId();
				    String titulo_libro = libros[index_libro].getTitulo();
				    String user_usu = usuarios[index_usuario].getUser();
				    libros[index_libro].reducir_ejemplares_prestamo();
				    usuarios[index_usuario].registrar_prestamo(id_libro, titulo_libro, user_usu);
				    guardar();
			    }
			}
		}
	}

		public void devolucion (int id_usuario, String titulo_libro) {
		int index_usuario = buscar_usuario(id_usuario);
		Scanner teclado = new Scanner(System.in);

		if (index_usuario == -1) {
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("ERROR. El usuario no existe");
			System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
		} else {
			int index_libro = buscar_libro(titulo_libro);
		if (index_libro == -1) {
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("ERROR. Este libro no existe");
			System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
		} else {
			int index_registro = usuarios[index_usuario].buscar_prestamo(usuarios[index_usuario].getUser(), da_formato(titulo_libro));
		if (index_registro == -1) {
			System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
			System.out.println("ERROR. No se encontro el registro del prestamo.");
			System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
		} else {
			//Usuario s = usuarios[index_usuario];
			//int index_prestamo = s.buscar_prestamo(usuarios[index_usuario].getUser(), da_formato(titulo_libro));
			    
			    LocalDate fecha_registro = prestamos[index_registro].getFecha_devolucion();
			    LocalDate fecha_devolucion = LocalDate.now();
			    if ((fecha_registro.isEqual(fecha_devolucion) || fecha_devolucion.isBefore(fecha_registro)) && prestamos[index_registro] != null) {
			    	libros[index_libro].aumentar_ejemplares_devolucion();
			    	//prestamos[index_prestamo].eliminar_prestamo();
			    	prestamos[index_registro] = null;

			    	System.out.println("Se ha completado la devolucion exitosamente.");

			    	guardar();
			    }
			}	    
		}
	}
}

	// Reporte de existencia 

	public void reporte_existencias() {
		System.out.println(String.format("%090d", 0).replace("0", "-"));
        System.out.println(String.format("\n%-7s | %-50s | %-3s", "Id", "Titulo", "Numero de ejemplares"));
        System.out.println(String.format("%090d", 0).replace("0", "-"));
		for (int i = 0; i < libros.length; i++) {
			if (libros[i] != null) {
				libros[i].reporte_existencias();
				System.out.println(String.format("%090d", 0).replace("0", "-"));
			}
		}
	}

	// Reporte de disponibilidad

	public void reporte_disponibilidad() {
		System.out.println(String.format("%090d", 0).replace("0", "-"));
		System.out.println(String.format("\n%-7s | %-50s | %-3s", "Id", "Titulo", "Libros disponibles"));
		System.out.println(String.format("%090d", 0).replace("0", "-"));
		for (int i = 0; i < libros.length; i++) {
			if (libros[i] != null) {
				libros[i].reporte_disponibilidad();
				System.out.println(String.format("%090d", 0).replace("0", "-"));
			}
		}
	}

	// Reporte de prestamos

	public void reporte_prestamos() {
		System.out.println(String.format("%090d", 0).replace("0", "-"));
		System.out.println(String.format("\n%-7s | %-20s | %-50s | %-10s | %-10s", "Id libro", "Usuario", "Titulo", "Fecha de prestamo", "Fecha de devolucion"));
		System.out.println(String.format("%090d", 0).replace("0", "-"));
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null) {
				usuarios[i].reporte_prestamos();
				System.out.println(String.format("%090d", 0).replace("0", "-"));
			}
		}
	}

	/*public void eliminar_prestamo(int id_usuario, String titulo_libro) {
	int index_usu = buscar_usuario(id_usuario);	
    int index = usuarios[index_usu].buscar_prestamo(id_usuario, titulo_libro);
    
    if (index == -1) {
      System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
      System.out.println("ERROR. No se encontró el registro del préstamo.");
      System.out.println(String.format("%050\n\n", 0).replace("0", "-"));
    } else {
      int index_usuario = buscar_usuario(id_usuario);
      int index_prestamo = usuarios[index_usuario].buscar_prestamo(id_usuario, da_formato(titulo_libro));
      prestamos[index_prestamo] = null;
      System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
      System.out.println("Tienes disponible el espacio para un prestamo");
      System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));

      guardar();
    }
  }*/
	
}
