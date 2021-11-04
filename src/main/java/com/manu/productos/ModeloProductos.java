package com.manu.productos;

import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

public class ModeloProductos {

	// aqui tenenos el pull que es el xml donde tenemos guardado los datos de
	// conexion
	private DataSource origenDatos;

	// en este constructor nos pasaran el pull
	public ModeloProductos(DataSource origenDatos) {

		this.origenDatos = origenDatos;

	}

	// Metodo que nos proporciona una lista con la consulta sql

	public List<Productos> getProductos() throws Exception {
		
		System.out.println("----------" + "METODO GETPRODUCTOS MODELO" + "---------------");

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
		
		System.out.println("----------" + "METODO AGREGAR MODELO" + "---------------");

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

			System.out.println("llega ultimo preparement" + miPrepareStatement);

			miPrepareStatement.execute();

			miConexion.close();

			miPrepareStatement.close();

			System.out.println("añadido correctamente");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public Productos getProducto(String codigoArticulo) {
		
		System.out.println("----------" + "METODO GETPRODUCTO MODELO" + "---------------");

		Productos elproducto = null;

		Connection miconexion = null;

		PreparedStatement miPrep = null;

		ResultSet miResulset = null;

		String CArticulo = codigoArticulo;

		// Conectarse a la base de datos

		try {

			miconexion = origenDatos.getConnection();

			// Crear SQL que busque el producto

			String SQL = "SELECT * FROM Productos WHERE CODIGOARTICULO=?";

			// crear la consulta preparada

			miPrep = miconexion.prepareStatement(SQL);

			// Establecer los parametros

			miPrep.setString(1, CArticulo);

			// Ejecutar la consulta

			miResulset = miPrep.executeQuery();

			// Obtener los dados de respuesta

			if (miResulset.next()) {

				String cArt = miResulset.getString("CODIGOARTICULO");

				String seccion = miResulset.getString("SECCION");

				String nArt = miResulset.getString("NOMBREARTICULO");

				double precio = miResulset.getDouble("PRECIO");

				Date fecha = miResulset.getDate("FECHA");

				String importado = miResulset.getString("IMPORTADO");

				String pOrigen = miResulset.getString("PAISDEORIGEN");

				// Creacion un objeto de productos con los datos de la BBDD

				elproducto = new Productos(cArt, seccion, nArt, precio, fecha, importado, pOrigen);

			} else {

				throw new Exception("No hemos encontrado el producto con codigo artiuclo " + CArticulo);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return elproducto;
	}

	// Actuzaliza un producto de la BBDD

	public void actualizarProducto(Productos productoActualizado) {
		
		System.out.println("----------" + "METODO ACTUAZLIARPRODUCTOS MODELO" + "---------------");

		Connection miConexion = null;

		PreparedStatement miPreparament = null;

		System.out.println("MODELOPRODUCTOS: " + productoActualizado.toString());
		
		// Conectar a la base de datos

		try {
			miConexion = origenDatos.getConnection();

			// Preparar la consulta SQL para actualiazar

			String SQL = "UPDATE Productos SET SECCION=?,NOMBREARTICULO=?,PRECIO=?,FECHA=?,IMPORTADO=?,PAISDEORIGEN=? WHERE CODIGOARTICULO=?";

			// Crear la consulta preaparada

			miPreparament = miConexion.prepareStatement(SQL);

			// Establecer los parametros

			miPreparament.setString(1, productoActualizado.getSeccion());
			miPreparament.setString(2, productoActualizado.getnArt());
			miPreparament.setDouble(3, productoActualizado.getPrecio());

			// conversion fecha a formato SQL
			java.util.Date fechaUtil = productoActualizado.getFecha();
			java.sql.Date fecha = new java.sql.Date(fechaUtil.getTime());
			miPreparament.setDate(4, fecha);

			miPreparament.setString(5, productoActualizado.getImportado());
			miPreparament.setString(6, productoActualizado.getpOring());
			miPreparament.setString(7, productoActualizado.getcArt());

			// Ejecutar la instruccion SQL

			miPreparament.execute();

			System.out.println("registro actualizado correnctamente");
			
			miConexion.close();
			miPreparament.close();

		} catch (SQLException e) {
		
			e.printStackTrace();
		} 

	}

	public void borrarProducto(String cart) throws Exception {
		
		
		System.out.println("----------" + "METODO BORRAR MODELO" + "---------------");
		
		System.out.println(cart);
		Connection miConexion = null;

		PreparedStatement miPreparament = null;
		
		
		//Establecer la conexion con la BBDD
		
		miConexion= origenDatos.getConnection();
		
		// Crear la instruccion SQL de eleminiacion
		
		String SQL ="DELETE FROM Productos WHERE CODIGOARTICULO=?";
		
		System.out.println(SQL);
		// Preparar la consulta
		
		miPreparament = miConexion.prepareStatement(SQL);
		
		
		//Establecer los prarametros de consulta
		
		miPreparament.setString(1, cart);
		
		// Ejecutar la consulta
		
		miPreparament.execute();
		
		System.out.println("Borrado Correctamente");
		
	};

}
