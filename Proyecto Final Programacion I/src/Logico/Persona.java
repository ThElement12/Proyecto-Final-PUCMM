package Logico;



public abstract class Persona {
	protected boolean selecte;
	private static int cant = 0;
	protected String cedula;
	protected String nombre;
	protected String numero;
	protected Evento evento;
	protected Comision comision;

	public Persona(String cedula, String nombre, String numero, Evento evento, Comision comision) {
		selecte = false;
		this.cedula = cedula;
		this.nombre = nombre;
		this.numero = numero;
		this.evento = evento;
		this.comision = comision;

	}
	
	
	
	public static int getCant() {
		return cant;
	}



	public static void setCant(int cant) {
		Persona.cant = cant;
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
	

}
