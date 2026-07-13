package ar.edu.unahur.obj2.cloud;

public class NotificacionSRE implements IObservadorCluster {
    private String ultimoMensajeEnviado;

    @Override
    public void notificarCambio(Cluster cluster) {
        this.ultimoMensajeEnviado = "SRE Alert: Variación de capacidad en " + cluster.getId() + ". Capacidad actual: " + cluster.consultarCapacidad() + " vCPUs.";
        
        // Simulación de envío de correo, Slack o PagerDuty
        System.out.println(this.ultimoMensajeEnviado);
    }

    public String obtenerUltimoMensajeEnviado() {
        return this.ultimoMensajeEnviado;
    }
}