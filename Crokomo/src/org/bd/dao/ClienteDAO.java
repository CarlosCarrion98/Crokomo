package org.bd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bd.utilidades.Conexion;
import org.objects.Cliente;
import org.objects.Proyecto;

public class ClienteDAO extends Conexion {
	
	public void insertar(Cliente c) {
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("INSERT INTO CLIENTE VALUES(null, ?, ?, ?)");
			st.setInt(1, c.getPeso());
			st.setString(2, c.getNombreCliente());
			st.setInt(3, c.getIdProyecto());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. Cliente es nulo.");
		} finally {
			cerrarConexion();
		}
	}
	
	public void modificar(Cliente c) {
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("UPDATE CLIENTE SET idCliente = ?, peso = ? where idCliente = ?");
			st.setInt(1, c.getIdCliente());
			st.setInt(2, c.getPeso());
			st.setInt(3, c.getIdCliente());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. Cliente es nulo.");
		} finally {
			cerrarConexion();
		}
	}
	public void eliminar(Cliente c) {
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("DELETE FROM CLIENTE where idCliente = ?");
			st.setInt(1, c.getIdCliente());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. Cliente es nulo.");
		} finally {
			cerrarConexion();
		}
	}
	
	public ArrayList<Cliente> listar() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM CLIENTE");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
				clientes.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return clientes;
	}
	
	public ArrayList<Cliente> listarPorProyecto(Proyecto p) {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM CLIENTE WHERE idProyecto = ?");
			st.setInt(1, p.getIdProyecto());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
				clientes.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return clientes;
	}
}
