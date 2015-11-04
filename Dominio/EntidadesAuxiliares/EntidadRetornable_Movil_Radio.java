package Dominio.EntidadesAuxiliares;

import Dominio.Movil;;

public class EntidadRetornable_Movil_Radio implements Comparable<EntidadRetornable_Movil_Radio> {

	private Movil movil;
	private int metros;
	
	public EntidadRetornable_Movil_Radio(){}
	
	public EntidadRetornable_Movil_Radio(Movil movil, int radio){
		this.movil = movil;
		this.metros = radio;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof EntidadRetornable_Movil_Radio))
			return false;
		EntidadRetornable_Movil_Radio that = (EntidadRetornable_Movil_Radio) other;
		return this.getMovil().equals(that.getMovil());
	}

	@Override
	public String toString() {
		return this.getMovil().getMatricula() + ";" + this.getMetros() + ";" + this.getMovil().getConductor() + " | ";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movil == null) ? 0 : movil.hashCode());
		result = prime * result + metros;
		return result;
	}

	public Movil getMovil() {
		return movil;
	}
	public void setMovil(Movil movil) {
		this.movil = movil;
	}
	public int getMetros() {
		return metros;
	}
	public void setMetros(int metros) {
		this.metros = metros;
	}

	@Override
	public int compareTo(EntidadRetornable_Movil_Radio other) {
		int distanciaUno = this.getMetros();
		int distanciaDos = other.getMetros();

		return ((Integer)(distanciaUno)).compareTo((Integer)distanciaDos);
	}
}
