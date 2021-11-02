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


			<h1>Insertar Registros</h1>

			<form name="form1" method="get" action="ControladorProductos">
				<input type="hidden" name="instruccion" value ="insertarBBDD">
				<table width="50%" border="0">

					<tr>
						<td>Código Artículo</td>
						<td><label for="Cart"></label> <input type="text"
							name="Cart" id="Cart">
					</tr>

					<tr>
						<td width="37%">Sección</td>
						<td width="73%"><label for="seccion"></label> <input
							type="text" name="seccion" id="seccion">
					</tr>

					<tr>
						<td width="37%">Nombre Attículo</td>
						<td width="73%"><label for="Nart"></label> <input type="text"
							name="Nart" id="Nart">
					</tr>

					<tr>
						<td width="37%">Precio</td>
						<td width="73%"><label for="precio"></label> <input
							type="text" name="precio" id="precio">
					</tr>

					<tr>
						<td width="37%">Fecha</td>
						<td width="73%"><label for="fecha"></label> <input
							type="text" name="fecha" id="fecha">
					</tr>

					<tr>
						<td width="37%">Importado</td>
						<td width="73%"><label for="importado"></label> <input
							type="text" name="importado" id="importado">
					</tr>

					<tr>
						<td width="37%">País de Origen</td>
						<td width="73%"><label for="pOrigen"></label> <input
							type="text" name="pOrigen" id="pOrigen">
					</tr>
					
					<tr>
						<td><input type="submit" name="envio" id="envio" value="Enviar"></td>
						<td><input type="submit" name="borrar" id="borrar" value="Borrar"></td>
						
					</tr>
				</table>

			</form>
		
</body>
</html>