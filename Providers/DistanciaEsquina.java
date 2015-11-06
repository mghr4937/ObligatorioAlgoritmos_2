package Providers;

//clase auxiliar para busqueda de caminos minimos
public class DistanciaEsquina{
	private int distanciaAcumulada;
	private int idEsquinaAnterior;
	
	
	public DistanciaEsquina(int dist, int idEsquinaAnterior) {
		this.distanciaAcumulada = dist;
		this.idEsquinaAnterior = idEsquinaAnterior;		
	}

	public DistanciaEsquina() {
		this.distanciaAcumulada = 0;
	}

	public int getDistanciaAcumulada() {
		return distanciaAcumulada;
	}

	public void setDistanciaAcumulada(int distanciaAcumulada) {
		this.distanciaAcumulada = distanciaAcumulada;
	}

	public int getIdEsquinaAnterior() {
		return idEsquinaAnterior;
	}

	public void setIdEsquinaAnterior(int idEsquinaAnterior) {
		this.idEsquinaAnterior = idEsquinaAnterior;
	}
	
	@Override
	public String toString(){
		return this.getIdEsquinaAnterior()+ "-" + this.getDistanciaAcumulada();
	}
}