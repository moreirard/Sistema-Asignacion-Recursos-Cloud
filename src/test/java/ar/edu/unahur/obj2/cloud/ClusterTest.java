package ar.edu.unahur.obj2.cloud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ClusterTest {

    @Test
    public void alCrearUnClusterDebeTenerLaCapacidadInicialAsignada() {
        // Arrange
        String idCluster = "US-EAST-1";
        Integer capacidadInicial = 1000;

        // Act
        Cluster cluster = new Cluster(idCluster, capacidadInicial);

        // Assert
        assertEquals(1000, cluster.consultarCapacidad());
    }

    @Test
    public void alAsignarCapacidadSeDebenDescontarLasVcpusDisponibles() throws OverprovisioningException {
        // Arrange
        Cluster cluster = new Cluster("US-EAST-1", 1000);

        // Act
        cluster.asignar(300);

        // Assert
        assertEquals(700, cluster.consultarCapacidad());
    }

    @Test
    public void alLiberarCapacidadSeDebenIncrementarLasVcpusDisponibles() throws OverprovisioningException {
        // Arrange
        Cluster cluster = new Cluster("US-EAST-1", 1000);
        cluster.asignar(300); // Lo dejamos en 700

        // Act
        cluster.liberar(100);

        // Assert
        assertEquals(800, cluster.consultarCapacidad());
    }

    @Test
    public void alIntentarOperarConValoresMenoresOIgualesACeroSeDebeLanzarExcepcion() {
        // Arrange
        Cluster cluster = new Cluster("US-EAST-1", 1000);

        // Act & Assert
        assertThrows(ValorInvalidoException.class, () -> cluster.asignar(0));
        assertThrows(ValorInvalidoException.class, () -> cluster.liberar(-50));
    }

    @Test
    public void alSuperarElLimiteDeOverprovisioningSeDebeLanzarExcepcionComprobada() {
        // Arrange
        Cluster cluster = new Cluster("US-EAST-1", 100);
        // Límite es -200, por lo que la capacidad total permitida a restar es 300.

        // Act & Assert
        assertThrows(OverprovisioningException.class, () -> cluster.asignar(301));
    }
}
