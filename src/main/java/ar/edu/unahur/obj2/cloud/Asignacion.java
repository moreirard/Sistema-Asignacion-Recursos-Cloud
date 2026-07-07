package ar.edu.unahur.obj2.cloud;

public class Asignacion implements IOperacion {
    private Cluster cluster;
    private Integer vcpus;

    public Asignacion(Cluster cluster, Integer vcpus) {
        this.cluster = cluster;
        this.vcpus = vcpus;
    }

    @Override
    public void ejecutar() throws OverprovisioningException {
        this.cluster.asignar(this.vcpus);
    }

    @Override
    public void deshacer() {
        // El inverso de asignar es liberar
        this.cluster.liberar(this.vcpus);
    }

}
