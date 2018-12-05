package Logico;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Evento implements Serializable {
	private String id;
	private String campus;
	private String nombre;
	private String area;
	private String lugar;
	private Date fechaIni;
	private Date fechaFin;
	private Date HorarioInicio;
	private Date HorarioFin;
	private ArrayList<Comision> misComisiones;
	private ArrayList<Recurso> misRecursos;
	private ArrayList <Trabajo> misTrabajos;
	
	public Evento(String id,String nombre, String area,String lugar, String campus, Date fechaIni, Date fechaFin, Date HorarioInicio, Date HorarioFin) {
		this.id = id;
		this.campus = campus;
		this.nombre = nombre;
		this.area = area;
		this.lugar = lugar;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.HorarioFin = HorarioFin;
		this.HorarioInicio = HorarioInicio;
		misComisiones = new ArrayList<>();
		misRecursos = new ArrayList<>();
		misTrabajos = new ArrayList<>();
	}
	
	public void hacerReporte() {
		File Fname = new File("Reporte-"+id);
		FileWriter fw;
		BufferedWriter bw;
		try {
			fw = new FileWriter(Fname);
			bw = new BufferedWriter(fw);
			
			bw.write("Nombre de evento: " + nombre);
			bw.newLine();
			bw.write("Id: " + id);
			bw.newLine();
			bw.write("Campus: " + campus);
			bw.newLine();
			bw.write("Area: " + area);
			bw.newLine();
			bw.write("Lugar: " + lugar);
			bw.newLine();
			bw.write("Fecha de Inicio: " + fechaIni.toString());
			bw.newLine();
			bw.write("Fecha de finalización: " + fechaFin.toString());
			bw.newLine();
			bw.newLine();
			
			bw.write("Comisiones");
			bw.newLine();
			bw.write("Id");
			bw.write("  Nombre");
			bw.newLine();
			
			for(int i = 0; i < misComisiones.size(); i ++) {
				bw.write(misComisiones.get(i).getId());
				bw.write("  "+misComisiones.get(i).getArea());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void agregarRecurso(Recurso recurso) {
		misRecursos.add(recurso);
	}
	public void agregarTrabajoEvento(Trabajo trabajo) {
		misTrabajos.add(trabajo);
	}
	
	public ArrayList<Comision> getMisComisiones() {
		return misComisiones;
	}

	public ArrayList<Trabajo> getMisTrabajos() {
		return misTrabajos;
	}

	public ArrayList<Recurso> getMisRecursos() {
		return misRecursos;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public Persona buscarParticipantePorCedula(String cedula) {
		Persona aux = null;
		int i = 0 , j = 0;
		boolean finded = false;
		
		while(!finded && i < misComisiones.size()) {
			while(!finded && j <misComisiones.get(i).getMisMiembros().size())
			if(misComisiones.get(i).getMisMiembros().get(j) instanceof Participante) {
				if(misComisiones.get(i).getMisMiembros().get(j).getCedula().equalsIgnoreCase(cedula)) {
					aux = misComisiones.get(i).getMisMiembros().get(j);
					finded = true;
				}
			}
			
		}	
		return aux;
	}
	public Persona buscarJuezporCedula(String cedula) {
		Persona aux = null;
		int i = 0 , j = 0;
		boolean finded = false;
		
		while(!finded && i < misComisiones.size()) {
			while(!finded && j <misComisiones.get(i).getMisMiembros().size())
			if(misComisiones.get(i).getMisMiembros().get(j) instanceof Juez) {
				if(misComisiones.get(i).getMisMiembros().get(j).getCedula().equalsIgnoreCase(cedula)) {
					aux = misComisiones.get(i).getMisMiembros().get(j);
					finded = true;
				}
			}
			
		}
		return aux;
	}
	public Recurso buscarRecursosPorId(int id) {
		Recurso aux = null;
		int i = 0;
		boolean finded = false;
		while(!finded && i < misRecursos.size()) {
			misRecursos.get(i);
			if(Recurso.getId() == id) {
				aux = misRecursos.get(i);
				finded = true;
			}		
		}
		return aux;
	}
	public int cantTrabajoDispPorArea(String area) {
		int aux = 0;
		
		for(int i = 0; i < misTrabajos.size(); i++) {
			if(misTrabajos.get(i).getPosicion().equalsIgnoreCase(area)) {
				if(misTrabajos.get(i).isDisponible()) {
					aux++;
				}
			}
		}
		return aux;
	}

	public Date getHorarioInicio() {
		return HorarioInicio;
	}

	public void setHorarioInicio(Date horarioInicio) {
		HorarioInicio = horarioInicio;
	}

	public Date getHorarioFin() {
		return HorarioFin;
	}

	public void setHorarioFin(Date horarioFin) {
		HorarioFin = horarioFin;
	}
	
	public int cantParticipantes() {
		int cant = 0;
		for(int i = 0; i < misComisiones.size(); i ++) {
			for(int j = 0; j < misComisiones.get(i).getMisMiembros().size(); j ++) {
				cant ++;
			}
		}
		return cant;
	}
	
	public int searchPosComByComId(int comId) {
		boolean finded = false;
		int i = 0;
		while(!finded && i < misComisiones.size()) {
			if(misComisiones.get(i).getId() == comId) {
				finded = true;
			}
			
			else {
				i ++;
			}
		}
		
		return i;
	}
}
