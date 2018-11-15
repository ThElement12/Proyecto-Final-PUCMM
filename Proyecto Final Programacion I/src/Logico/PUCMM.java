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

}
