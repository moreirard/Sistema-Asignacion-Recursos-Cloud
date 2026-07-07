package ar.edu.unahur.obj2.cloud;

public class Cluster {
    private String id;
    private Integer vCpusDisponibles;

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
    }

    public void liberar(Integer vcpus) {
        if (vcpus <= 0) {
            throw new ValorInvalidoException("La cantidad de vCPUs debe ser mayor a 0.");
        }
        this.vCpusDisponibles += vcpus;
    }
}
