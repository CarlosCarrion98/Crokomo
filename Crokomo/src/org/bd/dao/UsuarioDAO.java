package org.bd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bd.utilidades.Conexion;
import org.objects.Proyecto;
import org.objects.Usuario;

public class UsuarioDAO extends Conexion{
	public void insertar(Usuario u) {
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("INSERT INTO USUARIO VALUES(?, ?, ?)");
			st.setString(1, u.getUserName());
			st.setString(2, u.getPassword());
			st.setString(3, u.getRol());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. Usuario es nulo.");
		} finally {
			cerrarConexion();
		}
	}

	public void modificar(Usuario u) {
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("UPDATE USUARIO SET contraseña = ? where nombreUsuario = ?");
			st.setString(1, u.getPassword());
			st.setString(2, u.getUserName());

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. Usuario es nulo.");
		} finally {
			cerrarConexion();
		}
	}
	public void eliminar(Usuario u) {
		try {				
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("DELETE FROM USUARIO where nombreUsuario = ?");
			st.setString(1, u.getUserName());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. Usuario es nulo.");
		} finally {
			cerrarConexion();
		}
	}

	public String login(Usuario u) {
		String rol = "Failed";
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT Rol FROM usuario where nombreUsuario = ? AND contraseña = ?");
			st.setString(1, u.getUserName());
			st.setString(2, u.getPassword());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				rol = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return rol;
	}

	public Usuario obtenerUsuarioPorNombre(String userName) {
		Usuario user = null;

		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM USUARIO WHERE nombreUsuario = ?");
			st.setString(1, userName);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				user = new Usuario(rs.getString(1), rs.getString(2), rs.getString(3), null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("El nombre de búsqueda del proyecto es nulo");
		} finally {
			cerrarConexion();
		}

		return user;
	}

	public ArrayList<Usuario> listar() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM USUARIO");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Usuario u = new Usuario(rs.getString(1), rs.getString(2), rs.getString(3), null);
				usuarios.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return usuarios;
	}
}
