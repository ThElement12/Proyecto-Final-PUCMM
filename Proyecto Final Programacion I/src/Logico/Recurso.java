package Logico;

import java.io.Serializable;

public class Recurso implements Serializable {
	private String id;
	private String tipo;
	private boolean disponible;
	
	
	private Evento miEvento;
	public Recurso(String id, String tipo, Evento miEvento) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.miEvento = miEvento;
		disponible = true;
	}
	public String getId() {
		return id;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Evento getMiEvento() {
		return miEvento;
	}
	
	public void setMiEvento(Evento miEvento) {
		this.miEvento = miEvento;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	

}
