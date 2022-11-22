package org.tests.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.bd.dao.UsuarioDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.objects.Usuario;

class UsuarioDAOTest {
	private static UsuarioDAO udao;
	
	@BeforeAll
	public static void init() {
		udao = new UsuarioDAO();
		udao.activarPruebas();
	}
	
	@Test
	void updatesTests() {
		Usuario u = new Usuario("Prueba", "PasswordPrueba", "USER", null);
	}

}
