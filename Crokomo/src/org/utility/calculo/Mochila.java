package org.utility.calculo;

import java.util.ArrayList;
import java.util.Arrays;

import org.objects.Requisito;
import org.objects.Solucion;
import org.utility.comparator.RequisitoComparator;

public class Mochila {

	private void llenarMochila(ArrayList<Requisito> requisitos, int esfuerzoMaximo){
		requisitos.sort(new RequisitoComparator());
		ArrayList<Requisito> requisitosSolucion = new ArrayList<>();
		ArrayList<Solucion> soluciones = new ArrayList<>();
		soluciones.add(new Solucion(requisitosSolucion));
		for(int i = 1; i <= esfuerzoMaximo; i++) {
			int esfuerzoActual = 0;
			for(Requisito r : requisitos) {
				requisitosSolucion = soluciones.get(i-1).getRequisitos();
				for(Requisito rSolucion : soluciones.get(i-1).getRequisitos()) {
					esfuerzoActual += rSolucion.getEsfuerzo();
				}
				if((r.getEsfuerzo() + esfuerzoActual) < i) {
					requisitosSolucion.add(r);
				}
			}
		}
		
	}
	
	public Solucion mochilaExperimental(ArrayList<Requisito> requisitos, int esfuerzoMaximo) {
		int[][] matrizDeSatisfaccion = new int[requisitos.size() + 1][esfuerzoMaximo + 1];
		Solucion s = null;
		for (int j = 0; j <= esfuerzoMaximo; j++) {  
            for (int i = 0; i <= requisitos.size(); i++) {  
              
                if (i == 0 || j == 0) {  
                    matrizDeSatisfaccion[i][j] = 0;
                }     
                else   
                {  
                                         // Si el peso de la mochila i-th es mayor que la carga total, la solución óptima existe en las primeras mochilas i-1,  
                                         // Nota: la i-ésima mochila es bolsas [i-1]  
                    if (j < requisitos.get(i-1).getEsfuerzo()) {  
                        matrizDeSatisfaccion[i][j] = matrizDeSatisfaccion[i-1][j];  
                    }     
                    else   
                    {  
                                                 // Si la mochila i-th no es mayor que la carga total, la solución óptima es la solución óptima que contiene la mochila i-th,  
                                                 // O la solución óptima que no contiene la i-ésima mochila, la que sea máxima, y ​​se utiliza el método de discusión de clasificación  
                                                 // El peso, el peso y el valor del valor de la i-ésima mochila  
                        int iweight = requisitos.get(i-1).getEsfuerzo();  
                        int ivalue = requisitos.get(i-1).getSatisfaccion();  
                        matrizDeSatisfaccion[i][j] =   
                            Math.max(matrizDeSatisfaccion[i-1][j], ivalue + matrizDeSatisfaccion[i-1][j-iweight]);        
                    } // else  
                } //else           
           } //for  
        } //for  
          
                 // Resuelve la composición de la mochila  
        if (s == null) {  
            s = new Solucion(new ArrayList<Requisito>());  
        }  
        int tempWeight = esfuerzoMaximo;  
        for (int i=requisitos.size(); i >= 1; i--) {  
           if (matrizDeSatisfaccion[i][tempWeight] > matrizDeSatisfaccion[i-1][tempWeight]) {  
                               s.getRequisitos().add (requisitos.get(i-1)); 
               tempWeight -= requisitos.get(i-1).getEsfuerzo();  
           }  
           if (tempWeight == 0) { break; }  
        }  
        return s; 
    }  

}
