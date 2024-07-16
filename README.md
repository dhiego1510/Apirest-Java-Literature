# Literature Manager

Este proyecto es una aplicación Java para gestionar libros y autores de literatura. Utiliza la API de [Gutendex](https://gutendex.com) para buscar libros y autores en línea y guardar los datos en una base de datos local. El proyecto incluye un menú interactivo que permite a los usuarios realizar diversas operaciones, como buscar libros, listar autores y filtrar libros por idioma.

## Características

- **Buscar libros por título:** Permite buscar libros en la API de Gutendex y guardar los resultados en la base de datos.
- **Listar libros registrados:** Muestra todos los libros almacenados en la base de datos.
- **Listar autores registrados:** Muestra todos los autores almacenados en la base de datos.
- **Listar autores vivos en un determinado año:** Filtra y muestra autores que estaban vivos en un año específico.
- **Listar libros por idioma:** Filtra y muestra libros por idioma.

## Requisitos

- Java 11 o superior
- Maven
- Una base de datos compatible con JPA ( PostgreSQL)

Estructura del proyecto
- ** client/LiteratureManager.java: Clase principal que maneja el menú y las interacciones del usuario.
- ** entity/AuthorEntity.java y entity/BookEntity.java: Clases de entidades JPA que representan los autores y los libros en la base de datos.
- ** repository/IAuthorRepository.java y repository/IBookRepository.java: Interfaces de repositorios JPA para acceder a los datos de autores y libros.
- ** service/ApiConsumer.java: Clase que maneja las solicitudes HTTP a la API de Gutendex.
- ** mapper/ConvertData.java: Clase que convierte los datos JSON de la API a objetos Java.
- ** model/Answer.java: Clase modelo que representa la respuesta de la API.
