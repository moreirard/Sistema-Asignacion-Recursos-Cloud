package ar.edu.unahur.obj2.cloud;

public interface IOperacion {
    void ejecutar() throws OverprovisioningException;

    void deshacer() throws OverprovisioningException;
}