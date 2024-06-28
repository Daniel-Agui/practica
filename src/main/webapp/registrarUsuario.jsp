<%@ page import="mx.edu.utez.practica.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/bootstrap.css">
</head>
<body>
<div class="container">

    <%
        HttpSession sesion = request.getSession();
        Usuario u = (Usuario) sesion.getAttribute("usuario");
        if (u == null) { %>
    <!-- un formulario para insertar el usuario -->
    <form method="post" action="sign_in" class="form">
        <label class="form-label">Ingrese su nombre de usuario: </label>
        <input type="text" name="nombre_usuario">
        <br><br>
        <label class="form-label">Ingrese su contraseña: </label>
        <input type="password" name="pass1">
        <br><br>
        <label class="form-label">Confirme su contraseña: </label>
        <input type="password" name="pass2">
        <br>
        <br>
        <%
            HttpSession sesion1 = request.getSession();
            String mensaje2 = (String) sesion1.getAttribute("mensaje2");

            if(mensaje2 !=null){ %>
        <p style="color: red;"><%=mensaje2%></p>
        <% } %>
        <label class="form-label">Ingrese su correo: </label>
        <input type="email" name="correo_usuario">
        <br><br>
        <label class="form-label">Ingrese su tipo de usuario: </label>
        <select name="tipo_usuario">
            <option value="1">Admin</option>
            <option value="2">Normal</option>
        </select>
        <br><br>
        <input type="hidden" name="operacion" value="registro">
        <input type="submit" value="Registrarse" class="btn btn-primary">
    </form>
    <% } else{%>


    <!-- un formulario para actualizar el usuario -->
    <form method="post" action="sign_in">
        <label class="form-label">Ingrese su nombre de usuario: </label>
        <input type="text" name="nombre_usuario" value="<%=u.getNombre_usuario()  %>">
        <br><br>
        <label class="form-label">Ingrese su contraseña: </label>
        <input type="password" name="pass1" value="<%=u.getContra()  %>">
        <br><br>
        <label class="form-label">Confirme su contraseña: </label>
        <input type="password" name="pass2" value="<%=u.getContra()  %>">
        <br>
        <br>
        <%
            HttpSession sesion1 = request.getSession();
            String mensaje2 = (String) sesion1.getAttribute("mensaje2");

            if(mensaje2 !=null){ %>
        <p style="color: red;"><%=mensaje2%></p>
        <% } %>
        <label class="form-label">Ingrese su correo: </label>
        <input type="email" name="correo_usuario" value="<%=u.getCorreo()  %>">
        <br><br>
        <label class="form-label">Ingrese su tipo de usuario: </label>
        <select name="tipo_usuario">
            <% if(u.getTipo_usuario() == 1) { %>
            <option value="1" selected>Admin</option>
            <option value="2">Normal</option>
            <% } else { %>
            <option value="1">Admin</option>
            <option value="2" selected>Normal</option>
            <% } %>
        </select>
        <br><br>
        <input type="hidden" name="operacion" value="actualizar">
        <input type="hidden" name="id" value="<%=u.getId()%>">
        <input type="submit" value="Actualizar" class="btn btn-primary" >
    </form>

    <% } %>

    <br><br>
    <a href="index.jsp" class="btn btn-info">Ingresar</a>

    <%
        sesion.removeAttribute("usuario");
        sesion.removeAttribute("mensaje");
    %>
</div>
</body>
</html>