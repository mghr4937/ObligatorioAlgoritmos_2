package Estructuras.ArbolBinario;

public interface IArbolBinario {

	boolean esArbolVacio();

	void mostrarPreOrder();

	void mostrarPreOrder(NodoArbolBinario nodo);

	void mostrarInOrder();
	
	String mostrarInOrder(String str);

	void mostrarInOrder(NodoArbolBinario nodo);
	
	public String mostrarInOrder(NodoArbolBinario nodo, String str);

	void mostrarPosOrder();

	void mostrarPosOrder(NodoArbolBinario nodo);

	boolean existeElemento(NodoArbolBinario dato);

	boolean existe(NodoArbolBinario dato, NodoArbolBinario nodo);

	NodoArbolBinario obtenerElemento(Object buscado, NodoArbolBinario nodo);

	int cantNodos(NodoArbolBinario nodo);

	int obtenerPeso(NodoArbolBinario nodo);

	void insertarElemento(Object dato, NodoArbolBinario nodo);

	int cantHojas(NodoArbolBinario nodo);

	NodoArbolBinario borrarMinimo(NodoArbolBinario nodo);

	void insertar(Object dato);

	int altura(NodoArbolBinario nodo);
	
	void borrarElemento(NodoArbolBinario nodo);

}
