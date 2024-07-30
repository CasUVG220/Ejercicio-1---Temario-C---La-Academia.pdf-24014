import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicializar el controlador
        Controlador controlador = new Controlador();

        // Configuración inicial
        controlador.configurarLocalidades();
        controlador.leerDatosCSV();

        // Menú de opciones
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);

        while (opcion != 5) {
            // Mostrar menú
            System.out.println("1. Agregar Comprador");
            System.out.println("2. Consultar Disponibilidad Total");
            System.out.println("3. Consultar Disponibilidad Individual");
            System.out.println("4. Reporte de Caja");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();  // Limpiar el buffer del scanner

                if (opcion == 1) {
                    controlador.agregarComprador();
                } else if (opcion == 2) {
                    System.out.println(controlador.consultarDisponibilidadTotal());
                } else if (opcion == 3) {
                    System.out.println(controlador.consultarDisponibilidadIndividual());
                } else if (opcion == 4) {
                    System.out.println(controlador.consultarReporteDeCaja());
                } else if (opcion == 5) {
                    System.out.println("Saliendo del sistema.");
                    boolean guardado = controlador.guardarDatosCSV();
                    if (guardado) {
                        System.out.println("Datos guardados en el archivo CSV correctamente.");
                    } else {
                        System.out.println("Error al guardar los datos en el archivo CSV.");
                    }
                } else {
                    System.out.println("Opción no válida. Por favor, elija una opción del menú.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar el buffer del scanner
            }
        }

        scanner.close();
    }
}
