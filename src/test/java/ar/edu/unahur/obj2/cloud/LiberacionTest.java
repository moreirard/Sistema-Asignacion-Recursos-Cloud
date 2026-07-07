package ar.edu.unahur.obj2.cloud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LiberacionTest {

    @Test
    public void alEjecutarUnaLiberacionDebeIncrementarCapacidadDelCluster() throws OverprovisioningException {
        // Arrange
        Cluster cluster = new Cluster("US-EAST-1", 1000);
        IOperacion liberacion = new Liberacion(cluster, 300);

        // Act
        liberacion.ejecutar();

        // Assert
        assertEquals(1300, cluster.consultarCapacidad());
    }

    @Test
    public void alDeshacerUnaLiberacionDebeDescontarLaCapacidadPreviamenteLiberada() throws OverprovisioningException {
        // Arrange
        Cluster cluster = new Cluster("US-EAST-1", 1000);
        IOperacion liberacion = new Liberacion(cluster, 300);
        liberacion.ejecutar(); // Capacidad queda en 1300

        // Act
        liberacion.deshacer();

        // Assert
        assertEquals(1000, cluster.consultarCapacidad());
    }
}