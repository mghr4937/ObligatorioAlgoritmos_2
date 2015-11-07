package main;

import BaseEntregada.Sistema;

public class Main {

	public static void main(String[] args) {

		Sistema sistema = new Sistema();
		sistema.inicializarSistema(10);
		// INSERTAR MOVIL
		//sistema.registrarMovil("ABC 0002", "Conductor_Dos");
		//sistema.registrarMovil("ABC 0001", "Conductor_Uno");
		//sistema.registrarMovil("ABC 0003", "Conductor_Tres");

		// DESHABILITAR MOVIL
		//sistema.deshabilitarMovil("ABC 0002");
		//sistema.deshabilitarMovil("ABC 0003");
		//sistema.deshabilitarMovil("ABC 0001");

		// HABILITAR MOVIL
		//sistema.habilitarMovil("ABC 0001");
		//sistema.habilitarMovil("ABC 0003");
		//sistema.habilitarMovil("ABC 0002");
		
		// BUSCAR MOVIL
		//sistema.buscarMovil("ABC 0003");
		//sistema.buscarMovil("ABC 0001");
		//sistema.buscarMovil("ABC 0002");

		// ELIMINAR MOVIL
		//sistema.eliminarMovil("ABC 0002");
		//sistema.eliminarMovil("ABC 0001");
		//sistema.eliminarMovil("ABC 0003");
		
		
		sistema.registrarEsquina(1.555, 1.222);//1 a
		sistema.registrarEsquina(1.51, 1.21);//2  b
		sistema.registrarEsquina(1.52, 1.221);//3 c
		sistema.registrarEsquina(1.53, 1.24);//4 d
		sistema.registrarEsquina(1.54, 1.25);//5 e
		sistema.registrarEsquina(1.24, 1.28);//6 f
		
		sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1); //a-b
		sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);// a-d
		sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);// b-c
		sistema.registrarTramo( 1.52, 1.221, 1.54,1.25, 3);// c-e
		sistema.registrarTramo( 1.53, 1.24, 1.54,1.25, 2);// d-e
		sistema.registrarTramo( 1.53, 1.24, 1.24,1.28, 4);// d-f
		sistema.registrarTramo( 1.24, 1.28, 1.54,1.25, 1);// f-e
		
		sistema.imprimir();
	}
}
