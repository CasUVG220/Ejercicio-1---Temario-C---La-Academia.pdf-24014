public class Localidad {
    private String nombre;
    private int capacidad;
    private int precio;
    private int boletosDisponibles;
    private int boletosVendidos;

    public Localidad(String nombre, int capacidad, int precio) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precio = precio;
        this.boletosDisponibles = capacidad;
        this.boletosVendidos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getBoletosDisponibles() {
        return boletosDisponibles;
    }

    public void venderBoletos(int cantidad) {
        if (cantidad <= boletosDisponibles) {
            boletosDisponibles -= cantidad;
            boletosVendidos += cantidad;
        }
    }

    public int getIngresos() {
        return boletosVendidos * precio;
    }
}
