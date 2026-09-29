public class Carro extends Vehiculo {
    private int cantidadPasajeros;
    private boolean esAutomatica;

    public Carro(String placa, String marca, int modelo, double tarifaDiaria, int cantidadPasajeros, boolean esAutomatica) {
        super(placa, marca, modelo, tarifaDiaria);
        this.cantidadPasajeros = cantidadPasajeros;
        this.esAutomatica = esAutomatica;
    }

    @Override
    public double calcularCosto(int dias) {
        double total = tarifaDiaria * dias;
        if (esAutomatica) {
            total += 50.0 * dias; 
        }
        return total;
    }

    @Override
    public String getInformacion() {
        return super.getInformacion() + " | Pasajeros: " + cantidadPasajeros + 
               " | Transmisión: " + (esAutomatica ? "Automática" : "Manual");
    }
}

