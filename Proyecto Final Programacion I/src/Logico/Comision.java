package Logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Comision implements Serializable{
	private int id;
	private String tema;
	private ArrayList<Persona> misMiembros;
	private String area;
	private static int cant = 1;
	
	public Comision(String area, String tema) {
		this.area = area;
		this.tema = tema;
		misMiembros = new ArrayList<>();
		id = cant;
		cant++;
	}

	
	public int getId() {
		return id;
	}

	public String getArea() {
		return area;
	}

	public ArrayList<Persona> getMisMiembros() {
		return misMiembros;
	}

	public void setArea(String area) {
		this.area = area;
	}
	public void insertarJuez(Juez miJuez) {
		misMiembros.add(miJuez);
	}
	public void insertarPrincipante(Participante miParticipante) {
		misMiembros.add(miParticipante);
	}


	public String getTema() {
		return tema;
	}
}
