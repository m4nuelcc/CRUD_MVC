package com.manu.productos;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ControladorProductos
 */
@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	private ModeloProductos modeloProductos;
	
	
    //llamamos al xml de configuracion y lo guardamos en miPoll-----------------------
	
	@Resource(name = "jdbc/Productos")

	private DataSource miPoll;

	//---------------------------------------------------------------------------------
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		try {
			
			// cargamos el xml en el constructor de modelosproductos, para
			//que tenga los datos de conexion en el metodo getProductos y pueda 
			// conectarse.
			
			modeloProductos = new ModeloProductos(miPoll);

		} catch (Exception e) {

			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		// Obetener la lista de productos desde el modelo

		List<Productos> productos;

		try {
			
			// conexion a la base de datos y obtencion de todos los datos en un Arraylist
			
			productos = modeloProductos.getProductos();

			// agregar lista de prodcutos al request
			
			request.setAttribute("LISTAPRODUCTOS", productos);

			// enviar request a la pagina JSP
			
			RequestDispatcher miDisp = request.getRequestDispatcher("/ListaProductos.jsp");
			
			miDisp.forward(request, response);
			
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
