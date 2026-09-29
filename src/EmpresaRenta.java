import java.util.ArrayList;

public class EmpresaRenta {
    private ArrayList<Vehiculo> flota;
    private double ingresosAcumulados;

    public EmpresaRenta() {
        flota = new ArrayList<>();
        ingresosAcumulados = 0.0;
    }

    public boolean agregarVehiculo(Vehiculo v) {
        if (buscarPorPlaca(v.getPlaca()) != null) {
            return false; 
        }
        flota.add(v);
        return true;
    }

    public Vehiculo buscarPorPlaca(String placa) {
        for (Vehiculo v : flota) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null; 
    }

    public boolean confirmarAlquiler(Vehiculo v, int dias) {
        if (v == null || !v.isDisponible()) {
            return false;
        }
        double costoTotal = v.calcularCosto(dias);
        v.setDisponible(false);
        ingresosAcumulados += costoTotal;
        return true;
    }

    public boolean registrarDevolucion(Vehiculo v) {
        if (v == null || v.isDisponible()) {
            return false;
        }
        v.setDisponible(true);
        return true;
    }

    public void mostrarReporteGeneral() {
        int totalCarros = 0, carrosDisp = 0, carrosAlq = 0;
        int totalMotos = 0, motosDisp = 0, motosAlq = 0;
        int totalCamiones = 0, CamionesDisp = 0, CamionesAlq = 0;

        for (Vehiculo v : flota) {
            if (v instanceof Carro) {
                totalCarros++;
                if (v.isDisponible()) carrosDisp++; else carrosAlq++;
            } else if (v instanceof Moto) {
                totalMotos++;
                if (v.isDisponible()) motosDisp++; else motosAlq++;
            } else if (v instanceof Camion) {
                totalCamiones++;
                if (v.isDisponible()) CamionesDisp++; else CamionesAlq++;
            }
        }

        System.out.println("\n========== REPORTE GENERAL DE LA FLOTA ==========");
        System.out.println("Vehículos totales registrados: " + flota.size());
        System.out.println("-------------------------------------------------");
        System.out.println("Carros      -> Total: " + totalCarros + " | Disponibles: " + carrosDisp + " | Alquilados: " + carrosAlq);
        System.out.println("Motos       -> Total: " + totalMotos + " | Disponibles: " + motosDisp + " | Alquilados: " + motosAlq);
        System.out.println("Camionetas  -> Total: " + totalCamiones + " | Disponibles: " + CamionesDisp + " | Alquilados: " + CamionesAlq);
        System.out.println("-------------------------------------------------");
        System.out.printf("Dinero Total Acumulado: Q%.2f\n", ingresosAcumulados);
        System.out.println("=================================================\n");
    }

    public ArrayList<Vehiculo> getFlota() {
        return flota;
    }
}