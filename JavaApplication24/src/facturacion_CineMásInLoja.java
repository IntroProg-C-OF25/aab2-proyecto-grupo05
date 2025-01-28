
import java.util.Scanner;

public class facturacion_CineMásInLoja {
    public static void main(String[] args) {
        Scanner tcl = new Scanner(System.in);
        // Declaracion de variables 
        String pelicula;
        // Primero vamos a dar  una introduccion al sistema
        System.out.println("   BIENVENIDO A CINEMAS\nMas cerca de las estrellas");
        //arrego que contenga titulo, horario, sala, precio
        String cartelera[][] = {
            {"Moana 2", "9:20pm, 5:40pm", "Sala 3", "8.0 $"},
            {"Avatar 2", "3:00pm, 7:00pm", "Sala 1", "10.0 $"},
            {"El Rey León", "12:00pm, 6:00pm", "Sala 2", "7.5 $"},
            {"Frozen", "11:00am, 4:30pm", "Sala 4", "6.0 $"},
            {"Mario Bros", "2:00pm, 8:00pm", "Sala 5", "9.0 $"}
        }; 
        //Impresion de las peliculas disponibles y su datos adiccionales 
        for (int i = 0; i < cartelera.length; i++) {
            System.out.println((i + 1) + ". " + cartelera[i][0]);
            System.out.println("   Horarios: " + cartelera[i][1]);
            System.out.println("   Sala: " + cartelera[i][2]);
            System.out.println("   Precio: " + cartelera[i][3]);
            System.out.println();
        }
        //Busqueda de la pelicula con condicionales
        System.out.print("Dime que pelicula quieres ver: ");
        pelicula = tcl.nextLine();
        //Ciclo en el cual se va a encontrar en condicional para encontrar la pelicula segun su nombre
    }

}
