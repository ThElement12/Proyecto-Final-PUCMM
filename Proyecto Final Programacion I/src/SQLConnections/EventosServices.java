package SQLConnections;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;

import Logico.Evento;
import Logico.PUCMM;
import Logico.Recurso;
import javafx.util.Pair;

public class EventosServices {

	
	public static void regAreas(String area) throws SQLException{
		//String[] areas = {"Fisica", "Quimica", "Biologia/Medicina", "Mercadeo/Administracion", "Informatica/Redes"};
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		
		cstmt = myConnection.prepareCall("{call RegistrarArea(?)}");
		cstmt.setString("Nombre", area);

		cstmt.executeUpdate();
		cstmt.close();
		
	}
	public static void setEvento(Evento evento, int idArea, int idCampus) throws SQLException{
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		String inicio = new SimpleDateFormat("dd-MM-yyy").format(evento.getFechaIni()) + new SimpleDateFormat("HH:mm:ss").format(evento.getHorarioInicio());
		String finalito = new SimpleDateFormat("dd-MM-yyy").format(evento.getFechaFin()) + new SimpleDateFormat("HH:mm:ss").format(evento.getHorarioFin());
		
		Date ini = null;
		Date fin = null;
		try {
			ini = new SimpleDateFormat("dd-MM-yyy HH:mm:ss").parse(inicio);
			fin  = new SimpleDateFormat("dd-MM-yyy HH:mm:ss").parse(finalito);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
		
		cstmt = myConnection.prepareCall("{call RegistrarEvento(?, ?, ?, ?, ?)}");
		cstmt.setInt("IdArea", idArea);
		cstmt.setString("Nombre", evento.getNombre());
		cstmt.setInt("IdCampus", idCampus);
		cstmt.setTimestamp("FechaInicio", new java.sql.Timestamp(ini.getTime()));
		cstmt.setTimestamp("FechaFin", new java.sql.Timestamp(fin.getTime()));
		
		cstmt.executeUpdate();
		cstmt.close();
	}
	public static void setCampus(String campus) throws SQLException {
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		
		cstmt = myConnection.prepareCall("{call RegistrarCampus(?)");
		cstmt.setString("Nombre",campus);
		
		cstmt.executeUpdate();
		cstmt.close();
	}
	public static void setLugar(int idCampus, String nombre) throws SQLException{
		
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		
		cstmt = myConnection.prepareCall("{call RegistrarLugar(?)");
		cstmt.setString("Nombre",nombre);
		cstmt.setInt("IdCampus", idCampus);
		
		cstmt.executeUpdate();
		cstmt.close();
	}
	public static void getEventos() throws SQLException{
		ArrayList<Evento> eventos = new ArrayList<>();
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		cstmt = myConnection.prepareCall("{call Rpt_ListarEvento}");
		cstmt.execute();
		rs = cstmt.getResultSet();
		while(rs.next()) {
			String hourInit = String.valueOf(rs.getTime("horaIni"));
			String hourFin = String.valueOf(rs.getTime("horaFin"));
			
			eventos.add(new Evento(String.valueOf(rs.getInt("IdEvento")), rs.getString("NombreEvento"), rs.getString("Area"), 
					rs.getString("Lugar"), rs.getString("Campus"), rs.getDate("FechaInicio"), 
					rs.getDate("FechaFin"), Time.valueOf(sdf.format(rs.getDate("FechaInicio"))), Time.valueOf(sdf.format(rs.getDate("FechaFin")))));
		}
		PUCMM.pucmm().setMisEventos(eventos);
		rs.close();
		cstmt.close();
		myConnection.close();
		
	}
	public static ArrayList<String> getAreas() throws SQLException{

		ArrayList<String> areas = new ArrayList<>();
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;

		cstmt = myConnection.prepareCall("{call ListarArea}");
		cstmt.execute();
		rs = cstmt.getResultSet();
		while(rs.next()){
			areas.add(rs.getString("Nombre"));
		}

		rs.close();
		cstmt.close();
		myConnection.close();
		
		return areas;
	}
	public static ArrayList<String> getCampus() throws SQLException{
		ArrayList<String> campus = new ArrayList<>();
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;

		cstmt = myConnection.prepareCall("{call ListarCampus}");
		cstmt.execute();
		rs = cstmt.getResultSet();
		while(rs.next()){
			campus.add(rs.getString("Nombre"));
		}
		rs.close();
		cstmt.close();
		myConnection.close();

		return campus;
	}
	public static ArrayList<String> getLugares() throws SQLException{
		ArrayList<String> lugares = new ArrayList<>();
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;

		cstmt = myConnection.prepareCall("{call ListarLugar}");
		cstmt.execute();
		rs = cstmt.getResultSet();
		while(rs.next()){
			lugares.add(rs.getString("Nombre"));
		}
		rs.close();
		cstmt.close();
		myConnection.close();

		return lugares;
	}
	public static ArrayList<String> getTipoRecurso() throws SQLException{
		ArrayList<String> tipoRecursos = new ArrayList<>();
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;

		cstmt = myConnection.prepareCall("{call ListarTipoRecurso}");
		cstmt.execute();
		rs = cstmt.getResultSet();
		while(rs.next()){
			tipoRecursos.add(rs.getString("Nombre"));
		}
		rs.close();
		cstmt.close();
		myConnection.close();

		return tipoRecursos;
	}
	public static ArrayList<Recurso> getRecursosByEvento(int id) throws SQLException{
		ArrayList<Recurso> recursos = new ArrayList<>();
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;

		cstmt = myConnection.prepareCall("{call Rpt_ListarRecursoEvento(?)}");
		cstmt.setInt("IdEvento", id);
		boolean results = cstmt.execute();
		int rowsAffectd = 0;

		while(results || rowsAffectd != -1){
			if(results){
				rs = cstmt.getResultSet();
			}else{
				rowsAffectd = cstmt.getUpdateCount();
			}
			results = cstmt.getMoreResults();
		}
		while(rs.next()){
			recursos.add(new Recurso(Integer.valueOf(rs.getString("IdRecurso")),rs.getString("ModeloRecurso"),
							rs.getString("TipoRecurso"),rs.getString("EstadoRecurso")));
		}

		rs.close();
		cstmt.close();
		myConnection.close();
		return recursos;
	}
	public static ArrayList<Pair<String,Integer>> getCantidadEventoPorTipo() throws SQLException{
		ArrayList<Pair<String,Integer>> eventCant = new ArrayList<>();
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;

		cstmt = myConnection.prepareCall("{call Rpt_ListarCantidadEventosCelebradosPorTipo}");
		cstmt.execute();
		rs = cstmt.getResultSet();
		while(rs.next()){
			eventCant.add(new Pair<String,Integer>(rs.getString("TipoEvento"),rs.getInt("CantidadEventos")));
		}
		rs.close();
		cstmt.close();
		myConnection.close();
		return eventCant;
	}

}
