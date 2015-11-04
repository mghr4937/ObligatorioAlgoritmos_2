package Estructuras.Grafo.MatrizAdyacencia;

import Dominio.Esquina;
import Estructuras.Hash.Hash;
import Estructuras.ListaOrdenada.ILista;
import Estructuras.ListaOrdenada.ListaOrdenada_impl;

public class GrafoMatrizAdyacencia_impl implements IGrafo {

	int size;
	int cantNodos;
	public Arco_MA[][] MatrizAdyacencia;
	boolean[] nodosUsados;

	// Crea el grafo vacio (sin nodos ni aristas) con capacidad de
	// almacenamiento de n vértices
	public GrafoMatrizAdyacencia_impl(int cantNodos) {
		this.size = 0;
		this.cantNodos = cantNodos;

		this.MatrizAdyacencia = new Arco_MA[cantNodos + 1][cantNodos + 1];
		for (int i = 1; i <= cantNodos; i++)
			for (int j = 1; j <= cantNodos; j++)
				this.MatrizAdyacencia[i][j] = new Arco_MA();

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
		this.MatrizAdyacencia[origen][destino] = nuevo;
	}

	@Override
	public void eliminarVertice(int v) {
		this.nodosUsados[v] = false;
		this.size--;

		// Elimino las aristas donde v es miembro
		for (int i = 1; i <= this.cantNodos; i++) {
			this.MatrizAdyacencia[i][v] = new Arco_MA();
			this.MatrizAdyacencia[v][i] = new Arco_MA();
		}
	}

	@Override
	public void eliminarArista(int origen, int destino) {
		Arco_MA nuevo = new Arco_MA();
		this.MatrizAdyacencia[origen][destino] = nuevo;
	}

	@Override
	public ILista verticesAdyacentes(int v) {
		ILista l = new ListaOrdenada_impl();
		for (int i = 1; i <= this.cantNodos; i++) {
			if (this.sonAdyacentes(v, i)) {
				l.insertarOrdenado(i);
			}
		}
		return l;
	}

	@Override
	public boolean sonAdyacentes(int a, int b) {
		return this.MatrizAdyacencia[a][b].existe;
	}

	@Override
	public boolean estaVertice(int v) {
		return this.nodosUsados[v];
	}

	@Override
	public boolean esVacio() {
		return this.size == 0;
	}
	
	
	boolean[] visitados;
	String[] predecesores;
	int[] costos;
	private Hash hash;
	
	public String[] caminoMinimo(Esquina esquinaInicio, Esquina esquinaFin){	
		inicializarVectores();
		String[] caminoMinimo = new String[0];
		
		return caminoMinimo;
	}
	
	private void inicializarVectores(){
		this.visitados = new boolean[0];
		this.predecesores = new String[0];
		this.costos = new int[0];
	}
	
	private Esquina esquinaSinVisitarMasCercana(int[][] costos, boolean[] visitados){
		Esquina retorno = new Esquina();
		
		
		return retorno;
	}
	
	private int[] caminoMinimoHaciaAtras(Esquina fin, Esquina inicio){
		int[] retorno = new int[0];
		
		return retorno;
	}
	
	public Hash getHash() {
		return hash;
	}

	public void setHash(Hash hash) {
		this.hash = hash;
	}
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCantNodos() {
		return cantNodos;
	}

	public void setCantNodos(int cantNodos) {
		this.cantNodos = cantNodos;
	}

	public boolean[] getNodosUsados() {
		return nodosUsados;
	}

	public void setNodosUsados(boolean[] nodosUsados) {
		this.nodosUsados = nodosUsados;
	}

	public boolean[] getVisitados() {
		return visitados;
	}

	public void setVisitados(boolean[] visitados) {
		this.visitados = visitados;
	}

	public String[] getPredecesores() {
		return predecesores;
	}

	public void setPredecesores(String[] predecesores) {
		this.predecesores = predecesores;
	}

	public int[] getCostos() {
		return costos;
	}

	public void setCostos(int[] costos) {
		this.costos = costos;
	}

}
