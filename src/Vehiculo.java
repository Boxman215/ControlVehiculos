public abstract class Vehiculo {
    protected String placa;
    protected String marca;
    protected int modelo;
    protected double tarifaDiaria;
    protected boolean disponible;

    public Vehiculo(String placa, String marca, int modelo, double tarifaDiaria) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaDiaria = tarifaDiaria;
        this.disponible = true; 
    }

    public String getPlaca() {
        return placa;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    
    public abstract double calcularCosto(int dias);

    
    public String getInformacion() {
        return "Placa: " + placa + " | Marca: " + marca + " | Modelo: " + modelo + 
               " | Tarifa/día: Q" + String.format("%.2f", tarifaDiaria) + 
               " | Estado: " + (disponible ? "Disponible" : "Alquilado");
    }
}

