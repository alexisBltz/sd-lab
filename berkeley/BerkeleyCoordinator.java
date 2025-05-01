package berkeley;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa la lógica del coordinador para el algoritmo de Berkeley
 */
public class BerkeleyCoordinator {
    private final List<Clock> clocks;
    private Clock masterClock;
    
    public BerkeleyCoordinator(List<Clock> clocks) {
        this.clocks = clocks;
        selectMaster();
    }
    
    private void selectMaster() {
        // Seleccionar el primer reloj elegible como maestro
        for (Clock clock : clocks) {
            if (clock.isMasterEligible()) {
                masterClock = clock;
                System.out.println("Reloj " + masterClock.getId() + " seleccionado como coordinador.");
                return;
            }
        }
        throw new RuntimeException("No hay relojes elegibles para ser coordinador.");
    }
    
    // Implementa el algoritmo de Berkeley para sincronización
    public void synchronize() {
        System.out.println("\n=== Iniciando sincronización de Berkeley ===");
        System.out.println("Tiempo del coordinador: " + masterClock.getTime());
        
        // Paso 1: El coordinador solicita el tiempo a todos los servidores
        List<Long> timeDifferences = new ArrayList<>();
        long masterTime = masterClock.getTime();
        
        System.out.println("\nDiferencias con respecto al coordinador:");
        for (Clock clock : clocks) {
            if (clock != masterClock) {
                long difference = clock.getTime() - masterTime;
                timeDifferences.add(difference);
                System.out.println("Reloj " + clock.getId() + ": " + difference + " ms");
            }
        }
        
        // Paso 2: El coordinador calcula el promedio de las diferencias
        timeDifferences.add(0L); // Agregar la diferencia del coordinador (que es 0)
        long sum = 0;
        for (Long diff : timeDifferences) {
            sum += diff;
        }
        long averageDifference = sum / timeDifferences.size();
        
        System.out.println("\nPromedio de diferencias: " + averageDifference + " ms");
        
        // Paso 3: El coordinador envía los ajustes a cada servidor
        System.out.println("\nAjustes enviados a cada reloj:");
        
        // Ajustar el coordinador
        long masterAdjustment = averageDifference;
        masterClock.adjustTime(masterAdjustment);
        System.out.println("Coordinador (Reloj " + masterClock.getId() + "): " + masterAdjustment + " ms");
        
        // Ajustar los demás relojes
        int i = 0;
        for (Clock clock : clocks) {
            if (clock != masterClock) {
                long adjustment = averageDifference - timeDifferences.get(i);
                clock.adjustTime(adjustment);
                System.out.println("Reloj " + clock.getId() + ": " + adjustment + " ms");
                i++;
            }
        }
    }
    
    public Clock getMasterClock() {
        return masterClock;
    }
}
