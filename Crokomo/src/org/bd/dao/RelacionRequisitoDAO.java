package org.bd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bd.utilidades.Conexion;
import org.objects.Requisito;
import org.objects.relations.RelacionRequisito;

public class RelacionRequisitoDAO extends Conexion {
	
	public void insertar(RelacionRequisito rr) {
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("INSERT INTO SE_RELACIONAN VALUES(?, ?, ?)");
			st.setInt(1, rr.getIdRequisito1());
			st.setInt(2, rr.getIdRequisito2());
			st.setString(3, rr.getTipoRelacion());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. RequisitoRelacion es nulo.");
		} finally {
			cerrarConexion();
		}
	}
	
	public void modificar(RelacionRequisito rr) {
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("UPDATE SE_RELACIONAN SET tipoRelacion = ? where Requisito_idRequisito1 = ? AND Requisito_idRequisito2 = ?");
			st.setString(1, rr.getTipoRelacion());
			st.setInt(2, rr.getIdRequisito1());
			st.setInt(3, rr.getIdRequisito2());
			
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. RequisitoRelacion es nulo.");
		} finally {
			cerrarConexion();
		}
	}
	public void eliminar(RelacionRequisito rr) {
		try {				
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("DELETE FROM SE_RELACIONAN where Requisito_idRequisito1 = ? AND Requisito_idRequisito2 = ?");
			st.setInt(1, rr.getIdRequisito1());
			st.setInt(2, rr.getIdRequisito2());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.out.println("Operación cancelada. RelacionRequisito es nulo.");
		} finally {
			cerrarConexion();
		}
	}
	
	public ArrayList<RelacionRequisito> listar() {
		ArrayList<RelacionRequisito> relaciones = new ArrayList<RelacionRequisito>();
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM SE_RELACIONAN");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				RelacionRequisito rr = new RelacionRequisito(rs.getInt(1), rs.getInt(2), rs.getString(3));
				relaciones.add(rr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return relaciones;
	}
	
	public ArrayList<RelacionRequisito> listarRelacionesDeUnRequisito(Requisito r) {
		ArrayList<RelacionRequisito> relaciones = new ArrayList<RelacionRequisito>();
		try {
			iniciarConexion();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM SE_RELACIONAN WHERE Requisito_idRequisito1 = ? OR Requisito_idRequisito2 = ?");
			st.setInt(1, r.getIdRequisito());
			st.setInt(2, r.getIdRequisito());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				RelacionRequisito rr = new RelacionRequisito(rs.getInt(1), rs.getInt(2), rs.getString(3));
				relaciones.add(rr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return relaciones;
	}
}
