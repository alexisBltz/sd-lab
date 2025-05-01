package berkeley;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del Algoritmo de Berkeley para sincronización de relojes
 *
 * Este algoritmo selecciona un servidor como coordinador que recolecta las
 * diferencias de tiempo de todos los demás servidores, calcula un tiempo promedio,
 * y envía los ajustes necesarios a cada servidor.
 */
public class BerkeleyAlgorithm {
    public static void main(String[] args) {
        // Crear varios relojes con tiempos desincronizados
        List<Clock> clocks = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            // Solo el reloj 1 y 3 son elegibles para ser coordinador en este ejemplo
            clocks.add(new Clock(i, i == 1 || i == 3));
        }
    }

        // Mostrar tiempos originales
        System.out.println("=== Tiempos originales ===");
        for (Clock clock : clocks) {
        System.out.println(clock);

        // Crear el coordinador de Berkeley e iniciar la sincronización
        BerkeleyCoordinator coordinator = new BerkeleyCoordinator(clocks);
        coordinator.synchronize();
    }
}
