package ar.edu.unahur.obj2.cloud;

public class AlarmaSaturacion implements IObservadorCluster {
    private Boolean activada = false;

    @Override
    public void notificarCambio(Cluster cluster) {
        if (cluster.consultarCapacidad() < 0) {
            this.activada = true;
            // Aquí iría la lógica real de envío de alertas a un sistema externo
            System.out.println("¡ALERTA! El clúster " + cluster.getId() + " entró en overprovisioning.");
        } else {
            this.activada = false;
        }
    }

    public Boolean fueActivadaRecientemente() {
        return this.activada;
    }
}