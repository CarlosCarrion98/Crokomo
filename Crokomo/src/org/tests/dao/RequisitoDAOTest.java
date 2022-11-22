package org.tests.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.bd.dao.RequisitoDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.objects.Requisito;

class RequisitoDAOTest {

	static RequisitoDAO rdao;
	
	@BeforeAll
	static void init() {
		rdao = new RequisitoDAO();
		rdao.activarPruebas();
	}

	@Test
	void updatesTests() {
		Requisito r = new Requisito(0, 5, "RPrueba", null, null);
		
		rdao.insertar(r);
		
		assertNotNull(rdao.obtenerRequisitoPorNombre(r.getNombreRequisito()));
		
		//Obtenemos el id
		r = rdao.obtenerRequisitoPorNombre(r.getNombreRequisito());
		
		r.setNombreRequisito("RModificado");
		
		rdao.modificar(r);
		
		r = rdao.obtenerRequisitoPorNombre(r.getNombreRequisito());
		
		assertEquals("RModificado", r.getNombreRequisito());
		
		rdao.eliminar(r);
		
		assertNull(rdao.obtenerRequisitoPorNombre(r.getNombreRequisito()));
	}

}
