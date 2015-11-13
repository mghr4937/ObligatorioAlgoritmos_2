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
import Estructuras.Cola.ColaImpl;
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
	static String mensaje_movilMasCercano;
	static String mensaje_movilesEnRadio;

	private provider provider = new provider();

	public Sistema() {
		this.arbolMoviles = new ArbolBinarioImpl();
		this.listaTramos = new ListaSimple_impl();
		this.listaEsquinas = new ListaSimple_impl();
		this.puntosGrafo = new Hash(iCantEsquinas);
	}

	//Precondiciones: la cantidad de esquinas o puntos debe ser mayor 0.
	//Postcondiciones: Crea el sistema con una cantidad limite de esquinas igual a cantPuntos.
	@Override
	public Retorno inicializarSistema(int cantPuntos) {
		Esquina.setNumeradora(0);
		if (cantPuntos <= 0) {
			System.out.println("No hay esquinas en el mapa");
			return new Retorno(Resultado.ERROR_1);
		}
		this.setiCantEsquinas(cantPuntos);
		return new Retorno(Resultado.OK);
	}

	//Precondiciones: Debe existir una instancia del sistema ya creada.
	//Postcondiciones: Destruye el sistema de Car Pooling y todos sus elementos, liberando la memoria utilizada.
	@Override
	public Retorno destruirSistema() {
		this.arbolMoviles = null;
		this.listaTramos = null;
		this.listaEsquinas = null;
		this.puntosGrafo = null;
		return new Retorno(Resultado.OK);
	}

	//Precondiciones: Debemos recibir un string para matricula y otro para conductor. Se hacen busquedas internas de los objetos con dichos identificadores
	//Postcondiciones: Crea un nuevo movil con id la matricula dada, en estado DISPONIBLE y le asigna el identificador deseado su conductor
	@Override
	public Retorno registrarMovil(String matricula, String conductor) {
		Movil buscado = this.getMovilByMatricula(matricula);
		if (buscado == null) {
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

	//Precondiciones: Recibimos un string con la matricula del objeto movil
	//Postcondiciones: Cambia el estado del movil a DESHABILITADO, en caso de que se encuentre el mismo y este en estado DESHABILITADO
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

	//Precondiciones: Recibimos un string con la matricula del objeto movil
	//Postcondiciones: Cambia el estado del movil a DISPONIBLE en caso de que el movil exista y no este disponible
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

	//Precondiciones: Recibimos un string que representa la matricula del movil
	//Postcondiciones: Destruye el movil identificado con esa matricula, en caso de que el mismo no este asignado.
	@Override
	public Retorno eliminarMovil(String matricula) {
		Object entidadFantasia = new Movil(matricula);
		NodoArbolBinario nodoMovilBuscado = this.arbolMoviles.obtenerElemento(entidadFantasia,
				this.arbolMoviles.getRaiz());
		if (nodoMovilBuscado != null) {
			Movil movilBuscado = (Movil) nodoMovilBuscado.getDato();
			if (movilBuscado.getEstadoMovil().equals(EstadoMovil.ASIGNADO)) {
				System.out.println("Error 2 - Movil en estado asignado");
				return new Retorno(Resultado.ERROR_2);
			} else {
				this.arbolMoviles.borrarElemento(nodoMovilBuscado);
				return new Retorno(Resultado.OK);
			}
		} else {
			System.out.println("Error 1 - Movil no encontrado");
			return new Retorno(Resultado.ERROR_1);
		}
	}

	//Precondiciones: Recibimos un string que representa la matricula del movil y dos valores double, representando las coordenadas de una esquina del mapa
	//Postcondiciones: Ubica en la esquina de coordenadas x e y, al movil de la matricula pasada por parametro, en caso de que exista la esquina y el movil y ya no haya un movil en la misma
	@Override
	public Retorno asignarUbicacionMovil(String matricula, Double coordX, Double coordY) {
		Movil movil = getMovilByMatricula(matricula);
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
	
	//Precondiciones: Recibimos un string que representa la matricula de un movil
	//Postcondiciones: Imprime en pantalla los datos del movil requerido, en caso de que el mismo exista. Formato: [matricula;conductor;estado]
	@Override
	public Retorno buscarMovil(String matricula) {
		Object entidadFantasia = new Movil(matricula);
		NodoArbolBinario nodoMovilBuscado = this.arbolMoviles.obtenerElemento(entidadFantasia,this.arbolMoviles.getRaiz());
		if (nodoMovilBuscado != null){
			Movil movilBuscado = (Movil) nodoMovilBuscado.getDato();
			System.out.println(movilBuscado.toString());
			Retorno retorno = new Retorno(Resultado.OK);
			String mensaje = movilBuscado.toString();
			retorno.valorString = mensaje;
			return retorno;
		}else{
			System.out.println("Error 1 - Movil no encontrado");
			return new Retorno(Resultado.ERROR_1);
		}
	}

	//Precondiciones: No se encuentran precondiciones
	//Postcondiciones: En caso de haber moviles registrados, se imprimen en pantalla [Matricula;Conductor;Estado]
	@Override
	public Retorno informeMoviles() {
		String str = "";
		String aux = "";
		Retorno retorno = new Retorno(Resultado.OK);
		if(!this.arbolMoviles.esArbolVacio())
			str = this.arbolMoviles.mostrarInOrder(aux);
		System.out.println(str);
		retorno.valorString = str;
		return retorno;
	}

	//Precondiciones: Recibimos dos valores representantes de las coordenadas que forman una esquina.
	//Postcondiciones: Si hay lugar para guardar una esquina mas y la misma no existe en el sistema, se crea una esquina con coordenadas x e y 
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
			System.out.println("Error 1 - Ya hay registradas "+ this.getiCantEsquinas() + " esquinas");
			return new Retorno(Resultado.ERROR_1);
		}			
	}

	//Precondiciones: Se reciben dos juegos de coordenadas y una cantidad de metros (debe ser numero natural)
	//Postcondiciones: Si la cantidad de metros es coherente, el tramo aun no existe y cada esquina que compondran el tramo, existen, crea un tramo (bidireccionalmente) en el mapa
	@Override
	public Retorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int metros) {
		if(metros > 0){
			Esquina inicio = new Esquina(coordXi,coordYi);
			inicio = (Esquina)this.listaEsquinas.buscar(inicio);
			Esquina fin = new Esquina(coordXf,coordYf);
			fin = (Esquina)this.listaEsquinas.buscar(fin);
			if(inicio != null && fin != null){
				Tramo tramoIda = new Tramo(inicio, fin, metros);
				Tramo tramoVuelta = new Tramo(fin, inicio, metros);
				if(!this.listaTramos.pertenece(tramoIda)){
					this.listaTramos.insertarAlPrincipio(tramoIda);
					this.listaTramos.insertarAlPrincipio(tramoVuelta);
					System.out.println(tramoIda.toString());
					System.out.println(tramoVuelta.toString());
					return new Retorno(Resultado.OK);
				}else{
					System.out.println("Error 3 - Tramo ya existe en el sistema.");
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
	
	//Precondiciones: Recibimos las coordenadas de una esquina.
	//Postcondiciones: Si la esquina de coordenadas existe y no es parte de otra informacion utilizada en el sistema, la elimina del mapa
	@Override
	public Retorno eliminarEsquina(Double coordX, Double coordY) {
		Esquina esquina = new Esquina(coordX ,coordY);
		esquina = (Esquina)this.listaEsquinas.buscar(esquina);
		if(esquina != null){
			if(esquina.getMovil() != null){
				System.out.println("Error 2 - Hay un movil en esa esquina. No se puede borrar");
				return new Retorno(Resultado.ERROR_2);
			}
			
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

	//Precondiciones: Recibimos un juego de coordenadas que componen las dos esquinas del tramo que queremos eliminar.
	//Postcondiciones: Si la esquina de coordenadas existe y no es parte de informacion vigente del sistema, la elimina del mapa
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
	
//Precondiciones: Recibimos un juego de coordenadas que forman una esquina
	//Postcondiciones: Si existe la esquina, busca el movil mas cercano respecto a las coordenadas pasadas por paramero, que este DISPONIBLE
	@Override
	public Retorno movilMasCercano(Double coordX, Double coordY) {
		mensaje_movilMasCercano = "";
		EntidadRetornable_Movil_Radio movilMasCercanoEntidad = new EntidadRetornable_Movil_Radio();

		
		/*if(this.getArbolMoviles().esArbolVacio()){
			return new Retorno(Resultado.OK);

		}*/
		
		//El mejor caso posible. Que en la esquina en la que estoy parado, haya un movil disponible.
		Esquina esquina = (Esquina) this.listaEsquinas.buscar(new Esquina(coordX, coordY));
		if(esquina != null){
			if(esquina.getMovil() != null && esquina.getMovil().getEstadoMovil().equals(EstadoMovil.DISPONIBLE)){
				movilMasCercanoEntidad.setMovil(esquina.getMovil());
				movilMasCercanoEntidad.getMovil().setEstadoMovil(EstadoMovil.ASIGNADO);

				mensaje_movilMasCercano = movilMasCercanoEntidad.toString();
				return new Retorno(Resultado.OK);
			}
			
			//Si no encontre movil disponible en la esquina, voy a buscar en el radio.
			movilMasCercanoEntidad = this.getMovilMasCercano(esquina);
			movilMasCercanoEntidad.getMovil().setEstadoMovil(EstadoMovil.ASIGNADO);

			mensaje_movilMasCercano = movilMasCercanoEntidad.toString();
			return new Retorno(Resultado.OK);
		}else{
			System.out.println("Error 1 - No existe esquina en el sistema");
			return new Retorno(Resultado.ERROR_1);
		}
	}
	


	private EntidadRetornable_Movil_Radio getMovilMasCercano(Esquina esquina) {
		int[] distanciaEsquinas = this.caminosMinimos(esquina);





		int idMasCercano = posicionDelMinimoElemento(distanciaEsquinas, 1, distanciaEsquinas.length-1);
		EntidadRetornable_Movil_Radio aRetornar = new EntidadRetornable_Movil_Radio();
		boolean encontre = false;

		while (encontre == false) {
			Esquina esquinaEvaluar = getEsquinaById(idMasCercano);
			if (esquinaEvaluar != null) {
				if (esquinaEvaluar.getMovil() != null && esquinaEvaluar.getMovil().getEstadoMovil().equals(EstadoMovil.DISPONIBLE)) {
					aRetornar.setMovil(esquinaEvaluar.getMovil());
					aRetornar.setMetros(distanciaEsquinas[idMasCercano]);
					encontre = true;
				}
				distanciaEsquinas[idMasCercano] = 0;
				idMasCercano = posicionDelMinimoElemento(distanciaEsquinas, 1, distanciaEsquinas.length-1);
			}
		}
		return aRetornar;
	}
	
	 public static int posicionDelMinimoElemento(int v[], int desde, int hasta) {

	        if (desde == hasta) {
	            return desde;
	        } else {
	            int minimo = posicionDelMinimoElemento(v, desde + 1, hasta);
	            if (v[desde] < v[minimo] && v[desde] > 0) {
	            	minimo = desde;
	            }
	            return minimo;
	        }
	    }

	//Precondiciones: Recibimos un juego de coordenadas que forman una esquina y un valor numerico que represente el radio en mts en el que se deben buscar moviles
	//Postcondiciones: Si existe la esquina y el radio es coherente, nos trae la lista de moviles DISPONIBLES que se encuentran dentro de ese radio
	@Override
	public Retorno verMovilesEnRadio(Double coordX, Double coordY, int radio) {
		if(radio <= 0){
			return new Retorno(Resultado.ERROR_1);
		}
		mensaje_movilesEnRadio = "";
		if(this.getArbolMoviles().esArbolVacio()){
			return new Retorno(Resultado.OK);
		}
		Esquina esquina = (Esquina)this.listaEsquinas.buscar(new Esquina(coordX, coordY));
		if(esquina != null){
			ListaOrdenada_impl lstMovilesEnRadio = this.obtenerMovilesEnRadio(radio, esquina);
			if(lstMovilesEnRadio != null && !lstMovilesEnRadio.esVacia()){
			Nodo_ListaOrdenada aux = (Nodo_ListaOrdenada) lstMovilesEnRadio.ObtenerElementoPrimero();
				while (aux != null) {
					EntidadRetornable_Movil_Radio movilRadio = new EntidadRetornable_Movil_Radio();
					movilRadio = (EntidadRetornable_Movil_Radio) aux.getDato();
					mensaje_movilesEnRadio += movilRadio.toString();
					aux = aux.getSiguiente();
				}
			}
		}else{
			return new Retorno(Resultado.ERROR_2);
		}	
		return new Retorno(Resultado.OK);
	}
	
	public ListaOrdenada_impl obtenerMovilesEnRadio(int radio, Esquina esquina) {
		ListaOrdenada_impl lstRetorno = new ListaOrdenada_impl();
		// Aca recorro con Dijkstra y voy creando y guardando entidades EntidadRetornable_Movil_Radio (inserto ordenado)
		int distancia [] = this.caminosMinimos(esquina);
		for (int x = 1; x <= distancia.length-1; x++) {
			Esquina esq = this.getEsquinaById(x);
			Movil movil = esquina.getMovil();
			if(movil != null){
				EntidadRetornable_Movil_Radio aux = new EntidadRetornable_Movil_Radio(movil, distancia[x]);
				lstRetorno.insertarOrdenado(aux);
			}
		}		
		return lstRetorno;
	}

	//Precondiciones: No se encuentran precondiciones
	//Postcondiciones: Muestra la realidad vigente de nuestro sistema, en un mapa desplegado en el navegador
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
	

	
	public int[] caminosMinimos(Esquina esquina){			
		GrafoMatrizAdyacencia_impl grafo = new GrafoMatrizAdyacencia_impl(this.iCantEsquinas);
		grafo.MatrizAdyacencia = this.GenerarMatrizAdy();
		int[] distancia = new int[this.iCantEsquinas+1];// [x]=idCiudad [y]=paso
		for(int d = 1; d < distancia.length;d++){
			distancia[d] = 999999999;
		}
		//int distancia[ ] = new int[9999999];//distancia[ i ] distancia de esquina inicial a esquina con ID = i
		ColaImpl cola = new ColaImpl();
		int esquinaInicial = esquina.getEsquinaId();
		cola.enColar(esquinaInicial);
		distancia[esquinaInicial] =  0;
		int actual , adyacente , peso;
		while(!cola.esVacia()){
			actual = (int)cola.desEncolar().getDato(); //Obtengo de la cola el nodo con menor peso, en un comienzo será el inicial
			if(grafo.getNodosUsados()[actual]!= true){ //Si la esquina actual ya fue visitada entonces sigo sacando elementos de la cola
				grafo.getNodosUsados()[actual] = true;
				for(int destino = 1; destino < grafo.getCantNodos();destino++){//reviso adyacentes
					if (grafo.MatrizAdyacencia[actual][destino] >= 1) {// si hay camino, -1 = no adyacentes
							adyacente = destino;
							peso = grafo.MatrizAdyacencia[actual][destino];
							if( grafo.getNodosUsados()[adyacente] != true ){//si la esquina adyacente no fue visitada
				                //this.relajacion(actual , adyacente , peso ); //realizamos el paso de relajacion
								 if( distancia[ actual ]+ peso < distancia[ adyacente ] ){
								        distancia[ adyacente ]= distancia[ actual ] + peso;  //relajamos el vertice actualizando la distancia
								        //previo[ adyacente ] = actual;                         //a su vez actualizamos el vertice previo
								        cola.enColar(adyacente);; //agregamos adyacente a la cola de prioridad
								    }
				            }							
					}
				}			
			}	
		
		}
		return distancia;
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
		Esquina esq = this.getEsquinaById(5);
		int []dist= this.caminosMinimos(esq);
		for(int x = 0 ;x < dist.length-1;x++){			
				if(dist[x] > 0){
					System.out.print(" "+dist[x]+" ");
				}else{
					System.out.print(" #-# ");
				}
			}
			System.out.println(" ");
		
	}
	

	
	public Movil getMovilByMatricula(String matricula){
		Movil movilBuscado = new Movil(matricula);
		NodoArbolBinario nodoMovilBuscado = this.arbolMoviles.obtenerElemento(movilBuscado,this.arbolMoviles.getRaiz());
		if(nodoMovilBuscado != null){
			movilBuscado = (Movil) nodoMovilBuscado.getDato();
			return movilBuscado;
		}
		return null;
	}
	
}
