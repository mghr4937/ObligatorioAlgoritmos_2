package Estructuras.ArbolBinario;

public interface IArbolBinario {

	boolean esArbolVacio();

	void mostrarPreOrder();

	void mostrarPreOrder(NodoArbolBinario nodo);

	void mostrarInOrder();

	void mostrarInOrder(NodoArbolBinario nodo);

	void mostrarPosOrder();

	void mostrarPosOrder(NodoArbolBinario nodo);

	boolean existeElemento(Object dato);

	boolean existe(Object dato, NodoArbolBinario nodo);

	NodoArbolBinario obtenerElemento(Object dato, NodoArbolBinario nodo);

	int cantNodos(NodoArbolBinario nodo);

	int obtenerPeso(NodoArbolBinario nodo);

	void insertarElemento(Object dato, NodoArbolBinario nodo);

	int cantHojas(NodoArbolBinario nodo);

	NodoArbolBinario borrarMinimo(NodoArbolBinario nodo);

	void insertar(Object dato);

	int altura(NodoArbolBinario nodo);
	
	void borrarElemento(NodoArbolBinario nodo);

}
