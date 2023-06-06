import java.io.Serializable;

/**
 * Clase para alamacenar la información de los libros.
 *
 * @author Equipo
 */
public class Libro implements Serializable {

    private String titulo;
    private String autor;
    private String editorial;
    private int no_paginas;
    private int id;
    private int no_ejemplares;

  /**
   * Construye un objeto Libro con todos sus atributos.
   *
   * @param titulo El título del libro.
   * @param autor El autor del libro.
   * @param editorial La editorial del libro.
   * @param no_paginas El número de páginas del libro.
   * @param id El ID del libro.
   * @param no_ejemplares El número de ejemplares del libro. 
   */

    public Libro (int id, String titulo, String autor, String editorial, int no_paginas,  int no_ejemplares) {
    this.titulo = titulo;
    this.autor = autor;
    this.editorial = editorial;
    this.no_paginas = no_paginas;
    this.id = id;
    this.no_ejemplares = no_ejemplares;
   }

   /**
   * Regresa el título del libro.
   *
   * @return El título del libro.
   */
   public String getTitulo() {
    return titulo;
   }

   /**
   * Regresa el autor del libro.
   *
   * @return El autor del libro.
   */
   public String getAutor() {
    return autor;
   }

   /**
   * Regresa la editorial del libro.
   *
   * @return la editorial del libro.
   */
   public String getEditorial() {
    return editorial;
   }

   /**
   * Regresa el número de páginas del libro.
   *
   * @return El número de páginas del libro.
   */
   public int getNo_paginas() {
    return no_paginas;
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
   * Regresa el número de ejemplares del libro en la biblioteca.
   *
   * @return El número de ejemplares del libro en la biblioteca.
   */
   public int getNo_ejemplares(){
    return no_ejemplares;
   }

   public void setNo_ejemplares(int no_ejemplares) {
    this.no_ejemplares = no_ejemplares;
   }

  // Reducir el numero de ejemplares por prestamo

  public void reducir_ejemplares_prestamo(int no_ejemplares) {
    this.no_ejemplares = no_ejemplares - 1;
  }

  // Aumentar el numero de ejemplares por devolucion

  public void aumentar_ejemplares_prestamo(int no_ejemplares) {
    this.no_ejemplares = no_ejemplares + 1;
  }
   
   /**
   * Muestra la información del libro.
   */
   public void muestra_libro() {
    System.out.println(String.format("\t\tTitulo: %s", titulo));
    System.out.println(String.format("\t\tAutor: %s", autor));
    System.out.println(String.format("\t\tEditorial: %s", editorial));
    System.out.println(String.format("\t\tPaginas: %04d", no_paginas));
    System.out.println(String.format("\t\tId: %07d", id));
    System.out.println(String.format("\t\tEjemplares: %03d", no_ejemplares));
   }
}