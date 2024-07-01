package mx.edu.utez.practica.dao;

import mx.edu.utez.practica.model.Proveedor;
import mx.edu.utez.practica.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProveedorDao {

    //SE CREA ESTE METODO PARA OBTENER UN SOLO PROOVEDOR DE ACUERDO AL ID
    public Proveedor getProve(int id, String nombre){

        Proveedor proveedor = new Proveedor();
        //SE CREA EL QUERY PARA OBTENER LOS DATOS DE LA BASE DE DATOS
        String query = "SELECT * FROM proveedor WHERE id = ?";
        try{
            Connection con = DatabaseConnectionManager.getConnection();
            //2) Configurar el query y ejecutarlo
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            //SE OBTIENEN LOS DATOS Y SE MANDAN AL MODELO
            if(rs.next()){
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre(rs.getString("nombre"));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //SE RETORNA UN PROVEEDOR CON TODOS SUS DATOS
        return proveedor;
    }

    //SE CREA ESTE METODO PARA RETORNAR VARIOS PROVEEDORES
    public ArrayList<Proveedor> getProveedores(){
        //SE CREA UN ARREGLO PARA ALMACENAR TODOS LOS PROVEEDORES
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        //SE REALIZA EL QUERY PARA OBTENER TODOS
        String query = "SELECT * FROM proveedor ";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            //SE VAN INGRESANDO LOS DATOS DE CADA PROVEEDOR
            while(rs.next()){
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedores.add(proveedor);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //SE RETORNA TODOS LOS PROVEDORES AGREGAGOS AL ARREGLO
        return proveedores;
    }
}
