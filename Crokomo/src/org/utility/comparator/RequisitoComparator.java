package org.utility.comparator;

import java.util.Comparator;

import org.objects.Requisito;

public class RequisitoComparator implements Comparator<Requisito> {

	@Override
	public int compare(Requisito o1, Requisito o2) {
		if(o1.getSatisfaccion() < o2.getSatisfaccion()) {
			return 1;
		}
		if(o1.getSatisfaccion() > o2.getSatisfaccion())
			return -1;
		return 0;
	}

}
