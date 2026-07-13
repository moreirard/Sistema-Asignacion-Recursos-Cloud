package ar.edu.unahur.obj2.cloud;

public class Planificador {
    private PlanDespliegue planActual;

    public Planificador() {
        this.planActual = new PlanDespliegue();
    }

    public void ejecutarInmediato(IOperacion op) throws OverprovisioningException {
        op.ejecutar();
    }

    public void registrarEnPlan(IOperacion op) {
        this.planActual.agregarOperacion(op);
    }

    public void ejecutarPlan() throws OverprovisioningException {
        this.planActual.ejecutar();
    }

    public void vaciarPlan() {
        this.planActual.vaciarPlan();
    }
}