package org.bd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bd.utilidades.Conexion;
import org.objects.Cliente;
import org.objects.Requisito;
import org.objects.relations.ClienteRequisito;

public class ClienteRequisitoDAO extends Conexion {
	public void insertar(ClienteRequisito cr) {
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("INSERT INTO CLIENTE_HAS_REQUISITO VALUES(?, ?, ?)");
			st.setInt(1, cr.getIdCliente());
			st.setInt(2, cr.getIdRequisito());
			st.setInt(3, cr.getValor());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. Cliente/Requisito es nulo.");
		} finally {
			cerrarConexion();
		}
	}
	
	public void modificar(ClienteRequisito cr) {
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("UPDATE CLIENTE_HAS_REQUISITO SET valor = ? where Cliente_idCliente = ? AND Requisito_idRequisito = ?");
			st.setInt(1, cr.getValor());
			st.setInt(2, cr.getIdCliente());
			st.setInt(3, cr.getIdRequisito());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. Cliente/Requisito es nulo.");
		} finally {
			cerrarConexion();
		}
	}
	public void eliminar(ClienteRequisito cr) {
		try {				
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("DELETE FROM CLIENTE_HAS_REQUISITO where Cliente_idCliente = ? AND Requisito_idRequisito = ?");
			st.setInt(1, cr.getIdCliente());
			st.setInt(2, cr.getIdRequisito());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. Cliente es nulo.");
		} finally {
			cerrarConexion();
		}
	}
	
	public ArrayList<ClienteRequisito> listar() {
		ArrayList<ClienteRequisito> relaciones = new ArrayList<ClienteRequisito>();
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM CLIENTE_HAS_REQUISITO");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				ClienteRequisito cr = new ClienteRequisito(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				relaciones.add(cr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return relaciones;
	}
	
	public ArrayList<ClienteRequisito> listarPorCliente(Cliente c) {
		ArrayList<ClienteRequisito> relaciones = new ArrayList<ClienteRequisito>();
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM CLIENTE_HAS_REQUISITO WHERE Cliente_idCliente = ?");
			st.setInt(1, c.getIdCliente());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				ClienteRequisito cr = new ClienteRequisito(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				relaciones.add(cr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return relaciones;
	}
	
	public ArrayList<ClienteRequisito> listarPorRequisito(Requisito r) {
		ArrayList<ClienteRequisito> relaciones = new ArrayList<ClienteRequisito>();
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM CLIENTE_HAS_REQUISITO WHERE Requisito_idRequisito = ?");
			st.setInt(1, r.getIdRequisito());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				ClienteRequisito cr = new ClienteRequisito(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				relaciones.add(cr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return relaciones;
	}
	
	public ClienteRequisito listarPorClienteYRequisito(Cliente c, Requisito r) {
		ClienteRequisito cr = new ClienteRequisito(c.getIdCliente(), r.getIdRequisito(), 0);
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM CLIENTE_HAS_REQUISITO WHERE Requisito_idRequisito = ? AND Cliente_idCliente = ?");
			st.setInt(1, r.getIdRequisito());
			st.setInt(2, c.getIdCliente());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				cr = new ClienteRequisito(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return cr;
	}
}
