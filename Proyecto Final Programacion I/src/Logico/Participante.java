package Logico;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

public class Participante extends Persona{
	private String correo;
	private ArrayList<Trabajo> misTrabajos;

	public Participante(String cedula, String nombre, String numero, String area, Image foto) {
		super(cedula, nombre, numero,area,foto);
		misTrabajos = new ArrayList<>();
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public void agregarTrabajo(Trabajo trabajo) {
		
		misTrabajos.add(trabajo);
	}

	public ArrayList<Trabajo> getMisTrabajos() {
		return misTrabajos;
	}

	
}
