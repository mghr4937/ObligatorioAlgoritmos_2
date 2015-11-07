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
	public String mostrarInOrder(String str){
		return mostrarInOrder(this.raiz, str);
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
	public String mostrarInOrder(NodoArbolBinario nodo, String str) {
		if (nodo != null) {
			str =  mostrarInOrder(nodo.getIzq(), str);
			str = str +(nodo.getDato() + " | ");
			str =  mostrarInOrder(nodo.getDer(), str);
		}
		return str;
	}

	@Override
	public void mostrarPosOrder() {
		mostrarPosOrder(this.raiz);
		System.out.println();
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
	public boolean existeElemento(NodoArbolBinario nodoBuscado){
		return existeElemento(nodoBuscado, raiz);
	}
	
	private boolean existeElemento(NodoArbolBinario nodoBuscado, NodoArbolBinario n){
		if(n == null){
			return false;
		}
		if(n.getDato().equals(nodoBuscado)){
			return true;
		}else{
			return existeElemento(nodoBuscado,n.getIzq()) || existeElemento(nodoBuscado, n.getDer());
			//Esto hace que si encontras el resultado en la rama izquierda, no recorre la rama derecha. Porque true con false igual es true
		}
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
		eliminar(nodo);
	}

	public void eliminar(NodoArbolBinario nodo) {
		if (this.raiz == null)
			return;
		if (this.raiz.getDato().equals(nodo.getDato())) {
			if (this.raiz.getIzq() == null) {
				this.raiz = this.raiz.getDer();
			} else {
				if (this.raiz.getDer() == null) {
					this.raiz = this.raiz.getIzq();
				} else {
					ArbolBinarioImpl hDer = this.subDer();
					this.raiz = this.raiz.getIzq();
					NodoArbolBinario max = maximo();
					max.setDer(hDer.raiz);
				}
			}
		} else {
			if (raiz.getDato().toString().compareTo(nodo.getDato().toString()) > 0) {
				ArbolBinarioImpl hIzq = this.subIzq();
				hIzq.eliminar(nodo);
				this.raiz.setIzq(hIzq.getRaiz());
			} else {
				ArbolBinarioImpl hDer = this.subDer();
				hDer.eliminar(nodo);
				this.raiz.setDer(hDer.raiz);
			}
		}
	}

	// Retorna el sub Arbol Derecho --> INCOMPLETO
	private ArbolBinarioImpl subDer() {
		ArbolBinarioImpl der = new ArbolBinarioImpl();
		der.raiz = this.raiz.getDer();
		
		return der;
	}
	
	// Retorna el sub Arbol Izquierdo --> INCOMPLETO
	private ArbolBinarioImpl subIzq() {
		ArbolBinarioImpl izq = new ArbolBinarioImpl();
		izq.raiz = this.raiz.getIzq();
		
		return izq;
	}

	private NodoArbolBinario maximo() {
		if (this.raiz == null)
			return null;
		return maximo(this.raiz);
	}

	private NodoArbolBinario maximo(NodoArbolBinario nodo){
		if(nodo.getDer() == null)
			return nodo;
		else
			return maximo(nodo.getDer());
	}

	@Override
	public boolean existe(NodoArbolBinario dato, NodoArbolBinario nodo) {
		// TODO Auto-generated method stub
		return false;
	}
   
    
}
