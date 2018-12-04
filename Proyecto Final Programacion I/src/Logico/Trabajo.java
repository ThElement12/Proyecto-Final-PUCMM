package Logico;

import java.io.Serializable;

public class Trabajo implements Serializable{
	private Evento evento;
	private String nombre;
	private Participante participante;
	private String posicion;
	private Comision comision;
	private boolean disponible;
	
	public Trabajo(String nombre, String posicion, Evento evento, Comision comision) {
		super();
		this.comision = comision;
		this.evento = evento;
		this.nombre = nombre;
		participante = null;
		this.posicion = posicion;
		disponible = true;
	}

	
	public Comision getComision() {
		return comision;
	}

	public void setComision(Comision comision) {
		this.comision = comision;
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

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	
}
