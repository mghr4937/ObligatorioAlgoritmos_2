package BaseEntregada;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import BaseEntregada.Retorno.Resultado;
import Dominio.*;
import Dominio.Movil.EstadoMovil;
import Dominio.EntidadesAuxiliares.EntidadRetornable_Movil_Radio;
import Estructuras.ArbolBinario.ArbolBinarioImpl;
import Estructuras.ArbolBinario.NodoArbolBinario;
import Estructuras.Grafo.MatrizAdyacencia.GrafoMatrizAdyacencia_impl;
import Estructuras.Hash.Hash;
import Estructuras.ListaOrdenada.ListaOrdenada_impl;
import Estructuras.ListaOrdenada.Nodo_ListaOrdenada;
import Estructuras.ListaSimple.ListaSimple_impl;
import Estructuras.ListaSimple.NodoLista;
import Providers.DistanciaEsquina;
import Providers.provider;

public class Sistema implements ISistema {

	private int iCantEsquinas;
	private ArbolBinarioImpl arbolMoviles;
	private ListaSimple_impl listaTramos;
	private ListaSimple_impl listaEsquinas;
	private Hash puntosGrafo;
	//private GrafoMatrizAdyacencia_impl grafoMatrizAdy = new GrafoMatrizAdyacencia_impl(listaEsquinas.largo());

	private provider provider = new provider();

	public Sistema() {
		this.arbolMoviles = new ArbolBinarioImpl();
		this.listaTramos = new ListaSimple_impl();
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
		if(this.getiCantEsquinas() > this.listaEsquinas.largo()){
			Esquina esquina = new Esquina(coordX, coordY);
			if(this.listaEsquinas.buscar(esquina) == null){
				this.listaEsquinas.insertarAlPrincipio(esquina);
				System.out.println(esquina.toString());
				return new Retorno(Resultado.OK);
			}else{
				System.out.println("Error 2 - Ya existen esas coordenadas");
				return new Retorno(Resultado.ERROR_2);
			}			
		}else{
			System.out.println("Error 1 - Ya hay registrdas "+ this.getiCantEsquinas() + " esquinas");
			return new Retorno(Resultado.ERROR_1);
		}			
	}

