package cristian;

import java.util.Random;

/**
 * Representa un servidor de tiempo para el algoritmo de Cristian
 */
public class TimeServer {
    private long serverTime;
    private final Random random = new Random();
    
    public TimeServer() {
        // Inicializamos con el tiempo actual del sistema
        this.serverTime = System.currentTimeMillis();
    }
    
    // Método para obtener el tiempo actual del servidor
    public synchronized long getTime() {
        // Simulamos que el reloj del servidor avanza normalmente
        serverTime += random.nextInt(5);
        return serverTime;
    }
    
    // Método para calcular el tiempo con el retardo de red
    public synchronized long getTimeWithDelay() {
        // Simulamos latencia de red aleatoria
        try {
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getTime();
    }
}
