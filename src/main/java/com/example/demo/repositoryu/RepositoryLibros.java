//package com.example.demo.peticion.api;
//
//import com.example.demo.modelos.Libros;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface RepositoryLibros extends JpaRepository<Libros, Long> {
//    List<Libros> findByTitleContainsIgnoreCase(String title);
//    @Query(value = "SELECT * FROM book b WHERE b.languages LIKE %:language%", nativeQuery = true)
//    List<Libros> findByLanguages(String language);
//}
/////////////////////////////////////////////////////
package com.example.demo.repositoryu;

import com.example.demo.modelos.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryLibros extends JpaRepository<Libros, Long> {
    List<Libros> findByTitleContainsIgnoreCase(String title);

    @Query(value = "SELECT * FROM book b WHERE b.languages LIKE %:language%", nativeQuery = true)
    List<Libros> findByLanguages(String language);
}
