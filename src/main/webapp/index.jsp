<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/bootstrap.css">
</head>
<body>
<div class="container">
    <form method="post" action="login" class="form" >
        <label class="form-label">Ingrese nombre de usuario: </label> <br>
        <input type="text" name="nombre_usuario">
        <br><br>
        <label class="form-label">Ingrese contraseña: </label> <br>
        <input type="password" name="contra">
        <br><br>
        <%
            HttpSession sesion = request.getSession();
            String mensaje = (String) sesion.getAttribute("mensaje");

            if(mensaje!=null){ %>
        <p style="color: red;"><%=mensaje%></p>
        <% } %>
        <input type="submit" value="Iniciar sesión" class="btn btn-primary">
    </form>
    <br>
    <a class="btn btn-info" href="registrarUsuario.jsp">Registrarme</a>
    <a class="btn btn-info" href="gestionUsuario.jsp">Usuarios</a>
</div>
</body>
</html>