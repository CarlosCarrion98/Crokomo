package org.bd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bd.utilidades.Conexion;
import org.objects.Proyecto;
import org.objects.Usuario;
import org.objects.relations.UsuarioProyecto;

public class UsuarioProyectoDAO extends Conexion{
	public void insertar(UsuarioProyecto up) {
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("INSERT INTO PROYECTO_HAS_USUARIO VALUES(?, ?)");
			st.setInt(1, up.getIdProyecto());
			st.setString(2, up.getUserName());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. UsuarioProyecto es nulo.");
		} finally {
			cerrarConexion();
		}
	}
	
	public void eliminar(UsuarioProyecto up) {
		try {				
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("DELETE FROM PROYECTO_HAS_USUARIO where nombreUsuario = ? AND idProyecto = ?");
			st.setString(1, up.getUserName());
			st.setInt(2, up.getIdProyecto());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. UsuarioProyecto es nulo.");
		} finally {
			cerrarConexion();
		}
	}
	
	public ArrayList<UsuarioProyecto> listar() {
		ArrayList<UsuarioProyecto> usuarios = new ArrayList<>();
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM PROYECTO_HAS_USUARIO");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				UsuarioProyecto uc = new UsuarioProyecto(rs.getInt(1), rs.getString(2));
				usuarios.add(uc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return usuarios;
	}
	
	public ArrayList<Proyecto> listarProyectosPorUsuario(Usuario u) {
		ArrayList<Proyecto> proyectosUsuario = new ArrayList<>();
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT p.idProyecto, p.nombreProyecto"
					+ " FROM Proyecto p, Proyecto_has_usuario pu"
					+ " WHERE p.idProyecto = pu.idProyecto AND pu.nombreUsuario = ?");
			st.setString(1, u.getUserName());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Proyecto p = new Proyecto(rs.getInt(1), rs.getString(2), null);
				proyectosUsuario.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return proyectosUsuario;
	}
	
	public boolean existeRelacion(Proyecto p, Usuario u) {
		UsuarioProyecto temp = null;
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT pu.idProyecto, pu.nombreUsuario"
					+ " FROM Proyecto_has_usuario pu"
					+ " WHERE pu.idProyecto = ? AND pu.nombreUsuario = ?");
			st.setInt(1, p.getIdProyecto());
			st.setString(2, u.getUserName());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				temp = new UsuarioProyecto(rs.getInt(1), rs.getString(2));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		return temp == null ? false : true;
	}
}
