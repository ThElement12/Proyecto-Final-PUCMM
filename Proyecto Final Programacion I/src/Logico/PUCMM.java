package Logico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class PUCMM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4176944916332368203L;
	private ArrayList<Recurso> misRecursos;
	private ArrayList<Evento> misEventos;
	private ArrayList<Persona> misPersonas;

	private static PUCMM pucmm;
	private static File Fname = new File("Pucmm.dat");
	
	

	private PUCMM() {
		misPersonas = new ArrayList<>();
		misRecursos = new ArrayList<>();
		misEventos = new ArrayList<>();
		
	}
	
	public static PUCMM pucmm() {
		if(pucmm == null) {
			pucmm = new PUCMM();
		}
		return pucmm;
	}
	
	public static void setInstance() {
		if(Fname.exists()) {
			load();
		}
	}
	
	private static void load() {
		try {
			
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(Fname));
			pucmm = (PUCMM) input.readObject();
			input.close();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void save() {
		try {
			
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(Fname));
			output.writeObject(pucmm);
			output.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	public Evento buscarEventoPorNombre(String nombre){
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



}
