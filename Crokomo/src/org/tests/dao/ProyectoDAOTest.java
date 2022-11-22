package org.tests.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.bd.dao.ProyectoDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.objects.Proyecto;

class ProyectoDAOTest {

	private static ProyectoDAO pdao;
	
	@BeforeAll
	static void init() {
		pdao = new ProyectoDAO();
		pdao.activarPruebas();
	}
	
	@Test
	public void updatesTests() {
		Proyecto prueba = new Proyecto(0, "Prueba", null);
		
		pdao.insertar(prueba);
		
		prueba = pdao.obtenerProyectoPorNombre(prueba.getNombreProyecto());
		
		assertNotNull(prueba);
		
		pdao.eliminar(prueba);
		
		prueba = pdao.obtenerProyectoPorNombre(prueba.getNombreProyecto());
		
		assertNull(prueba);
		
	}
	
	@Test
	public void listarTodosLosProyectos() {
		ArrayList<Proyecto> proyectos = new ArrayList<>();
		proyectos = pdao.listar();
		assertEquals(2, proyectos.size());
	}

}
