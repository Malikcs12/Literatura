//package com.example.demo.principal;
//
//import com.example.demo.modelos.Autores;
//import com.example.demo.modelos.Dato;
//import com.example.demo.modelos.DatoDelLibro;
//import com.example.demo.modelos.Libros;
//import com.example.demo.peticion.api.ConvercionDeDatosDeApi;
//import com.example.demo.peticion.api.PeticionApi;
//import com.example.demo.repositoryu.RepositoryAutores;
//import com.example.demo.repositoryu.RepositoryLibros;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Scanner;
//import java.util.stream.Collectors;
//
//@Service
//public class Principal {
////clase scaner para poder interacturar con el usuario
//    Scanner busquedaUsuario = new Scanner(System.in);
//
//    //Mnadara la informacion de la api
//    private PeticionApi peticionApi = new PeticionApi();
//    private final String URL_BASE = "https://gutendex.com/books/?search=";
//
//    private ConvercionDeDatosDeApi.ConvertData convertData = new ConvercionDeDatosDeApi.ConvertData();
//
//
//    //mandamos el repositorio de libros donde tendremos la lista
//    @Autowired
//    private RepositoryLibros repositoryLibros;
//    @Autowired
//    RepositoryAutores repositoryAutores;
//
//    public Principal (RepositoryLibros repositoryLibros, RepositoryAutores repositoryAutores){
//        this.repositoryLibros = repositoryLibros;
//        this.repositoryAutores = repositoryAutores;
//    }
//
//    //menu del usuario
//    public void menuDeUsuario(){
//        var opcion = -1;
//        while (opcion != 0){
//            var menu = """
//                    ******************************************
//                    ***Bienvenido*****************************
//					Elija la opcion a través de su numero
//					1 - buscar libro por titulo
//					2 - buscar libro registrados por titulo
//					3 - listar libros registrados
//					4 - listar autores registrados
//					5 - listar autores vivos en un determinado año
//					6 - listar libros por idioma
//					**
//					0 - Salir
//					*********************************************
//					***Hasta luego*******************************
//                    """;
//            System.out.println(menu);
//            try{
//                opcion = busquedaUsuario.nextInt();
//                busquedaUsuario.nextLine();
//            }catch (Exception e ){
//                System.out.println("Opcion invalida");
//                busquedaUsuario.nextLine();
//                continue;
//            }
//            switch (opcion) {
//                case 1:
//                    busquedaGeneral();
//                    break;
//                case 2:
//                    findByTitle();
//                    break;
//                case 3:
//                    findAllBooks();
//                    break;
//                case 4:
//                    findAllPersons();
//                    break;
//                case 5:
//                    findPersonByYear();
//                    break;
//                case 6:
//                    findLanguages();
//                    break;
//            }
//        }
//    }
//
//
//    private void busquedaGeneral() {
//        DatoDelLibro data = getDatoDelLibro();
//
//        Libros boo2 = new Libros();
//        List<Autores> persons = null;
//        try {
//            persons = data.author().stream().map(p -> new Autores(p.name(), p.birthYear(), p.deathYear()))
//                    .collect(Collectors.toList());
//        } catch (NullPointerException e) {
//            System.out.println("Libro no encontrado o nulls");
//            menuDeUsuario();
//        }
//
//        List<Autores> person2 = null;
//        for (int i = 0; i < persons.size(); i++) {
//            String name = persons.get(i).getName();
//            person2 = repositoryAutores.findByName(name);
//        }
//        if (person2.isEmpty()) {
//            for (Autores person : persons) {
//                repositoryAutores.save(person);
//            }
//        } else {
//            for (Autores person : person2) {
//                repositoryAutores.save(person);
//                persons = person2;
//            }
//        }
//
//        boo2.setTitle(data.title());
//        boo2.setAutores(persons);
//        boo2.setLenguaje(data.languages());
//        boo2.setContenido(data.downloadCount());
//
//        List<Libros> findBook = repositoryLibros.findByTitleContainsIgnoreCase(boo2.getTitle());
//
//        if (findBook.isEmpty()) {
//            repositoryLibros.save(boo2);
//        } else {
//            System.out.println("El libro ya esta en la base de datos");
//        }
//
//    }
//
//    // Se obtiene los datos de la API y se convierte a la clase DataBook
//    private DatoDelLibro getDatoDelLibro() {
//        System.out.println("Escribe el nombre del libro");
//        var nameBook = busquedaUsuario.nextLine();
//        var json = peticionApi.getData(URL_BASE + nameBook.replace(" ", "%20"));
//        var data = convertData.getData(json, Dato.class);
//        Optional<DatoDelLibro> datos = data.resultados().stream()
//                .filter(l -> l.title().toUpperCase().contains(nameBook.toUpperCase())).findFirst();
//        DatoDelLibro libro2 = null;
//
//        if (datos.isPresent()) {
//            System.out.println("Libro encontrado");
//            libro2 = new DatoDelLibro(datos.get().title(), datos.get().author(), datos.get().languages(),
//                    datos.get().downloadCount());
//        } else {
//            System.out.println("Libro no encontrado");
//        }
//
//        return libro2;
//    }
//
//    // Se busca dentro de la base datos por nombre de la clase Book
//    private void findByTitle() {
//        System.out.println("Escriba el nombre del libro que desea buscar");
//        String title = busquedaUsuario.nextLine();
//        List<Libros> libros1 = repositoryLibros.findByTitleContainsIgnoreCase(title);
//        System.out.println(libros1);
//
//    }
//
//    // Se busca dentro de la base dato la clase Book
//    private void findAllBooks() {
//        List<Libros> todosLosLibros = repositoryLibros.findAll();
//        extractedBook(todosLosLibros);
//
//    }
//
//    // Se busca dentro de la base dato la clase Person
//    private void findAllPersons() {
//        List<Autores> allAutores = repositoryAutores.findAll();
//        extractedPerson(allAutores);
//    }
//
//    //Busca dentro de la base de datos por  año para determinar si estaba vivo
//    private void findPersonByYear() {
//        System.out.println("Ingresa el año vivo del autor(es) que decea buscar");
//        Integer year = busquedaUsuario.nextInt();
//        List<Autores> allPersons = repositoryAutores.findYear(year);
//        extractedPerson(allPersons);
//    }
//
//    //Busca dentro de la base de datos los libros por idioma
//    private void findLanguages() {
//        System.out.println("""
//				Escriba el idioma del libro que desea buscar:
//				es- español
//				en- ingles
//				fr- francés
//				pt- portugués
//				""");
//        String lang = busquedaUsuario.nextLine();
//        List<Libros> allbooks = repositoryLibros.findByLanguages(lang);
//        if (allbooks.isEmpty()) {
//            System.out.println("No hay libros en ese idioma");
//        }
//        extractedBook(allbooks);
//    }
//
//    //Metodo para mostrar la informaion de libros en pantalla
//    private void extractedBook(List<Libros> allbooks) {
//        for (Libros b : allbooks) {
//
//            String name = b.getAutores().stream().map(Autores::getName).collect(Collectors.joining(", "));
//            String lenguaje = b.getLenguaje().stream().collect(Collectors.joining(", "));
//            String print = String.format("""
//					----- LiBRO -----
//					Titulo: %s
//					Autor: %s
//					Idioma: %s
//					Numero de descargas: %.0f
//					------------------
//					""", b.getTitle(), name, lenguaje, b.getContenido());
//            System.out.println(print);
//        }
//    }
//
//    //Metodo para mostrar la informaion de personas en pantalla
//    private void extractedPerson(List<Autores> allPersons) {
//        for (Autores p : allPersons) {
//            String book = p.getLibros().stream().map(Libros::getTitle).collect(Collectors.joining(", "));
//
//            String print = String.format("""
//					-----------------
//					Autor: %s
//					Fecha de nacimiento: %d
//					Fecha de fallecimiento: %d
//					Libros: %s
//					------------------
//					""", p.getName(), p.getBirthYear(), p.getDeahtYear(), book);
//            System.out.println(print);
//        }
//
//    }
//    }
//
//}
////////////////////////////////
package com.example.demo.principal;

