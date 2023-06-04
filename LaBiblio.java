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
                System.out.println("\n\nQue accion quieres realizar? \n");
                System.out.println("  1. Agregar un nuevo libro");
                System.out.println("  2. Dar de baja un libro");
                System.out.println("  3. Reporte de existencias");
                System.out.println("  4. Reporte de disponibilidad");
                System.out.println("  5. Reporte de prestamos");
                System.out.println("  6. Lista de espera");
                System.out.println("  0. Regresar al menu anterior\n\n");
                System.out.print("Elige una opcion (0-6): ");

                op = Integer.parseInt(teclado.nextLine());

                switch (op) {
                    case 0:
                        op = -1;
                        break;
                    case 1:
                        c.agregar_libro();
                        break;
                    case 2:
                        System.out.print("\n\nIngresa el ID del libro:");
                        id = Integer.parseInt(teclado.nextLine());
                        c.eliminar_libro(id);
                        break;
                    case 3:
                        c.existencia_libros();
                        break;
                    case 4:
                        System.out.print("\n\nIngresa el ID del libro:");
                        id = Integer.parseInt(teclado.nextLine());
                        c.mostrar_usuario_con_libros();
                        break;
                    case 5:
                        //c.muestra_prestamo();
                        break;
                    case 6:
                        break;
                }
                break;
            case 2:
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
                       c.existencia_libros();
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 0:
                        op = -1;
                        break;
                }
                break;
            case 3:
                c.agregar_usuario();
            }
        }while (op != 0);
    }

}