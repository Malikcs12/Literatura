//package com.example.demo.modelos;
//
//import jakarta.persistence.*;
//
//import java.security.PrivateKey;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//public class Autores {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    private Integer birthYear;
//    private Integer deahtYear;
//    @ManyToMany(mappedBy = "autores", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    private List<Libros> libros = new ArrayList<>();
//
//    public Autores(){
//
//    }
//    public Autores(String name, Integer birthYear, Integer deahtYear) {
//        this.name = name;
//        this.birthYear = birthYear;
//        this.deahtYear = deahtYear;
//    }
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getBirthYear() {
//        return birthYear;
//    }
//
//    public void setBirthYear(Integer birthYear) {
//        this.birthYear = birthYear;
//    }
//
//    public Integer getDeahtYear() {
//        return deahtYear;
//    }
//
//    public void setDeahtYear(Integer deahtYear) {
//        this.deahtYear = deahtYear;
//    }
//
//    public List<Libros> getLibros() {
//        return libros;
//    }
//
//    public void setLibros(List<Libros> libros) {
//        this.libros = libros;
//        for (Libros libros1 : libros){
//            libros1.getDatoDelAutors().add(this);
//        }
//    }
//
//    //    public void setLibros(List<Libros> libros) {
////        this.libros = libros;
////    }
//
//
//    public void setAutores(String name){
//        this.name = name;
//    }
//    @Override
//    public  String toString(){
//        return  "Autores [id = " + id + ", name=" + name + ", brith Year" + birthYear + ",  deaht Year = " + deahtYear + "]";
//    }
//}
/////////////////////////////////////////////
package com.example.demo.modelos;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @ManyToMany(mappedBy = "autores", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Libros> libros = new ArrayList<>();

    public Autores() {
    }

    public Autores(String name, Integer birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Libros> getLibros() {
        return libros;
    }

    public void setLibros(List<Libros> libros) {
        this.libros = libros;
        for (Libros libro : libros) {
            libro.getAutores().add(this);
        }
    }

    @Override
    public String toString() {
        return "Autores [id=" + id + ", name=" + name + ", birthYear=" + birthYear + ", deathYear=" + deathYear + "]";
    }
}
