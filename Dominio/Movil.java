package Dominio;

public class Movil implements Comparable<Movil> {

	public enum EstadoMovil {
		DISPONIBLE, ASIGNADO, DESHABILITADO
	};

	private String matricula;
	private String conductor;
	private EstadoMovil estadoMovil;
	private Esquina esquinaActual;

	public Movil() {
	}

	public Movil(String matricula) {
		this.matricula = matricula;
	}

	public Movil(String matricula, String conductor, EstadoMovil estado) {
		this.matricula = matricula;
		this.conductor = conductor;
		this.estadoMovil = estado;
	}
	
	public Movil(String matricula, String conductor, EstadoMovil estado, Esquina esquinaActual) {
		this.matricula = matricula;
		this.conductor = conductor;
		this.estadoMovil = estado;
		this.esquinaActual = esquinaActual;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Movil))
			return false;
		Movil that = (Movil) other;
		return this.getMatricula().equals(that.getMatricula());
	}

	@Override
	public String toString() {
		return this.getMatricula() + ";" + this.getConductor() + ";" + this.getEstadoMovil() + ";" + this.getEsquinaActual();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conductor == null) ? 0 : conductor.hashCode());
		result = prime * result + ((estadoMovil == null) ? 0 : estadoMovil.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public int compareTo(Movil other) {
		String primerMatricula = this.getMatricula();
		String segundaMatricula = other.getMatricula();

		return (primerMatricula).compareTo(segundaMatricula);
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getConductor() {
		return conductor;
	}

	public void setConductor(String conductor) {
		this.conductor = conductor;
	}

	public EstadoMovil getEstadoMovil() {
		return estadoMovil;
	}

	public void setEstadoMovil(EstadoMovil estadoMovil) {
		this.estadoMovil = estadoMovil;
	}
	
	public Esquina getEsquinaActual() {
		return esquinaActual;
	}

	public void setEsquinaActual(Esquina esquinaActual) {
		this.esquinaActual = esquinaActual;
	}
}
