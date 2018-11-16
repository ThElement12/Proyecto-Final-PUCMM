package Logico;

import java.util.ArrayList;

public class PUCMM {
	private ArrayList<Recurso> misRecursos;
	private ArrayList<Evento> misEventos;
	private static PUCMM pucmm;

	private PUCMM() {
		misRecursos = new ArrayList<>();
		misEventos = new ArrayList<>();
		
	}
	public static PUCMM getInstance() {
		if(pucmm == null) {
			pucmm = new PUCMM();
		}
		
		return pucmm;
	}
	public void crearEvento(Evento evento) {
		misEventos.add(evento);
	}
	public void registrarRecurso(Recurso recurso) {
		misRecursos.add(recurso);
	}

	public ArrayList<Persona> listTrabajadores(Trabajo trabajo, Evento evento){
		ArrayList<Persona> misTrabajadores = new ArrayList<>();
		int i = 0;
		while(i < misEventos.size()) {
			if(misEventos.get(i).getId().equalsIgnoreCase(evento.getId())) {
				break;
			}
			
			else {
				i ++;
			}
		}
		
		for(int j = 0;j < misEventos.get(i).getMisTrabajos().size(); j ++) {
			if(misEventos.get(i).getMisTrabajos().get(j).getNombre().equalsIgnoreCase(trabajo.getNombre())) {
				misTrabajadores.add(misEventos.get(i).getMisTrabajos().get(j).getParticipante());
			}
		}
		
		return misTrabajadores;
	}
}
