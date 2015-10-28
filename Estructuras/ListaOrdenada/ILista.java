package Estructuras.ListaOrdenada;

public interface ILista extends Iterable<Object> {

	int largo();

	boolean esVacia();

	boolean pertenece(Object elemento);

	void borrarPrincipio();

	boolean borrarElemento(Object elemento);

	void vaciarLista();

	void MostrarDatoLista();

	Nodo_ListaOrdenada ObtenerElementoPrimero();

	Object buscar(Object dato);

	int contador();

	void insertarOrdenado(Object nuevo);
}
