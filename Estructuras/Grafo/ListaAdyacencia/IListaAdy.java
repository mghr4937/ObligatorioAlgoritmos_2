package Estructuras.Grafo.ListaAdyacencia;

public interface IListaAdy {

	void agregarArista(int origen, int destino, int peso);

	void agregarVertice(int v);

	void eliminarArista(int origen, int destino);

	boolean esVacio();

	boolean sonAdyacentes(int a, int b);

	void eliminarVertice(int v);

	ListaArco verticesAdyacentes(int v);

	boolean estaVertice(int v);
}
