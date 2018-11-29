package Logico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import org.jfree.chart.*;

public class PUCMM {
	private ArrayList<Recurso> misRecursos;
	private ArrayList<Evento> misEventos;
	private static PUCMM pucmm;

	private PUCMM() {
		misRecursos = new ArrayList<>();
		misEventos = new ArrayList<>();
		
	}
	
	public static PUCMM pucmm() {
		if(pucmm == null) {
			pucmm = new PUCMM();
		}
		
		return pucmm;
	}
	
	public void lineGraph() {
		
	}
	
	public void pieGraph() {
		
	}
	
	public void barGraph() {
		
	}
	public void load() {
		File Fname = new File("Pucmm Eventos.dat");
		if(Fname.exists()) {
			try {
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(Fname));
				pucmm = (PUCMM) input.readObject();
				input.close();
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void save() {
		File Fname = new File("Pucmm Eventos.dat");
		ObjectOutputStream output = null;
		try {
			output = new ObjectOutputStream(new FileOutputStream(Fname));
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

	public ArrayList<Persona> listTrabajadores(Trabajo trabajo, Evento evento){
		ArrayList<Persona> misTrabajadores = new ArrayList<>();
		boolean finded =false;
		int i = 0;
		while(i < misEventos.size() && !finded) {
			if(misEventos.get(i).getId().equalsIgnoreCase(evento.getId())) {
				finded = true;
			}
			
			else {
				i ++;
			}
		}
		
		for(int j = 0;j < misEventos.get(i).getMisTrabajos().size(); j ++) {
			if(misEventos.get(i).getMisTrabajos().get(j).getNombre().equalsIgnoreCase(trabajo.getNombre())) {
				misTrabajadores.add(misEventos.get(i).getMisTrabajos().get(j).getParticipante());
			}
		}
		
		return misTrabajadores;
	}
	
	public void insertarPersona(Persona miPersona, String eventId) {
		Evento miEvento = searchEventoById(eventId);
		if(miPersona instanceof Juez) {
			miEvento.agregarJuez(miPersona);
		}
		
		if(miPersona instanceof Participante) {
			miEvento.agregarParticipante(miPersona);
		}
	}
	
	private Evento searchEventoById(String Id) {
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
	public ArrayList<Recurso> getMisRecursos() {
		return misRecursos;
	}
	public ArrayList<Evento> getMisEventos() {
		return misEventos;
	}
}
