package org.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.objects.Cliente;
import org.objects.Proyecto;
import org.objects.relations.ClienteRequisito;

class ProyectoTest {

	Proyecto Prueba1 = new Proyecto(1, "krocomo", null);
	Cliente aux = new Cliente(1, 1, "Ramon", 1);
	ArrayList<Cliente> arrAux = new ArrayList<Cliente>();

	@Test
	void test() { 
		//Prueba de getter
		assertTrue(Prueba1.getIdProyecto() == 1);
		assertTrue(Prueba1.getNombreProyecto().equals("krocomo"));
		assertTrue(Prueba1.getCliente().size() == 0);
		
		
		//Prueba de setter 
		Prueba1.setIdProyecto(2);;
		Prueba1.setNombreProyecto("Ejem");
		arrAux.add(aux);
		Prueba1.setCliente(arrAux);
		assertTrue(Prueba1.getIdProyecto() == 2);
		assertTrue(Prueba1.getNombreProyecto().equals("Ejem"));
		assertTrue(Prueba1.getCliente().contains(aux));
		
		//Pruebas con el arrayList 
		Proyecto Prueba2 = new Proyecto(2, "Krocomo", arrAux);
		assertTrue(Prueba2.getCliente().contains(aux));
		
		//Prueba toString
		assertTrue(Prueba2.toString().equals("Krocomo"));
		

	}

}
