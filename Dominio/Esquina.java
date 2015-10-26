package Dominio;

public class Esquina {

	private Double x;
	private Double y;
	private Movil movil;
	
	public Esquina(){}
	
	public Esquina(Double x, Double y){
		this.x = x;
		this.y = y;
		this.setMovil(null);
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Esquina))
			return false;
		Esquina that = (Esquina) other;
		return this.getX().equals(that.getX()) && this.getY().equals(that.getY());
	}
	
	@Override
	public String toString() {
		return "Intersección " + this.getX() + " " + this.getY() + ".";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}

	public Movil getMovil() {
		return movil;
	}

	public void setMovil(Movil movil) {
		this.movil = movil;
	}
}
