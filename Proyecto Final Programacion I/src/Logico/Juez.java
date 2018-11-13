package Logico;

public class Juez extends Persona {
	private boolean representante;
	private String tema;
	public Juez(String cedula, String nombre, String numero, Evento evento, boolean representante, String tema) {
		super(cedula, nombre, numero, evento);
		this.representante = representante;
		this.tema = tema;
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
	

}
