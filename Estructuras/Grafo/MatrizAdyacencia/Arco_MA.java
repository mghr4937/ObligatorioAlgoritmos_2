package Estructuras.Grafo.MatrizAdyacencia;

public class Arco_MA {
	
	public boolean existe;
	public int peso;
	
	public Arco_MA() {
		this.existe = false;
		this.peso = 0;
	}
	
	public Arco_MA(int peso) {
		this.existe = true;
		this.peso = peso;
	}
}
