package SQLConnections;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonasServices {

	public static void setTipoPersona(String nombre) throws SQLException{
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		
		cstmt = myConnection.prepareCall("{call RegistrarTipoPersona(?)");
		cstmt.setString("Nombre",nombre);
		
		cstmt.executeUpdate();
		cstmt.close();
		
	}
	public static ArrayList<String> getTipoPersona() throws SQLException{
		ArrayList<String> tiposPersonas = new ArrayList<>();
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;

		cstmt = myConnection.prepareCall("{call ListarTipoPersona}");
		cstmt.execute();
		rs = cstmt.getResultSet();
		while(rs.next()){
			tiposPersonas.add(rs.getString("Nombre"));
		}
		rs.close();
		cstmt.close();
		myConnection.close();

		return tiposPersonas;
	}

}
