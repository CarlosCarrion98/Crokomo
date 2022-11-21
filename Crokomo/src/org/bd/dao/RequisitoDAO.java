package org.bd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bd.utilidades.Conexion;
import org.objects.Cliente;
import org.objects.Proyecto;
import org.objects.Requisito;

public class RequisitoDAO extends Conexion {
	public void insertar(Requisito r) {
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("INSERT INTO REQUISITO VALUES(?, ?)");
			st.setInt(1, r.getIdRequisito());
			st.setInt(2, r.getEsfuerzo());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. Requisito es nulo.");
		} finally {
			cerrarConexion();
		}
	}
	
	public void modificar(Requisito r) {
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("UPDATE REQUISITO SET esfuerzo = ? where idRequisito = ?");
			st.setInt(1, r.getEsfuerzo());
			st.setInt(2, r.getIdRequisito());
			
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. Requisito es nulo.");
		} finally {
			cerrarConexion();
		}
	}
	public void eliminar(Requisito r) {
		try {				
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("DELETE FROM REQUISITO where idRequisito = ?");
			st.setInt(1, r.getIdRequisito());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. Requisito es nulo.");
		} finally {
			cerrarConexion();
		}
	}
	
	public ArrayList<Requisito> listar() {
		ArrayList<Requisito> requisitos = new ArrayList<Requisito>();
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM REQUISITO");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				//Requisito está preparado para recibir valores nulos en las listas.
				Requisito r = new Requisito(rs.getInt(1), rs.getInt(2), rs.getString(3), null, null);
				requisitos.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return requisitos;
	}
	
	public ArrayList<Requisito> listarPorCliente(Cliente c) {
		ArrayList<Requisito> requisitos = new ArrayList<Requisito>();
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT r.idRequisito, r.esfuerzo, r.nombreRequisito"
					+ " FROM REQUISITO r, CLIENTE_HAS_REQUISITO cr"
					+ " WHERE r.idRequisito = cr.Requisito_idRequisito AND cr.Cliente_idCliente = ?");
			st.setInt(1, c.getIdCliente());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				//Requisito está preparado para recibir valores nulos en las listas.
				Requisito r = new Requisito(rs.getInt(1), rs.getInt(2), rs.getString(3), null, null);
				requisitos.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return requisitos;
	}
	
	public ArrayList<Requisito> listarPorProyecto(Proyecto p) {
		ArrayList<Requisito> requisitos = new ArrayList<Requisito>();
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT DISTINCT r.idRequisito, r.esfuerzo, r.nombreRequisito"
					+ " FROM REQUISITO r, CLIENTE_HAS_REQUISITO cr, Cliente c"
					+ " WHERE r.idRequisito = cr.Requisito_idRequisito AND cr.Cliente_idCliente = c.idCliente AND c.idProyecto = ?");
			st.setInt(1, p.getIdProyecto());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				//Requisito está preparado para recibir valores nulos en las listas.
				Requisito r = new Requisito(rs.getInt(1), rs.getInt(2), rs.getString(3), null, null);
				requisitos.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return requisitos;
	}
}
