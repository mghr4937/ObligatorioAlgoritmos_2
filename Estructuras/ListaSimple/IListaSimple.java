package Estructuras.ListaSimple;

import Estructuras.ListaSimple.NodoLista;

public interface IListaSimple extends Iterable<Object> {

	int largo();

	boolean esVacia();

	boolean pertenece(Object elemento);

	void borrarPrincipio();

	boolean borrarElemento(Object elemento);

	void vaciarLista();

	void insertarAlPrincipio(Object o);

	void MostrarDatoLista();

	NodoLista ObtenerElementoPrimero();

	Object buscar(Object dato);

	void insertarAlFinal(Object elemento);

	int contador();

	void insertarOrdenado(Object nuevo);
}
