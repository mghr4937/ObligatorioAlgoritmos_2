package Estructuras.Cola;

import Estructuras.Comunes.IMetodosComunes;

public interface ICola extends IMetodosComunes{

	public void enColar(Object o);
	
	public boolean esVacia();
	
	public boolean estaLlena();

	public Object inicio();

	public NodoCola desEncolar();

	public void vaciar();

	public void mostrar();

}
