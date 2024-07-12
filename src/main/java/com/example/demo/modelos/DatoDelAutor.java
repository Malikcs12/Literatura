//package com.example.demo.modelos;
//
//import com.fasterxml.jackson.annotation.JsonAlias;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//@JsonIgnoreProperties(ignoreUnknown = true)
//public record DatoDelAutor(@JsonAlias("name") String name, @JsonAlias("birth_year") Integer birthYear,
//                         @JsonAlias("death_year") Integer deathYear) {
//}
///////////////////////////+
package com.example.demo.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatoDelAutor(@JsonAlias("name") String name, @JsonAlias("birth_year") Integer birthYear,
                           @JsonAlias("death_year") Integer deathYear) {
}
