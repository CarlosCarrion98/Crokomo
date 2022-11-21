package org.bd.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private final String url = "jdbc:mysql://localhost:3306/mydb";
	private final String username = "root";
	private final String password = "pris";
	protected Connection connection;

	public void iniciarConexion() {

		try  {
			connection = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public void cerrarConexion() {
		if(connection == null)
			return;
		try {
			if(!connection.isClosed()) {
				connection.close();
//				System.out.println("Conexión cerrada");
			}

		} catch (SQLException e) {
			e = new SQLException("Error al cerrar la conexión");
			e.printStackTrace();
		}
	}
}
