package cristian;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del Algoritmo de Cristian para sincronización de relojes
 * Este algoritmo permite a los clientes sincronizar sus relojes con un servidor de tiempo
 */
public class CristianAlgorithm {
    
    public static void main(String[] args) {
        // Crear servidor de tiempo
        TimeServer server = new TimeServer();
        System.out.println("Servidor de tiempo iniciado. Tiempo actual: " + server.getTime());
        
        // Crear varios clientes con relojes desincronizados
        List<ClientClock> clients = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            clients.add(new ClientClock(i));
        }
        
        // Mostrar tiempos originales
        System.out.println("\nTiempos originales de los clientes:");
        for (ClientClock client : clients) {
            System.out.println(client);
        }
        
        // Sincronizar cada cliente con el servidor
        System.out.println("\nIniciando sincronización usando el Algoritmo de Cristian:");
        for (ClientClock client : clients) {
            client.synchronizeWithServer(server);
            // Pequeña pausa entre sincronizaciones de clientes
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Mostrar tiempos sincronizados
        System.out.println("\nResumen de tiempos después de sincronización:");
        for (ClientClock client : clients) {
            System.out.println(client);
        }
        
        // Mostrar estadísticas
        SynchronizationStatistics stats = new SynchronizationStatistics(clients, server);
        stats.displayStatistics();
    }
}
