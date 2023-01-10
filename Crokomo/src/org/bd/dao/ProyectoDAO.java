package org.bd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bd.utilidades.Conexion;
import org.objects.Proyecto;

public class ProyectoDAO extends Conexion {

	
	public void insertar(Proyecto p) {
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("INSERT INTO PROYECTO VALUES(null, ?)");
			st.setString(1, p.getNombreProyecto());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
	}
	
	public void eliminar(Proyecto p) {
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("DELETE FROM PROYECTO WHERE idProyecto = ?");
			st.setInt(1, p.getIdProyecto());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
	}
	
	public ArrayList<Proyecto> listar() {
		ArrayList<Proyecto> proyectos = new ArrayList<>();
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM PROYECTO");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Proyecto p = new Proyecto(rs.getInt(1), rs.getString(2), null);
				proyectos.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return proyectos;
	}
	
	public Proyecto obtenerProyectoPorNombre(String nombre) {
		Proyecto pro = null;
		
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM PROYECTO WHERE nombreProyecto = ?");
			st.setString(1, nombre);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				pro = new Proyecto(rs.getInt(1), rs.getString(2), null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("El nombre de búsqueda del proyecto es nulo");
		} finally {
			cerrarConexion();
		}
		
		return pro;
	}
}
