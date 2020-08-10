package SQLConnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private final static String url = "jdbc:sqlserver://localhost:1433; databaseName = Eventos;";
	private final static String user = "sa";
	private final static String password = "1234";
	public static Connection getConnection() throws SQLException{
		
		try {
			Connection myConnection = DriverManager.getConnection(url,user,password);
			return myConnection;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
