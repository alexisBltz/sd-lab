package cristian;

import java.util.List;

/**
 * Clase para calcular y mostrar estadísticas sobre la sincronización
 */
public class SynchronizationStatistics {
    private final List<ClientClock> clients;
    private final TimeServer server;
    
    public SynchronizationStatistics(List<ClientClock> clients, TimeServer server) {
        this.clients = clients;
        this.server = server;
    }
    
    public void displayStatistics() {
        double stdDev = calculateStandardDeviation();
        
        System.out.println("\nEstadísticas de sincronización:");
        System.out.println("Desviación estándar: " + stdDev + " ms");
        System.out.println("Tiempo del servidor: " + server.getTime() + " ms");
    }
    
    private double calculateStandardDeviation() {
        long serverTimeNow = server.getTime();
        double sum = 0;
        
        for (ClientClock client : clients) {
            sum += Math.pow(client.getTime() - serverTimeNow, 2);
        }
        
        return Math.sqrt(sum / clients.size());
    }
}
