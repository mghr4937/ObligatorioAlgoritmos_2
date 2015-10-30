package BaseEntregada;

import BaseEntregada.Retorno.Resultado;
import Dominio.*;
import Dominio.Movil.EstadoMovil;
import Estructuras.ArbolBinario.ArbolBinarioImpl;
import Estructuras.ArbolBinario.NodoArbolBinario;
import Estructuras.Grafo.ListaAdyacencia.ListaAdyacencia_impl;
import Estructuras.ListaSimple.ListaSimple_impl;

public class Sistema implements ISistema {

	private int iCantEsquinas;
	private ArbolBinarioImpl arbolMoviles;
	private ListaAdyacencia_impl listaTramos;
	private ListaSimple_impl listaEsquinas;

	public Sistema() {
		this.arbolMoviles = new ArbolBinarioImpl();
		this.listaTramos = new ListaAdyacencia_impl();
		this.listaEsquinas = new ListaSimple_impl();
	}

	public Retorno inicializarSistema(int cantPuntos) {
		if (cantPuntos <= 0) {
			System.out.println("No hay esquinas en el mapa");
			return new Retorno(Resultado.ERROR_1);
		}
		this.setiCantEsquinas(cantPuntos);
		return new Retorno(Resultado.OK);
	}

	public Retorno destruirSistema() {
		this.arbolMoviles = null;
		this.listaTramos = null;
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarMovil(String matricula, String conductor) {
		boolean yaExiste = arbolMoviles.existeElemento(matricula);
		if (yaExiste == false) {
			Movil nuevoMovil = new Movil(matricula, conductor, EstadoMovil.DISPONIBLE);
			if (this.arbolMoviles.esArbolVacio()) {
				this.arbolMoviles.insertar(nuevoMovil);
				System.out.println("Movil insertado: " + nuevoMovil.toString());
				return new Retorno(Resultado.OK);
			} else {
				this.arbolMoviles.insertar(nuevoMovil, this.arbolMoviles.getRaiz());
				System.out.println("Movil insertado: " + nuevoMovil.toString());
				return new Retorno(Resultado.OK);
			}
		} else
			return new Retorno(Resultado.ERROR_1);
	}

	@Override
	public Retorno deshabilitarMovil(String matricula) {
		Object entidadFantasia = new Movil(matricula);
		NodoArbolBinario nodoMovilBuscado = this.arbolMoviles.obtenerElemento(entidadFantasia,
				this.arbolMoviles.getRaiz());
		if (nodoMovilBuscado != null) {
			Movil movilBuscado = (Movil) nodoMovilBuscado.getDato();
			if (movilBuscado.getEstadoMovil().equals(EstadoMovil.ASIGNADO)
					|| movilBuscado.getEstadoMovil().equals(EstadoMovil.DESHABILITADO)) {
				System.out.println("Error 2 - Movil Asignado o ya Deshabilitado");
				return new Retorno(Resultado.ERROR_2);
			} else {
				movilBuscado.setEstadoMovil(EstadoMovil.DESHABILITADO);
				if (movilBuscado != null)
					System.out.println("Movil deshabilitado: " + movilBuscado.toString());
				return new Retorno(Resultado.OK);
			}
		} else {
			System.out.println("Error 1 - Movil no encontrado");
			return new Retorno(Resultado.ERROR_1);
		}
	}

	@Override
	public Retorno habilitarMovil(String matricula) {
		Object entidadFantasia = new Movil(matricula);
		NodoArbolBinario nodoMovilBuscado = this.arbolMoviles.obtenerElemento(entidadFantasia,this.arbolMoviles.getRaiz());
		if (nodoMovilBuscado != null) {
			Movil movilBuscado = (Movil) nodoMovilBuscado.getDato();
			if (movilBuscado.getEstadoMovil().equals(EstadoMovil.ASIGNADO) || movilBuscado.getEstadoMovil().equals(EstadoMovil.DISPONIBLE)) {
				System.out.println("Error 2 - Movil ya Asignado o Disponible");
				return new Retorno(Resultado.ERROR_2);
			} else {
				movilBuscado.setEstadoMovil(EstadoMovil.DISPONIBLE);
				if (movilBuscado != null)
					System.out.println("Movil habilitado: " + movilBuscado.toString());
				return new Retorno(Resultado.OK);
			}
		} else {
			System.out.println("Error 1 - Movil no encontrado");
			return new Retorno(Resultado.ERROR_1);
		}
	}

	@Override
	public Retorno eliminarMovil(String matricula) {
		Object entidadFantasia = new Movil(matricula);
		NodoArbolBinario nodoMovilBuscado = this.arbolMoviles.obtenerElemento(entidadFantasia,
				this.arbolMoviles.getRaiz());
		if (nodoMovilBuscado != null) {
			Movil movilBuscado = (Movil) nodoMovilBuscado.getDato();
			if (movilBuscado.getEstadoMovil().equals(EstadoMovil.ASIGNADO)) {
				System.out.println("Error 1 - Movil en estado asignado");
				return new Retorno(Resultado.ERROR_2);
			} else {
				this.arbolMoviles.borrarElemento(nodoMovilBuscado);
				this.arbolMoviles.mostrarPreOrder();
				return new Retorno(Resultado.OK);
			}
		} else {
			System.out.println("Error 1 - Movil no encontrado");
			return new Retorno(Resultado.ERROR_1);
		}
	}

	@Override
	public Retorno asignarUbicacionMovil(String matricula, Double coordX, Double coordY) {
		Movil movil =(Movil) this.arbolMoviles.obtenerElemento(matricula, this.arbolMoviles.getRaiz()).getDato();
		if (movil != null){
			Esquina esquina =(Esquina) this.listaEsquinas.buscar(new Esquina(coordX,coordY));
			if(esquina != null){			
				if(esquina.getMovil() == null){
					esquina.setMovil(movil);
					movil.setEsquinaActual(esquina);
					return new Retorno(Resultado.OK);
				}else{
					System.out.println("Error 2 - Ya hay un movil en esta esquina");
					return new Retorno(Resultado.ERROR_2);
				}
			}else{
				System.out.println("Error 1 - coordX,coordY no existen en el sistema");
				return new Retorno(Resultado.ERROR_1);
			}				
		}else{
			System.out.println("Error 3 - Movil no encontrado");
			return new Retorno(Resultado.ERROR_3);
		}	
	}
	@Override
	public Retorno buscarMovil(String matricula) {
		Object entidadFantasia = new Movil(matricula);
		NodoArbolBinario nodoMovilBuscado = this.arbolMoviles.obtenerElemento(entidadFantasia,this.arbolMoviles.getRaiz());
		if (nodoMovilBuscado != null){
			Movil movilBuscado = (Movil) nodoMovilBuscado.getDato();
			System.out.println(movilBuscado.toString());
			return new Retorno(Resultado.OK);
		}else{
			System.out.println("Error 1 - Movil no encontrado");
			return new Retorno(Resultado.ERROR_1);
		}
	}

	@Override
	public Retorno informeMoviles() {
		String str = "";
		String aux = "";
		Retorno retorno = new Retorno(Resultado.OK);
		if(!this.arbolMoviles.esArbolVacio()){
			str = this.arbolMoviles.mostrarInOrder(aux);
		}
		System.out.println(str);
		retorno.valorString = str;
		return retorno;
	}

	@Override
	public Retorno registrarEsquina(Double coordX, Double coordY) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int metros) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno eliminarEsquina(Double coordX, Double coordY) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno eliminarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno movilMasCercano(Double coordX, Double coordY) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno verMovilesEnRadio(Double coordX, Double coordY, int radio) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno verMapa() {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	
	
	
	
	
	
	
	
	
	
	public int getiCantEsquinas() {
		return iCantEsquinas;
	}

	public void setiCantEsquinas(int iCantEsquinas) {
		this.iCantEsquinas = iCantEsquinas;
	}

	public ArbolBinarioImpl getArbolMoviles() {
		return arbolMoviles;
	}

	public void setArbolMoviles(ArbolBinarioImpl arbolMoviles) {
		this.arbolMoviles = arbolMoviles;
	}

	public ListaAdyacencia_impl getListaTramos() {
		return listaTramos;
	}

	public void setListaTramos(ListaAdyacencia_impl listaTramos) {
		this.listaTramos = listaTramos;
	}
}
