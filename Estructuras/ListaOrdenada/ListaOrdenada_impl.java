package Estructuras.ListaOrdenada;

import java.util.Comparator;
import java.util.Iterator;

public class ListaOrdenada_impl implements IListaOrdenada {

    private Nodo_ListaOrdenada inicio;
    @SuppressWarnings("unused")
	private Nodo_ListaOrdenada fin;
    private Comparator<Object> comp;   

    
    public ListaOrdenada_impl(Comparator<Object> comp) {
        this.inicio = null;
        this.fin = null;
        this.comp = comp;
    }
    
    public ListaOrdenada_impl() {
		this.inicio = null;
		this.fin = null;
	}

	//Precondicion: No existen precondiciones
    //Postcondicion: Retorna un int con la cantidad de elementos de la lista
    @Override
    public int largo() {
        Nodo_ListaOrdenada auxiliar = this.inicio;

        int contadorLongitud = 0;

        if (this.esVacia()) {
            return 0;
        } else {
            while (auxiliar != null) {
                contadorLongitud++;
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return contadorLongitud;
    } 
    
    //Precondicion: No existen precondiciones
    //Postcondicion: Retorna TRUE si la lista no tiene elementos.
    @Override
    public boolean esVacia() {
        return this.inicio == null;
    }
    
    //Precondicion: No existen precondiciones
    //Postcondicion: Retorna TRUE si el dato pasado como parametro pertenece a la lista
    @Override
    public boolean pertenece(Object elemento) {
        return perteneceAuxiliar(elemento, inicio);
    }

    private boolean perteneceAuxiliar(Object elemento, Nodo_ListaOrdenada nodo) {
        if (nodo == null) {
            return false;
        } else {
            if (nodo.getDato().equals(elemento)) {
                return true;
            } else {
                return perteneceAuxiliar(elemento, nodo.getSiguiente());
            }
        }
    }
    
    //Precondicion: No existen precondiciones
    //Postcondicion: Elimina el primer Nodo de la lista
    @Override
    public void borrarPrincipio() {
        if (!esVacia()) {
            this.inicio = this.inicio.getSiguiente();
        }
    }
    
    //Precondicion: El elemento pasado como parametro pertenece a la lista
    //Postcondicion: Elimina de la lista la primer ocurrencia del elemento pasado por parámetro
    @Override
    public boolean borrarElemento(Object elemento) {
        Nodo_ListaOrdenada auxiliar = this.inicio;

        if (auxiliar.getDato().equals(elemento)) {
            this.borrarPrincipio();
            return true;
        } else {
            Nodo_ListaOrdenada anterior = new Nodo_ListaOrdenada(elemento);

            while (auxiliar != null) {
                if (auxiliar.getDato().equals(elemento)) {
                    Nodo_ListaOrdenada borrar = auxiliar.getSiguiente();
                    auxiliar.setDato(borrar);
                    anterior.setSiguiente(auxiliar.getSiguiente());
                    return true;
                }
                anterior = auxiliar;
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return false;
    }
    
    //Precondicion: No existen precondiciones
    //Postcondicion: Elimina todos los Nodos de la lista, en caso de que la misma tuviera 1 o mas de uno
    @Override
    public void vaciarLista() {
        while (this.inicio != null) {
            borrarPrincipio();
        }
    }
   
    //Precondiciones: La lista esta ordenada segun el criterio adoptado por cada Entidad
    //Postcondiciones: Inserta el elemento pasado por parametro, ordenado en la lista correspondiente
    @Override
    public void insertarOrdenado(Object n) {
        if (inicio == null || comp.compare(inicio.getDato(), n) >= 0) {
            Nodo_ListaOrdenada nu = new Nodo_ListaOrdenada(n, inicio);
            inicio = nu;
            /*if (fin == null) {
                fin = inicio;
            }*/
        } else {
            Nodo_ListaOrdenada aux = inicio;
            while (aux.getSiguiente() != null && comp.compare(aux.getSiguiente().getDato(), n) < 0) {
                aux = aux.getSiguiente();
            }
            if (aux.getSiguiente() != null) {
                Nodo_ListaOrdenada nu = new Nodo_ListaOrdenada(n, aux.getSiguiente());
                aux.setSiguiente(nu);
            } else {
                aux.setSiguiente(new Nodo_ListaOrdenada(n));
                fin = aux.getSiguiente();
            }
        }
    }

   
    //Precondicion: No existen precondiciones
    //Postcondicion: Muestra por consola el elemento llamado
    @Override
    public void MostrarDatoLista() {
        Nodo_ListaOrdenada auxiliar = this.inicio;

        while (auxiliar != null) {
            System.out.println(auxiliar.getDato().toString());
            auxiliar = auxiliar.getSiguiente();
        }
    }
   
    //Precondicion: No existen precondiciones
    //Postcondicion: Obtiene el primer nodo de la lista
    @Override
    public Nodo_ListaOrdenada ObtenerElementoPrimero() {
        Nodo_ListaOrdenada primero = this.inicio;
        return primero;
    }
   
    //Precondicion: No existen precondiciones
    //Postcondicion: Devuelve la cantida de elementos de la lista
    @Override
    public int contador() {
        Nodo_ListaOrdenada auxiliar = this.inicio;
        int contador = 0;

        while (auxiliar != null) {
            contador++;
            auxiliar.getSiguiente();
        }
        return contador;
    }
   
    //Precondicion: No existen precondiciones
    //Postcondicion: Devuelve el elemento de una lista
    @Override
    public Object buscar(Object dato) {
        Nodo_ListaOrdenada auxiliar = this.inicio;

        while (auxiliar != null) {
            if (auxiliar.getDato().equals(dato)) {
                return auxiliar.getDato();
            } else {
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return null;
    }
   
    @Override
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {

            Nodo_ListaOrdenada actual = inicio;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public Object next() {
                Object ret = actual.getDato();
                actual = actual.getSiguiente();
                return ret;
            }

            @Override
            public void remove() {

            }
        };
    }


}
