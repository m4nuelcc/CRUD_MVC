package com.manu.productos;

import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

public class ModeloProductos {

	private DataSource origenDatos;

	// en este constructor nos pasaran el pull
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

			// Creacion un objeto de productos con los datos de la BBDD

			Productos temProd = new Productos(cArt, seccion, nArt, precio, fecha, importado, pOrigen);

			// Agregamos el objeto productos a ArrayList para devolver el valor

			productos.add(temProd);

		}

		return productos;
	}

	public void agregarElNuevoProducto(Productos nuevoProducto) throws Exception {

		// Obtener la conexion
		

		Connection miConexion = null;

		PreparedStatement miPrepareStatement = null;

		try {
			
			miConexion = origenDatos.getConnection();
			
			System.out.println("conectado" + miConexion);

			// Crear instruccion SQL. Crear la consulta preparada (statement)

			String SentenciaSQL = "INSERT INTO Productos (CODIGOARTICULO,SECCION,NOMBREARTICULO,PRECIO,FECHA,IMPORTADO,PAISDEORIGEN) VALUES (?,?,?,?,?,?,?)";

			miPrepareStatement = miConexion.prepareStatement(SentenciaSQL);
		
			// Crear instruccion SQL. crear la consulta preparada (statement).
			
			miPrepareStatement.setString(1, nuevoProducto.getcArt());
			
			miPrepareStatement.setString(2, nuevoProducto.getSeccion());
			
			System.out.println("seccion " + nuevoProducto.getSeccion());
			
			miPrepareStatement.setString(3, nuevoProducto.getnArt());
			
			miPrepareStatement.setDouble(4, nuevoProducto.getPrecio());
			
			java.util.Date utilDate = nuevoProducto.getFecha();
			
			java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
			
			miPrepareStatement.setDate(5, fechaConvertida);

			miPrepareStatement.setString(6, nuevoProducto.getImportado());
			
			miPrepareStatement.setString(7, nuevoProducto.getpOring());
			

			// Ejecutar SQL
			
			System.out.println("llega ultimo preparement"+ miPrepareStatement);
			
			miPrepareStatement.execute();
			
		System.out.println("añadido correctamente");

		} catch (Exception e) {

			
			
		}


	};

}
