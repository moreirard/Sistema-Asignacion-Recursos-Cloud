package ar.edu.unahur.obj2.cloud;

import java.util.ArrayList;
import java.util.List;

public class AuditoriaCloudTrail implements IObservadorCluster {
    private List<String> logs = new ArrayList<>();

    @Override
    public void notificarCambio(Cluster cluster) {
        String registro = "Auditoría: El clúster " + cluster.getId() + " tiene ahora " + cluster.consultarCapacidad() + " vCPUs.";
        this.logs.add(registro);
        
        // Simulación de envío a un sistema centralizado de logs
        System.out.println(registro);
    }

    public int obtenerCantidadDeRegistros() {
        return this.logs.size();
    }

    public String obtenerUltimoRegistro() {
        if (this.logs.isEmpty()) return null;
        return this.logs.get(this.logs.size() - 1);
    }
}
