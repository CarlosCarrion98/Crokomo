package org.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.objects.Cliente;
import org.objects.relations.ClienteRequisito;

class ClienteTest {
	Cliente Prueba1 = new Cliente(1, 1, "Juan", 1);
	ClienteRequisito aux = new ClienteRequisito(1, 1, 7);
	ArrayList<ClienteRequisito> arrAux = new ArrayList<ClienteRequisito>();
	Cliente Prueba2 = new Cliente(1, 1,arrAux, "Juan", 1);
	@Test
	void test() { 
		//Prueba de getter
		assertTrue(Prueba1.getIdCliente() == 1);
		assertTrue(Prueba1.getPeso() == 1);
		assertTrue(Prueba1.getNombreCliente().equals("Juan"));
		assertTrue(Prueba1.getRelacionesRequisito().isEmpty());
		assertTrue(Prueba1.getIdProyecto() == 1);
		
		//Prueba de setter 
		Prueba1.setIdCliente(2);
		Prueba1.setPeso(9);
		Prueba1.setNombreCliente("Ivan");
		Prueba1.setIdProyecto(2);
		assertTrue(Prueba1.getIdCliente() == 2);
		assertTrue(Prueba1.getPeso() == 9);
		assertTrue(Prueba1.getNombreCliente().equals("Ivan"));
		assertTrue(Prueba1.getIdProyecto() == 2);
		
		//Pruebas con el arrayList 
		assertTrue(Prueba2.getRelacionesRequisito().size() == 0);
		arrAux.add(aux);
		assertTrue(Prueba2.getRelacionesRequisito().size() == 0);
		Prueba2.getRelacionesRequisito().add(aux);
		assertTrue(Prueba2.getRelacionesRequisito().size() == 1);
		Cliente Prueba3 = new Cliente(1, 1,arrAux, "Juan", 1);
		assertTrue(Prueba3.getRelacionesRequisito().size() == 1);
		

		
	}

}
