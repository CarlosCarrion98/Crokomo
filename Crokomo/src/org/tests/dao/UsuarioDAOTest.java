package org.tests.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
		
		udao.insertar(u);
		
		u = udao.obtenerUsuarioPorNombre(u.getUserName());
		
		assertNotNull(u);
		
		u.setPassword("pass");
		
		udao.modificar(u);
		
		u = udao.obtenerUsuarioPorNombre(u.getUserName());
		
		assertEquals("pass", u.getPassword());
		
		udao.eliminar(u);
		
		assertNull(udao.obtenerUsuarioPorNombre(u.getUserName()));
	}
	
	@Test
	public void loginTest() {
		//Existe un usuario con estas credenciales en la base de datos
		Usuario u = new Usuario("Soy", "Correcto", "", null);
		
		String rol = udao.login(u);
		
		assertEquals("user", rol);
	}
	
	@Test
	public void todosLosUsuariosTest() {
		ArrayList<Usuario> users = new ArrayList<>();
		
		users = udao.listar();
		
		assertEquals(2, users.size());
	}

}
