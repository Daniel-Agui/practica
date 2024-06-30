
package mx.edu.utez.pizzeria.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pizza implements Serializable {
    private int id;
    private String nombre;
    private double precio;
    private ArrayList<Ingrediente> ingredientes;

    public Pizza() {
    }

    public Pizza(int id, String nombre, double precio, ArrayList<Ingrediente> ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.ingredientes = ingredientes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
    
}