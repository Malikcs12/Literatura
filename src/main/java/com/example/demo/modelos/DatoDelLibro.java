//package com.example.demo.modelos;
//
//import com.fasterxml.jackson.annotation.JsonAlias;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import java.util.List;
//
//@JsonIgnoreProperties(ignoreUnknown = true)
//public record DatoDelLibro(
//        @JsonAlias("title") String title,
//        @JsonAlias("authors") List<DatoDelAutor> author,
//        @JsonAlias("languages") List<String> languages,
//        @JsonAlias("download_count") Double downloadCount
//) {
//}

///////////////////////////////7
package com.example.demo.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatoDelLibro(
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<DatoDelAutor> author,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Double downloadCount
) {
}
