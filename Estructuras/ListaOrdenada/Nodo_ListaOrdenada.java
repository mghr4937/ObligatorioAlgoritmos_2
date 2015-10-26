package Estructuras.ListaOrdenada;

public class Nodo_ListaOrdenada {

    private Object dato;
    private Nodo_ListaOrdenada siguiente;

    public Nodo_ListaOrdenada(Object n) {
        this.dato = n;
        this.siguiente = null;
    }

    public Nodo_ListaOrdenada(Object n, Nodo_ListaOrdenada sigNodo) {
        this.dato = n;
        this.siguiente = sigNodo;
    }

    public Object getDato() {
        return this.dato;
    }

    public void setDato(Object n) {
        this.dato = n;
    }

    public Nodo_ListaOrdenada getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(Nodo_ListaOrdenada elemento) {
        this.siguiente = elemento;
    }
}
