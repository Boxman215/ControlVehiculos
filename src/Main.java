import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmpresaRenta empresa = new EmpresaRenta();

    
        empresa.agregarVehiculo(new Carro("P123AAA", "Toyota", 2022, 150.0, 5, false));
        empresa.agregarVehiculo(new Carro("P456BBB", "Honda", 2023, 200.0, 5, true));

        empresa.agregarVehiculo(new Moto("M789CCC", "Suzuki", 2021, 80.0, 150));
        empresa.agregarVehiculo(new Moto("M012DDD", "Yamaha", 2024, 100.0, 300));

        empresa.agregarVehiculo(new Camion("C345EEE", "Isuzu", 2020, 200.0, 1.5));
        empresa.agregarVehiculo(new Camion("C678FFF", "Hino", 2022, 300.0, 3.0));

        int opcion = -1;

        while (opcion != 6) {
            System.out.println("=== MENÚ RENTAMOVIL ===");
            System.out.println("1. Registrar nuevo vehículo");
            System.out.println("2. Consultar flota");
            System.out.println("3. Cotizar alquiler");
            System.out.println("4. Confirmar alquiler");
            System.out.println("5. Registrar devolución");
            System.out.println("6. Reporte general y Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("\n[!] Error: Ingrese un número válido de opción.\n");
                continue;
            }

            switch (opcion) {
                case 1:
                    registrarVehiculoMenu(empresa, scanner);
                    break;
                case 2:
                    System.out.println("\n--- LISTA DE VEHÍCULOS ---");
                    for (Vehiculo v : empresa.getFlota()) {
                        System.out.println(v.getInformacion());
                    }
                    System.out.println();
                    break;
                case 3:
                    cotizarMenu(empresa, scanner);
                    break;
                case 4:
                    confirmarAlquilerMenu(empresa, scanner);
                    break;
                case 5:
                    registrarDevolucionMenu(empresa, scanner);
                    break;
                case 6:
                    empresa.mostrarReporteGeneral();
                    System.out.println("Gracias por usar el sistema.");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intente de nuevo.\n");
            }
        }
        scanner.close();
    }

    private static void registrarVehiculoMenu(EmpresaRenta empresa, Scanner scanner) {
        try {
            System.out.print("Ingrese la placa: ");
            String placa = scanner.nextLine().trim();
            if (placa.isEmpty()) {
                System.out.println("[!] La placa no puede estar vacía.\n");
                return;
            }

            System.out.print("Ingrese la marca: ");
            String marca = scanner.nextLine().trim();
            
            System.out.print("Ingrese el modelo (año): ");
            int modelo = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese la tarifa diaria (> 0): ");
            double tarifa = Double.parseDouble(scanner.nextLine());
            if (tarifa <= 0) {
                System.out.println("[!] La tarifa debe ser mayor a 0.\n");
                return;
            }

            System.out.println("Tipo de vehículo: 1. Carro | 2. Moto | 3. Camión");
            int tipo = Integer.parseInt(scanner.nextLine());

            Vehiculo nuevo = null;
            if (tipo == 1) {
                System.out.print("Pasajeros (> 0): ");
                int pas = Integer.parseInt(scanner.nextLine());
                System.out.print("¿Es automática? (s/n): ");
                boolean auto = scanner.nextLine().equalsIgnoreCase("s");
                if (pas <= 0) return;
                nuevo = new Carro(placa, marca, modelo, tarifa, pas, auto);
            } else if (tipo == 2) {
                System.out.print("Cilindraje cc (> 0): ");
                int cc = Integer.parseInt(scanner.nextLine());
                if (cc <= 0) return;
                nuevo = new Moto(placa, marca, modelo, tarifa, cc);
            } else if (tipo == 3) {
                System.out.print("Capacidad Toneladas (> 0): ");
                double cap = Double.parseDouble(scanner.nextLine());
                if (cap <= 0) return;
                nuevo = new Camion(placa, marca, modelo, tarifa, cap);
            }

            if (nuevo != null && empresa.agregarVehiculo(nuevo)) {
                System.out.println("¡Vehículo registrado exitosamente!\n");
            } else {
                System.out.println("[!] Error: La placa ya existe en el sistema.\n");
            }
        } catch (Exception e) {
            System.out.println("[!] Datos ingresados con formato incorrecto.\n");
        }
    }

    private static void cotizarMenu(EmpresaRenta empresa, Scanner scanner) {
        try {
            System.out.print("Ingrese la placa del vehículo a cotizar: ");
            String placa = scanner.nextLine();
            Vehiculo v = empresa.buscarPorPlaca(placa);

            if (v == null) {
                System.out.println("[!] Vehículo no encontrado.\n");
                return;
            }

            System.out.print("Ingrese días de alquiler: ");
            int dias = Integer.parseInt(scanner.nextLine());
            if (dias <= 0) {
                System.out.println("[!] Los días deben ser mayores a 0.\n");
                return;
            }

            double costo = v.calcularCosto(dias);
            System.out.println("\n--- COTIZACIÓN ---");
            System.out.println(v.getInformacion());
            System.out.printf("Costo total por %d día(s): Q%.2f\n\n", dias, costo);

        } catch (Exception e) {
            System.out.println("[!] Formato de datos no válido.\n");
        }
    }

    private static void confirmarAlquilerMenu(EmpresaRenta empresa, Scanner scanner) {
        try {
            System.out.print("Ingrese la placa del vehículo a alquilar: ");
            String placa = scanner.nextLine();
            Vehiculo v = empresa.buscarPorPlaca(placa);

            if (v == null) {
                System.out.println("[!] Vehículo no encontrado.\n");
                return;
            }

            if (!v.isDisponible()) {
                System.out.println("[!] El vehículo se encuentra ocupado/alquilado actualmente.\n");
                return;
            }

            System.out.print("Ingrese días de alquiler: ");
            int dias = Integer.parseInt(scanner.nextLine());
            if (dias <= 0) {
                System.out.println("[!] Los días deben ser un entero positivo.\n");
                return;
            }

            double costo = v.calcularCosto(dias);
            System.out.printf("El monto total a cobrar es: Q%.2f\n", costo);
            System.out.print("¿Desea confirmar la operación? (s/n): ");
            String conf = scanner.nextLine();

            if (conf.equalsIgnoreCase("s")) {
                empresa.confirmarAlquiler(v, dias);
                System.out.println("¡Alquiler realizado con éxito! Vehículo asignado.\n");
            } else {
                System.out.println("Operación cancelada. No se realizó ningún cobro.\n");
            }

        } catch (Exception e) {
            System.out.println("[!] Error en la entrada de datos.\n");
        }
    }

    private static void registrarDevolucionMenu(EmpresaRenta empresa, Scanner scanner) {
        System.out.print("Ingrese la placa del vehículo a devolver: ");
        String placa = scanner.nextLine();
        Vehiculo v = empresa.buscarPorPlaca(placa);

        if (v == null) {
            System.out.println("[!] Vehículo no encontrado.\n");
            return;
        }

        if (empresa.registrarDevolucion(v)) {
            System.out.println("¡Devolución exitosa! El vehículo vuelve a estar disponible.\n");
        } else {
            System.out.println("[!] Error: El vehículo ya estaba disponible (no estaba alquilado).\n");
        }
    }
}