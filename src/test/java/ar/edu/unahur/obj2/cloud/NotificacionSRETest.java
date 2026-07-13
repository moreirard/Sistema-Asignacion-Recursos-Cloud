package ar.edu.unahur.obj2.cloud;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificacionSRETest {

    @Test
    public void elSREDebeRecibirUnAvisoCuandoElClusterCambie() throws OverprovisioningException {
        // Arrange
        Cluster cluster = new Cluster("EU-WEST-3", 500);
        NotificacionSRE notificacionSRE = new NotificacionSRE();
        cluster.registrarObservador(notificacionSRE);

        // Act
        cluster.asignar(100);

        // Assert
        String mensajeEsperado = "SRE Alert: Variación de capacidad en EU-WEST-3. Capacidad actual: 400 vCPUs.";
        assertEquals(mensajeEsperado, notificacionSRE.obtenerUltimoMensajeEnviado());
    }
}