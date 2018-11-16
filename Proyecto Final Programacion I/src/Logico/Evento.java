package Logico;

import java.util.ArrayList;
import java.util.Date;

public class Evento {
	private String id;
	private String area;
	private Date fechaIni;
	private Date fechaFin;
	private ArrayList<Persona> misMiembros;
	private ArrayList<Recurso> misRecursos;
	
	public Evento(String id, String area, Date fechaIni, Date fechaFin) {
		this.id = id;
		this.area = area;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		misMiembros = new ArrayList<>();
		misRecursos = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public void agregarJuez(Persona juez) {
		misMiembros.add(juez);
	}
	public void agregarParticipante(Persona participante) {
		misMiembros.add(participante);
	}
	public void agregarRecurso(Recurso recurso) {
		misRecursos.add(recurso);
	}
	
	
	public ArrayList<Persona> getMisMiembros() {
		return misMiembros;
	}

	public ArrayList<Recurso> getMisRecursos() {
		return misRecursos;
	}

	public Persona buscarParticipantePorCedula(String cedula) {
		Persona aux = null;
		int i = 0;
		boolean encontrado = false;
		
		while(encontrado != true && i < misMiembros.size()) {
			
			if(misMiembros.get(i) instanceof Participante) {
				if(misMiembros.get(i).getCedula().equalsIgnoreCase(cedula)) {
					aux = misMiembros.get(i);
					encontrado = true;
				}
			}
			
		}	
		return aux;
	}
	public Persona buscarParticipantePorCe(String cedula) {
		Persona aux = null;
		int i = 0;
		boolean encontrado = false;
		
		while(encontrado != true && i < misMiembros.size()) {
			
			if(misMiembros.get(i) instanceof Participante) {
				if(misMiembros.get(i).getCedula().equalsIgnoreCase(cedula)) {
					aux = misMiembros.get(i);
					encontrado = true;
				}
			}
			
		}	
		return aux;
	}
	
}
