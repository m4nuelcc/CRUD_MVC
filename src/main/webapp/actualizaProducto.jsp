<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
#pantallaPrincipal {
	text-align: center;
}

#pantallaFormulario {
	height: 50px;
	width: 400px;
	margin: 0px auto;
}
</style>
</head>
<body>
	<div align="center">

		<h1 align="center">Actualizar Producto</h1>
		<div align="center">
			<form name="form1" method="get" action="ControladorProductos">
			
				<input type="hidden" name="instruccion" value="actualizarBBDD">
				
<!-- 				Campo clave oculto para enviar -->

				<input type="hidden" name="Cart" value="${ACTICULO_ACTUALIZADO.cArt}">
				
<!-- 				TABLA PARA MOSTRAR LOS DATOS -->

				<table width="50%" border="0">

					<!-- 					<tr> -->
					<!-- 						<td>Código Artículo</td> -->
					<!-- 						<td><label for="Cart"></label> <input type="text" -->
					<!-- 							name="Cart" id="Cart"> -->
					<!-- 					</tr> -->

					<tr>
						<td width="37%">Sección</td>
						<td width="73%"><label for="seccion"></label> <input
							type="text" name="seccion" id="seccion"
							value="${ACTICULO_ACTUALIZADO.seccion}">
					</tr>

					<tr>
						<td width="37%">Nombre Attículo</td>
						<td width="73%"><label for="Nart"></label> <input type="text"
							name="Nart" id="Nart" value="${ACTICULO_ACTUALIZADO.nArt}">
					</tr>

					<tr>
						<td width="37%">Precio</td>
						<td width="73%"><label for="precio"></label> <input
							type="text" name="precio" id="precio" value="${ACTICULO_ACTUALIZADO.precio}">
					</tr>

					<tr>
						<td width="37%">Fecha</td>
						<td width="73%"><label for="fecha"></label> <input
							type="text" name="fecha" id="fecha" value="${ACTICULO_ACTUALIZADO.fecha}">
					</tr>

					<tr>
						<td width="37%">Importado</td>
						<td width="73%"><label for="importado"></label> <input
							type="text" name="importado" id="importado" value="${ACTICULO_ACTUALIZADO.importado}">
					</tr>

					<tr>
						<td width="37%">País de Origen</td>
						<td width="73%"><label for="pOrigen"></label> <input
							type="text" name="pOrigen" id="pOrigen" value="${ACTICULO_ACTUALIZADO.pOring}">
					</tr>

					<tr>
						<td><input type="submit" name="envio" id="Actualizar"
							value="Enviar"></td>
						<td><input type="submit" name="borrar" id="borrar"
							value="Borrar"></td>

					</tr>
				</table>

			</form>
		</div>
	</div>
</body>
</html>