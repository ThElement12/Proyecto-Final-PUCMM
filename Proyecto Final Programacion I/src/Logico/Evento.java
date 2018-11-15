package Logico;

import java.util.ArrayList;
import java.util.Date;

public class Evento {
	private String id;
	private String temaPrinci;
	private Date fechaIni;
	private Date fechaFin;
	private ArrayList<Persona> misJueces;
	private ArrayList<Persona> misParticipantes;
	private ArrayList<Recurso> misRecursos;
	
	public Evento(String id, String temaPrinci, Date fechaIni, Date fechaFin) {
		this.id = id;
		this.temaPrinci = temaPrinci;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		misJueces = new ArrayList<>();
		misParticipantes = new ArrayList<>();
		misRecursos = new ArrayList<>();
	}

	public String getId() {
		return id;
	}
	public String getTemaPrinci() {
		return temaPrinci;
	}

	public void setTemaPrinci(String temaPrinci) {
		this.temaPrinci = temaPrinci;
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
		misJueces.add(juez);
	}
	public void agregarParticipante(Persona participante) {
		misParticipantes.add(participante);
	}
	public void agregarRecurso(Recurso recurso) {
		misRecursos.add(recurso);
	}
}
