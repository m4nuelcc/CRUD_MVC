<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!--   importamos libreria java util y todas las clases del paquete productos -->

<%@ page import="java.util.*, com.manu.productos.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">

	.cabecera{
		
		border-bottom: solid #F00 1px;
	}

</style>
</head>


<%
//obtiene los productos del controlador (servlets)

List<Productos> losProductos = (List<Productos>) request.getAttribute("LISTAPRODUCTOS");
%>
<body>
	<div>

		<table border="1">
			<tr>
				<th class="cabecera">CODIGO</th>
				<th class="cabecera">SECCION</th>
				<th class="cabecera">NOMBRE</th>
				<th class="cabecera">PRECIO</th>
				<th class="cabecera">FECHA</th>
				<th class="cabecera">IMPORTADO</th>
				<th class="cabecera">PAIS</th>
			</tr>
			
			<% for(Productos produc: losProductos){ %>
				
				<tr>
					<td><%=produc.getcArt() %></td>
					<td><%=produc.getSeccion() %></td>
					<td><%=produc.getnArt() %></td>
					<td><%=produc.getPrecio() %></td>
					<td><%=produc.getFecha() %></td>
					<td><%=produc.getImportado() %></td>
					<td><%=produc.getpOring() %></td>
				
				
				
				</tr>
			
			<%} %>
		</table>

	</div>
</body>
</html>