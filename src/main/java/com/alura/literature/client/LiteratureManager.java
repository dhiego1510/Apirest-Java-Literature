package com.alura.literature.client;

import com.alura.literature.entity.AuthorEntity;
import com.alura.literature.entity.BookEntity;
import com.alura.literature.mapper.ConvertData;
import com.alura.literature.model.Answer;
import com.alura.literature.repository.IAuthorRepository;
import com.alura.literature.repository.IBookRepository;
import com.alura.literature.service.ApiConsumer;

import java.util.List;
import java.util.Scanner;

public class LiteratureManager {

  private final String URL_BASE = "https://gutendex.com/books/?search=";
  private Scanner teclado = new Scanner(System.in);
  private ApiConsumer apiConsumer = new ApiConsumer();
  private ConvertData convert = new ConvertData();

  private IBookRepository bookRepository;
  private IAuthorRepository authorRepository;

  public LiteratureManager(IBookRepository bookRepository, IAuthorRepository authorRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
  }

  public void menu() {
    var option = -1;
    while (option != 0) {
      var menu = """
					Elija la opción a traves de su número:
						1.- Buscar libro por titulo
						2.- Lista libros registrados
						3.- Lista autores registrados
						4.- Lista autores vivos en un determinado año
						5.- Listar libros por idioma
						0 - Salir
						""";
      System.out.println(menu);
      option = teclado.nextInt();
      teclado.nextLine();

      switch (option) {
        case 1:
          searchBookWeb();
          break;
        case 2:
          searchBooks();
          break;
        case 3:
          searchAuthors();
          break;
        case 4:
          searchLivingAuthors();
          break;
        case 5:
          searchByLanguage();
          break;
        case 0:
          System.out.println("Adios, Vuelva Pronto...");
          break;
        default:
          System.out.println("Opción inválida");
      }
    }

  }

  private void searchBooks() {

    List<BookEntity> books = bookRepository.findAll();

    if (!books.isEmpty()) {

      for (BookEntity book : books) {
        System.out.println("\n\n---------- LIBROS -------\n");
        System.out.println(" Titulo: " + book.getTitle());
        System.out.println(" Autor: " + book.getAuthor().getName());
        System.out.println(" Idioma: " + book.getLanguage());
        System.out.println(" Descargas: " + book.getDownloads());
        System.out.println("\n-------------------------\n\n");
      }

    } else {
      System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");
    }

  }

  private void searchAuthors() {
    List<AuthorEntity> authors = authorRepository.findAll();

    if (!authors.isEmpty()) {
      for (AuthorEntity author : authors) {
        System.out.println("\n\n---------- Autores -------\n");
        System.out.println(" Nombre: " + author.getName());
        System.out.println(" Fecha de Nacimiento: " + author.getbirthDate());
        System.out.println(" Fecha de Fallecimiento: " + author.getdateOfDeath());
        System.out.println(" Libros: " + author.getBooks().getTitle());
        System.out.println("\n-------------------------\n\n");
      }
    } else {
      System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");

    }

  }

  private void searchLivingAuthors() {
    System.out.println("Escriba el año para en el que vivio: ");
    var anio = teclado.nextInt();
    teclado.nextLine();

    List<AuthorEntity> authors = authorRepository.findForYear(anio);

    if (!authors.isEmpty()) {
      for (AuthorEntity author : authors) {
        System.out.println("\n\n---------- Autores Vivos -------\n");
        System.out.println(" Nombre: " + author.getName());
        System.out.println(" Fecha de nacimiento: " + author.getbirthDate());
        System.out.println(" Fecha de fallecimiento: " + author.getdateOfDeath());
        System.out.println(" Libros: " + author.getBooks().getTitle());
        System.out.println("\n-------------------------\n\n");
      }
    } else {
      System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");

    }
  }

  private void searchByLanguage() {

    var menu = """
				Seleccione un Idioma:
					1.- Español
					2.- Ingles
				
					""";
    System.out.println(menu);
    var idioma = teclado.nextInt();
    teclado.nextLine();

    String selection = "";

    if(idioma == 1) {
      selection = "es";
    } else 	if(idioma == 2) {
      selection = "en";
    }

    List<BookEntity> books = bookRepository.findForLanguaje(selection);

    if (!books.isEmpty()) {

      for (BookEntity book : books) {
        System.out.println("\n\n---------- LIBROS POR IDIOMA-------\n");
        System.out.println(" Titulo: " + book.getTitle());
        System.out.println(" Autor: " + book.getAuthor().getName());
        System.out.println(" Idioma: " + book.getLanguage());
        System.out.println(" Descargas: " + book.getDownloads());
        System.out.println("\n-------------------------\n\n");
      }

    } else {
      System.out.println("\n\n ----- NO SE ENCONTRARON RESULTADOS ---- \n\n");
    }


  }

  private void searchBookWeb() {
    Answer data = getDataSerie();

    if (!data.results().isEmpty()) {

      BookEntity book = new BookEntity(data.results().get(0));
      book = bookRepository.save(book);

    }

    System.out.println("Datos: ");
    System.out.println(data);
  }

  private Answer getDataSerie() {
    System.out.println("Ingresa el nombre del libro que deseas buscar: ");
    var title = teclado.nextLine();
    title = title.replace(" ", "%20");
    System.out.println("Titlulo : " + title);
    System.out.println(URL_BASE + title);
    var json = apiConsumer.getInformation(URL_BASE + title);
    System.out.println(json);
    Answer dates = convert.obtenerDatos(json, Answer.class);
    return dates;
  }

}
