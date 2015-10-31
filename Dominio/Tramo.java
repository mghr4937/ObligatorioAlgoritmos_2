package Dominio;

import Dominio.Esquina;;

public class Tramo {
	
	private int iTramoId;
	private Esquina esquinaOrigen;
	private Esquina esquinaDestino;
	private int metros;
	private static int numerador = 0;

	public Tramo() {}

	public Tramo(Esquina origen, Esquina destino, int metros) {
		this.esquinaOrigen = origen;
		this.esquinaDestino = destino;
		this.metros = metros;
		this.NumerarTramo();
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Tramo))
			return false;
		Tramo that = (Tramo) other;
		return this.getEsquinaOrigen().equals(that.getEsquinaOrigen()) && 
				this.getEsquinaDestino().equals(that.getEsquinaDestino()) &&
				this.getMetros() == that.getMetros();
	}

	@Override
	public String toString() {
		return "Tramo: Esquina origen '" + this.getEsquinaOrigen().toString() + "'-" + " Esquina destino '" + this.getEsquinaDestino().toString() + "'";  
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((esquinaDestino == null) ? 0 : esquinaDestino.hashCode());
		result = prime * result + ((esquinaOrigen == null) ? 0 : esquinaOrigen.hashCode());
		result = prime * result + metros;
		return result;
	}

	public Esquina getEsquinaOrigen() {
		return esquinaOrigen;
	}

	public void setEsquinaOrigen(Esquina esquinaOrigen) {
		this.esquinaOrigen = esquinaOrigen;
	}

	public Esquina getEsquinaDestino() {
		return esquinaDestino;
	}

	public void setEsquinaDestino(Esquina esquinaDestino) {
		this.esquinaDestino = esquinaDestino;
	}

	public int getMetros() {
		return metros;
	}

	public void setMetros(int metros) {
		this.metros = metros;
	}

	public int getiTramoId() {
		return iTramoId;
	}

	public void setiTramoId(int iTramoId) {
		this.iTramoId = iTramoId;
	}

	public static int getNumerador() {
		return numerador;
	}

	public static void setNumerador(int numerador) {
		Tramo.numerador = numerador;
	}
	
	private void NumerarTramo() {
		this.iTramoId = ++Tramo.numerador;
	}

}
