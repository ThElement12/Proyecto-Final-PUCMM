package Logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Participante extends Persona{
	private String correo;
	private String tema;
	private ArrayList<Trabajo> misTrabajos;
	private String area;

	public Participante(String cedula, String nombre, String numero, Evento evento, String correo, String area, String tema, Comision comision) {
		super(cedula, nombre, numero, evento,comision);
		this.area = area;
		this.correo = correo;
		misTrabajos = new ArrayList<>();
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	public void agregarTrabajo(Trabajo trabajo) {
		
		misTrabajos.add(trabajo);
	}

	public ArrayList<Trabajo> getMisTrabajos() {
		return misTrabajos;
	}

	
}
