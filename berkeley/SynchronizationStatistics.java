package berkeley;
import java.util.List;

/**
 * Clase para calcular y mostrar estadísticas sobre la sincronización
 */
public class SynchronizationStatistics {
    private final List<Clock> clocks;

    public SynchronizationStatistics(List<Clock> clocks) {
        this.clocks = clocks;
    }

    // Calcula la desviación estándar de los tiempos de los relojes
    public void displayStatistics() {
        double stdDev = calculateStandardDeviation();
        long avgTime = calculateAverageTime();

        System.out.println("\nEstadísticas de sincronización:");
        System.out.println("Desviación estándar: " + stdDev + " ms");
        System.out.println("Tiempo promedio: " + avgTime + " ms");
    }

    // Calcula el tiempo promedio de los relojes
    private double calculateStandardDeviation() {
        long avg = calculateAverageTime();
        double sum = 0;

        for (Clock clock : clocks) {
            sum += Math.pow(clock.getTime() - avg, 2);
        }

        return Math.sqrt(sum / clocks.size());
    }

    // Calcula la desviación estándar de los tiempos de los relojes
    private long calculateAverageTime() {
        long sum = 0;
        for (Clock clock : clocks) {
            sum += clock.getTime();
        }
        return sum / clocks.size();
    }
}
