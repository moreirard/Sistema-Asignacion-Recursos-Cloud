package ar.edu.unahur.obj2.cloud;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PlanDespliegue implements IOperacion {
    private List<IOperacion> operaciones = new ArrayList<>();
    // Usamos un Stack para recordar el orden exacto de ejecución para el rollback
    private Stack<IOperacion> operacionesEjecutadas = new Stack<>();

    public void agregarOperacion(IOperacion operacion) {
        this.operaciones.add(operacion);
    }

    public void vaciarPlan() {
        this.operaciones.clear();
        this.operacionesEjecutadas.clear();
    }

    /*
     * 
     * @Override
     * public void ejecutar() throws OverprovisioningException {
     * for (IOperacion op : operaciones) {
     * op.ejecutar();
     * operacionesEjecutadas.push(op); // Registramos el éxito
     * }
     * }
     */

    @Override
    public void ejecutar() throws OverprovisioningException {
        for (IOperacion op : operaciones) {
            try {
                op.ejecutar();
                operacionesEjecutadas.push(op);
            } catch (OverprovisioningException ex) {
                // Iniciamos el proceso de compensación (Rollback automático)
                this.deshacer();
                // Relanzamos la excepción para que el orquestador se entere del fallo
                throw ex;
            }
        }
    }

    @Override
    public void deshacer() throws OverprovisioningException {
        while (!operacionesEjecutadas.isEmpty()) {
            IOperacion op = operacionesEjecutadas.pop();
            op.deshacer();
        }
    }
}