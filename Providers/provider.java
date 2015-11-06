package Providers;

import BaseEntregada.Sistema;
import Dominio.*;
import Estructuras.Grafo.MatrizAdyacencia.GrafoMatrizAdyacencia_impl;
import Estructuras.ListaOrdenada.ListaOrdenada_impl;
import Estructuras.ListaSimple.ListaSimple_impl;
import Estructuras.ListaSimple.NodoLista;

public class provider {

//	//Sistema sis = Sistema.getInstance();
//	
//	public ListaOrdenada_impl obtenerMovilesEnRadio(int radio) {
//		ListaOrdenada_impl lstRetorno = new ListaOrdenada_impl();
//		// Aca recorro con Dijkstra y voy creando y guardando entidades EntidadRetornable_Movil_Radio (inserto ordenado)
//		
//		return lstRetorno;
//	}
//	
//	public DistanciaEsquina[][] caminosMinimos(Esquina esquina){
//		DistanciaEsquina minimo = new DistanciaEsquina(99999, -1) ;
//		int min = 999999;
//		int esquinaSig = -1;
//		int esquinaActual = esquina.getEsquinaId();
//		GrafoMatrizAdyacencia_impl grafo = new GrafoMatrizAdyacencia_impl(sis.getiCantEsquinas());
//		DistanciaEsquina[][] caminos = new DistanciaEsquina[grafo.getSize()][grafo.getSize()];// [x]=idCiudad [y]=paso		
//		grafo.MatrizAdyacencia = this.GenerarMatrizAdy();
//		for (int numeroDePaso = 1; numeroDePaso < grafo.getSize(); numeroDePaso++) {
//			for (int destino = 1; destino < grafo.getSize(); destino++) {
//				if (grafo.MatrizAdyacencia[esquinaActual][destino] > 0) {// si hay camino, -1 = no adyacentes
//					if (grafo.getNodosUsados()[destino] != true) {
//						grafo.getNodosUsados()[destino] = true;
//						DistanciaEsquina distanciaEsquina = new DistanciaEsquina();
//						distanciaEsquina.setIdEsquinaAnterior(esquinaActual);
//						distanciaEsquina.setDistanciaAcumulada(distanciaEsquina.getDistanciaAcumulada()
//																+ grafo.MatrizAdyacencia[esquinaActual][destino]);
//						DistanciaEsquina aux = caminos[destino][numeroDePaso - 1];
//						if (aux == null	|| aux.getDistanciaAcumulada() > distanciaEsquina.getDistanciaAcumulada()) {//comparo con la distancia del paso anterior al mismo destino
//							caminos[destino][numeroDePaso] = distanciaEsquina;	//si es menor la guardo				
//						} else {
//							caminos[destino][numeroDePaso] = aux; // si es mayor me quedo con la anterior
//						}
//						if (min > caminos[destino][numeroDePaso].getDistanciaAcumulada()) {//me quedo con el de menor distancia de ese paso
//							minimo = new DistanciaEsquina();
//							minimo = caminos[destino][numeroDePaso];
//							min = minimo.getDistanciaAcumulada();
//							esquinaSig = destino;
//						}
//					}
//				}
//			}
//			esquinaActual = esquinaSig;			
//			min = 9999999;
//		}	
//		
//		return caminos;
//	}
//	
//	
//
//
//	private int[][] GenerarMatrizAdy( ) {
//			
//		int largo = sis.getiCantEsquinas()+1;
//		int x = 1;
//			
//		int[][] matriz = new int[largo+1][largo+1];
//		while (x < largo) {
//			int y = 1;	
//			while (y < largo) {
//				Tramo tramo = new Tramo();
//				tramo.setEsquinaOrigen(this.getEsquinaById(x));
//				tramo.setEsquinaDestino(this.getEsquinaById(y));
//				tramo = (Tramo) sis.getListaTramos().buscar(tramo);
//				if (tramo != null) {
//					matriz[x][y] = tramo.getMetros();
//					matriz[y][x] = tramo.getMetros();
//				} else if (x == y) {
//					matriz[x][y] = 0;
//					matriz[y][x] = 0;
//				} else {
//					matriz[x][y] = -1;
//					matriz[y][x] = -1;
//				}
//				y++;
//			}			
//			x++;
//		}
//		return matriz;
//	}
//	
//	public Esquina getEsquinaById(int id){
//		Esquina esquinaBuscada;
//
//		if (sis.getListaEsquinas().esVacia())
//			return null;
//		else {
//			NodoLista aux = (NodoLista) sis.getListaEsquinas().ObtenerElementoPrimero();
//
//			while (aux != null) {
//				esquinaBuscada = (Esquina) aux.getDato();
//
//				if (esquinaBuscada.getEsquinaId() == id)
//					return esquinaBuscada;
//				else
//					aux = aux.getSiguiente();
//			}
//		}
//		return null;
//	}
//	
//	
}
