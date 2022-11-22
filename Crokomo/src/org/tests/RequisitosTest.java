package org.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.objects.Requisito;
import org.objects.relations.ClienteRequisito;
import org.objects.relations.RelacionRequisito;

class RequisitosTest {

	Requisito Prueba1 = new Requisito(1, 5, "Rec1", null, null);
	Requisito Prueba2 = new Requisito(1, 2, "Rec1", new ArrayList<RelacionRequisito>(), new ArrayList<ClienteRequisito>());
	ArrayList<RelacionRequisito> arrAux1 = new ArrayList<RelacionRequisito>();
	RelacionRequisito Aux1 = new RelacionRequisito(1, 2, "Dependencia");
	ArrayList<ClienteRequisito> arrAux2 = new ArrayList<ClienteRequisito>();
	ClienteRequisito Aux2 = new ClienteRequisito(1, 2, 3);
	Requisito Prueba3 = new Requisito(1, 5, "Rec1",2, null, null);
	Requisito Prueba4 = new Requisito(1, 2, "Rec1",2, new ArrayList<RelacionRequisito>(), new ArrayList<ClienteRequisito>());
	
	@Test
	void test() {
		//Pruebas getter
		assertTrue(Prueba1.getIdRequisito() == 1);
		assertTrue(Prueba1.getEsfuerzo() == 5);
		assertTrue(Prueba1.getNombreRequisito().equals("Rec1"));
		assertTrue(Prueba1.getSatisfaccion() == -1);
		assertTrue(Prueba1.getListaDeRequisitos().size() == 0);
		assertTrue(Prueba1.getRelaciones().size() == 0);
		assertTrue(Prueba3.getSatisfaccion() == 2);
		
		//Prueba setter 
		Prueba1.setIdRequisito(2);
		Prueba1.setEsfuerzo(9);
		Prueba1.setNombreRequisito("Ejemplo");
		Prueba1.setSatisfaccion(3);
		arrAux1.add(Aux1);
		Prueba1.setListaDeRequisitos(arrAux1);
		arrAux2.add(Aux2);
		Prueba1.setRelaciones(arrAux2);
		Prueba3.setListaDeRequisitos(arrAux1);
		Prueba3.setRelaciones(arrAux2);
		assertTrue(Prueba1.getIdRequisito() == 2);
		assertTrue(Prueba1.getEsfuerzo() == 9);
		assertTrue(Prueba1.getNombreRequisito().equals("Ejemplo"));
		assertTrue(Prueba1.getSatisfaccion() == 3);
		assertTrue(Prueba1.getListaDeRequisitos().size() == 1);
		assertTrue(Prueba1.getRelaciones().size() == 1);
		assertTrue(Prueba3.getListaDeRequisitos().size() == 1);
		assertTrue(Prueba3.getRelaciones().size() == 1);
		
		//Prueba de constructores con arraylist
		assertTrue(Prueba2.getListaDeRequisitos().size() == 0);
		assertTrue(Prueba2.getRelaciones().size() == 0);
		assertTrue(Prueba4.getListaDeRequisitos().size() == 0);
		assertTrue(Prueba4.getRelaciones().size() == 0);
		Requisito Prueba5 = new Requisito(1, 2, "Rec1",arrAux1, arrAux2);
		assertTrue(Prueba5.getListaDeRequisitos().size() == 1);
		assertTrue(Prueba5.getRelaciones().size() == 1);
		
		
	}

}
