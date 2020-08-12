package SQLConnections;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Logico.PUCMM;
import Logico.Recurso;

public class TrabajosServices {

	public static void setTipoTrabajo(String nombre) throws SQLException{
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		
		cstmt = myConnection.prepareCall("{call RegistrarTrabajo(?)}");
		cstmt.setString("Nombre",nombre);
		
		cstmt.executeUpdate();
		cstmt.close();
		
	}
	public static ArrayList<String> getTipoTrabajo() throws SQLException{
		ArrayList<String> tiposTrabajo = new ArrayList<>();
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;

		cstmt = myConnection.prepareCall("{call ListarTrabajos}");
		cstmt.execute();
		rs = cstmt.getResultSet();
		while(rs.next()){
			tiposTrabajo.add(rs.getString("Nombre"));
		}
		rs.close();
		cstmt.close();
		myConnection.close();

		return tiposTrabajo;
	}
}
