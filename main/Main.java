package main;

import BaseEntregada.Sistema;

public class Main {

	public static void main(String[] args) {

		Sistema sistema = new Sistema();
		
		// INSERTAR MOVIL
		sistema.registrarMovil("ABC 0002", "Conductor_Dos");
		sistema.registrarMovil("ABC 0001", "Conductor_Uno");
		sistema.registrarMovil("ABC 0003", "Conductor_Tres");

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
		
		sistema.informeMoviles();
	}
}
