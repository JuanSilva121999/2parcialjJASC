<%@page import="com.emergentes.modelo.Productos"%>
<%@page import="com.emergentes.modelo.GestorProductos"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("agenda") == null) {
        GestorProductos objeto1 = new GestorProductos();
        objeto1.insertarTarea(new Productos(1, "Coca Cola", 100, 10, "Bebidas"));
        objeto1.insertarTarea(new Productos(2, "Pepsi", 50, 11, "Bebidas"));
        objeto1.insertarTarea(new Productos(3, "Flock", 100, 2.50, "Galletas"));
        objeto1.insertarTarea(new Productos(4, "Serranitas", 80, 1.50, "Galletas"));

        session.setAttribute("agenda", objeto1);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>2°Parcial</title>
    </head>
    <body>
        <section style="padding-top: 25px;margin-left: 15%;margin-right: 15%;">
            <fieldset><legend> </legend>
                <div aling="center">
            <p>SEGUNDO PARCIAL TEM-742</p>
            <p>Nombre: Juan Alberto Silva Cayo</p>
            <p>Carnet: 10013618</p>
            </div>
            </fieldset>
        </section>
        <h1>Productos</h1>
        <a href="controlador?op=nuevo">Nuevo</a>
        <table border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Descripcion</th>
                    <th>Cantidad</th>
                    <th>Precio</th>
                    <th>Categoria</th>
                    <th>Editar</th>
                    <th>Eliminar</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${sessionScope.agenda.getLista()}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.descripcion}</td>
                        <td>${item.cantidad}</td>
                        <td>${item.precio}</td>
                        <td>${item.categoria}</td>
                        <td><a href="controlador?op=modificar&id=${item.id}">Editar</a></td>
                        <td><a href="controlador?op=eliminar&id=${item.id}">Eliminar</a></td>
                    </tr>
                </c:forEach>


            </tbody>
        </table>
    </body>
</html>
