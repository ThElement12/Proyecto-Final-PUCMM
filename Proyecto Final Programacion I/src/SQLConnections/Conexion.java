package SQLConnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private final static String url = "jdbc:sqlserver://localhost:1433;" + "databaseName = NorthWind;integratedSecurity = true";
	public static Connection getConnection() throws SQLException{
		Connection myConnection = DriverManager.getConnection(url);
		return myConnection;
	}
}
