import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class cinemasIn {
    static class Pelicula {
        String nombre;
        String horario;
        int sala;
        double precio;
        Pelicula(String nombre, String horario, int sala, double precio) {
            this.nombre = nombre;
            this.horario = horario;
            this.sala = sala;
            this.precio = precio;
        }
    }
    static class Venta {
        String pelicula;
        String horario;
        int cantidadBoletos;
        List<String> asientos;
        double total;
        Venta(String pelicula, String horario, int cantidadBoletos, List<String> asientos, double total) {
            this.pelicula = pelicula;
            this.horario = horario;
            this.cantidadBoletos = cantidadBoletos;
            this.asientos = asientos;
            this.total = total;
        }
    }
    static class Snack {
        String nombre;
        double precio;
        Snack(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = precio;
        }
    }
    private static final List<Pelicula> cartelera = new ArrayList<>();
    private static final List<Venta> registroVentas = new ArrayList<>();
    private static final List<Snack> menuSnacks = new ArrayList<>();
    private static final String[][][] asientosPorSala = new String[10][10][10];
    public static void main(String[] args) {
        inicializarCartelera();
        inicializarAsientos();
        inicializarMenuSnacks();
        menu();
    }
    private static void inicializarCartelera() {
        cartelera.add(new Pelicula("Avatar", "14:00", 1, 5.00));
        cartelera.add(new Pelicula("Avengers: Endgame", "16:00", 2, 6.00));
        cartelera.add(new Pelicula("Titanic", "18:00", 3, 7.00));
        cartelera.add(new Pelicula("Inception", "20:00", 4, 8.00));
        cartelera.add(new Pelicula("The Dark Knight", "22:00", 5, 9.00));
        cartelera.add(new Pelicula("Forrest Gump", "14:00", 6, 5.00));
        cartelera.add(new Pelicula("The Matrix", "16:00", 7, 6.00));
        cartelera.add(new Pelicula("Pulp Fiction", "18:00", 8, 7.00));
        cartelera.add(new Pelicula("The Lion King", "20:00", 9, 8.00));
        cartelera.add(new Pelicula("Frozen", "22:00", 10, 9.00));
    }
    private static void inicializarAsientos() {
        for (int sala = 0; sala < 10; sala++) {
            for (int fila = 0; fila < 10; fila++) {
                for (int columna = 0; columna < 10; columna++) {
                    asientosPorSala[sala][fila][columna] = "O";
                }
            }
        }
    }
    private static void inicializarMenuSnacks() {
        menuSnacks.add(new Snack("Palomitas", 3.00));
        menuSnacks.add(new Snack("Soda", 2.50));
        menuSnacks.add(new Snack("Nachos", 4.00));
        menuSnacks.add(new Snack("Hot Dog", 5.00));
        menuSnacks.add(new Snack("Dulces", 1.50));
    }
    private static void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nBienvenido al Sistema de Gestion CineMas-Loja");
            System.out.println("1. Ver Cartelera");
            System.out.println("2. Comprar Boletos");
            System.out.println("3. Ver Registro de Ventas");
            System.out.println("4. Comprar Snacks");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion (1-5): ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            switch (opcion) {
                case 1:
                    mostrarCartelera();
                    break;
                case 2:
                    comprarBoletos(scanner);
                    break;
                case 3:
                    mostrarRegistroVentas();
                    break;
                case 4:
                    comprarSnacks(scanner);
                    break;
                case 5:
                    System.out.println("Saliendo del sistema. Gracias por usar CineMas-Loja!");
                    return;
                default:
                    System.out.println("Opción invalida. Por favor, elija una opcion del 1 al 5.");
            }
        }
    }
    private static void mostrarCartelera() {
        System.out.println("\nCartelera de Peliculas:");
        for (int i = 0; i < cartelera.size(); i++) {
            Pelicula pelicula = cartelera.get(i);
            System.out.printf("%d. %s - Horario: %s - Sala: %d - Precio: $%.2f\n", i + 1, pelicula.nombre, pelicula.horario, pelicula.sala, pelicula.precio);
        }
    }
    private static void comprarBoletos(Scanner scanner) {
        mostrarCartelera();
        System.out.print("Seleccione el numero de la pelicula: ");
        int seleccion = scanner.nextInt() - 1;
        scanner.nextLine();
        if (seleccion < 0 || seleccion >= cartelera.size()) {
            System.out.println("Selección invalida. Por favor, intentelo de nuevo.");
            return;
        }
        Pelicula peliculaSeleccionada = cartelera.get(seleccion);
        int sala = peliculaSeleccionada.sala;
        System.out.printf("Usted selecciono: %s - Sala: %d\n", peliculaSeleccionada.nombre, sala);
        mostrarAsientos(sala);
        System.out.print("¿Cuantos boletos desea comprar?: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();
        List<String> asientosSeleccionados = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            System.out.print("Seleccione la fila (1-10): ");
            int fila = scanner.nextInt() - 1;
            System.out.print("Seleccione la columna (1-10): ");
            int columna = scanner.nextInt() - 1;
            scanner.nextLine();
            if (asientosPorSala[sala - 1][fila][columna].equals("X")) {
                System.out.println("Este asiento ya esta ocupado. Por favor, elija otro.");
                i--;
                continue;
            }
            asientosPorSala[sala - 1][fila][columna] = "X";
            asientosSeleccionados.add("Fila " + (fila + 1) + " Columna " + (columna + 1));
        }
        double total = cantidad * peliculaSeleccionada.precio;
        System.out.println("Factura:");
        System.out.println("Pelicula: " + peliculaSeleccionada.nombre);
        System.out.println("Cantidad de boletos: " + cantidad);
        System.out.println("Asientos seleccionados: " + asientosSeleccionados);
        System.out.printf("Total a pagar: $%.2f\n", total);
        registroVentas.add(new Venta(peliculaSeleccionada.nombre, peliculaSeleccionada.horario, cantidad, asientosSeleccionados, total));
    }
    private static void mostrarAsientos(int sala) {
        System.out.println("\nEstado de los asientos (O = Libre, X = Ocupado):");
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                System.out.print(asientosPorSala[sala - 1][fila][columna] + " ");
            }
            System.out.println();
        }
    }
    private static void mostrarRegistroVentas() {
        System.out.println("\nRegistro de Ventas:");
        for (Venta venta : registroVentas) {
            System.out.println("Pelicula: " + venta.pelicula);
            System.out.println("Horario: " + venta.horario);
            System.out.println("Cantidad de boletos: " + venta.cantidadBoletos);
            System.out.println("Asientos: " + venta.asientos);
            System.out.printf("Total: $%.2f\n", venta.total);
            System.out.println("--------------------");
        }
    }
    private static void comprarSnacks(Scanner scanner) {
        System.out.println("\nMenu de Snacks:");
        for (int i = 0; i < menuSnacks.size(); i++) {
            Snack snack = menuSnacks.get(i);
            System.out.printf("%d. %s - Precio: $%.2f\n", i + 1, snack.nombre, snack.precio);
        }
        System.out.print("Seleccione el numero del snack que desea: ");
        int seleccion = scanner.nextInt() - 1;
        scanner.nextLine();
        if (seleccion < 0 || seleccion >= menuSnacks.size()) {
            System.out.println("Selección invalida. Por favor, intentelo de nuevo.");
            return;
        }
        Snack snackSeleccionado = menuSnacks.get(seleccion);
        System.out.print("Cuantas unidades desea comprar?: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();
        double total = cantidad * snackSeleccionado.precio;
        System.out.printf("Ha comprado %d %s(s) por un total de $%.2f\n", cantidad, snackSeleccionado.nombre, total);
    }
}
/***
 * run:

Bienvenido al Sistema de Gestion CineMas-Loja
1. Ver Cartelera
2. Comprar Boletos
3. Ver Registro de Ventas
4. Comprar Snacks
5. Salir
Seleccione una opcion (1-5): 1

Cartelera de Peliculas:
1. Avatar - Horario: 14:00 - Sala: 1 - Precio: $5,00
2. Avengers: Endgame - Horario: 16:00 - Sala: 2 - Precio: $6,00
3. Titanic - Horario: 18:00 - Sala: 3 - Precio: $7,00
4. Inception - Horario: 20:00 - Sala: 4 - Precio: $8,00
5. The Dark Knight - Horario: 22:00 - Sala: 5 - Precio: $9,00
6. Forrest Gump - Horario: 14:00 - Sala: 6 - Precio: $5,00
7. The Matrix - Horario: 16:00 - Sala: 7 - Precio: $6,00
8. Pulp Fiction - Horario: 18:00 - Sala: 8 - Precio: $7,00
9. The Lion King - Horario: 20:00 - Sala: 9 - Precio: $8,00
10. Frozen - Horario: 22:00 - Sala: 10 - Precio: $9,00

Bienvenido al Sistema de Gestion CineMas-Loja
1. Ver Cartelera
2. Comprar Boletos
3. Ver Registro de Ventas
4. Comprar Snacks
5. Salir
Seleccione una opcion (1-5): 4

Menu de Snacks:
1. Palomitas - Precio: $3,00
2. Soda - Precio: $2,50
3. Nachos - Precio: $4,00
4. Hot Dog - Precio: $5,00
5. Dulces - Precio: $1,50
Seleccione el numero del snack que desea: 3
Cuantas unidades desea comprar?: 2
Ha comprado 2 Nachos(s) por un total de $8,00

Bienvenido al Sistema de Gestion CineMas-Loja
1. Ver Cartelera
2. Comprar Boletos
3. Ver Registro de Ventas
4. Comprar Snacks
5. Salir
Seleccione una opcion (1-5): 2

Cartelera de Peliculas:
1. Avatar - Horario: 14:00 - Sala: 1 - Precio: $5,00
2. Avengers: Endgame - Horario: 16:00 - Sala: 2 - Precio: $6,00
3. Titanic - Horario: 18:00 - Sala: 3 - Precio: $7,00
4. Inception - Horario: 20:00 - Sala: 4 - Precio: $8,00
5. The Dark Knight - Horario: 22:00 - Sala: 5 - Precio: $9,00
6. Forrest Gump - Horario: 14:00 - Sala: 6 - Precio: $5,00
7. The Matrix - Horario: 16:00 - Sala: 7 - Precio: $6,00
8. Pulp Fiction - Horario: 18:00 - Sala: 8 - Precio: $7,00
9. The Lion King - Horario: 20:00 - Sala: 9 - Precio: $8,00
10. Frozen - Horario: 22:00 - Sala: 10 - Precio: $9,00
Seleccione el numero de la pelicula: 2
Usted selecciono: Avengers: Endgame - Sala: 2

Estado de los asientos (O = Libre, X = Ocupado):
O O O O O O O O O O 
O O O O O O O O O O 
O O O O O O O O O O 
O O O O O O O O O O 
O O O O O O O O O O 
O O O O O O O O O O 
O O O O O O O O O O 
O O O O O O O O O O 
O O O O O O O O O O 
O O O O O O O O O O 
¿Cuantos boletos desea comprar?: 6
Seleccione la fila (1-10): 4
Seleccione la columna (1-10): 3
Seleccione la fila (1-10): 4
Seleccione la columna (1-10): 2
Seleccione la fila (1-10): 4
Seleccione la columna (1-10): 4
Seleccione la fila (1-10): 4
Seleccione la columna (1-10): 1
Seleccione la fila (1-10): 4
Seleccione la columna (1-10): 5
Seleccione la fila (1-10): 4
Seleccione la columna (1-10): 6
Factura:
Pelicula: Avengers: Endgame
Cantidad de boletos: 6
Asientos seleccionados: [Fila 4 Columna 3, Fila 4 Columna 2, Fila 4 Columna 4, Fila 4 Columna 1, Fila 4 Columna 5, Fila 4 Columna 6]
Total a pagar: $36,00

Bienvenido al Sistema de Gestion CineMas-Loja
1. Ver Cartelera
2. Comprar Boletos
3. Ver Registro de Ventas
4. Comprar Snacks
5. Salir
Seleccione una opcion (1-5): 3

Registro de Ventas:
Pelicula: Avengers: Endgame
Horario: 16:00
Cantidad de boletos: 6
Asientos: [Fila 4 Columna 3, Fila 4 Columna 2, Fila 4 Columna 4, Fila 4 Columna 1, Fila 4 Columna 5, Fila 4 Columna 6]
Total: $36,00
--------------------

Bienvenido al Sistema de Gestion CineMas-Loja
1. Ver Cartelera
2. Comprar Boletos
3. Ver Registro de Ventas
4. Comprar Snacks
5. Salir
Seleccione una opcion (1-5): 5
Saliendo del sistema. Gracias por usar CineMas-Loja!
BUILD SUCCESSFUL (total time: 2 minutes 5 seconds)
 */