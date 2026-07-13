package ar.edu.unahur.obj2.cloud;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AlarmaSaturacionTest {

    @Test
    public void laAlarmaDeSaturacionDebeActivarseSoloSiElClusterQuedaEnNegativo() throws OverprovisioningException {
        // Arrange
        Cluster cluster = new Cluster("US-EAST-1", 100);
        AlarmaSaturacion alarma = new AlarmaSaturacion();
        cluster.registrarObservador(alarma);

        // Act 1 - Operación segura (Queda en 50)
        cluster.asignar(50);
        
        // Assert 1
        assertFalse(alarma.fueActivadaRecientemente());

        // Act 2 - Operación que entra en zona de overdraft (Queda en -50)
        cluster.asignar(100);

        // Assert 2
        assertTrue(alarma.fueActivadaRecientemente());
    }
}