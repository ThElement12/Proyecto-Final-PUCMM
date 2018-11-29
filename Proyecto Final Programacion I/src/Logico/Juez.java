package Logico;

import java.io.Serializable;

public class Juez extends Persona {
	private boolean representante;
	private String area;
	private String tema;
	public Juez(String cedula, String nombre, String numero, Evento evento, Comision comision, boolean representante, String area, String tema) {
		super(cedula, nombre, numero, evento,comision);
		this.representante = representante;
		this.tema = tema;
		this.setArea(area);
	}
	public boolean isRepresentante() {
		return representante;
	}
	public void setRepresentante(boolean representante) {
		this.representante = representante;
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
	

}
