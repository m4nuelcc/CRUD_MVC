package com.manu.productos;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class ControladorProductos
 */
@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModeloProductos modeloProductos;

	// llamamos al xml de configuracion y lo guardamos en
	// miPoll-----------------------

	@Resource(name = "jdbc/Productos")

	private DataSource miPoll;

	// ---------------------------------------------------------------------------------

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		try {

			// cargamos el xml en el constructor de modelosproductos, para
			// que tenga los datos de conexion en el metodo getProductos y pueda
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

		// Leer el parametro del formulario

		String elcomando = request.getParameter("instruccion");

		System.out.println("----------EL COMANDO " + elcomando + "---------------");

		// Sino se envia el parametro, listar productos

		if (elcomando == null)
			elcomando = "listar";

		// Redirigir el flujo de ejecucion al mentodo adecuado

		switch (elcomando) {

		case "listar":

			try {
				listarProductos(request, response);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			break;

		case "insertarBBDD":

			try {
				agregarProducto(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;

		case "cargar":

			try {
				cargarProducto(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "borrar":

			try {
				borrarProducto(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

		case "actualizarBBDD":

			try {

				actualizaProductos(request, response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		default:

			try {
				listarProductos(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		}

	}

	private void borrarProducto(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("----------" + "METODO BORRAR CONTROLADOR" + "---------------");

		// Campturar el codigo articulo

		String Cart = request.getParameter("CArticuloBorrar");
		
		System.out.println("valor de codigo articulo en controlador: " + Cart);

		// Borrar producto de la BBDD

		modeloProductos.borrarProducto(Cart);

		// Volver al listado de Productos

		listarProductos(request, response);

	}

	private void actualizaProductos(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Productos productoActualizado = null;

		System.out.println("----------" + "METODO ACTUALIZAR CONTROLADOR" + "---------------");

		// Obtener los datos del formulario actuzaliza.jsp

		String Cart = request.getParameter("Cart");

		String seccion = request.getParameter("seccion");

		String Nart = request.getParameter("Nart");

		Double precio = Double.parseDouble(request.getParameter("precio"));

		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

		Date fecha = null;

		try {
			fecha = formatoFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String importado = request.getParameter("importado");

		String pOrigen = request.getParameter("pOrigen");

		// Empaquetarlos en un objeto Producto

		productoActualizado = new Productos(Cart, seccion, Nart, precio, fecha, importado, pOrigen);
		
		System.out.println("METODO  ACTUALIZAR: " + productoActualizado.toString());

		// enviarlo al Modeloproductos para actualizar la BBDD

		modeloProductos.actualizarProducto(productoActualizado);

		// volver al listado

		listarProductos(request, response);

	}

//--------------------METODO PARA MOSTAR LOS DATOS QUE HEMOS SELECCIONADO DE LA LISTA--------
	

	private void cargarProducto(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("----------" + "METODO CARGAR CONTROLADOR" + "---------------");

		Productos elProducto = null;

		// leer el codigo articulo que viene del listado (jsp)

		String codigoArticulo = request.getParameter("CArticulo");

		// Enviar codigo articulo a modeloProductos

//		Productos elProducto = modeloProductos.getProducto(codigoArticulo);

		elProducto = modeloProductos.getProducto(codigoArticulo);

		System.out.println(elProducto.toString());

		// colocar artributo correspondiente al codigo articulo

		request.setAttribute("ACTICULO_ACTUALIZADO", elProducto);

		// enviar producto al formulario de actualizar (jsp)

		RequestDispatcher miDisp = request.getRequestDispatcher("/actualizaProducto.jsp");

		miDisp.forward(request, response);

	}

	// ----------------METODO PARA AGREGAR PRODUCTOS----------------------------

	private void agregarProducto(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("----------" + "METODO AGREGAR CONTROLADOR" + "---------------");

		// leer la informacion del producto que viene en el formulario

		String Cart = request.getParameter("Cart");

		String seccion = request.getParameter("seccion");

		String Nart = request.getParameter("Nart");

		Double precio = Double.parseDouble(request.getParameter("precio"));

		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

		Date fecha = null;

		try {
			fecha = formatoFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String importado = request.getParameter("importado");

		String pOrigen = request.getParameter("pOrigen");

		// crear un objeto de tipo producto

		Productos nuevoProducto = new Productos(Cart, seccion, Nart, precio, fecha, importado, pOrigen);

		// Enviar el objeto al modelo y despues insertar el objeto en la BBDD

		try {
			modeloProductos.agregarElNuevoProducto(nuevoProducto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// volver a listar la lista de productos

		listarProductos(request, response);

	}

	// ------------------------METODO QUE LISTA PRODUCTOS---------------------------
	

	private void listarProductos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("----------" + "METODO LISTAR CONTROLADOR" + "---------------");

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
