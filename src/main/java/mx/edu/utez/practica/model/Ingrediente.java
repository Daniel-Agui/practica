package mx.edu.utez.practica.model;

import java.io.Serializable;

public class Ingrediente implements Serializable {
    private int idIngrediente;
    private String nombreIngrediente;
    private Proveedor proveedor;
    public Ingrediente() {}
    public Ingrediente(int idIngrediente, String nombreIngrediente, Proveedor proveedor) {
        this.idIngrediente = idIngrediente;
        this.nombreIngrediente = nombreIngrediente;
        this.proveedor = proveedor;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
