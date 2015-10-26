package Dominio.Comparadores;

import java.util.Comparator;

import Dominio.Movil;

public class MovilComparatorByMatricula implements Comparator<Object>{

	@Override
	public int compare(Object movilUno, Object movilDos) {

		Movil primerMovil = (Movil) movilUno;
		Movil segundoMovil = (Movil) movilDos;

		return primerMovil.getMatricula().compareTo(segundoMovil.getMatricula());
	}
}
