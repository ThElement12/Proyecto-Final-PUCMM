package Logico;

import java.util.ArrayList;

public abstract class Persona {
	private String cedula;
	private String nombre;
	private String numero;
	private Evento evento;
	private ArrayList<Trabajo> misTrabajos;
	public Persona(String cedula, String nombre, String numero, Evento evento) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.numero = numero;
		this.evento = evento;
		misTrabajos = new ArrayList<>();
	}
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	

}
