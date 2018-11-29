package Logico;

import java.awt.Image;
import java.io.Serializable;

public class Juez extends Persona {
	private boolean representante;
	private Image foto;
	
	public Juez(String cedula, String nombre, String numero, boolean representante, String area, Image foto) {
		super(cedula, nombre, numero,area);
		this.representante = representante;
		this.area = area;	
		this.foto = foto;
	}
	public boolean isRepresentante() {
		return representante;
	}
	public void setRepresentante(boolean representante) {
		this.representante = representante;
	}
	public Image getFoto() {
		return foto;
	}
	public void setFoto(Image foto) {
		this.foto = foto;
	}


}
