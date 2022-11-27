package org.tests.utility;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.objects.Requisito;
import org.objects.Solucion;
import org.utility.calculo.Mochila;

class MochilaTest {
	static ArrayList<Requisito> requisitos;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		requisitos = new ArrayList<>();
		Requisito r1 = new Requisito(1, 2, "R1", null, null);
		Requisito r2 = new Requisito(2, 6, "R2", null, null);
		Requisito r3 = new Requisito(3, 8, "R3", null, null);
		r1.setSatisfaccion(3);
		r2.setSatisfaccion(6);
		r3.setSatisfaccion(8);
		requisitos.add(r3);
		requisitos.add(r1);
		requisitos.add(r2);
	}

	@Test
	void test() {
		Mochila m = new Mochila();
		
		Solucion s = m.mochilaExperimental(requisitos, 10);
		
		assertEquals(2, s.getRequisitos().size());
		
		System.out.println(s.getRequisitos().toString());
	}

}
