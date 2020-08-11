package Logico;

import java.io.Serializable;

public class Recurso implements Serializable {
	private static final long serialVersionUID =  5156264519276269302L;

	private int id;
	private String tipo;
	private String modelo;
	private boolean disponible;
	private Evento miEvento;
	
	public Recurso(int id,String modelo, String tipo, String Estado) {
		super();

		this.tipo = tipo;
		this.modelo = modelo;
		if(Estado.equalsIgnoreCase("Disponible")){
			disponible = true;
		}
		else{
			disponible = false;
		}
		this.id = id;
		
	}
	public int getId() {
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
	
	public String getModelo() {
		return modelo;
	}	

}
