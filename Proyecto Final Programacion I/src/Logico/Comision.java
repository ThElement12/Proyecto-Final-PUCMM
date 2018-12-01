package Logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Comision implements Serializable{
	private String id;
	private String tema;
	private ArrayList<Persona> misMiembros;
	private String area;
	
	public Comision(String id,String area, String tema) {
		this.id = id;
		this.area = area;
		this.tema = tema;
		misMiembros = new ArrayList<>();
	}

	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
