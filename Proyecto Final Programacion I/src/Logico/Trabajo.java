package Logico;

import SQLConnections.ComisionServices;

import java.io.Serializable;
import java.sql.SQLException;

public class Trabajo implements Serializable{
	private Evento evento;
	private Participante participante;
	private String posicion;
	private Comision comision;
	private boolean disponible;
	
	public Trabajo(String posicion) {
		super();
		comision = null;
		evento = null;
		participante = null;
		this.posicion = posicion;
		disponible = true;
	}
	public void saveTrabajo() throws SQLException{
		ComisionServices.setMiembrosComision(participante,comision,evento,this);
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
