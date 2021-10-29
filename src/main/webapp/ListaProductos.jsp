<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- IMPORTAMOS LAS LIBRERIAS DE CORE -->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- ---------------------------------ESTILO DE LA WEB----------------------------------->

<style type="text/css">
.cabecera {
	font-size: 1.2em;
	font-weight: bold;
	background-color: #2980b9;
	color: white;
}

.filas {
	background-color: #5dade2;
}

table {
	float: left;
}

#ContenedorBoton {
	margin-left: 10px;

}
</style>

<!-- --------------------------------------------------------------------------------- -->

</head>

<body>
	<div>

		<table>
			<tr>
				<th class="cabecera">CODIGO</th>
				<th class="cabecera">SECCION</th>
				<th class="cabecera">NOMBRE</th>
				<th class="cabecera">PRECIO</th>
				<th class="cabecera">FECHA</th>
				<th class="cabecera">IMPORTADO</th>
				<th class="cabecera">PAIS</th>

			</tr>

			<c:forEach var="produc" items="${LISTAPRODUCTOS}">

				<tr>
					<td class="filas">${produc.cArt}</td>
					<td class="filas">${produc.seccion}</td>
					<td class="filas">${produc.nArt}</td>
					<td class="filas">${produc.precio}</td>
					<td class="filas">${produc.fecha}</td>
					<td class="filas">${produc.importado}</td>
					<td class="filas">${produc.pOring}</td>


				</tr>

			</c:forEach>

		</table>

	</div>
	<div id="ContenedorBoton">

		<input type="button" value="Insertar Registro"  onclick="window.location.href='inserta_producto.jsp'"/>
	</div>
</body>
</html>