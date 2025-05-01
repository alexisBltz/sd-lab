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

}
