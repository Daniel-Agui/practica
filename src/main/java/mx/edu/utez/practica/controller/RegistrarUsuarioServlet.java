package mx.edu.utez.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.practica.dao.UsuarioDao;
import mx.edu.utez.practica.model.Usuario;

import java.io.IOException;


@WebServlet(name="RegistrarUsuarioServlet", value="/sign_in")
public class RegistrarUsuarioServlet extends HttpServlet {



    //1) Primero configurar la clase para que sea servlet
    //2) Manejar el método doPost para obtener la información del formulario de registro de persona
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operacion = req.getParameter("operacion");
        if(operacion.equals("registrar")){
            String nombre_usuario = req.getParameter("nombre_usuario");
            String pass1 = req.getParameter("pass1");
            String pass2 = req.getParameter("pass2");
            String correo = req.getParameter("correo_usuario");
            int tipo_usuario = Integer.parseInt(req.getParameter("tipo_usuario"));
            boolean estado = true;
            String ruta = "index.jsp";
            if (pass1.equals(pass2)) {
                pass1 = pass2;
                //3) utilizar el dao con la función insert para insertar una persona en la BD
                UsuarioDao dao = new UsuarioDao();
                Usuario u = new Usuario();
                {
                    u.setNombre_usuario(nombre_usuario);
                    u.setContra(pass1);
                    u.setCorreo(correo);
                    u.setTipo_usuario(tipo_usuario);
                    u.setEstado(estado);
                };
                boolean insert = dao.insert(u);
                //4) una vez insertada la persona redirigir el usuario hacia el index.jsp
                if (insert) {
                    resp.sendRedirect(ruta);
                }else{
                    ruta = "registrarUsuario.jsp";
                    HttpSession sesion = req.getSession();
                    sesion.setAttribute("mensaje3","El usuario no se registro correctamente");
                    resp.sendRedirect(ruta);
                }
            }else{
                HttpSession sesion = req.getSession();
                sesion.setAttribute("mensaje2","Las contraseñas no coinciden");
                resp.sendRedirect("registrarUsuario.jsp");
            }
        } else{
            //Actualizar usuario
            String nombre_usuario = req.getParameter("nombre_usuario");
            String pass1 = req.getParameter("pass1");
            String pass2 = req.getParameter("pass2");
            String correo = req.getParameter("correo_usuario");
            int tipo_usuario = Integer.parseInt(req.getParameter("tipo_usuario"));
            int id = Integer.parseInt(req.getParameter("id"));
            if (pass1.equals(pass2)) {
                pass1 = pass2;
                Usuario u = new Usuario();
                u.setNombre_usuario(nombre_usuario);
                u.setContra(pass1);
                u.setCorreo(correo);
                u.setTipo_usuario(tipo_usuario);
                u.setId(id);

                UsuarioDao dao = new UsuarioDao();
                if (dao.update(u)) {
                    //Si se hizo el update
                    resp.sendRedirect("gestionUsuario.jsp");
                } else {
                    //pues no y mando un error
                    req.getSession().setAttribute("mensaje", "No se pudo actualizar");
                    resp.sendRedirect("index.jsp");
                }

            }else{
                HttpSession sesion = req.getSession();
                sesion.setAttribute("mensaje2","Las contraseñas no coinciden");
                resp.sendRedirect("registrarUsuario.jsp");
            }

        }



    }
}

