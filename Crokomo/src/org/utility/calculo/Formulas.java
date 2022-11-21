package org.utility.calculo;
import java.util.ArrayList;

import org.objects.Cliente;
import org.objects.Requisito;
import org.objects.Solucion;
import org.objects.relations.ClienteRequisito;

public class Formulas {
	
	public static void calcularSatisfaccion(Requisito r, ArrayList<Cliente> clientes) {
		int sumatoria = 0;
		for(ClienteRequisito cr : r.getRelaciones()) {
			int index = clientes.indexOf(new Cliente(cr.getIdCliente(), 0, "", 0));
			if(index == -1) {
				System.out.println("El cliente de la lista de relaciones no se ha encontrado.");
				return;
			}
			Cliente c = clientes.get(index);
			sumatoria += (c.getPeso() * cr.getValor());
		}
	
		r.setSatisfaccion(sumatoria);
	}
	
	public static double productividad(Requisito r) {
		if(r.getSatisfaccion() == -1) {
			return -1;
		}
		return (double)r.getSatisfaccion()/r.getEsfuerzo();
	}
	
	public static double contribucion(Requisito r, Cliente c) {
		if(r.getSatisfaccion() == -1) {
			return -1;
		}
		int index = r.getRelaciones().indexOf(new ClienteRequisito(c.getIdCliente(), r.getIdRequisito(), 0));
		ClienteRequisito cr = r.getRelaciones().get(index);
		
		return (double)(c.getPeso() * cr.getValor()) / r.getSatisfaccion();
	}
	
	public static double productividadSolucion(Solucion s) {
		double productividadSolucion = 0;
		for(Requisito r : s.getRequisitos()) {
			productividadSolucion += productividad(r);
		}
		return productividadSolucion;
	}
	
	public static int contribucionSolucion(Solucion s, Cliente c) {
		int contribucionSolucion = 0;
		for(Requisito r : s.getRequisitos()) {
			for(ClienteRequisito cr : r.getRelaciones()) {
				if(cr.getIdCliente() == c.getIdCliente()) {
					contribucionSolucion += contribucion(r, c);
				}
			}
			
		}
		return contribucionSolucion;
	}
	
	/*
	 * Porcentaje del problema total que cubre una solución
	 */
	public static double coberturaSolucion(Solucion s, ArrayList<Requisito> requisitos) {
		ArrayList<Requisito> requisitosSolucion = s.getRequisitos();
		int valorTotal = 0;
		int valorSolucion = 0;
		for(Requisito r : requisitos) {
			for(ClienteRequisito cr : r.getRelaciones()) {
				if(requisitosSolucion.contains(r))
					valorSolucion += cr.getValor();
				valorTotal += cr.getValor();
			}
		}
		return valorSolucion / valorTotal;
	}

}
