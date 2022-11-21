package org.objects;

import java.util.ArrayList;

public class Solucion {
	private ArrayList<Requisito> requisitos;
	
	public Solucion(ArrayList<Requisito> requisitos) {
		if (requisitos != null)
			for(Requisito r : requisitos)
				this.requisitos.add(r);
	}
	
	public ArrayList<Requisito> getRequisitos() {
		return requisitos;
	}
}
