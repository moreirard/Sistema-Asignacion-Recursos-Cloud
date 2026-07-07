package ar.edu.unahur.obj2.cloud;

public class Liberacion implements IOperacion {
    private Cluster cluster;
    private Integer vcpus;

    public Liberacion(Cluster cluster, Integer vcpus) {
        this.cluster = cluster;
        this.vcpus = vcpus;
    }

    @Override
    public void ejecutar() {
        this.cluster.liberar(this.vcpus);
    }

    @Override
    public void deshacer() throws OverprovisioningException {
        // El inverso de liberar es asignar.
        // Al asignar, nos obliga a manejar la firma con la excepción.
        this.cluster.asignar(this.vcpus);
    }
}