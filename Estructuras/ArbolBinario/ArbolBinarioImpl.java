package Estructuras.ArbolBinario;

public class ArbolBinarioImpl implements IArbolBinario {

	private NodoArbolBinario raiz;

	public ArbolBinarioImpl() {
		this.raiz = null;
	}

	public NodoArbolBinario getRaiz() {
		return raiz;
	}

	public boolean esArbolVacio() {
		return (raiz == null);
	}

	@Override
	public void mostrarPreOrder() {
		mostrarPreOrder(this.raiz);
	}

	@Override
	public void mostrarPreOrder(NodoArbolBinario nodo) {
		if (nodo != null) {
			System.out.print(nodo.getDato() + "   ");
			mostrarPreOrder(nodo.getIzq());
			mostrarPreOrder(nodo.getDer());
		}
	}

	@Override
	public void mostrarInOrder() {
		mostrarInOrder(this.raiz);
	}

	@Override
	public void mostrarInOrder(NodoArbolBinario nodo) {
		if (nodo != null) {
			mostrarInOrder(nodo.getIzq());
			System.out.print(nodo.getDato() + "  ");
			mostrarInOrder(nodo.getDer());
		}
	}

	@Override
	public void mostrarPosOrder() {
		mostrarPosOrder(this.raiz);
	}

	@Override
	public void mostrarPosOrder(NodoArbolBinario nodo) {
		if (nodo != null) {
			mostrarPosOrder(nodo.getIzq());
			mostrarPosOrder(nodo.getDer());
			System.out.print(nodo.getDato() + "  ");
		}
	}

	@Override
	public boolean existeElemento(Object dato) {
		NodoArbolBinario nodo = obtenerElemento(dato, raiz);
		if (nodo != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean existe(Object dato, NodoArbolBinario nodo) {
		boolean existe;
		if (nodo == null)
			existe = false;
		else {
			if (dato == nodo.getDato())
				existe = true;
			else if (dato.toString().compareToIgnoreCase(nodo.getDato().toString()) < 0)
				existe = existe(dato, nodo.getIzq());
			else
				existe = existe(dato, nodo.getDer());
		}
		return existe;
	}

	@Override
	public NodoArbolBinario obtenerElemento(Object dato, NodoArbolBinario nodo) {
		if (nodo == null) {
			return null;
		} else {
			if (dato.equals(nodo.getDato())) {
				return nodo;
			} else if (dato.toString().compareToIgnoreCase(nodo.getDato().toString()) < 0) {
				return obtenerElemento(dato, nodo.getIzq());
			} else {
				return obtenerElemento(dato, nodo.getDer());
			}
		}
	}

	@Override
	public int cantNodos(NodoArbolBinario nodo) {
		int cont = 0;
		if (nodo != null) {
			cont += cantNodos(nodo.getIzq()); // cuenta subarbol izquierdo
			cont++; // contabilizar el nodo visitado
			cont += cantNodos(nodo.getDer()); // cuenta subarbol derecho
		}
		return cont;
	}

	@Override
	public int obtenerPeso(NodoArbolBinario nodo) {
		int peso = 0;
		int peso_izq = 0;
		int peso_der = 0;

		if (nodo != null) {
			peso_izq = cantNodos(nodo.getIzq());
			peso_der = cantNodos(nodo.getDer());
			peso = peso_izq + peso_der;

		}
		return peso;
	}

	@Override
	public void insertarElemento(Object dato, NodoArbolBinario nodo) {
		NodoArbolBinario nuevo = null;

		if (this.esArbolVacio())
			this.raiz = new NodoArbolBinario(dato);

		else if (dato.toString().compareToIgnoreCase(nodo.getDato().toString()) < 0) { 
			// n<dato=>insertaréensubárbolizq.
			if (nodo.getIzq() == null) {
				nuevo = new NodoArbolBinario(dato);
				nodo.setIzq(nuevo);
			} else
				insertarElemento(dato, nodo.getIzq());
		} else if (dato.toString().compareToIgnoreCase(nodo.getDato().toString()) > 0) { 
			// n>dato=>insertaréensubárbolderecho
			if (nodo.getDer() == null) {
				nuevo = new NodoArbolBinario(dato);
				nodo.setDer(nuevo);
			} else
				insertarElemento(dato, nodo.getDer());
		}
	}

	@Override
	public int cantHojas(NodoArbolBinario nodo) {
		if (nodo.getDer() == null)
			if (nodo.getIzq() == null)
				return 1;
			else
				return cantHojas(nodo.getIzq());
		else if (nodo.getIzq() == null)
			return cantHojas(nodo.getDer());
		else
			return cantHojas(nodo.getIzq()) + cantHojas(nodo.getDer());
	}

	@Override
	public NodoArbolBinario borrarMinimo(NodoArbolBinario nodo) {
		if (nodo == null)
			return nodo;

		if (nodo.getIzq() != null) {
			nodo.setIzq(borrarMinimo(nodo.getIzq()));
			return nodo;
		} else
			return nodo.getDer();
	}

	public void insertar(Object dato) {
		raiz = insertar(dato, raiz);
	}

	public NodoArbolBinario insertar(Object dato, NodoArbolBinario nodo) {
		if (nodo == null)
			nodo = new NodoArbolBinario(dato);
		else if (dato.toString().compareToIgnoreCase(nodo.getDato().toString()) < 0) {
			nodo.setIzq(insertar(dato, nodo.getIzq())); // a.izq = insertar(x,
														// a.izq); con los
														// atributos públicos
		} else if (dato.toString().compareToIgnoreCase(nodo.getDato().toString()) > 0) {
			nodo.setDer(insertar(dato, nodo.getDer())); // a.der = insertar(x,
														// a.der); con los
														// atributos públicos
		}
		return nodo;
	}

	@Override
	public int altura(NodoArbolBinario nodo) {
		if (nodo == null) {
			return -1;
		} else {
			int altIzq = altura(nodo.getIzq());
			int altDer = altura(nodo.getDer());

			if (altIzq > altDer) {
				return 1 + altIzq;
			} else {
				return 1 + altDer;
			}
		}
	}

	@Override
	public void borrarElemento(NodoArbolBinario nodo) {
		NodoArbolBinario aux = null;

		if ((nodo.getIzq() == null) && (nodo.getDer() == null))
			nodo = null;
		else {
			if (nodo.getIzq() != null && nodo.getDer() != null) {
				if (nodo == raiz) {
					aux = nodo.getIzq();
					nodo = nodo.getDer();

					while (nodo.getIzq() != null) {
						nodo = nodo.getIzq();
					}
					nodo.setIzq(aux);
				} else {
					aux = nodo.getIzq();
					nodo = nodo.getDer();

					while (nodo.getIzq() != null) {
						nodo = nodo.getIzq();
					}
					nodo.setIzq(aux);
				}
			} else {
				if (nodo == raiz) {
					if (nodo.getIzq() != null) {
						nodo = nodo.getIzq();
					} else {
						nodo = nodo.getDer();
					}
				} else {
					if (nodo.getIzq() != null) {
						nodo = nodo.getIzq();
					} else {
						nodo = nodo.getDer();
					}
				}
			}
		}
		System.out.println("El Arbol impreso no debe contener el elemento borrado:");
		mostrarPosOrder();
		System.out.println();
	}
	
	
}
