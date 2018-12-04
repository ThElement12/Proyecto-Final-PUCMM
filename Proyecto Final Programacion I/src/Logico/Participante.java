package Logico;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

public class Participante extends Persona{
	
	private ArrayList<Trabajo> misTrabajos;

	public Participante(int id,String cedula, String nombre, String numero, String area, Image foto) {
		super(id,cedula, nombre, numero,area,foto);
		misTrabajos = new ArrayList<>();
	}

	public void agregarTrabajo(Trabajo trabajo) {
		
		misTrabajos.add(trabajo);
	}

	public ArrayList<Trabajo> getMisTrabajos() {
		return misTrabajos;
	}

	
}
