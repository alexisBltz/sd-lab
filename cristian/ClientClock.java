package cristian;

import java.util.Random;

/**
 * Representa el reloj de un cliente en el algoritmo de Cristian
 */
public class ClientClock {
    private long clientTime;
    private final int id;
    private final Random random = new Random();
    
    public ClientClock(int id) {
        this.id = id;
        // Inicializamos con un tiempo desincronizado (simulado)
        this.clientTime = System.currentTimeMillis() + random.nextInt(1000) - 500;
    }
    
    public long getTime() {
        return clientTime;
    }
    
    public void setTime(long newTime) {
        this.clientTime = newTime;
    }
    
    public int getId() {
        return id;
    }
    
    // Método para sincronizar con el servidor usando el algoritmo de Cristian
    public void synchronizeWithServer(TimeServer server) {
        long t0 = System.currentTimeMillis(); // Tiempo de solicitud
        long serverTime = server.getTimeWithDelay(); // Obtiene tiempo del servidor
        long t1 = System.currentTimeMillis(); // Tiempo de recepción
        
        // Calcula el tiempo de ida y vuelta (RTT)
        long rtt = t1 - t0;
        
        // Algoritmo de Cristian: estima el tiempo del servidor + tiempo de viaje
        long adjustedTime = serverTime + (rtt / 2);
        
        System.out.println("Cliente " + id + ":");
        System.out.println("  Tiempo original: " + clientTime);
        System.out.println("  Tiempo del servidor: " + serverTime);
        System.out.println("  RTT: " + rtt + "ms");
        System.out.println("  Tiempo ajustado: " + adjustedTime);
        
        // Actualiza su reloj
        setTime(adjustedTime);
        System.out.println("  Tiempo después de sincronización: " + clientTime);
    }
    
    @Override
    public String toString() {
        return "Cliente " + id + ": " + clientTime;
    }
}
