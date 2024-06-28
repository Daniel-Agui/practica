<%@ page import="mx.edu.utez.practica.dao.UsuarioDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mx.edu.utez.practica.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/datatables.css">
</head>
<body>
    <div class="container">
        <div class="row m-2"> <h1>USUARIOS - AGUILAR POPOCA ANGEL DANIEL - 20233tn088</h1></div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Contraseña</th>
                <th>Correo</th>
                <th>Tipo</th>
                <th>Estado</th>
                <th>Actualizar</th>
                <th>Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <%
                UsuarioDao dao = new UsuarioDao();
                ArrayList<Usuario> lista = dao.getAll();
                for(Usuario u : lista){%>
            <tr>
                <td><%=u.getId()%></td>
                <td><%=u.getNombre_usuario()%></td>
                <td><%=u.getContra()%></td>
                <td><%=u.getCorreo()%></td>
                <td><%=u.getTipo_usuario()%></td>
                <td><%=u.isEstado()%></td>
                <td><a href="login?id=<%=u.getId()%>" class="btn btn-primary">Actualizar</a></td>
                <td>
                    <form action="eliminar" method="post">
                        <input type="hidden" name="id" class="" value="<%=u.getId()%>">
                        <input type="hidden" name="estado" value="<%=u.isEstado()%>">
                        <input type="submit" class="btn btn-danger" value="<%=u.isEstado() ? "Eliminar" : "Activar"%>">
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <script src="${pageContext.request.contextPath}/JS/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/JS/jquery-3.7.0.js"></script>
        <script src="${pageContext.request.contextPath}/JS/datatables.js"></script>
        <script src="${pageContext.request.contextPath}/JS/dataTables.bootstrap5.js"></script>

        <script src="${pageContext.request.contextPath}/JS/es-MX.json"></script>
        <script>
            document.addEventListener('DOMContentLoaded', () => {
                const table = document.getElementById('example');
                new DataTable(table,{
                    language:{
                        url:'${pageContext.request.contextPath}/JS/es-MX.json'
                    }
                });
            });
        </script>
    </div>
</body>
</html>