	@Override
	public Retorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int metros) {
		if(metros > 0){
			Esquina inicio = new Esquina(coordXi,coordYi);
			inicio = (Esquina)this.listaEsquinas.buscar(inicio);
			Esquina fin = new Esquina(coordXf,coordYf);
			fin = (Esquina)this.listaEsquinas.buscar(fin);
			if(inicio != null && fin != null){
				Tramo tramo = new Tramo(inicio, fin, metros);
				if(!this.listaTramos.pertenece(tramo)){
					this.listaTramos.insertarAlPrincipio(tramo);
					System.out.println(tramo.toString());
					return new Retorno(Resultado.OK);
				}else{
					System.out.println("Error 3 - Tramo ya existe en es sistema.");
					return new Retorno(Resultado.ERROR_3);
				}
			}else{
				System.out.println("Error 2 - Error en coordenadas");
				return new Retorno(Resultado.ERROR_2);
			}
		}else{
			System.out.println("Error 1 - Metros menor o igual a 0");
			return new Retorno(Resultado.ERROR_1);
		}
	}
	
	@Override
	public Retorno eliminarEsquina(Double coordX, Double coordY) {
		Esquina esquina = new Esquina(coordX ,coordY);
		esquina = (Esquina)this.listaEsquinas.buscar(esquina);
		if(esquina != null){
			ListaSimple_impl tramos = this.buscarTramosByEsquina(esquina);
			NodoLista nodo = (NodoLista)tramos.ObtenerElementoPrimero();
			while(nodo != null){
				Tramo tramo = (Tramo) nodo.getDato();
				if(tramo != null){
					this.listaTramos.borrarElemento(tramo);
				}
				nodo = nodo.getSiguiente();
			}
			this.listaEsquinas.borrarElemento(esquina);
			return new Retorno(Resultado.OK);
		}else{
			System.out.println("Error 1 - No existe esquina en el sistema");
			return new Retorno(Resultado.ERROR_1);
		}		
	}

	@Override
	public Retorno eliminarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf) {
		Esquina inicio = (Esquina) this.listaEsquinas.buscar(new Esquina(coordXi, coordYi));
		Esquina fin = (Esquina) this.listaEsquinas.buscar(new Esquina(coordXf, coordYf));
		if(inicio != null && fin != null){
			Tramo tramo = (Tramo) this.listaTramos.buscar(new Tramo(inicio, fin, 0));
			if(tramo != null){
				this.listaTramos.borrarElemento(tramo);
				return new Retorno(Resultado.OK);
			}else{
				System.out.println("Error 2 - No existe Tramo en el sistema");
				return new Retorno(Resultado.ERROR_2);
			}				
		}else{
			System.out.println("Error 1 - No existe esquina en el sistema");
			return new Retorno(Resultado.ERROR_1);
		}
	}

	@Override
	public Retorno movilMasCercano(Double coordX, Double coordY) {
		Movil movilMasCercano = new Movil();
		int metros = -1;
		
		if(this.getArbolMoviles().esArbolVacio())
			return new Retorno(Resultado.OK);
		
		//El mejor caso posible. Que en la esquina en la que estoy parado, haya un movil disponible.
		Esquina esquina = (Esquina) this.listaEsquinas.buscar(new Esquina(coordX, coordY));
		if(esquina != null){
			if(esquina.getMovil() != null && esquina.getMovil().getEstadoMovil().equals(EstadoMovil.DISPONIBLE)){
				movilMasCercano = esquina.getMovil();
				movilMasCercano.setEstadoMovil(EstadoMovil.ASIGNADO);
				System.out.println(movilMasCercano.getMatricula() + ";" + metros + ";" + movilMasCercano.getConductor());
				return new Retorno(Resultado.OK);
			}
			
			//Si no encontre movil disponible en la esquina, voy a buscar en el radio.
			//movilMasCercano = provider.movilMasCercano(esquina);
				movilMasCercano.setEstadoMovil(EstadoMovil.ASIGNADO);
				System.out.println(movilMasCercano.getMatricula() + ";" + metros + ";" + movilMasCercano.getConductor());
				return new Retorno(Resultado.OK);
		}else{
			System.out.println("Error 1 - No existe esquina en el sistema");
			return new Retorno(Resultado.ERROR_1);
		}
	}

	@Override
	public Retorno verMovilesEnRadio(Double coordX, Double coordY, int radio) {
//		if(this.getArbolMoviles().esArbolVacio())
//			return new Retorno(Resultado.OK);
//		
//		//ListaOrdenada_impl lstMovilesEnRadio = provider.obtenerMovilesEnRadio(radio);
//		
//		if(lstMovilesEnRadio != null && !lstMovilesEnRadio.esVacia()){
//			Nodo_ListaOrdenada aux = (Nodo_ListaOrdenada) lstMovilesEnRadio.ObtenerElementoPrimero();
//			while(aux != null){
//				EntidadRetornable_Movil_Radio movilRadio = new EntidadRetornable_Movil_Radio();
//				movilRadio = (EntidadRetornable_Movil_Radio) aux.getDato();
//				movilRadio.toString();
//				aux = aux.getSiguiente();
//			}
//		}
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno verMapa() {
		try {
            abrirMapa();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
		
		return new Retorno(Resultado.OK);
	}

    private void abrirMapa() throws IOException, URISyntaxException {
        String puntosMapa = "http://maps.googleapis.com/maps/api/staticmap?size=1200x600" + this.listaEsquinas + this.listaTramos;
        Desktop.getDesktop().browse(new URI(puntosMapa));
    }
    
    public Hash listadoPuntosMapa() {
        return puntosGrafo;
    }
    
	private ListaSimple_impl buscarTramosByEsquina(Esquina esquina){
		ListaSimple_impl retorno = new ListaSimple_impl();
		NodoLista nodo =(NodoLista) this.listaTramos.ObtenerElementoPrimero();
		while(nodo != null){
			Tramo tramo = (Tramo)nodo.getDato();
			if (tramo.getEsquinaOrigen().equals(esquina) || tramo.getEsquinaDestino().equals(esquina)){
				retorno.insertarAlPrincipio(tramo);
			}
			nodo = nodo.getSiguiente();
		}
		return retorno;
	}
	
	public ListaOrdenada_impl obtenerMovilesEnRadio(int radio) {
		ListaOrdenada_impl lstRetorno = new ListaOrdenada_impl();
		// Aca recorro con Dijkstra y voy creando y guardando entidades EntidadRetornable_Movil_Radio (inserto ordenado)
		
		return lstRetorno;
	}
	
	public DistanciaEsquina[][] caminosMinimos(Esquina esquina){
		DistanciaEsquina minimo = new DistanciaEsquina(99999, -1) ;
		int min = 999999;
		int esquinaSig = -1;
		int esquinaActual = esquina.getEsquinaId();
		GrafoMatrizAdyacencia_impl grafo = new GrafoMatrizAdyacencia_impl(this.listaEsquinas.largo());
		DistanciaEsquina[][] caminos = new DistanciaEsquina[grafo.getCantNodos()+1][grafo.getCantNodos()+1];// [x]=idCiudad [y]=paso		
		grafo.MatrizAdyacencia = this.GenerarMatrizAdy();
		for (int numeroDePaso = 1; numeroDePaso < grafo.getCantNodos()+1; numeroDePaso++) {
			for (int destino = 1; destino < grafo.getCantNodos()+1; destino++) {
				if (grafo.MatrizAdyacencia[esquinaActual][destino] > 0) {// si hay camino, -1 = no adyacentes
					if (grafo.getNodosUsados()[destino] != true) {
						grafo.getNodosUsados()[destino] = true;
						DistanciaEsquina distanciaEsquina = new DistanciaEsquina();
						distanciaEsquina.setIdEsquinaAnterior(esquinaActual);
						distanciaEsquina.setDistanciaAcumulada(distanciaEsquina.getDistanciaAcumulada()
																+ grafo.MatrizAdyacencia[esquinaActual][destino]);
						DistanciaEsquina aux = caminos[destino][numeroDePaso - 1];
						if (aux == null	|| aux.getDistanciaAcumulada() > distanciaEsquina.getDistanciaAcumulada()) {//comparo con la distancia del paso anterior al mismo destino
							caminos[destino][numeroDePaso] = distanciaEsquina;	//si es menor la guardo				
						} else {
							caminos[destino][numeroDePaso] = aux; // si es mayor me quedo con la anterior
						}
						if (min > caminos[destino][numeroDePaso].getDistanciaAcumulada()) {//me quedo con el de menor distancia de ese paso
							minimo = new DistanciaEsquina();
							minimo = caminos[destino][numeroDePaso];
							min = minimo.getDistanciaAcumulada();
							esquinaSig = destino;
						}
					}
				}
			}
			esquinaActual = esquinaSig;			
			min = 9999999;
		}	
		
		return caminos;
	}
	
	private int[][] GenerarMatrizAdy( ) {
			
		int largo = this.listaEsquinas.largo()+1;
		int x = 1;
			
		int[][] matriz = new int[largo][largo];
		while (x < largo) {
			int y = 1;	
			while (y < largo) {
				Tramo tramo = new Tramo();
				tramo.setEsquinaOrigen(this.getEsquinaById(x));
				tramo.setEsquinaDestino(this.getEsquinaById(y));
				tramo = (Tramo) this.getListaTramos().buscar(tramo);
				if (tramo != null) {
					matriz[x][y] = tramo.getMetros();
					matriz[y][x] = tramo.getMetros();
				} else if (x == y) {
					matriz[x][y] = 0;
					matriz[y][x] = 0;
				} else {
					matriz[x][y] = -1;
					matriz[y][x] = -1;
				}
				y++;
			}			
			x++;
		}
		return matriz;
	}
	
	public Esquina getEsquinaById(int id){
		Esquina esquinaBuscada;

		if (this.getListaEsquinas().esVacia())
			return null;
		else {
			NodoLista aux = (NodoLista) this.getListaEsquinas().ObtenerElementoPrimero();

			while (aux != null) {
				esquinaBuscada = (Esquina) aux.getDato();

				if (esquinaBuscada.getEsquinaId() == id)
					return esquinaBuscada;
				else
					aux = aux.getSiguiente();
			}
		}
		return null;
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

	public ListaSimple_impl getListaTramos() {
		return listaTramos;
	}

	public void setListaTramos(ListaSimple_impl listaTramos) {
		this.listaTramos = listaTramos;
	}
	public ListaSimple_impl getListaEsquinas() {
		return listaEsquinas;
	}

	public void setListaEsquinas(ListaSimple_impl listaEsquinas) {
		this.listaEsquinas = listaEsquinas;
	}

	public Hash getPuntosGrafo() {
		return puntosGrafo;
	}

	public void setPuntosGrafo(Hash puntosGrafo) {
		this.puntosGrafo = puntosGrafo;
	}

	public provider getProvider() {
		return provider;
	}

	public void setProvider(provider provider) {
		this.provider = provider;
	}

//	public GrafoMatrizAdyacencia_impl getGrafoMatrizAdy() {
//		return grafoMatrizAdy;
//	}
//
//	public void setGrafoMatrizAdy(GrafoMatrizAdyacencia_impl grafoMatrizAdy) {
//		this.grafoMatrizAdy = grafoMatrizAdy;
//	}
	
	public void imprimir(){
		//provider prov = new provider();
		Esquina esq = this.getEsquinaById(1);
		DistanciaEsquina [][] dist= this.caminosMinimos(esq);
		for(int x = 0 ;x < dist.length-1;x++){
			for(int y = 0 ;y < dist[0].length-1;y++){
				if(dist[x][y] !=null){
					System.out.print(" "+dist[x][y].toString()+" ");
				}else{
					System.out.print(" #-# ");
				}
			}
			System.out.println(" ");
		}
	}
	
	public void imprimir2(){
		//provider prov = new provider();
		//Esquina esq = this.getEsquinaById(1);
		int [][] dist= this.GenerarMatrizAdy();
		for(int x = 0 ;x < dist.length;x++){
			for(int y = 0 ;y < dist[0].length;y++){
				System.out.print(" ("+dist[x][y]+") ");
			}
			System.out.println(" ");
		}
	}
	

}
