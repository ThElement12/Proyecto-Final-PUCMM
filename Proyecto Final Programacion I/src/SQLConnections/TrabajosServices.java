package SQLConnections;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class TrabajosServices {

	public static void setTipoTrabajo(String nombre) throws SQLException{
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		
		cstmt = myConnection.prepareCall("{call RegistrarTrabajo(?)");
		cstmt.setString("Nombre",nombre);
		
		cstmt.executeUpdate();
		cstmt.close();
		
	}
}
