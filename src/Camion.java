public class Camion extends Vehiculo {
    private double capacidadToneladas;

    public Camion(String placa, String marca, int modelo, double tarifaDiaria, double capacidadToneladas) {
        super(placa, marca, modelo, tarifaDiaria);
        this.capacidadToneladas = capacidadToneladas;
    }

    @Override
    public double calcularCosto(int dias) {
        double total = (tarifaDiaria * dias) + (100.0 * capacidadToneladas * dias);
            return total;
    }

    @Override
    public String getInformacion() {
        return super.getInformacion() + " | Capacidad: " + capacidadToneladas + " Toneladas";
    }
}

