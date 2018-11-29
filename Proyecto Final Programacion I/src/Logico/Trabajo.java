package Logico;

import java.io.Serializable;

public class Trabajo implements Serializable{
	private Evento evento;
	private String nombre;
	private Participante participante;
	private String area;
	private boolean disponible;
	
	public Trabajo(String nombre, Participante participante, String area, Evento evento) {
		super();
		this.evento = evento;
		this.nombre = nombre;
		this.participante = participante;
		this.area = area;
		disponible = true;
	}

	
	public Evento getEvento() {
		return evento;
	}
	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	
}
