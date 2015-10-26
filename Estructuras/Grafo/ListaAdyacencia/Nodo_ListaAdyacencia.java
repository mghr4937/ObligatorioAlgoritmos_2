package Estructuras.Grafo.ListaAdyacencia;

public class Nodo_ListaAdyacencia {

	
	private int vertice;
	private int pesoArista;
	private Nodo_ListaAdyacencia next;
	
	public Nodo_ListaAdyacencia(int v, int a){
		this.setVertice(v);
		this.setPesoArista(a);
		this.setNext(null);
	}

	public Nodo_ListaAdyacencia getNext() {
		return next;
	}

	public void setNext(Nodo_ListaAdyacencia next) {
		this.next = next;
	}

	public int getPesoArista() {
		return pesoArista;
	}

	public void setPesoArista(int pesoArista) {
		this.pesoArista = pesoArista;
	}

	public int getVertice() {
		return vertice;
	}

	public void setVertice(int vertice) {
		this.vertice = vertice;
	}
}
