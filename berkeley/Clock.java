package berkeley;

import java.util.Random;

/**
 * Representa un reloj en el sistema Berkeley.
 */
public class Clock {
    private long time;
    private final int id;
    private final Random random = new Random();
    private final boolean isMasterEligible;
    
    public Clock(int id, boolean isMasterEligible) {
        this.id = id;
        this.isMasterEligible = isMasterEligible;
        // Inicializamos con un tiempo ligeramente desincronizado (simulado)
        this.time = System.currentTimeMillis() + random.nextInt(2000) - 1000;
    }
    
    public long getTime() {
        return time;
    }
    
    public void adjustTime(long offset) {
        time += offset;
    }
    
    public int getId() {
        return id;
    }
    
    public boolean isMasterEligible() {
        return isMasterEligible;
    }
    
    @Override
    public String toString() {
        return "Reloj " + id + ": " + time + (isMasterEligible ? " (elegible para coordinador)" : "");
    }
}
