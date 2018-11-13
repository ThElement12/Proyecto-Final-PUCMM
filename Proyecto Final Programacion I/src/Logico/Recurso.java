package Logico;

public class Recurso {
	private String id;
	private String tipo;
	private Evento miEvento;
	public Recurso(String id, String tipo, Evento miEvento) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.miEvento = miEvento;
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

}
