import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVManager {
    private static final String FILE_PATH = "Ejercicio Prueba/boletos.csv";

    public CSVManager() {
        // Verificar si la carpeta existe, si no, crearla
        File folder = new File("Ejercicio Prueba");
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public List<Boleto> leerBoletos() {
        List<Boleto> boletos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            br.readLine(); // Leer el encabezado
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                int numero = Integer.parseInt(datos[0]);
                String fechaCompra = datos[1];
                String localidad = datos[2];
                String comprador = datos[3];
                boletos.add(new Boleto(numero, fechaCompra, localidad, comprador));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return boletos;
    }

    public boolean guardarBoletos(List<Boleto> boletos) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.append("Número,FechaCompra,Localidad,Comprador\n");
            for (Boleto boleto : boletos) {
                writer.append(boleto.toCSV());
                writer.append("\n");
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV: " + e.getMessage());
            return false;
        }
    }
}