import com.example.demo.modelos.*;
import com.example.demo.peticion.api.ConvercionDeDatosDeApi;
import com.example.demo.peticion.api.PeticionApi;
import com.example.demo.repositoryu.RepositoryAutores;
import com.example.demo.repositoryu.RepositoryLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class Principal {
    // Clase Scanner para interactuar con el usuario
    private final Scanner busquedaUsuario = new Scanner(System.in);

    // Manejara la informacion de la API
    private final PeticionApi peticionApi = new PeticionApi();
    private final String URL_BASE = "https://gutendex.com/books/?search=";

    private final ConvercionDeDatosDeApi.ConvertData convertData = new ConvercionDeDatosDeApi.ConvertData();

    // Repositorios de libros y autores
    @Autowired
    private RepositoryLibros repositoryLibros;

    @Autowired
    private RepositoryAutores repositoryAutores;

    public Principal(RepositoryLibros repositoryLibros, RepositoryAutores repositoryAutores) {
        this.repositoryLibros = repositoryLibros;
        this.repositoryAutores = repositoryAutores;
    }

    // Menu del usuario
    public void menuDeUsuario() {
        int opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ******************************************
                    *** Bienvenido ****************************
                    Elija la opción a través de su número
                    1 - Buscar libro por título
                    2 - Buscar libro registrados por título
                    3 - Listar libros registrados
                    4 - Listar autores registrados
                    5 - Listar autores vivos en un determinado año
                    6 - Listar libros por idioma
                    **
                    0 - Salir
                    *********************************************
                    *** Hasta luego *******************************
                    """;
            System.out.println(menu);
            try {
                opcion = busquedaUsuario.nextInt();
                busquedaUsuario.nextLine(); // Consumir la nueva línea
            } catch (Exception e) {
                System.out.println("Opción inválida. Por favor, intente de nuevo.");
                busquedaUsuario.nextLine(); // Consumir la nueva línea
                continue;
            }
            switch (opcion) {
                case 1 -> busquedaGeneral();
                case 2 -> findByTitle();
                case 3 -> findAllBooks();
                case 4 -> findAllPersons();
                case 5 -> findPersonByYear();
                case 6 -> findLanguages();
                case 0 -> System.out.println("Saliendo del sistema. ¡Hasta luego!");
                default -> System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private void busquedaGeneral() {
        DatoDelLibro data = getDataBook();
        if (data == null) {
            System.out.println("No se encontró el libro.");
            return;
        }

        Libros libro = new Libros();
        List<Autores> autores = data.author().stream()
                .map(p -> new Autores(p.name(), p.birthYear(), p.deathYear()))
                .collect(Collectors.toList());

        for (Autores autor : autores) {
            List<Autores> autorExistente = repositoryAutores.findByName(autor.getName());
            if (autorExistente.isEmpty()) {
                repositoryAutores.save(autor);
            } else {
                autores = autorExistente;
            }
        }

        libro.setTitle(data.title());
        libro.setAutores(autores);
        libro.setLenguaje(data.languages());
        libro.setContenido(data.downloadCount());

        List<Libros> librosExistentes = repositoryLibros.findByTitleContainsIgnoreCase(libro.getTitle());
        if (librosExistentes.isEmpty()) {
            repositoryLibros.save(libro);
        } else {
            System.out.println("El libro ya está en la base de datos");
        }
    }

    // Se obtiene los datos de la API y se convierte a la clase DataBook
    private DatoDelLibro getDataBook() {
        System.out.println("Escribe el nombre del libro");
        var nameBook = busquedaUsuario.nextLine();
        var json = peticionApi.getData(URL_BASE + nameBook.replace(" ", "%20"));
        var data = convertData.getData(json, Datos.class);
        Optional<DatoDelLibro> datos = data.resultados().stream()
                .filter(l -> l.title().toUpperCase().contains(nameBook.toUpperCase())).findFirst();
        DatoDelLibro book2 = null;

        if (datos.isPresent()) {
            System.out.println("Libro encontrado");
            book2 = new DatoDelLibro(datos.get().title(), datos.get().author(), datos.get().languages(),
                    datos.get().downloadCount());
        } else {
            System.out.println("Libro no encontrado");
        }

        return book2;
    }


    // Se busca dentro de la base datos por nombre de la clase Book
    private void findByTitle() {
        System.out.println("Escriba el nombre del libro que desea buscar:");
        String title = busquedaUsuario.nextLine();
        List<Libros> libros = repositoryLibros.findByTitleContainsIgnoreCase(title);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
        } else {
            extractedBook(libros);
        }
    }

    // Se busca dentro de la base dato la clase Book
    private void findAllBooks() {
        List<Libros> todosLosLibros = repositoryLibros.findAll();
        extractedBook(todosLosLibros);
    }

    // Se busca dentro de la base dato la clase Person
    private void findAllPersons() {
        List<Autores> allAutores = repositoryAutores.findAll();
        extractedPerson(allAutores);
    }

    // Busca dentro de la base de datos por año para determinar si estaba vivo
    private void findPersonByYear() {
        System.out.println("Ingresa el año en el que el autor(es) estaba(n) vivo(s):");
        int year = busquedaUsuario.nextInt();
        busquedaUsuario.nextLine(); // Consumir la nueva línea
        List<Autores> autores = repositoryAutores.findYear(year);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año.");
        } else {
            extractedPerson(autores);
        }
    }

    // Busca dentro de la base de datos los libros por idioma
    private void findLanguages() {
        System.out.println("""
            Escriba el idioma del libro que desea buscar:
            es- español
            en- inglés
            fr- francés
            pt- portugués
        """);
        String lang = busquedaUsuario.nextLine();
        List<Libros> libros = repositoryLibros.findByLanguages(lang);
        if (libros.isEmpty()) {
            System.out.println("No hay libros en ese idioma.");
        } else {
            extractedBook(libros);
        }
    }

    // Método para mostrar la información de libros en pantalla
    private void extractedBook(List<Libros> libros) {
        for (Libros libro : libros) {
            String autores = libro.getAutores().stream()
                    .map(Autores::getName)
                    .collect(Collectors.joining(", "));
            String idiomas = String.join(", ", libro.getLenguaje());
            String info = String.format("""
                ----- LIBRO -----
                Título: %s
                Autor(es): %s
                Idioma(s): %s
                Número de descargas: %.0f
                -----------------
            """, libro.getTitle(), autores, idiomas, libro.getContenido());
            System.out.println(info);
        }
    }

    // Método para mostrar la información de autores en pantalla
    private void extractedPerson(List<Autores> autores) {
        for (Autores autor : autores) {
            String libros = autor.getLibros().stream()
                    .map(Libros::getTitle)
                    .collect(Collectors.joining(", "));
            String info = String.format("""
                -----------------
                Autor: %s
                Fecha de nacimiento: %d
                Fecha de fallecimiento: %d
                Libros: %s
                -----------------
            """, autor.getName(), autor.getBirthYear(), autor.getDeathYear(), libros);
            System.out.println(info);
        }
    }
}
