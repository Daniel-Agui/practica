package mx.edu.utez.practica.dao;

import mx.edu.utez.practica.model.Ingrediente;
import mx.edu.utez.practica.model.Pizza;
import mx.edu.utez.practica.model.Proveedor;

import mx.edu.utez.practica.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PizzaDao{
    //SE CREA PARA OBTENER UN REGISTRO DE UNA PIZZA
    public Pizza getPizza(int id) {
        Pizza pizza = new Pizza();
        String queryPizza = "SELECT id_pizza, nombre_pizza, precio_pizza FROM pizza WHERE id_pizza=?";
        String queryIngredientes =  "SELECT i.id_ingrediente, i.nombre_ingrediente, p.id_proveedor, p.nombre_proveedor " +
                                    "FROM ingredientes i " +
                                    "JOIN pizza_ingredientes pi ON i.id_ingrediente = pi.id_ingrediente " +
                                    "JOIN proveedores p ON i.id_proveedor = p.id_proveedor " +
                                    "WHERE pi.id_pizza = ?";

        try {
            Connection con = DatabaseConnectionManager.getConnection();

            // Consultar la pizza
            PreparedStatement psPizza = con.prepareStatement(queryPizza);
            psPizza.setInt(1, id);
            ResultSet rsPizza = psPizza.executeQuery();

            if (rsPizza.next()) {
                pizza = new Pizza();
                pizza.setId(rsPizza.getInt("id_pizza"));
                pizza.setNombre(rsPizza.getString("nombre_pizza"));
                pizza.setPrecio(rsPizza.getDouble("precio_pizza"));

                // Consultar los ingredientes
                PreparedStatement psIngredientes = con.prepareStatement(queryIngredientes);
                psIngredientes.setInt(1, id);
                ResultSet rsIngredientes = psIngredientes.executeQuery();

                ArrayList<Ingrediente> ingredientes = new ArrayList<>();
                while (rsIngredientes.next()) {
                    //SE OBTIENE EL ID Y EL NOMBRE Y SE MANDA A SU MODELO
                    int idIngrediente = rsIngredientes.getInt("id_ingrediente");
                    String nombreIngrediente = rsIngredientes.getString("nombre_ingrediente");
                    //AHORA CON EL OTRO JOIN SE OBTIENE EL ID Y NOMBRE PROVEEDOR
                    int idProveedor = rsIngredientes.getInt("id_proveedor");
                    String nombreProveedor = rsIngredientes.getString("nombre_proveedor");
                    //SE LLENA EL MODELO PROVEEDOR
                    Proveedor proveedor = new Proveedor(idProveedor, nombreProveedor);
                    //SE LLENA EL MODELO DE INGREDIENTE
                    Ingrediente ingrediente = new Ingrediente(idIngrediente, nombreIngrediente, proveedor);
                    //SE AGREGAN AL ARREGLO
                    ingredientes.add(ingrediente);
                }
                //AHORA LOS INGREDIENTES SE AGREGAN A LA PIZZA
                pizza.setIngredientes(ingredientes);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //SE RETORNA LA PIZZA YA CON SUS INGREDIENTES
        return pizza;
    }


    //SE CREA PARA OBTENER TODAS LOS REGISTROS DE PIZZA
    public ArrayList<Pizza> getAllPizzas(){
        ArrayList<Pizza> pizzas = new ArrayList<>();
        String query = "SELECT * FROM pizza";
        String queryIngredientes =  "SELECT i.id_ingrediente, i.nombre_ingrediente, p.id_proveedor, p.nombre_proveedor " +
                                    "FROM ingredientes i " +
                                    "JOIN pizza_ingredientes pi ON i.id_ingrediente = pi.id_ingrediente " +
                                    "JOIN proveedores p ON i.id_proveedor = p.id_proveedor ";
        try{
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement psPizza = con.prepareStatement(query);
            ResultSet rsPizza = psPizza.executeQuery();

            PreparedStatement psIngredientes = con.prepareStatement(queryIngredientes);
            ResultSet rsIngredientes = psIngredientes.executeQuery();


        }catch (SQLException e){
            e.printStackTrace();
        }

        return pizzas;

    }
}

