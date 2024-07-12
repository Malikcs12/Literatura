package com.example.demo.main;

import java.util.Scanner;

import com.example.demo.modelos.Datos;
import com.example.demo.peticion.api.ConvercionDeDatosDeApi;
import com.example.demo.peticion.api.PeticionApi;



public class Principal {

    Scanner busquedaUsuario = new Scanner(System.in);
    private PeticionApi getApi = new PeticionApi();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvercionDeDatosDeApi.ConvertData convertData = new ConvercionDeDatosDeApi.ConvertData();

    public void showMenu() {
        var opcion = -1;
        while (opcion != 0) {

            System.out.println("Selecciona una opcion");
            try {
                opcion = busquedaUsuario.nextInt();
                busquedaUsuario.nextLine();
            } catch (Exception e){
                System.out.println("Opción inválida");
                break;
            }
            findBookWeb();
        }
    }

    private void findBookWeb(){
        Datos data = getDatoDelLibro();
        System.out.println("data = " + data);
    }
    private Datos getDatoDelLibro (){
        System.out.println("Escribe el nombre del libro");
        var nameBook = busquedaUsuario.nextLine();
        var json = getApi.getData(URL_BASE + nameBook.replace(" ", "%20"));
        System.out.println(json);
        Datos data = convertData.getData(json, Datos.class);
        return data;
    }
}
