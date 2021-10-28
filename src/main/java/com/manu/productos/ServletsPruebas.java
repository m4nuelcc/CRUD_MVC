package com.manu.productos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;

//import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class ServletsPruebas
 */
@WebServlet("/ServletsPruebas")

public class ServletsPruebas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Definir o establecer el SataSource

	@Resource(name = "jdbc/Productos")

	private DataSource miPoll;

	public ServletsPruebas() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Crear el objeto printWriter

		PrintWriter salida = response.getWriter();

		response.setContentType("text/html");

		// Crear conexión con BBDD
		
		Connection miConexion=null;
		
		Statement miStatement = null;
		
		ResultSet miResulset=null;
		
		try {
			
			miConexion=miPoll.getConnection();
			
			miStatement = miConexion.createStatement();
			
			String Sql ="SELECT * FROM Productos";
			
			miResulset= miStatement.executeQuery(Sql);
			
			salida.println("<html><body>");
			
			salida.println("<div style=\"text-align: center;\">");
			
			
			
			while(miResulset.next()) {
				
				//String nombre = miResulset.getNString(3);
				
				
				salida.println(miResulset.getNString(2)+ "   " + miResulset.getNString(3) +"   " + miResulset.getDouble(4)+ "   " + miResulset.getNString(6)+"<br>");
				salida.println("");
				
				
				
			}
			
			salida.println("</div>");
			salida.println("<html><body>");
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
