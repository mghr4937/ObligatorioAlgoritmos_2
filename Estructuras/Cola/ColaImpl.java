package Estructuras.Cola;

import Estructuras.Comunes.MetodosComunes;

public class ColaImpl extends MetodosComunes implements ICola {

	private NodoCola primero;
	private NodoCola ultimo;
	private int tamanio;

	public NodoCola getPrimero() {
		return primero;
	}

	public void setPrimero(NodoCola primero) {
		this.primero = primero;
	}

	public NodoCola getUltimo() {
		return ultimo;
	}

	public void setUltimo(NodoCola ultimo) {
		this.ultimo = ultimo;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	public ColaImpl() {
		super();
		this.primero = null;
		this.ultimo = null;
		this.tamanio = 0;
	}

	/**
	 * Pre.: La cola no esta llena. Pos.: Inserta el car�cter pasado como
	 * argumento en la cola.
	 * 
	 * @param o
	 */
	@Override
	public void enColar(Object o) {
		NodoCola nuevo = new NodoCola(o);
		nuevo.setSiguiente((this.getPrimero()));
		if(this.esVacia()){
			this.setUltimo(nuevo);
		}
		this.setPrimero(nuevo);		
		this.setTamanio(this.getTamanio() + 1);

	}

	/**
	 * Pos.: Retorna true si y solo si la cola esta llena.
	 * 
	 * @return true si y solo si la cola esta llena
	 */
	@Override
	public boolean estaLlena() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Pre.: La cola no es vac�a. Pos.: Retorna el elemento ubicado en el frente
	 * de la cola.
	 * 
	 * @return elemento ubicado en el frente de la cola
	 */
	@Override
	public NodoCola inicio() {
		if (primero == null)
			return null;
		else
			return (NodoCola) primero.getDato();
	}

	/**
	 * Pre.: La cola no es vac�a. Pos.: Elimina el elemento ubicado en el frente
	 * de la cola.
	 */
	@Override
	public NodoCola desEncolar() {
		NodoCola retorno = null;
		if (!this.esVacia()) {
			retorno = this.ultimo;
			if (this.primero == this.ultimo || this.primero.getSiguiente() == null) {
				this.primero = null;
				this.ultimo = null;
			} else {
				NodoCola aux = this.primero;
				while (aux.getSiguiente().getSiguiente() != null) {
					aux = aux.getSiguiente();
				}
				this.ultimo = aux;
				this.ultimo.setSiguiente(null);				
			}
			this.setTamanio(this.getTamanio() - 1);
		}

		return retorno;
	}	

	@Override
	public void vaciar() {
		this.primero = null;
		this.ultimo = null;
	}
	
	@Override
	public boolean esVacia(){
		if (this.primero == null)
			return true;
		else
			return false;
	}

	@Override
	public void mostrar() {
		NodoCola aux = this.primero;
		while (aux != null) {
			System.out.println(aux.getDato());
			aux = aux.getSiguiente();
		}
	}

}
