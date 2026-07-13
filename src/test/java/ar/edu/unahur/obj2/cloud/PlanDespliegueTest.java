package ar.edu.unahur.obj2.cloud;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlanDespliegueTest {

    @Test
    public void alEjecutarUnPlanSeDebenEjecutarTodasSusOperacionesExitosamente() throws OverprovisioningException {
        // Arrange
        Cluster cluster = new Cluster("US-EAST-1", 1000);
        PlanDespliegue plan = new PlanDespliegue();
        
        plan.agregarOperacion(new Asignacion(cluster, 200)); // Queda en 800
        plan.agregarOperacion(new Liberacion(cluster, 500)); // Queda en 1300
        plan.agregarOperacion(new Asignacion(cluster, 300)); // Queda en 1000

        // Act
        plan.ejecutar();

        // Assert
        assertEquals(1000, cluster.consultarCapacidad());
    }

    @Test
    public void siUnaOperacionFallaElPlanDebeDeshacerLasAnterioresYRelanzarElError() {
        // Arrange
        Cluster cluster = new Cluster("US-EAST-1", 1000);
        PlanDespliegue plan = new PlanDespliegue();
        
        plan.agregarOperacion(new Asignacion(cluster, 800)); // Exitoso -> Queda en 200
        plan.agregarOperacion(new Asignacion(cluster, 500)); // FALLA! Límite es -200 (200 - 500 = -300)
        plan.agregarOperacion(new Liberacion(cluster, 100)); // No debería llegar aquí

        // Act & Assert
        assertThrows(OverprovisioningException.class, () -> plan.ejecutar());
        
        // Verificamos el Rollback: La capacidad debe volver al estado inicial (1000)
        assertEquals(1000, cluster.consultarCapacidad());
    }
}