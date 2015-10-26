package main;

import BaseEntregada.Sistema;

public class Main {

	public static void main(String[] args) {

		Sistema sistema = new Sistema();

		// INSERTAR MOVIL
		sistema.registrarMovil("MAE 2873", "Conductor_Uno");
		sistema.registrarMovil("ABC 1234", "Conductor_Dos");

		// DESHABILITAR MOVIL
		sistema.deshabilitarMovil("MAE 2873");
		sistema.deshabilitarMovil("ABC 1234");

		// HABILITAR MOVIL
		sistema.habilitarMovil("MAE 2873");
		sistema.habilitarMovil("ABC 1234");
		
		// BUSCAR MOVIL
		sistema.buscarMovil("MAE 2873");
		sistema.buscarMovil("ABC 1234");

		// ELIMINAR MOVIL
		sistema.eliminarMovil("MAE 2873");
		sistema.eliminarMovil("ABC 1234");
	}
}
