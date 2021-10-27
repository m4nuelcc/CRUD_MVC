package com.manu.productos;

import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.sql.DataSource;

public class ModeloProductos {

	private DataSource origenDatos;

	//en este constructor nos pasaran el pull
	public ModeloProductos(DataSource origenDatos) {

		this.origenDatos = origenDatos;

	}

	// Metodo que nos proporciona una lista con la consulta sql

	public List<Productos> getProductos() throws Exception {

		List<Productos> productos = new ArrayList<>();

		Connection miConexion = null;

		Statement miStatement = null;

		ResultSet miResulset = null;

		// --------------establecer la conexion----------------

		miConexion = origenDatos.getConnection();

		// --------------crear sentencia sql y Statement--------------------

		String Sql = "SELECT * FROM Productos";

		miStatement = miConexion.createStatement();

		// -------------ejecutar la consulta--------------------

		miResulset = miStatement.executeQuery(Sql);

		// -------------resultado de la consulta-----------------

		while (miResulset.next()) {

			String cArt = miResulset.getString("CODIGOARTICULO");
			String seccion = miResulset.getString("SECCION");
			String nArt = miResulset.getString("NOMBREARTICULO");
			double precio = miResulset.getDouble("PRECIO");
			Date fecha = miResulset.getDate("FECHA");
			String importado = miResulset.getString("IMPORTADO");
			String pOrigen = miResulset.getString("PAISDEORIGEN");

			Productos temProd = new Productos(cArt,seccion, nArt, precio, fecha, importado, pOrigen);

			productos.add(temProd);

		}

		return productos;
	};
}
