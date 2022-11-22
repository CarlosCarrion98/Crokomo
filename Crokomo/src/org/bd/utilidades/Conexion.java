package org.bd.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private final String url = "jdbc:mysql://localhost:3306/mydb";
	private final String urlPruebas = "jdbc:mysql://localhost:3306/pruebas";
	private final String username = "root";
	private final String password = "pris";
	protected Connection connection;
	private boolean modoPrueba = false;

	public void iniciarConexion() {

		try  {
			if(!modoPrueba)
				connection = DriverManager.getConnection(url, username, password);
			else
				connection = DriverManager.getConnection(urlPruebas, username, password);
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
			}

		} catch (SQLException e) {
			e = new SQLException("Error al cerrar la conexión");
			e.printStackTrace();
		}
	}

	public void activarPruebas() {
		modoPrueba = true;
	}
}
