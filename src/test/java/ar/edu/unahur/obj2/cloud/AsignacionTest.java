package ar.edu.unahur.obj2.cloud;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsignacionTest {

    @Test
    public void alEjecutarUnaAsignacionDebeDescontarCapacidadDelCluster() throws OverprovisioningException {
        // Arrange
        Cluster cluster = new Cluster("US-EAST-1", 1000);
        IOperacion asignacion = new Asignacion(cluster, 200);

        // Act
        asignacion.ejecutar();

        // Assert
        assertEquals(800, cluster.consultarCapacidad());
    }

    @Test
    public void alDeshacerUnaAsignacionDebeRestaurarLaCapacidadDelCluster() throws OverprovisioningException {
        // Arrange
        Cluster cluster = new Cluster("US-EAST-1", 1000);
        IOperacion asignacion = new Asignacion(cluster, 200);
        asignacion.ejecutar(); // Capacidad queda en 800

        // Act
        asignacion.deshacer();

        // Assert
        assertEquals(1000, cluster.consultarCapacidad());
    }
}