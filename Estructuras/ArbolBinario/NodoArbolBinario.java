package Estructuras.ArbolBinario;

public class NodoArbolBinario {

	private Object dato;
	private NodoArbolBinario der;
	private NodoArbolBinario izq;

	public NodoArbolBinario(Object o) {
		dato = o;
		izq = null;
		der = null;
	}

	public NodoArbolBinario(Object o, NodoArbolBinario nodoI, NodoArbolBinario nodoD) {
		dato = o;
		izq = nodoI;
		der = nodoD;
	}

	public Object getDato() {
		return dato;
	}

	public void setDato(Object o) {
		dato = 0;
	}

	public NodoArbolBinario getDer() {
		return der;
	}

	public void setDer(NodoArbolBinario nodoD) {
		der = nodoD;
	}

	public NodoArbolBinario getIzq() {
		return izq;
	}

	public void setIzq(NodoArbolBinario nodoI) {
		izq = nodoI;
	}

}
