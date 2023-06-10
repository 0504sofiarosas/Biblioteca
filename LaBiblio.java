import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Equipo
 */
public class LaBiblio {

  /**
   * @param args the command line arguments
   */

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        Biblioteca c = new Biblioteca();
        int op = -1;
        int id;
        String nombre_usuar, nombre_mas, primer_ap, segundo_ap, tel, correo;

        // Si existe archivo con información guaradada, entonces lo cargamos.
        
        File archivo = new File("labiblio.dat");
        if (archivo.exists()) {
           c = c.cargar();
        }

        // En otro caso, se entiende que es la primera vez que se ejecuta el programa
        // y se inicializa desde cero.

        System.out.println("\n\n--------------------------------------------------");
        System.out.println("               Bienvendo a LaBiblio               ");
        System.out.println("--------------------------------------------------");

        do {
            System.out.println("\n\nCon que entidad vas a trabajar?\n");

            System.out.println("  1. Bibliotecario");
            System.out.println("  2. Usuario");
            System.out.println("  3. Registrarse");
            System.out.println("  0. Salir\n\n");
            System.out.print("Elige una opcion (0-3): ");

            op = Integer.parseInt(teclado.nextLine());

            switch (op) {

            case 0:

                System.out.print("\n\nSeguro que quiere salir? (Y/N): ");
                char exit = teclado.nextLine().toUpperCase().charAt(0);
                if (exit == 'Y' || exit == 'S') {
                    System.out.println("\n\n Buen trabajo.");
                  } else {
                    op = -1;
                  }
                  break;

            case 1:

            System.out.println("Ingresa tu nombre de usuario:");
            String nombre_usu = teclado.nextLine();
            System.out.println("Ingresa tu contrasena:");
            String contra_usu = teclado.nextLine();
            
            if (c.verificar_contrasena_biblio(nombre_usu, contra_usu) == -1) {
                System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
                System.out.println("ERROR. Contrasena o Nombre de usuario incorrectos.");
                System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
            } else {
                
                System.out.println("\n\nQue accion quieres realizar? \n");
                System.out.println("  1. Agregar un nuevo libro");
                System.out.println("  2. Dar de baja un libro");
                System.out.println("  3. Reporte de existencias");
                System.out.println("  4. Reporte de disponibilidad");
                System.out.println("  5. Reporte de prestamos");
                System.out.println("  6. Lista de espera");
                System.out.println("  7. Registrar un bibliotecario");
                System.out.println("  0. Regresar al menu anterior\n\n");
                System.out.print("Elige una opcion (0-7): ");

                op = Integer.parseInt(teclado.nextLine());

                switch (op) {

                    case 0:
                        op = -1;
                        break;

                    case 1:
                        c.agregar_libro();
                        break;

                    case 2:
                        System.out.println("\n\nElige una opcion: \n");
                        System.out.println("  1.Eliminar por ID");
                        System.out.println("  2.Eliminar por titulo del libro\n\n");
                        System.out.println("Elige una opcion (1-2): ");
                        int el_li = Integer.parseInt(teclado.nextLine());
                        if (el_li == 1) {
                            System.out.print("\n\nIngresa el ID del libro:");
                            id = teclado.nextInt();
                            c.eliminar_libro(id);
                        } else if (el_li == 2) {
                            System.out.println("\n\nIngresa el titulo del libro:");
                            String titulo = teclado.nextLine();
                            c.eliminar_libro(titulo);
                        }
                        break;

                    case 3:
                        c.reporte_existencias();
                        break;

                    case 4:
                        c.reporte_disponibilidad();
                        break;

                    case 5:
                        c.reporte_prestamos();
                        break;

                    case 6:
                        break;

                    case 7:
                        c.agregar_bibliotecario();
                        break;    
                }
            
            }    
             
                break;

            case 2:

            System.out.println("Ingresa tu nombre de usuario:");
            String user_biblio = teclado.nextLine();
            System.out.println("Ingresa tu contraseña: ");
            String contra_biblio = teclado.nextLine();

            if (c.verificar_contrasena_usu(user_biblio, contra_biblio) == -1) {
                System.out.println(String.format("\n\n%050d", 0).replace("0", "-"));
                System.out.println("ERROR. Contrasena o Nombre de usuario incorrectos.");
                System.out.println(String.format("%050d\n\n", 0).replace("0", "-"));
            } else {
                System.out.println("\n\nQue accion quieres realizar? \n");
                System.out.println("  1. Mostrar todos libros");
                System.out.println("  2. Solicitar prestamo");
                System.out.println("  3. Devolucion");
                System.out.println("  4. Solicitar una prorroga");
                System.out.println("  0. Regresar al menu anterior\n\n");
                System.out.print("Elige una opcion (0-4): ");

                op = Integer.parseInt(teclado.nextLine());

                switch (op) {
                    case 1:
                       c.muestra_todos_libros();
                        break;
                    case 2:
                       System.out.println("Ingresa el Id del usuarioque solicita el prestamo:");
                       int id_libro = teclado.nextInt();
                       c.agregar_prestamo(id_libro);
                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 0:
                        op = -1;
                        break;
                }
            }

                    break;

            case 3:
                c.agregar_usuario();
            }



        } while (op != 0);
    }

}