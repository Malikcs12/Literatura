//package com.example.demo.modelos;
//
//import jakarta.persistence.*;
//import org.apache.catalina.LifecycleState;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Entity
//public class Libros {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String title;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "libro_persona", joinColumns = @JoinColumn(name = "libro_id"),
//            inverseJoinColumns = @JoinColumn(name = "autor_id"))
//    private List<DatoDelAutor> datoDelAutors = new ArrayList<>();
//    private  List<String> lenguaje;
//    private  Double contenido;
//
//    //me falta el constructor basio que ocupa jpa
//    public Libros() {
//    }
//
//    public Libros (String title, List<DatoDelAutor> datoDelAutors, List<String> lenguaje, Double contenido){
//        this.title = title;
//        this.datoDelAutors = datoDelAutors;
//        this.lenguaje = lenguaje;
//        this.contenido = contenido;
//    }
//
//    public  Libros (String title, Double contenido){
//        this.title = title;
//        this.contenido = contenido;
//    }
//    public Libros(DatoDelLibro datoDelLibro){
//        this.title = datoDelLibro.title();
//        this.datoDelAutors = datoDelLibro.author().stream().map(a -> new DatoDelAutor(a.name(), a.birthYear(), a.deathYear()))
//                .collect(Collectors.toList());
//        this.lenguaje = datoDelLibro.languages();
//        this.contenido = datoDelLibro.downloadCount();
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public List<DatoDelAutor> getDatoDelAutors() {
//        return datoDelAutors;
//    }
//
//    public void setDatoDelAutors(List<DatoDelAutor> datoDelAutors) {
//        this.datoDelAutors = datoDelAutors;
//    }
//
//    public List<String> getLenguaje() {
//        return lenguaje;
//    }
//
//    public void setLenguaje(List<String> lenguaje) {
//        this.lenguaje = lenguaje;
//    }
//
//    public Double getContenido() {
//        return contenido;
//    }
//
//    public void setContenido(Double contenido) {
//        this.contenido = contenido;
//    }
//
//    @Override
//    public String toString() {
//        return "title=" + title + ", downloadCount=" + contenido;
//    }
//
//}

//////////////////////////////////////////////////
package com.example.demo.modelos;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autores> autores = new ArrayList<>();

    private List<String> lenguaje;
    private Double contenido;

    public Libros() {
    }

    public Libros(String title, List<Autores> autores, List<String> lenguaje, Double contenido) {
        this.title = title;
        this.autores = autores;
        this.lenguaje = lenguaje;
        this.contenido = contenido;
    }

    public Libros(String title, Double contenido) {
        this.title = title;
        this.contenido = contenido;
    }

    public Libros(DatoDelLibro datoDelLibro) {
        this.title = datoDelLibro.title();
        this.autores = datoDelLibro.author().stream().map(a -> new Autores(a.name(), a.birthYear(), a.deathYear()))
                .collect(Collectors.toList());
        this.lenguaje = datoDelLibro.languages();
        this.contenido = datoDelLibro.downloadCount();
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Autores> getAutores() {
        return autores;
    }

    public void setAutores(List<Autores> autores) {
        this.autores = autores;
    }

    public List<String> getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(List<String> lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Double getContenido() {
        return contenido;
    }

    public void setContenido(Double contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "title=" + title + ", downloadCount=" + contenido;
    }
}
