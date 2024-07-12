//package com.example.demo.peticion.api;
//
//import com.example.demo.modelos.Autores;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface RepositoryAutores  extends JpaRepository<Autores, Long> {
//    List<Autores> findByName(String name);
//
//    @Query("SELECT p FROM Person p WHERE p.birthYear <= :year AND p.deahtYear >= :year")
//    List<Autores>findYear(Integer year);
//
//}
//////////////////////////////////////////////////
package com.example.demo.repositoryu;

import com.example.demo.modelos.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryAutores extends JpaRepository<Autores, Long> {
    List<Autores> findByName(String name);

    @Query("SELECT a FROM Autores a WHERE a.birthYear <= :year AND a.deathYear >= :year")
    List<Autores> findYear(@Param("year") Integer year);
}
