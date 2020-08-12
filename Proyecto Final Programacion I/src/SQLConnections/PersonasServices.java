package SQLConnections;

import Logico.Juez;
import Logico.PUCMM;
import Logico.Participante;
import Logico.Persona;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonasServices {

	public static void setTipoPersona(String nombre) throws SQLException{
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		
		cstmt = myConnection.prepareCall("{call RegistrarTipoPersona(?)}");
		cstmt.setString("Nombre",nombre);
		
		cstmt.executeUpdate();
		cstmt.close();
		
	}
	public static void setEstadoPersonas(String nombre) throws SQLException{
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;

		cstmt = myConnection.prepareCall("{call RegistrarEstadoPersona(?)}");
		cstmt.setString("Nombre",nombre);

		cstmt.executeUpdate();
		cstmt.close();

	}
	public static ArrayList<String> getEstadoPersonas() throws SQLException{
		ArrayList<String> estados = new ArrayList<>();
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;

		cstmt = myConnection.prepareCall("{call ListarEstadoPersona}");
		cstmt.execute();
		rs = cstmt.getResultSet();
		while(rs.next()){
			estados.add(rs.getString("Nombre"));
		}
		rs.close();
		cstmt.close();
		myConnection.close();
		return estados;
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
	public static ArrayList<Persona> getPersona() throws SQLException{
		ArrayList<Persona> personas = new ArrayList<>();
		ArrayList<String> tiposPersonas = getTipoPersona();
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;


		cstmt = myConnection.prepareCall("{call ListarPersona}");
		cstmt.execute();
		rs = cstmt.getResultSet();
		while(rs.next()){
			if(tiposPersonas.get(rs.getInt("IdTipoPersona") - 1).equalsIgnoreCase("Juez")){
				personas.add(new Juez(rs.getString("Cedula"),rs.getString("Nombre"),
						rs.getString("Telefono"),PUCMM.pucmm().getMisAreas().get(rs.getInt("IdArea")), null));
			}
			else{
				personas.add(new Participante(rs.getString("Cedula"),rs.getString("Nombre"),
						rs.getString("Telefono"),PUCMM.pucmm().getMisAreas().get(rs.getInt("IdArea")), null));
			}
		}
		rs.close();
		cstmt.close();
		myConnection.close();
		return personas;
	}
	public static void setPersona(Persona persona) throws SQLException{
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;

		cstmt = myConnection.prepareCall("{call RegistrarPersona(?, ?, ?, ?, ?, ?, ?)}");
		cstmt.setString("Cedula", persona.getCedula());
		cstmt.setInt("IdArea", PUCMM.pucmm().getMisAreas().indexOf(persona.getArea()));
		cstmt.setInt("IdTipoPersona", persona instanceof Juez ? 1 : 2);
		cstmt.setString("Nombre", persona.getNombre());
		cstmt.setString("Telefono", persona.getNumero());
		cstmt.setInt("IdEstado", persona.isdisponible() ? 1 : 2);
		cstmt.setString("UrlImagen", persona.getURL());

		cstmt.executeUpdate();
		cstmt.close();
	}
	public static void updateEstado (boolean Estado, String cedula ) throws SQLException{
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;

		cstmt = myConnection.prepareCall("{call ActualizarEstadoPersona(?, ?)}");
		cstmt.setString("Cedula", cedula);
		cstmt.setInt("IdEstadoPersona", Estado ? 1 : 2);

		cstmt.executeUpdate();
		cstmt.close();

	}

}
