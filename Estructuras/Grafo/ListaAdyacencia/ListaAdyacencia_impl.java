package Estructuras.Grafo.ListaAdyacencia;

public class ListaAdyacencia_impl implements IListaAdy {

	int size;
	int cantNodos;
	ListaAdy[] listaAdyacencia;
	boolean[] nodosUsados;

	// Crea el grafo vacio (sin nodos ni aristas) con capacidad de
	// almacenamiento de n vértices
	public ListaAdyacencia_impl(int n) {
		this.size = 0;
		this.cantNodos = n;
		this.listaAdyacencia = new ListaAdy[this.cantNodos + 1];
		for (int i = 1; i <= this.cantNodos; i++)
			this.listaAdyacencia[i] = new ListaAdy();

		this.nodosUsados = new boolean[this.cantNodos + 1];
	}

	public ListaAdyacencia_impl() {
	}

	public void agregarArista(int origen, int destino, int peso) {
		this.listaAdyacencia[origen].insertar(destino, peso);
	}

	public void agregarVertice(int v) {
		this.nodosUsados[v] = true;
		this.size++;
	}

	public void eliminarArista(int origen, int destino) {
		this.listaAdyacencia[origen].eliminar(destino);
	}

	public boolean esVacio() {
		return this.size == 0;
	}

	public boolean sonAdyacentes(int a, int b) {
		return this.listaAdyacencia[a].pertenece(b);
	}

	public void eliminarVertice(int v) {
		this.nodosUsados[v] = false;
		this.size--;

		// Elimino las aristas donde v es miembro
		this.listaAdyacencia[v] = new ListaAdy();
		// BUSCAR EN TODOS LOS VERTICES LA ARISTA
		for (int i = 1; i <= cantNodos; i++)
			this.listaAdyacencia[i].eliminar(v);
	}

	public ListaArco verticesAdyacentes(int v) {
		return null;// this.listaAdyacencia[v];
	}

	public boolean estaVertice(int v) {
		return this.nodosUsados[v];
	}

}
