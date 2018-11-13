package Logico;

public class Participante extends Persona {
	private String correo;

	public Participante(String cedula, String nombre, String numero, Evento evento, String correo) {
		super(cedula, nombre, numero, evento);
		this.correo = correo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	

}
