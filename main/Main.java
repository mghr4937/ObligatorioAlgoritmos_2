package main;

import BaseEntregada.Sistema;

public class Main {

	public static void main(String[] args) {

		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		
		sistema.registrarEsquina(1.555, 1.222); // A
		sistema.registrarEsquina(1.51, 1.21); // B
		sistema.registrarEsquina(1.52, 1.221); // C
		sistema.registrarEsquina(1.53, 1.24); // D
		sistema.registrarEsquina(1.54, 1.25); // E
		
		sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1); // A-B
		sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5); // A-D
		sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2); // B-C
		sistema.registrarTramo( 1.52, 1.221, 1.54,1.25, 3); // C-D
		sistema.registrarTramo( 1.53, 1.24, 1.54,1.25, 2); // D-E
		
		sistema.registrarMovil("ABC 0001", "Conductor_Uno");
		sistema.registrarMovil("ABC 0002", "Conductor_Dos");
		sistema.registrarMovil("ABC 0003", "Conductor_Tres");
		sistema.registrarMovil("ABC 0004", "Conductor_Cuatro");
		sistema.registrarMovil("ABC 0005", "Conductor_Cinco");
		
		sistema.asignarUbicacionMovil("ABC 0001", 1.555, 1.222);
		sistema.asignarUbicacionMovil("ABC 0002", 1.51, 1.21);
		sistema.asignarUbicacionMovil("ABC 0003", 1.52, 1.221);
		sistema.asignarUbicacionMovil("ABC 0004", 1.53, 1.24);
		sistema.asignarUbicacionMovil("ABC 0005", 1.54, 1.25);
		
		sistema.verMapa();
	}
}
