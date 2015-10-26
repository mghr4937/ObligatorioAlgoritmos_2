package Estructuras.Grafo.MatrizAdyacencia;

import Estructuras.ListaOrdenada.IListaOrdenada;
import Estructuras.ListaOrdenada.ListaOrdenada_impl;

public class GrafoMatrizAdyacencia_impl implements IGrafo {

	int size;
	int cantNodos;
	Arco_MA[][] matrizAdyacencia;
	boolean[] nodosUsados;

	// Crea el grafo vacio (sin nodos ni aristas) con capacidad de
	// almacenamiento de n vértices
	public GrafoMatrizAdyacencia_impl(int cantNodos) {
		this.size = 0;
		this.cantNodos = cantNodos;

		this.matrizAdyacencia = new Arco_MA[cantNodos + 1][cantNodos + 1];
		for (int i = 1; i <= cantNodos; i++)
			for (int j = 1; j <= cantNodos; j++)
				this.matrizAdyacencia[i][j] = new Arco_MA();

		this.nodosUsados = new boolean[cantNodos + 1];
	}

	@Override
	public void agregarVertice(int v) {
		this.nodosUsados[v] = true;
		this.size++;
	}

	@Override
	public void agregarArista(int origen, int destino, int peso) {
		Arco_MA nuevo = new Arco_MA(peso);
		this.matrizAdyacencia[origen][destino] = nuevo;
	}

	@Override
	public void eliminarVertice(int v) {
		this.nodosUsados[v] = false;
		this.size--;

		// Elimino las aristas donde v es miembro
		for (int i = 1; i <= this.cantNodos; i++) {
			this.matrizAdyacencia[i][v] = new Arco_MA();
			this.matrizAdyacencia[v][i] = new Arco_MA();
		}
	}

	@Override
	public void eliminarArista(int origen, int destino) {
		Arco_MA nuevo = new Arco_MA();
		this.matrizAdyacencia[origen][destino] = nuevo;
	}

	@Override
	public IListaOrdenada verticesAdyacentes(int v) {
		IListaOrdenada l = new ListaOrdenada_impl();
		for (int i = 1; i <= this.cantNodos; i++) {
			if (this.sonAdyacentes(v, i)) {
				l.insertarOrdenado(i);
			}
		}
		return l;
	}

	@Override
	public boolean sonAdyacentes(int a, int b) {
		return this.matrizAdyacencia[a][b].existe;
	}

	@Override
	public boolean estaVertice(int v) {
		return this.nodosUsados[v];
	}

	@Override
	public boolean esVacio() {
		return this.size == 0;
	}

}
