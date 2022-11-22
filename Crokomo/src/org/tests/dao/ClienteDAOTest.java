package org.tests.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.bd.dao.ClienteDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.objects.Cliente;
import org.objects.Proyecto;

class ClienteDAOTest {
	
	private static ClienteDAO cdao;
	
	@BeforeAll
	public static void init() {
		cdao = new ClienteDAO();
		cdao.activarPruebas();
		
	}
	
	@Test
	public void updatesTest() {
		//Se asocia a un proyecto de prueba para que no falle la fk
		Cliente prueba = new Cliente(0, 3, "Prueba", 10);
		
		cdao.insertar(prueba);
		
		assertNotNull(cdao.obtenerClientePorNombre(prueba.getNombreCliente()));
		
		//Obtenemos al cliente para tener su id para la modificación, ya que el ID se coloca solo en la bd con autoincrement
		prueba = cdao.obtenerClientePorNombre("Prueba");
		
		prueba.setNombreCliente("Probando");
		
		cdao.modificar(prueba);
		
		assertNotNull(cdao.obtenerClientePorNombre("Probando"));
		
		cdao.eliminar(prueba);
		
		assertNull(cdao.obtenerClientePorNombre("Probando"));
	}
	
	//En la base de datos de pruebas, hay 4 clientes; 3 pertecen a 1 proyecto y 1 al otro
	@Test
	void listarTodosLosClientesTest() {
		ArrayList<Cliente> clientesDePrueba = cdao.listar();
		assertEquals(4, clientesDePrueba.size());
	}

	@Test
	public void listarPorProyectoTest() {
		Proyecto p = new Proyecto(10, "", null);
		ArrayList<Cliente> clientesProyecto = cdao.listarPorProyecto(p);
		assertEquals(3, clientesProyecto.size());
	}
	
}
