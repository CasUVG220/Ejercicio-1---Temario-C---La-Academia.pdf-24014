import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controlador {
    private List<Boleto> boletos;
    private Localidad[] localidades;

    public Controlador() {
        boletos = new ArrayList<>();
    }

    public void configurarLocalidades() {
        // Configurar las localidades con capacidad inicial
        localidades = new Localidad[3];
        localidades[0] = new Localidad("Balcón 2", 100, 300);
        localidades[1] = new Localidad("Platea", 100, 600);
        localidades[2] = new Localidad("Balcón 1", 100, 1800);
    }

    public void leerDatosCSV() {
        // Leer datos del archivo CSV y llenar la lista de boletos
        CSVManager csvManager = new CSVManager();
        boletos = csvManager.leerBoletos();
    }

    public void agregarComprador() {
        Scanner scanner = new Scanner(System.in);

        // Obtener datos del comprador
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Cantidad de boletos: ");
        int cantidad = scanner.nextInt();
        System.out.print("Presupuesto máximo: ");
        int presupuestoMaximo = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer del scanner

        System.out.println("Seleccione localidad (1. Balcón 2, 2. Platea, 3. Balcón 1): ");
        int seleccion = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer del scanner

        // Validar selección
        if (seleccion < 1 || seleccion > 3) {
            System.out.println("Selección inválida.");
            return;
        }

        Localidad localidadSeleccionada = localidades[seleccion - 1];

        // Procesar la compra
        if (localidadSeleccionada.getPrecio() > presupuestoMaximo) {
            System.out.println("El precio de la localidad supera el presupuesto.");
            return;
        }

        int boletosDisponibles = localidadSeleccionada.getBoletosDisponibles();
        if (boletosDisponibles < cantidad) {
            cantidad = boletosDisponibles;
        }

        if (cantidad > 0) {
            int numeroBoleto = boletos.size() + 1;
            String fechaCompra = java.time.LocalDate.now().toString();
            for (int i = 0; i < cantidad; i++) {
                boletos.add(new Boleto(numeroBoleto++, fechaCompra, localidadSeleccionada.getNombre(), nombre));
            }
            localidadSeleccionada.venderBoletos(cantidad);
            System.out.println("Boletos comprados exitosamente.");
        } else {
            System.out.println("No hay boletos disponibles en la localidad seleccionada.");
        }
    }

    public String consultarDisponibilidadTotal() {
        StringBuilder sb = new StringBuilder();
        for (Localidad localidad : localidades) {
            sb.append(localidad.getNombre()).append(": ")
              .append(localidad.getBoletosDisponibles()).append(" boletos disponibles\n");
        }
        return sb.toString();
    }

    public String consultarDisponibilidadIndividual() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione localidad (1. Balcón 2, 2. Platea, 3. Balcón 1): ");
        int seleccion = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer del scanner

        if (seleccion < 1 || seleccion > 3) {
            return "Selección inválida.";
        }

        Localidad localidadSeleccionada = localidades[seleccion - 1];
        return localidadSeleccionada.getNombre() + ": "
                + localidadSeleccionada.getBoletosDisponibles() + " boletos disponibles";
    }

    public String consultarReporteDeCaja() {
        int totalIngresos = 0;
        for (Localidad localidad : localidades) {
            totalIngresos += localidad.getIngresos();
        }
        return "Total de ingresos: Q" + totalIngresos;
    }

    public boolean guardarDatosCSV() {
        CSVManager csvManager = new CSVManager();
        return csvManager.guardarBoletos(boletos);
    }
}
