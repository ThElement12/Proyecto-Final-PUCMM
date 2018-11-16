package Logico;

public class Trabajo {
	private String nombre;
	private Participante participante;
	private String area;
	private boolean disponible;
	
	public Trabajo(String nombre, Participante participante, String area) {
		super();
		this.nombre = nombre;
		this.participante = participante;
		this.area = area;
		disponible = true;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	
}
