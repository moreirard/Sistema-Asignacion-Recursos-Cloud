package ar.edu.unahur.obj2.cloud;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlanificadorTest {

    @Test
    public void elPlanificadorDebeEjecutarOperacionesIndividualesYPlanes() throws OverprovisioningException {
        // Arrange
        Cluster cluster = new Cluster("US-EAST-1", 1000);
        Planificador planificador = new Planificador();
        
        IOperacion asignacionUnica = new Asignacion(cluster, 200);

        // Act 1 - Ejecución Inmediata
        planificador.ejecutarInmediato(asignacionUnica); // Queda en 800

        // Act 2 - Ejecución por Lote
        planificador.registrarEnPlan(new Liberacion(cluster, 400)); // +400
        planificador.registrarEnPlan(new Asignacion(cluster, 100)); // -100
        planificador.ejecutarPlan(); // Lote completo. Total = +300. Queda en 1100.

        // Assert
        assertEquals(1100, cluster.consultarCapacidad());
    }
}