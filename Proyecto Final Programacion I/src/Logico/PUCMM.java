package Logico;


import SQLConnections.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Date;


public class PUCMM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4176944916332368203L;
	private static ArrayList<Recurso> misRecursos;
	private static ArrayList<Evento> misEventos;
	private static ArrayList<Persona> misPersonas;

	private static ArrayList<String> misAreas;
	private static ArrayList<String> misCampus;
	private static ArrayList<String> misLugares;

	private static ArrayList<String> misTiposRecursos;
	private static ArrayList<String> misEstadosRecursos;

	private static ArrayList<String> misTiposTrabajo;

	private static PUCMM pucmm;
	private static File Fname = new File("Pucmm.dat");

	private PUCMM() {
		misPersonas = new ArrayList<>();
		misRecursos = new ArrayList<>();
		misEventos = new ArrayList<>();
		misAreas =new ArrayList<>();
		misCampus = new ArrayList<>();
		misLugares = new ArrayList<>();
		misTiposRecursos = new ArrayList<>();
		misEstadosRecursos = new ArrayList<>();
		misTiposTrabajo = new ArrayList<>();
	}
	
	public static PUCMM pucmm() {
		if(pucmm == null) {
			pucmm = new PUCMM();
			pucmm.setInstance();
		}
		return pucmm;
	}
	public static void setInstance() {
		try{

			misEventos = EventosServices.getEventos();
			for (Evento evento :
					misEventos) {
				evento.setMisComisiones(ComisionServices.getComisiones());
			}
			for (Evento evento:
				 misEventos) {
				evento.setMisRecursos(RecursosServices.getRecursosByEvento(Integer.parseInt(evento.getId())));
			}
			misAreas = EventosServices.getAreas();
			misCampus = EventosServices.getCampus();
			misLugares = EventosServices.getLugares();

			misRecursos = RecursosServices.getRecursos();
			misTiposRecursos = RecursosServices.getTipoRecurso();
			misEstadosRecursos = RecursosServices.getEstadoRecursos();

			misTiposTrabajo = TrabajosServices.getTipoTrabajo();

			misPersonas = PersonasServices.getPersona();

		}catch (SQLException e){
			e.printStackTrace();
		}
		/*try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(Fname));
			pucmm = (PUCMM) input.readObject();
			input.close();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}*/
	}

	public static ArrayList<String> getMisTiposTrabajo() {
		return misTiposTrabajo;
	}

	public static void save() {

		try {
			savePersonas();
			saveEventos();
			saveAreas();
			saveCampus();
			saveLugares();
			saveTipoRecursos();
			saveTipoDisponibilidad();
			saveRecursos();
			saveTipoPersona();
			saveTipoTrabajo();
			for (Evento evento:
				 misEventos) {
				evento.saveRecursos();
			}
			updateEstados();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(Fname));
			output.writeObject(pucmm);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	public void crearEvento(Evento evento) {
		misEventos.add(evento);
	}
	public void registrarRecurso(Recurso recurso) {
		misRecursos.add(recurso);
	}
	public int getCantEventos() {
		
		return misEventos.size();
		
	}
	public static ArrayList<String> getMisEstadosRecursos() {
		return misEstadosRecursos;
	}
	public static ArrayList<String> getMisTiposRecursos() {
		return misTiposRecursos;
	}
	public void insertarPersona(Persona miPersona, String eventId, int comId) {
		Evento miEvento = searchEventoById(eventId);
		int comPos = miEvento.searchPosComByComId(comId);
		if(miPersona instanceof Juez) {
			miEvento.getMisComisiones().get(comPos).insertarJuez((Juez) miPersona);
		}

		if(miPersona instanceof Participante) {
			miEvento.getMisComisiones().get(comPos).getMisMiembros().add(miPersona);
		}
	}
	public ArrayList<String> getMisAreas() {
		return misAreas;
	}

	public ArrayList<String> getMisCampus() {
		return misCampus;
	}

	public ArrayList<String> getMisLugares() {
		return misLugares;
	}
	public void removeEventoById(String Id) {
		boolean finded = false;
		int i = 0;
		while(!finded && i < misEventos.size()) {
			if(misEventos.get(i).getId().equalsIgnoreCase(Id)) {
				misEventos.get(i).quitarTodasComision();
				misEventos.remove(i);
				finded = true;
			}
			
			else {
				i++;
			}
		}
	}
	
	public Evento searchEventoById(String Id) {
		Evento miEvento = null;
		boolean finded = false;
		int i = 0;
		while (!finded && i < misEventos.size()) {
			if(misEventos.get(i).getId().equalsIgnoreCase(Id)) {
				miEvento = misEventos.get(i);
				finded = true;
			}
			
			else {
				i ++;
			}
		}
		return miEvento;
	}
	
	public Persona searchByCedula(String cedula) {
		int i = 0;
		boolean finded = false;
		Persona miPersona = null;
		
		while(i < misPersonas.size() && !finded) {
			if(misPersonas.get(i).getCedula().equalsIgnoreCase(cedula)) {
				miPersona = misPersonas.get(i);
				finded = true;
			}
			
			i++;
		}
		
		return miPersona;
	}
	public int searchIndexById(String cedula) {
		int i = 0, aux = -1;
		boolean finded = false;
		
		while(i < misPersonas.size() && !finded) {
			if(misPersonas.get(i).getCedula().equalsIgnoreCase(cedula) ) {
				aux = i;
			}
			i++;
		}
		return aux;
	}
	public Recurso searchRecursoById(int id) {
		Recurso aux = null;
		int i = 0;
		boolean finded = false;
		
		while(i < misRecursos.size() && !finded) {
			if(misRecursos.get(i).getId() == id){
				aux = misRecursos.get(i);
				finded = true;
			}
			i++;
		}
		
		return aux;
	}
	public static Evento buscarEventoPorNombre(String nombre){
		for (Evento evento:
			 misEventos) {
			if(evento.getNombre().equalsIgnoreCase(nombre)){
				return evento;
			}
		}
		return null;
	}
	public ArrayList<Persona> getMisPersonas() {
		return misPersonas;
	}

	public ArrayList<Recurso> getMisRecursos() {
		return misRecursos;
	}
	public ArrayList<Evento> getMisEventos() {
		return misEventos;
	}
	public Persona getPersonaByCedula(String cedula){
		for (Persona persona:
			 misPersonas) {
			if(persona.getCedula().equalsIgnoreCase(cedula)){
				return persona;
			}
		}
		return null;
	}
	public ArrayList<Evento> getEventosByFecha(Date fechaMinima, Date fechaMaxima, String tipo) {
		ArrayList<Evento> eventos = new ArrayList<>();
		///Consulta SQL SERVER
		for(Evento evento : misEventos) {
			if((fechaMinima.compareTo(evento.getFechaIni()) * evento.getFechaIni().compareTo(fechaMaxima) >=0) && (evento.getArea().equalsIgnoreCase(tipo) || tipo.equalsIgnoreCase("Todos"))) {
				eventos.add(evento);
			}
		}
		
		return eventos;
	}
	public void setMisRecursos(ArrayList<Recurso> misRecursos) {
		this.misRecursos = misRecursos;
	}

	public void setMisEventos(ArrayList<Evento> misEventos) {
		this.misEventos = misEventos;
	}

	public void setMisPersonas(ArrayList<Persona> misPersonas) {
		this.misPersonas = misPersonas;
	}
	private static void saveAreas() throws SQLException{
		String[] areasNuevas =  {"Fisica", "Quimica", "Biologia/Medicina", "Mercadeo/Administracion", "Informatica/Redes"};

		ArrayList<String> areasRegistradas = new ArrayList<>();

		areasRegistradas = EventosServices.getAreas();
		for (String area :
				areasNuevas) {
			if (!areasRegistradas.contains(area)) {
				EventosServices.regAreas(area);
			}
		}

	}
	private static void saveCampus() throws SQLException{
		String[] campusNuevos = {"CSTI", "CSTA"};
		ArrayList<String> campusRegistrados = new ArrayList<>();

		campusRegistrados = EventosServices.getCampus();
		for (String campus :
				campusNuevos) {
			if(!campusRegistrados.contains(campus)){
				EventosServices.setCampus(campus);
			}
		}
	}
	private static void saveLugares() throws SQLException {
		String[] lugaresNuevos ={"Multiuso", "Teatro", "Anfiteatro", "Auditorio I", "Sede Postgrado", "Sala Reuniones (PA)"};
		ArrayList<String> lugaresRegistrados = new ArrayList<>();

		lugaresRegistrados = EventosServices.getLugares();
		for(String lugar: lugaresNuevos){
			if(!lugaresRegistrados.contains(lugar)){
				EventosServices.setLugar(1,lugar);
			}
		}


 	}
 	private static void saveTipoRecursos() throws SQLException{
		String[] tiposRecursosNuevos = {"Audio", "Visual", "Computadora", "Luces", "Pirotecnia"};
		ArrayList<String> recursosRegistrados = new ArrayList<>();

		recursosRegistrados = RecursosServices.getTipoRecurso();
		for(String recurso: tiposRecursosNuevos){
			if(!recursosRegistrados.contains(recurso)){
				RecursosServices.setTipoRecurso(recurso);
			}
		}
	}
	private static void saveTipoDisponibilidad() throws SQLException{
		String[] disponibilidadNueva = {"Disponible", "No Disponible"};

		ArrayList<String> disponibilidadRegistrada = new ArrayList<>();
		ArrayList<String> disponibilidadRegistradaPersonas = new ArrayList<>();
		disponibilidadRegistrada = RecursosServices.getEstadoRecursos();
		disponibilidadRegistradaPersonas = PersonasServices.getEstadoPersonas();

		for(String disponibilidad: disponibilidadNueva){
			if(!disponibilidadRegistrada.contains(disponibilidad)){
				RecursosServices.setEstadoRecurso(disponibilidad);
			}
			if(!disponibilidadRegistradaPersonas.contains(disponibilidad)){
				PersonasServices.setEstadoPersonas(disponibilidad);
			}
		}

	}
	private static void saveEventos() throws SQLException{
		ArrayList<String> idEventosRegistrados = new ArrayList<>();
		ArrayList<Evento> eventosRegistrados = EventosServices.getEventos();
		for (Evento evento :
				eventosRegistrados) {
			idEventosRegistrados.add(evento.getId());
		}
		for (Evento evento :
				misEventos) {
			if(!idEventosRegistrados.contains(evento.getId())){
				EventosServices.setEvento(evento,misAreas.indexOf(evento.getArea()),misCampus.indexOf(evento.getCampus()));
			}
		}
	}
	private static void saveRecursos() throws SQLException{
		ArrayList<Recurso> recursosRegistrados = RecursosServices.getRecursos();
		ArrayList<Integer> idRecursosRegistrados = new ArrayList<>();
		for(Recurso recurso: recursosRegistrados){
			idRecursosRegistrados.add(recurso.getId());
		}
		for (Recurso recurso: misRecursos){
			if(!idRecursosRegistrados.contains(recurso.getId())){
				RecursosServices.setModeloRecurso(recurso.getModelo(),String.valueOf(recurso.getId()),
						misTiposRecursos.indexOf(recurso.getTipo()),
						misEstadosRecursos.indexOf(recurso.isDisponible() ? "Disponible" : "No Disponible"));
			}
		}
	}
	private static void saveTipoPersona() throws SQLException{
		String[] tiposNuevos = {"Juez", "Participante"};
		ArrayList<String> tiposRegistrados = PersonasServices.getTipoPersona();
		for (String tipo :
				tiposNuevos) {
			if(!tiposRegistrados.contains(tipo)){
				PersonasServices.setTipoPersona(tipo);
			}
		}
	}
	private static void saveTipoTrabajo() throws SQLException{
		String[] trabajosNuevos = {"Lider", "Co-Lider", "Orador 1", "Orador 2", "Organizador 1", "Organizador 2"};
		ArrayList<String> trabajosRegistrados = TrabajosServices.getTipoTrabajo();
		for (String tipo: trabajosNuevos){
			if(!trabajosRegistrados.contains(tipo)){
				TrabajosServices.setTipoTrabajo(tipo);
			}
		}
	}
	private static void savePersonas() throws SQLException{
		ArrayList<String> cedulaPersonasRegistradas = new ArrayList<>();
		ArrayList<Persona> personasRegistradas = PersonasServices.getPersona();
		for (Persona persona :
				personasRegistradas) {
			cedulaPersonasRegistradas.add(persona.getCedula());
		}
		for (Persona persona :
				misPersonas) {
			if(!cedulaPersonasRegistradas.contains(persona.getCedula())){
				PersonasServices.setPersona(persona);
			}
		}
	}
	private static void updateEstados() throws SQLException{
		for (Recurso recurso:
				misRecursos) {
			RecursosServices.updateEstado(recurso.isDisponible(), recurso.getId());
		}
		for(Persona persona: misPersonas){
			PersonasServices.updateEstado(persona.isdisponible(), persona.getCedula());
		}
	}
}




