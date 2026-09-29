public class Moto extends Vehiculo {
    private int cilindraje;

    public Moto (String placa, String marca, int modelo, double tarifaDiaria, int cilindraje) {
        super(placa, marca, modelo, tarifaDiaria);
        this.cilindraje = cilindraje;
    }

    @Override
    public double calcularCosto(int dias) {
        double total = tarifaDiaria * dias;
        if (cilindraje > 250) {
            total += 75.0; 
        }
        return total;
    }

    @Override
    public String getInformacion() {
        return super.getInformacion() + " | Cilindraje: " + cilindraje + " cc";
    }
}