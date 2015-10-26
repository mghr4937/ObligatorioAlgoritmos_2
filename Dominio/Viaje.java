package Dominio;

import java.util.*;

public class Viaje {

	private Movil movil;
	private Tramo tramoRecorrido;
	private Calendar fechaHora;
	
	public Viaje(){}
	
	public Viaje(Movil movil, Tramo tramo, Calendar momento){
		this.movil = movil;
		this.tramoRecorrido = tramo;
		this.fechaHora = momento;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Viaje))
			return false;
		Viaje that = (Viaje) other;
		return this.getMovil().equals(that.getMovil()) &&
				this.getTramoRecorrido().equals(that.getTramoRecorrido()) &&
				this.getFechaHora().compareTo(that.getFechaHora()) == 0;
	}

	@Override
	public String toString() {
		return "Datos Viaje: Movil " + this.getMovil().getMatricula() + " Tramo " + this.getTramoRecorrido().toString() +
				" - " + this.getFechaHora();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaHora == null) ? 0 : fechaHora.hashCode());
		result = prime * result + ((movil == null) ? 0 : movil.hashCode());
		result = prime * result + ((tramoRecorrido == null) ? 0 : tramoRecorrido.hashCode());
		return result;
	}

	public Movil getMovil() {
		return movil;
	}
	public void setMovil(Movil movil) {
		this.movil = movil;
	}
	public Tramo getTramoRecorrido() {
		return tramoRecorrido;
	}
	public void setTramoRecorrido(Tramo tramoRecorrido) {
		this.tramoRecorrido = tramoRecorrido;
	}
	public Calendar getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Calendar fechaHora) {
		this.fechaHora = fechaHora;
	}
}
