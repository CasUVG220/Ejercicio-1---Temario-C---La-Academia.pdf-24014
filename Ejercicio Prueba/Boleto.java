public class Boleto {
    private int numero;
    private String fechaCompra;
    private String localidad;
    private String comprador;

    public Boleto(int numero, String fechaCompra, String localidad, String comprador) {
        this.numero = numero;
        this.fechaCompra = fechaCompra;
        this.localidad = localidad;
        this.comprador = comprador;
    }

    public String toCSV() {
        return numero + "," + fechaCompra + "," + localidad + "," + comprador;
    }
}
