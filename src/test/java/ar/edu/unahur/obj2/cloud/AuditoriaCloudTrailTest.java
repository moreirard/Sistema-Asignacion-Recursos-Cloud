package ar.edu.unahur.obj2.cloud;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuditoriaCloudTrailTest {

    @Test
    public void laAuditoriaDebeRegistrarUnEventoPorCadaVariacionDeCapacidad() throws OverprovisioningException {
        // Arrange
        Cluster cluster = new Cluster("US-EAST-1", 1000);
        AuditoriaCloudTrail auditoria = new AuditoriaCloudTrail();
        cluster.registrarObservador(auditoria);

        // Act
        cluster.asignar(200); // Exitoso (Queda en 800)
        cluster.liberar(50);  // Exitoso (Queda en 850)

        // Assert
        assertEquals(2, auditoria.obtenerCantidadDeRegistros());
        assertEquals("Auditoría: El clúster US-EAST-1 tiene ahora 850 vCPUs.", auditoria.obtenerUltimoRegistro());
    }
}