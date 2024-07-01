package mx.edu.utez.practica.dao;

import mx.edu.utez.practica.model.Ingrediente;
import mx.edu.utez.practica.model.Proveedor;
import mx.edu.utez.practica.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredienteDao {
    //SE CREA PARA OBTENER UN SOLO INGREDIENTE DE ACUERDO CON SU ID
    public Ingrediente getIngrediente(int idIngrediente) {
        Ingrediente ingrediente = new Ingrediente();
        //SE REALIZA QUERY CON JOIN PARA OBTENER EL ID PROVEEDOR DEL INGREDIENTE
        String query =  "SELECT i.idIngrediente, i.nombreIngrediente, p.idProveedor FROM ingrediente i JOIN proveedor p ON i.idProveedor = p.idProveedor WHERE i.idIngrediente = ?";
        try{
            Connection con = DatabaseConnectionManager.getConnection();
            //2) Configurar el query y ejecutarlo
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idIngrediente);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                //SE CREAN EL ID Y EL NOMBRE PARA INGRESARLO EN EL OBJETO PROOVEDOR
                int idProv = rs.getInt("idProveedor");
                String nombreProv = rs.getString("nombreProveedor");
                Proveedor proveedor = new Proveedor(idProv, nombreProv);

                ingrediente = new Ingrediente();

                //SE OBTIENEN LOS DATOS Y SE MANDAN AL MODELO
                ingrediente.setIdIngrediente(rs.getInt("idIngrediente"));
                ingrediente.setNombreIngrediente(rs.getString("nombreIngrediente"));
                ingrediente.setProveedor(proveedor);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        //REGRESA UN REGISTRO DE UN INGREDIENTE
        return ingrediente;
    }


    //SE CREA COMO ARREGLO PARA MANDAR TODOS LOS REGISTROS
   public ArrayList<Ingrediente> getIngredientes() {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        //SE REALIZA EL QUERY PARA OBTENER TODOS LOS REGISTROS
        String query = "SELECT * FROM ingrediente i JOIN proveedor p ON i.idProveedor = p.idProveedor";
        try{
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Ingrediente ingrediente = new Ingrediente();

                int idProv = rs.getInt("idProveedor");
                String nombreProv = rs.getString("nombreProveedor");
                Proveedor proveedor = new Proveedor(idProv, nombreProv);

                //SE OBTIENE EL DATO Y SE MANDA AL MODELO
                ingrediente.setIdIngrediente(rs.getInt("idIngrediente"));
                ingrediente.setNombreIngrediente(rs.getString("nombreIngrediente"));
                ingrediente.setProveedor(proveedor);
                //SE VAN GUARDANDO CADA REGISTRO EN EL ARREGLO
                ingredientes.add(ingrediente);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //SE RETORNAN TODOS LOS REGISTROS
        return ingredientes;
   }
}
