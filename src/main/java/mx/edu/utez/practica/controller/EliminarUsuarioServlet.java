package mx.edu.utez.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.practica.dao.UsuarioDao;
import mx.edu.utez.practica.model.Usuario;

import java.io.IOException;

@WebServlet(name="EliminarUsuarioServlet", value = "/eliminar")
public class EliminarUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean estado = Boolean.parseBoolean(req.getParameter("estado"));
        int id = Integer.parseInt(req.getParameter("id"));
        Usuario usuario = new Usuario();
        usuario.setId(id);
        if (estado) {
            usuario.setEstado(false);
        }else{
            usuario.setEstado(true);
        }

        UsuarioDao usuarioDao = new UsuarioDao();

        if (usuarioDao.delete(usuario)){
            resp.sendRedirect("gestionUsuario.jsp");
        }


    }
}
