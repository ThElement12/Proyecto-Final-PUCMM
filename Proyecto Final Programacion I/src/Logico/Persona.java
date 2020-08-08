package Logico;

import java.awt.Image;
import java.io.Serializable;

public abstract class Persona implements Serializable {
	protected boolean disponible;
	protected String cedula;
	protected String nombre;
	protected String numero;
	protected Evento evento = null;
	protected Comision comision = null;
	protected String area;
	protected transient Image foto = null;

	public Persona(String cedula, String nombre, String numero, String area, Image foto) {
		disponible = true;
		this.cedula = cedula;
		this.nombre = nombre;
		this.numero = numero;
		this.area = area;
		this.foto = foto;
	}

	public boolean isdisponible() {
		return disponible;
	}

	public void setdisponible(boolean disponible) {
		this.disponible = disponible;
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
	public Comision getComision() {
		return comision;
	}
	public void setComision(Comision comision) {
		this.comision = comision;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Image getFoto() {
		return foto;
	}


}
