package Logico;

import java.awt.Image;
import java.io.Serializable;

public class Juez extends Persona {
	private boolean representante;
	
	public Juez(String cedula, String nombre, String numero, String area, Image foto) {
		super(cedula, nombre, numero,area, foto);
		representante = false;
		this.area = area;	
		this.foto = foto;
	}
	public boolean isRepresentante() {
		return representante;
	}
	public void setRepresentante(boolean representante) {
		this.representante = representante;
	}

}
