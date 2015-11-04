package Dominio.Comparadores;

import java.util.Comparator;

import Dominio.EntidadesAuxiliares.EntidadRetornable_Movil_Radio;

public class EntidadRetornable_Movil_Radio_Comparator implements Comparator<Object> {

	@Override
	public int compare(Object distanciaUno, Object distanciaDos) {

		EntidadRetornable_Movil_Radio unaDistancia = (EntidadRetornable_Movil_Radio) distanciaUno;
		EntidadRetornable_Movil_Radio otraDistancia = (EntidadRetornable_Movil_Radio) distanciaDos;

		return ((Integer)unaDistancia.getMetros()).compareTo((Integer)otraDistancia.getMetros());
	}
}
