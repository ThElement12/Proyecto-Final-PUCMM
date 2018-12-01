package Logico;

import java.io.Serializable;

public class Recurso implements Serializable {
	private static int id = 1;
	private String tipo;
	private String modelo;
	private int cantidad;
	private boolean disponible;
	private Evento miEvento;
	public Recurso(String modelo, String tipo, int cantidad) {
		super();
		this.tipo = tipo;
		this.modelo = modelo;
		this.cantidad = cantidad;
		disponible = true;
		
	}
	public static int getId() {
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
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getModelo() {
		return modelo;
	}
	

}
