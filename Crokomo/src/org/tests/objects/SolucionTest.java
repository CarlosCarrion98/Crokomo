package org.tests.objects;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.objects.Requisito;
import org.objects.Solucion;

class SolucionTest {
	
	Solucion Prueba1 = new Solucion(null);
	Requisito aux1 = new Requisito(1, 2, "requisito1", null, null);
	ArrayList<Requisito> arrAux = new ArrayList<Requisito>();
	Solucion Prueba2 = new Solucion(arrAux);
	

	@Test
	void test() {
		//Prueba getter
		assertTrue(Prueba1.getRequisitos().size() == 0);
		Prueba1.getRequisitos().add(aux1);
		assertTrue(Prueba1.getRequisitos().size() == 1);
		assertTrue(Prueba2.getRequisitos().size() == 0);
		Prueba2.getRequisitos().add(aux1);
		assertTrue(Prueba2.getRequisitos().size() == 1);
		arrAux.add(aux1);
		Solucion Prueba3 = new Solucion(arrAux); 
		assertTrue(Prueba3.getRequisitos().size() == 1);
	}

}
