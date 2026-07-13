package ar.edu.unahur.obj2.cloud;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private String id;
    private Integer vCpusDisponibles;
    private List<IObservadorCluster> observadores = new ArrayList<>();

    public Cluster(String id, Integer capacidadInicial) {
        this.id = id;
        this.vCpusDisponibles = capacidadInicial;
    }

    public Integer consultarCapacidad() {
        return this.vCpusDisponibles;
    }

    public void asignar(int vcpus) throws OverprovisioningException {
        if (vcpus <= 0) {
            throw new ValorInvalidoException("La cantidad de vCPUs debe ser mayor a 0.");
        }
        if ((this.vCpusDisponibles - vcpus) < -200) {
            throw new OverprovisioningException(
                    "Operación rechazada: Supera el límite de overprovisioning (-200 vCPUs).");
        }
        this.vCpusDisponibles -= vcpus;
        this.notificar(); // Se llama solo si no hubo excepciones
    }

    public void liberar(Integer vcpus) {
        if (vcpus <= 0) {
            throw new ValorInvalidoException("La cantidad de vCPUs debe ser mayor a 0.");
        }
        this.vCpusDisponibles += vcpus;
        this.notificar(); // Se llama solo si no hubo excepciones
    }

    public void registrarObservador(IObservadorCluster obs) {
        this.observadores.add(obs);
    }

    public void eliminarObservador(IObservadorCluster obs) {
        this.observadores.remove(obs);
    }

    private void notificar() {
        for (IObservadorCluster obs : observadores) {
            obs.notificarCambio(this);
        }
    }

    public String getId() {
        return id;
        
    }

}
