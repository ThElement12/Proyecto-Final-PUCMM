package Logico;

import java.io.Serializable;

public abstract class Persona implements Serializable {
	protected boolean selecte;
	private static int cant = 1;
	protected String cedula;
	protected String nombre;
	protected String numero;
	protected Evento evento;
	protected String tema;
	protected Comision comision;
	protected String area;

	public Persona(String cedula, String nombre, String numero) {
		selecte = false;
		this.cedula = cedula;
		this.nombre = nombre;
		this.numero = numero;
		cant++;
	}
	public static int getCant() {
		return cant;
	}

	public boolean isSelecte() {
		return selecte;
	}

	public void setSelecte(boolean selecte) {
		this.selecte = selecte;
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
