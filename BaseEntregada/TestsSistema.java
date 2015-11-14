package BaseEntregada;

import static org.junit.Assert.*;

import org.junit.Test;

import Dominio.Movil;
import Dominio.Movil.EstadoMovil;

public class TestsSistema {

	//******************* INICIO PUNTOS 1 ********************
	
	@Test
	public void inicializarSistema_returnOk() {
		Sistema sistema = new Sistema();
		Retorno retorno = sistema.inicializarSistema(5);
		assertEquals(retorno.resultado, Retorno.Resultado.OK);
	}

	@Test
	public void inicializarSistema_Cero_returnFail() {
		Sistema sistema = new Sistema();
		Retorno retorno = sistema.inicializarSistema(0);
		assertEquals(retorno.resultado, Retorno.Resultado.ERROR_1);
	}

	@Test
	public void inicializarSistema_menorCero_returnFail() {
		Sistema sistema = new Sistema();
		Retorno retorno = sistema.inicializarSistema(-1);
		assertEquals(retorno.resultado, Retorno.Resultado.ERROR_1);
	}

	@Test
	public void destruirSistema() {
		Sistema sistema = new Sistema();
		Retorno retorno = sistema.destruirSistema();
		assertEquals(retorno.resultado, Retorno.Resultado.OK);
	}

	//******************** INICIO PUNTOS 2 ********************

	@Test
	public void registrarMovil_uno_returnOk() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		String matricula = "ABC1234";
		String conductor = "46110887";
		Retorno retorno = sistema.registrarMovil(matricula, conductor);
		assertEquals(retorno.resultado, Retorno.Resultado.OK);
	}

	@Test
	public void registrarMovil_tres_returnOk() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		String matriculaUno = "ABC1234";
		String conductorUno = "46110887";
		Retorno retornoMovilUno = sistema.registrarMovil(matriculaUno, conductorUno);
		assertEquals(retornoMovilUno.resultado, Retorno.Resultado.OK);
		String matriculaDos = "BCD2345";
		String conductorDos = "43553319";
		Retorno retornoMovilDos = sistema.registrarMovil(matriculaDos, conductorDos);
		assertEquals(retornoMovilDos.resultado, Retorno.Resultado.OK);
		String matriculaTres = "DEF2345";
		String conductorTres = "35059091";
		Retorno retornoMovilTres = sistema.registrarMovil(matriculaTres, conductorTres);
		assertEquals(retornoMovilTres.resultado, Retorno.Resultado.OK);
	}

	@Test
	public void registrarMovil_movilYaExiste_returnFail() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		String matriculaUno = "ABC1234";
		String conductorUno = "46110887";
		Retorno retornoMovilUno = sistema.registrarMovil(matriculaUno, conductorUno);
		assertEquals(retornoMovilUno.resultado, Retorno.Resultado.OK);
		String matriculaDos = "ABC1234";
		String conductorDos = "46110887";
		Retorno retornoMovilDos = sistema.registrarMovil(matriculaDos, conductorDos);
		assertEquals(retornoMovilDos.resultado, Retorno.Resultado.ERROR_1);
	}

	@Test
	public void deshabilitarMovil_uno_returnOk() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		String matriculaUno = "ABC1234";
		String conductorUno = "46110887";
		Retorno retornoMovilUno = sistema.registrarMovil(matriculaUno, conductorUno);
		assertEquals(retornoMovilUno.resultado, Retorno.Resultado.OK);
		Retorno deshabilitarMovilReturn = sistema.deshabilitarMovil(matriculaUno);
		assertEquals(deshabilitarMovilReturn.resultado, Retorno.Resultado.OK);
	}

	@Test
	public void deshabilitarMovil_tres_returnOk() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		String matriculaUno = "ABC1234";
		String conductorUno = "46110887";
		Retorno retornoMovilUno = sistema.registrarMovil(matriculaUno, conductorUno);
		assertEquals(retornoMovilUno.resultado, Retorno.Resultado.OK);
		String matriculaDos = "BCD2345";
		String conductorDos = "43553319";
		Retorno retornoMovilDos = sistema.registrarMovil(matriculaDos, conductorDos);
		assertEquals(retornoMovilDos.resultado, Retorno.Resultado.OK);
		String matriculaTres = "DEF2345";
		String conductorTres = "35059091";
		Retorno retornoMovilTres = sistema.registrarMovil(matriculaTres, conductorTres);
		assertEquals(retornoMovilTres.resultado, Retorno.Resultado.OK);
		Retorno deshabilitarMovilUno = sistema.deshabilitarMovil(matriculaUno);
		assertEquals(deshabilitarMovilUno.resultado, Retorno.Resultado.OK);
		Retorno deshabilitarMovilDos = sistema.deshabilitarMovil(matriculaDos);
		assertEquals(deshabilitarMovilDos.resultado, Retorno.Resultado.OK);
		Retorno deshabilitarMovilTres = sistema.deshabilitarMovil(matriculaTres);
		assertEquals(deshabilitarMovilTres.resultado, Retorno.Resultado.OK);
	}

	@Test
	public void deshabilitarMovil_errorUno_returnFail() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno deshabilitarMovilPruebaUno = sistema.deshabilitarMovil("ABC1234");
		assertEquals(deshabilitarMovilPruebaUno.resultado, Retorno.Resultado.ERROR_1);
		Retorno deshabilitarMovilPruebaDos = sistema.deshabilitarMovil("BCD2345");
		assertEquals(deshabilitarMovilPruebaDos.resultado, Retorno.Resultado.ERROR_1);
	}

	@Test
	public void deshabilitarMovil_errorDos_returnFail() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		String matriculaUno = "ABC1234";
		String conductorUno = "46110887";
		sistema.registrarMovil(matriculaUno, conductorUno);
		Movil movilUno = sistema.getMovilByMatricula(matriculaUno);
		movilUno.setEstadoMovil(EstadoMovil.ASIGNADO);
		Retorno retornoUno = sistema.deshabilitarMovil("ABC1234");
		assertEquals(retornoUno.resultado, Retorno.Resultado.ERROR_2);
		String matriculaDos = "BCD2345";
		String conductorDos = "43553319";
		sistema.registrarMovil(matriculaDos, conductorDos);
		Movil movilDos = sistema.getMovilByMatricula(matriculaDos);
		movilDos.setEstadoMovil(EstadoMovil.DESHABILITADO);
		Retorno retornoDos = sistema.deshabilitarMovil("BCD2345");
		assertEquals(retornoDos.resultado, Retorno.Resultado.ERROR_2);
	}

	@Test
	public void eliminarMovil_returnOk() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		String matricula = "ABC1234";
		String conductor = "46110887";
		Retorno retornoRegistro = sistema.registrarMovil(matricula, conductor);
		assertEquals(retornoRegistro.resultado, Retorno.Resultado.OK);
		Retorno retornoBorrado = sistema.eliminarMovil(matricula);
		assertEquals(retornoBorrado.resultado, Retorno.Resultado.OK);
	}

	@Test
	public void eliminarMovil_tresUnidades_returnOk() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		String matriculaUno = "ABC1234";
		String conductorUno = "46110887";
		Retorno retornoRegistroUno = sistema.registrarMovil(matriculaUno, conductorUno);
		assertEquals(retornoRegistroUno.resultado, Retorno.Resultado.OK);
		String matriculaDos = "BCD2345";
		String conductorDos = "43553319";
		Retorno retornoRegistroDos = sistema.registrarMovil(matriculaDos, conductorDos);
		assertEquals(retornoRegistroDos.resultado, Retorno.Resultado.OK);
		String matriculaTres = "CDE3456";
		String conductorTres = "45559997";
		Retorno retornoRegistroTres = sistema.registrarMovil(matriculaTres, conductorTres);
		assertEquals(retornoRegistroTres.resultado, Retorno.Resultado.OK);
		Retorno retornoBorradoDos = sistema.eliminarMovil(matriculaDos);
		Retorno retornoBorradoUno = sistema.eliminarMovil(matriculaUno);
		Retorno retornoBorradoTres = sistema.eliminarMovil(matriculaTres);
		assertEquals(retornoBorradoDos.resultado, Retorno.Resultado.OK);
		assertEquals(retornoBorradoUno.resultado, Retorno.Resultado.OK);
		assertEquals(retornoBorradoTres.resultado, Retorno.Resultado.OK);
	}

	@Test
	public void eliminarMovil_returnErrorUno() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistro = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoRegistro.resultado, Retorno.Resultado.OK);
		Retorno retornoBorrado = sistema.eliminarMovil("ZZZ9999");
		assertEquals(retornoBorrado.resultado, Retorno.Resultado.ERROR_1);
	}

	@Test
	public void eliminarMovil_returnErrorDos() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistro = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoRegistro.resultado, Retorno.Resultado.OK);
		Movil movil = sistema.getMovilByMatricula("ABC1234");
		movil.setEstadoMovil(EstadoMovil.ASIGNADO);
		Retorno retornoBorrado = sistema.eliminarMovil("ABC1234");
		assertEquals(retornoBorrado.resultado, Retorno.Resultado.ERROR_2);
	}

	@Test
	public void habilitarMovil_returnOk() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistro = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoRegistro.resultado, Retorno.Resultado.OK);
		Movil movil = sistema.getMovilByMatricula("ABC1234");
		movil.setEstadoMovil(EstadoMovil.DESHABILITADO);
		Retorno retornoHabilitacion = sistema.habilitarMovil("ABC1234");
		assertEquals(retornoHabilitacion.resultado, Retorno.Resultado.OK);
	}

	@Test
	public void habilitarMovil_returnErrorUno() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoHabilitacion = sistema.habilitarMovil("ABC1234");
		assertEquals(retornoHabilitacion.resultado, Retorno.Resultado.ERROR_1);
	}

	@Test
	public void habilitarMovil_returnErrorDos() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistroUno = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoRegistroUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistroDos = sistema.registrarMovil("BCD2345", "43553319");
		assertEquals(retornoRegistroDos.resultado, Retorno.Resultado.OK);
		Movil movilDos = sistema.getMovilByMatricula("BCD2345");
		movilDos.setEstadoMovil(EstadoMovil.ASIGNADO);
		Retorno retornoHabilitacionMovilUno = sistema.habilitarMovil("ABC1234");
		assertEquals(retornoHabilitacionMovilUno.resultado, Retorno.Resultado.ERROR_2);
		Retorno retornoHabilitacionMovilDos = sistema.habilitarMovil("BCD2345");
		assertEquals(retornoHabilitacionMovilDos.resultado, Retorno.Resultado.ERROR_2);
	}

	@Test
	public void asignarUbicacionMovil_returnOk() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		sistema.registrarEsquina(1.555, 1.222);
		sistema.registrarEsquina(1.51, 1.21);
		sistema.registrarEsquina(1.52, 1.221);
		sistema.registrarEsquina(1.53, 1.24);
		sistema.registrarEsquina(1.54, 1.25);
		sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);
		sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		Retorno retornoMovil = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovil.resultado, Retorno.Resultado.OK);
		Retorno retornoAsignacion = sistema.asignarUbicacionMovil("ABC1234", 1.555, 1.222);
		assertEquals(retornoAsignacion.resultado, Retorno.Resultado.OK);
	}

	@Test
	public void asignarUbicacionMovil_returnErrorUno() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoMovil = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovil.resultado, Retorno.Resultado.OK);
		Retorno retornoAsignacion = sistema.asignarUbicacionMovil("ABC1234", 1.555, 1.222);
		assertEquals(retornoAsignacion.resultado, Retorno.Resultado.ERROR_1);
	}

	@Test
	public void asignarUbicacionMovil_returnErrorDos() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		sistema.registrarEsquina(1.555, 1.222);
		sistema.registrarEsquina(1.51, 1.21);
		sistema.registrarEsquina(1.52, 1.221);
		sistema.registrarEsquina(1.53, 1.24);
		sistema.registrarEsquina(1.54, 1.25);
		sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);
		sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		Retorno retornoMovilUno = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovilUno.resultado, Retorno.Resultado.OK);
		Retorno retornoAsignacionUno = sistema.asignarUbicacionMovil("ABC1234", 1.555, 1.222);
		assertEquals(retornoAsignacionUno.resultado, Retorno.Resultado.OK);
		Retorno retornoMovilDos = sistema.registrarMovil("BCD2345", "43553319");
		assertEquals(retornoMovilDos.resultado, Retorno.Resultado.OK);
		Retorno retornoAsignacionDos = sistema.asignarUbicacionMovil("BCD2345", 1.555, 1.222);
		assertEquals(retornoAsignacionDos.resultado, Retorno.Resultado.ERROR_2);
	}

	@Test
	public void asignarUbicacionMovil_returnErrorTres() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		sistema.registrarEsquina(1.555, 1.222);
		sistema.registrarEsquina(1.51, 1.21);
		sistema.registrarEsquina(1.52, 1.221);
		sistema.registrarEsquina(1.53, 1.24);
		sistema.registrarEsquina(1.54, 1.25);
		sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);
		sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		Retorno retornoAsignacionUno = sistema.asignarUbicacionMovil("ABC1234", 1.555, 1.222);
		assertEquals(retornoAsignacionUno.resultado, Retorno.Resultado.ERROR_3);
	}

	@Test
	public void buscarMovil_returnOk() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoMovil = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovil.resultado, Retorno.Resultado.OK);
		Retorno retornoBuscar = sistema.buscarMovil("ABC1234");
		assertEquals(retornoBuscar.resultado, Retorno.Resultado.OK);
	}

	@Test
	public void buscarMovil_returnErrorUno() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoBuscar = sistema.buscarMovil("ABC1234");
		assertEquals(retornoBuscar.resultado, Retorno.Resultado.ERROR_1);
	}

	@Test
	public void informeMoviles_returnOk() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoMovilUno = sistema.registrarMovil("DEF4567", "11111111");
		assertEquals(retornoMovilUno.resultado, Retorno.Resultado.OK);
		Retorno retornoMovilDos = sistema.registrarMovil("BCD2345", "22222222");
		assertEquals(retornoMovilDos.resultado, Retorno.Resultado.OK);
		Retorno retornoMovilTres = sistema.registrarMovil("ABC1234", "33333333");
		assertEquals(retornoMovilTres.resultado, Retorno.Resultado.OK);
		Retorno retornoMovilCuatro = sistema.registrarMovil("EFG5678", "44444444");
		assertEquals(retornoMovilCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoMovilCinco = sistema.registrarMovil("CDE3456", "55555555");
		assertEquals(retornoMovilCinco.resultado, Retorno.Resultado.OK);
		Retorno retornoInforme = sistema.informeMoviles();
		assertEquals(retornoInforme.resultado, Retorno.Resultado.OK);
	}

	@Test
	public void informeMoviles_returnVacio() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoInforme = sistema.informeMoviles();
		assertEquals(retornoInforme.resultado, Retorno.Resultado.OK);
	}
	
	///******************** INICIO PUNTOS 3 ********************

	@Test
	public void registrarEquina_returnOk() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
	}
	
	@Test
	public void registrarEquina_returnErrorUno() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(1);
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.ERROR_1);
	}
	
	@Test
	public void registrarEquina_returnErrorDos() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.ERROR_2);
	}
	
	@Test
	public void registrarTramo_returnOk() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_D = sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);
		assertEquals(retornoRegistrarTramo_A_D.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_B_C = sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		assertEquals(retornoRegistrarTramo_B_C.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_C_E = sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		assertEquals(retornoRegistrarTramo_C_E.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_D_E = sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		assertEquals(retornoRegistrarTramo_D_E.resultado, Retorno.Resultado.OK);
	}
	
	@Test
	public void registrarTramo_returnMtsIgualCero() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 0);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.ERROR_1);
	}
	
	@Test
	public void registrarTramo_returnMtsMenorCero() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, -10);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.ERROR_1);
	}
	
	@Test
	public void registrarTramo_return_errorDos() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo = sistema.registrarTramo(1.999, 1.222, 1.51, 1.21, 10);
		assertEquals(retornoRegistrarTramo.resultado, Retorno.Resultado.ERROR_2);
	}
	
	@Test
	public void registrarTramo_return_errorTres() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramoUno = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 10);
		assertEquals(retornoRegistrarTramoUno.resultado, Retorno.Resultado.OK);	
		Retorno retornoRegistrarTramoDos = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 10);
		assertEquals(retornoRegistrarTramoDos.resultado, Retorno.Resultado.ERROR_3);
	}
	
	@Test
	public void eliminarEsquina_returnOk() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistrarEsquina = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquina.resultado, Retorno.Resultado.OK);
		assertEquals(1,sistema.getListaEsquinas().largo());
		Retorno eliminarEsquina = sistema.eliminarEsquina(1.555, 1.222);
		assertEquals(eliminarEsquina.resultado, Retorno.Resultado.OK);
		assertEquals(0,sistema.getListaEsquinas().largo());
	}
	
	@Test
	public void eliminarEsquina_returnErrorUno() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistrarEsquina = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquina.resultado, Retorno.Resultado.OK);
		assertEquals(1,sistema.getListaEsquinas().largo());
		Retorno eliminarEsquina = sistema.eliminarEsquina(1.111, 1.999);
		assertEquals(eliminarEsquina.resultado, Retorno.Resultado.ERROR_1);
		assertEquals(1,sistema.getListaEsquinas().largo());
	}
	
	@Test
	public void eliminarEsquina_returnErrorDos() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistrarEsquina = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquina.resultado, Retorno.Resultado.OK);
		assertEquals(1,sistema.getListaEsquinas().largo());
		Retorno retornoMovil = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovil.resultado, Retorno.Resultado.OK);
		Retorno retornoAsignacion = sistema.asignarUbicacionMovil("ABC1234", 1.555, 1.222);
		assertEquals(retornoAsignacion.resultado, Retorno.Resultado.OK);
		Retorno eliminarEsquina = sistema.eliminarEsquina(1.555, 1.222);
		assertEquals(eliminarEsquina.resultado, Retorno.Resultado.ERROR_2);
		assertEquals(1,sistema.getListaEsquinas().largo());
	}
	
	@Test
	public void eliminarTramo_returnOk() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistrarEsquina = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquina.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramoUno = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 10);
		assertEquals(retornoRegistrarTramoUno.resultado, Retorno.Resultado.OK);
		assertEquals(2,sistema.getListaTramos().largo());
		Retorno eliminarTramo = sistema.eliminarTramo(1.555, 1.222, 1.51, 1.21);
		assertEquals(eliminarTramo.resultado, Retorno.Resultado.OK);
		assertEquals(0,sistema.getListaTramos().largo());
	}

	@Test
	public void eliminarTramo_returnErrorUno() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistrarEsquina = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquina.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramoUno = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 10);
		assertEquals(retornoRegistrarTramoUno.resultado, Retorno.Resultado.OK);
		assertEquals(2,sistema.getListaTramos().largo());
		Retorno eliminarTramo = sistema.eliminarTramo(1.555, 1.222, 1.51, 1.999);
		assertEquals(eliminarTramo.resultado, Retorno.Resultado.ERROR_1);
		assertEquals(2,sistema.getListaTramos().largo());
	}
	
	@Test
	public void eliminarTramo_return_errorDos() {
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		Retorno retornoRegistrarEsquina = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquina.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno eliminarTramo = sistema.eliminarTramo(1.555, 1.222, 1.51, 1.21);
		assertEquals(eliminarTramo.resultado, Retorno.Resultado.ERROR_2);
		assertEquals(0,sistema.getListaTramos().largo());
	}
	
	@Test
	public void movilMasCercano_noHayMoviles() {
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		
		//Registro 5 esquinas
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
		
		//Cuento las esquinas del sistema
		assertEquals(5,sistema.getListaEsquinas().largo());
		
		//Registro los tramos
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_D = sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);
		assertEquals(retornoRegistrarTramo_A_D.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_B_C = sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		assertEquals(retornoRegistrarTramo_B_C.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_C_E = sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		assertEquals(retornoRegistrarTramo_C_E.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_D_E = sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		assertEquals(retornoRegistrarTramo_D_E.resultado, Retorno.Resultado.OK);
		
		//Cuento los tramos (deben ser el doble de la cantidad de esquinas porque son bidireccionales)
		assertEquals(10,sistema.getListaTramos().largo());
		
		//Busco el movil mas cercano a la esquina uno (sin haber registrado moviles)
		Retorno retornoMovilMasCercano = sistema.movilMasCercano(1.555, 1.222);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.OK);
		
		//Compruebo que ademas de devolverme el Retorno que esperaba, 
		//EL MOVIL QUE ME DEVOLVIO ES EL QUE YO SE QUE ESTA MAS CERCA. Y NO OTRO.
		assertEquals("", Sistema.mensaje_movilMasCercano);
	}
	
	@Test
	public void movilMasCercano_movilEnEsquina_returnOk() {
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
				
		//Registro 5 esquinas
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
				
		//Cuento las esquinas del sistema
		assertEquals(5,sistema.getListaEsquinas().largo());
				
		//Registro los tramos
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_D = sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);
		assertEquals(retornoRegistrarTramo_A_D.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_B_C = sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		assertEquals(retornoRegistrarTramo_B_C.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_C_E = sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		assertEquals(retornoRegistrarTramo_C_E.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_D_E = sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		assertEquals(retornoRegistrarTramo_D_E.resultado, Retorno.Resultado.OK);
				
		//Cuento los tramos (deben ser el doble de la cantidad de esquinas porque son bidireccionales)
		assertEquals(10,sistema.getListaTramos().largo());
		
		//Registro un movil
		Retorno retornoMovil = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovil.resultado, Retorno.Resultado.OK);
		
		//Lo asigno a la esquina A
		Retorno retornoAsignacion = sistema.asignarUbicacionMovil("ABC1234", 1.555, 1.222);
		assertEquals(retornoAsignacion.resultado, Retorno.Resultado.OK);
		
		//Busco el movil mas cercano a la esquina uno
		Retorno retornoMovilMasCercano = sistema.movilMasCercano(1.555, 1.222);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.OK);
				
		//Compruebo que ademas de devolverme el Retorno que esperaba, 
		//EL MOVIL QUE ME DEVOLVIO ES EL QUE YO SE QUE ESTA MAS CERCA. Y NO OTRO.
		assertEquals("ABC1234;0;46110887 | ", Sistema.mensaje_movilMasCercano);
	}
	
	@Test
	public void movilMasCercano_esquinaMasCercanaRespectoParametro() {
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
						
		//Registro 5 esquinas
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
						
		//Cuento las esquinas del sistema
		assertEquals(5,sistema.getListaEsquinas().largo());
						
		//Registro los tramos
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_D = sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);
		assertEquals(retornoRegistrarTramo_A_D.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_B_C = sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		assertEquals(retornoRegistrarTramo_B_C.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_C_E = sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		assertEquals(retornoRegistrarTramo_C_E.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_D_E = sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		assertEquals(retornoRegistrarTramo_D_E.resultado, Retorno.Resultado.OK);
						
		//Cuento los tramos (deben ser el doble de la cantidad de esquinas porque son bidireccionales)
		assertEquals(10,sistema.getListaTramos().largo());
				
		//Registro movil uno
		Retorno retornoMovilUno = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovilUno.resultado, Retorno.Resultado.OK);
				
		//Lo asigno a la esquina mas cercana (sin ser la del parametro)
		Retorno retornoAsignacionUno = sistema.asignarUbicacionMovil("ABC1234", 1.51, 1.21);
		assertEquals(retornoAsignacionUno.resultado, Retorno.Resultado.OK);
		
		//Registro movil dos
		Retorno retornoMovilDos = sistema.registrarMovil("BCD2345", "43553319");
		assertEquals(retornoMovilDos.resultado, Retorno.Resultado.OK);
						
		//Lo asigno a la esquina mas lejana
		Retorno retornoAsignacionDos = sistema.asignarUbicacionMovil("BCD2345", 1.54, 1.25);
		assertEquals(retornoAsignacionDos.resultado, Retorno.Resultado.OK);
				
		//Busco el movil mas cercano a la esquina uno
		Retorno retornoMovilMasCercano = sistema.movilMasCercano(1.555, 1.222);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.OK);
						
		//Compruebo que ademas de devolverme el Retorno que esperaba, 
		//EL MOVIL QUE ME DEVOLVIO ES EL QUE YO SE QUE ESTA MAS CERCA. Y NO OTRO.
		assertEquals("ABC1234;1;46110887 | ", Sistema.mensaje_movilMasCercano);
	}
	
	@Test
	public void movilMasCercano_esquinaMasLejanaRespectoParametro() {
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
								
		//Registro 5 esquinas
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
								
		//Cuento las esquinas del sistema
		assertEquals(5,sistema.getListaEsquinas().largo());
								
		//Registro los tramos
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_D = sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);
		assertEquals(retornoRegistrarTramo_A_D.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_B_C = sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		assertEquals(retornoRegistrarTramo_B_C.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_C_E = sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		assertEquals(retornoRegistrarTramo_C_E.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_D_E = sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		assertEquals(retornoRegistrarTramo_D_E.resultado, Retorno.Resultado.OK);
								
		//Cuento los tramos (deben ser el doble de la cantidad de esquinas porque son bidireccionales)
		assertEquals(10,sistema.getListaTramos().largo());
				
		//Registro movil unico
		Retorno retornoMovilDos = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovilDos.resultado, Retorno.Resultado.OK);
								
		//Lo asigno a la esquina mas lejana
		Retorno retornoAsignacionDos = sistema.asignarUbicacionMovil("ABC1234", 1.54, 1.25);
		assertEquals(retornoAsignacionDos.resultado, Retorno.Resultado.OK);
						
		//Busco el movil mas cercano a la esquina uno
		Retorno retornoMovilMasCercano = sistema.movilMasCercano(1.555, 1.222);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.OK);
								
		//Compruebo que ademas de devolverme el Retorno que esperaba, 
		//EL MOVIL QUE ME DEVOLVIO ES EL QUE YO SE QUE ESTA MAS CERCA. Y NO OTRO.
		assertEquals("ABC1234;6;46110887 | ", Sistema.mensaje_movilMasCercano);
	}
	
	@Test
	public void movilMasCercano_dosMovilesEnDiferentesEsquinasPeroMismaDistancia() {
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
						
		//Registro 5 esquinas
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
						
		//Cuento las esquinas del sistema
		assertEquals(5,sistema.getListaEsquinas().largo());
						
		//Registro los tramos
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_D = sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 3);
		assertEquals(retornoRegistrarTramo_A_D.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_B_C = sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		assertEquals(retornoRegistrarTramo_B_C.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_C_E = sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		assertEquals(retornoRegistrarTramo_C_E.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_D_E = sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		assertEquals(retornoRegistrarTramo_D_E.resultado, Retorno.Resultado.OK);
						
		//Cuento los tramos (deben ser el doble de la cantidad de esquinas porque son bidireccionales)
		assertEquals(10,sistema.getListaTramos().largo());
				
		//Registro movil uno
		Retorno retornoMovilUno = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovilUno.resultado, Retorno.Resultado.OK);
				
		//Lo asigno a la esquina C
		Retorno retornoAsignacionUno = sistema.asignarUbicacionMovil("ABC1234", 1.52, 1.221);
		assertEquals(retornoAsignacionUno.resultado, Retorno.Resultado.OK);
		
		//Registro movil dos
		Retorno retornoMovilDos = sistema.registrarMovil("BCD2345", "43553319");
		assertEquals(retornoMovilDos.resultado, Retorno.Resultado.OK);
						
		//Lo asigno a la esquina D
		Retorno retornoAsignacionDos = sistema.asignarUbicacionMovil("BCD2345", 1.53, 1.24);
		assertEquals(retornoAsignacionDos.resultado, Retorno.Resultado.OK);
				
		//Busco el movil mas cercano a la esquina uno
		Retorno retornoMovilMasCercano = sistema.movilMasCercano(1.555, 1.222);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.OK);
						
		//Compruebo que ademas de devolverme el Retorno que esperaba, 
		//EL MOVIL QUE ME DEVOLVIO ES EL QUE YO SE QUE ESTA MAS CERCA. Y NO OTRO.
		//EN ESTA PRUEBA, LOS DOS MOVILES ESTAN A LA MISMA DISTANCIA.
		//LA RESOLUCION DE CUAL DEVUELVE, PODRIA SER POR UN SEGUNDO CRITERIO (MENOR CANTIDAD DE PASOS POR EJ)
		assertEquals("BCD2345;3;43553319 | ", Sistema.mensaje_movilMasCercano);
	}
	
	@Test
	public void movilMasCercano_unicoMovilEnEsquinaDelParametroPeroNoDisponible() {
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
										
		//Registro 5 esquinas
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
										
		//Cuento las esquinas del sistema
		assertEquals(5,sistema.getListaEsquinas().largo());
										
		//Registro los tramos
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_D = sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);
		assertEquals(retornoRegistrarTramo_A_D.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_B_C = sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		assertEquals(retornoRegistrarTramo_B_C.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_C_E = sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		assertEquals(retornoRegistrarTramo_C_E.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_D_E = sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		assertEquals(retornoRegistrarTramo_D_E.resultado, Retorno.Resultado.OK);
										
		//Cuento los tramos (deben ser el doble de la cantidad de esquinas porque son bidireccionales)
		assertEquals(10,sistema.getListaTramos().largo());
						
		//Registro movil unico
		Retorno retornoMovilDos = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovilDos.resultado, Retorno.Resultado.OK);
										
		//Lo asigno a la esquina mas lejana
		Retorno retornoAsignacionDos = sistema.asignarUbicacionMovil("ABC1234", 1.54, 1.25);
		assertEquals(retornoAsignacionDos.resultado, Retorno.Resultado.OK);
		
		//Lo deshabilito para asegurar que si no hay moviles disponibles, no me traiga nada
		Movil movil = sistema.getMovilByMatricula("ABC1234");
		movil.setEstadoMovil(EstadoMovil.DESHABILITADO);
								
		//Busco el movil mas cercano a la esquina uno
		Retorno retornoMovilMasCercano = sistema.movilMasCercano(1.555, 1.222);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.OK);
										
		//Compruebo que ademas de devolverme el Retorno que esperaba, 
		//EL MOVIL QUE ME DEVOLVIO ES EL QUE YO SE QUE ESTA MAS CERCA. Y NO OTRO.
		assertEquals("", Sistema.mensaje_movilMasCercano);
	}
	
	@Test
	public void movilMasCercano_errorUno() {
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		
		//Busco el movil mas cercano, pasandole una esquina que no existe
		Retorno retornoMovilMasCercano = sistema.movilMasCercano(1.555, 1.222);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.ERROR_1);
		assertEquals("", Sistema.mensaje_movilMasCercano);
	}
	
	@Test
	public void movilMasCercano_movilLejosDeEsquinaDelParametroNoDisponible_retornaSiguiente() {
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
								
		//Registro 5 esquinas
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
								
		//Cuento las esquinas del sistema
		assertEquals(5,sistema.getListaEsquinas().largo());
								
		//Registro los tramos
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_D = sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 3);
		assertEquals(retornoRegistrarTramo_A_D.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_B_C = sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		assertEquals(retornoRegistrarTramo_B_C.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_C_E = sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		assertEquals(retornoRegistrarTramo_C_E.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_D_E = sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		assertEquals(retornoRegistrarTramo_D_E.resultado, Retorno.Resultado.OK);
								
		//Cuento los tramos (deben ser el doble de la cantidad de esquinas porque son bidireccionales)
		assertEquals(10,sistema.getListaTramos().largo());
						
		//Registro movil uno
		Retorno retornoMovilUno = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovilUno.resultado, Retorno.Resultado.OK);
						
		//Lo asigno a la esquina B
		Retorno retornoAsignacionUno = sistema.asignarUbicacionMovil("ABC1234", 1.51, 1.21);
		assertEquals(retornoAsignacionUno.resultado, Retorno.Resultado.OK);
				
		//Lo deshabilito para probar que no me trae este movil
		Movil movilDos = sistema.getMovilByMatricula("ABC1234");
		movilDos.setEstadoMovil(EstadoMovil.DESHABILITADO);
				
		//Registro movil dos
		Retorno retornoMovilDos = sistema.registrarMovil("BCD2345", "43553319");
		assertEquals(retornoMovilDos.resultado, Retorno.Resultado.OK);
								
		//Lo asigno a la esquina E
		Retorno retornoAsignacionDos = sistema.asignarUbicacionMovil("BCD2345", 1.54, 1.25);
		assertEquals(retornoAsignacionDos.resultado, Retorno.Resultado.OK);
						
		//Busco el movil mas cercano a la esquina uno
		Retorno retornoMovilMasCercano = sistema.movilMasCercano(1.555, 1.222);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.OK);
								
		//Compruebo que ademas de devolverme el Retorno que esperaba, 
		//EL MOVIL QUE ME DEVOLVIO ES EL QUE YO SE QUE ESTA MAS CERCA. Y NO OTRO.
		//EN ESTA PRUEBA, EL MOVIL ABC1234 ESTA MAS CERCA, PERO DESHABILITADO. ASI QUE ME TIENE QUE DEVOLVER EL OTRO
		assertEquals("BCD2345;6;43553319 | ", Sistema.mensaje_movilMasCercano);
	}
	
	@Test
	public void movilMasCercano_movilEnEsquinaDelParametroPeroNoDisponible_retornaSiguiente() {
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
										
		//Registro 5 esquinas
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
										
		//Cuento las esquinas del sistema
		assertEquals(5,sistema.getListaEsquinas().largo());
										
		//Registro los tramos
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_D = sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 3);
		assertEquals(retornoRegistrarTramo_A_D.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_B_C = sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		assertEquals(retornoRegistrarTramo_B_C.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_C_E = sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		assertEquals(retornoRegistrarTramo_C_E.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_D_E = sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		assertEquals(retornoRegistrarTramo_D_E.resultado, Retorno.Resultado.OK);
										
		//Cuento los tramos (deben ser el doble de la cantidad de esquinas porque son bidireccionales)
		assertEquals(10,sistema.getListaTramos().largo());
								
		//Registro movil uno
		Retorno retornoMovilUno = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovilUno.resultado, Retorno.Resultado.OK);
								
		//Lo asigno a la esquina A
		Retorno retornoAsignacionUno = sistema.asignarUbicacionMovil("ABC1234", 1.555, 1.222);
		assertEquals(retornoAsignacionUno.resultado, Retorno.Resultado.OK);
						
		//Lo deshabilito para probar que no me trae este movil
		Movil movilDos = sistema.getMovilByMatricula("ABC1234");
		movilDos.setEstadoMovil(EstadoMovil.DESHABILITADO);
						
		//Registro movil dos
		Retorno retornoMovilDos = sistema.registrarMovil("BCD2345", "43553319");
		assertEquals(retornoMovilDos.resultado, Retorno.Resultado.OK);
										
		//Lo asigno a la esquina E
		Retorno retornoAsignacionDos = sistema.asignarUbicacionMovil("BCD2345", 1.54, 1.25);
		assertEquals(retornoAsignacionDos.resultado, Retorno.Resultado.OK);
								
		//Busco el movil mas cercano a la esquina uno
		Retorno retornoMovilMasCercano = sistema.movilMasCercano(1.555, 1.222);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.OK);
										
		//Compruebo que ademas de devolverme el Retorno que esperaba, 
		//EL MOVIL QUE ME DEVOLVIO ES EL QUE YO SE QUE ESTA MAS CERCA. Y NO OTRO.
		//EN ESTA PRUEBA, EL MOVIL ABC1234 ESTA MAS CERCA, PERO DESHABILITADO. ASI QUE ME TIENE QUE DEVOLVER EL OTRO
		assertEquals("BCD2345;6;43553319 | ", Sistema.mensaje_movilMasCercano);
	}
	
	@Test
	public void movilEnRadio_radioMenorQueCero() {
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		
		//Registro 5 esquinas
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
		
		//Cuento las esquinas del sistema
		assertEquals(5,sistema.getListaEsquinas().largo());
		
		//Registro los tramos
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_D = sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);
		assertEquals(retornoRegistrarTramo_A_D.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_B_C = sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		assertEquals(retornoRegistrarTramo_B_C.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_C_E = sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		assertEquals(retornoRegistrarTramo_C_E.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_D_E = sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		assertEquals(retornoRegistrarTramo_D_E.resultado, Retorno.Resultado.OK);
		
		//Cuento los tramos (deben ser el doble de la cantidad de esquinas porque son bidireccionales)
		assertEquals(10,sistema.getListaTramos().largo());
		
		//Registro movil uno
		Retorno retornoMovilUno = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovilUno.resultado, Retorno.Resultado.OK);
										
		//Lo asigno a la esquina B
		Retorno retornoAsignacionUno = sistema.asignarUbicacionMovil("ABC1234", 1.51, 1.21);
		assertEquals(retornoAsignacionUno.resultado, Retorno.Resultado.OK);
				
		//Busco el movil mas cercano marcando el radio como -10
		Retorno retornoMovilMasCercano = sistema.verMovilesEnRadio(1.555, 1.222, -10);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.ERROR_1);
		
		//Compruebo que ademas de devolverme el Retorno que esperaba, 
		//EL/LOS MOVILES QUE ME DEVOLVIO, SON LOS QUE YO SE QUE CORRESPONDEN. NI UNO MAS NI UNO MENOS.
		assertEquals("", Sistema.mensaje_movilesEnRadio);
	}
	
	@Test
	public void movilEnRadio_radioCero() {
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		
		//Registro 5 esquinas
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
		
		//Cuento las esquinas del sistema
		assertEquals(5,sistema.getListaEsquinas().largo());
		
		//Registro los tramos
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_D = sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);
		assertEquals(retornoRegistrarTramo_A_D.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_B_C = sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		assertEquals(retornoRegistrarTramo_B_C.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_C_E = sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		assertEquals(retornoRegistrarTramo_C_E.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_D_E = sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		assertEquals(retornoRegistrarTramo_D_E.resultado, Retorno.Resultado.OK);
		
		//Cuento los tramos (deben ser el doble de la cantidad de esquinas porque son bidireccionales)
		assertEquals(10,sistema.getListaTramos().largo());
		
		//Registro movil uno
		Retorno retornoMovilUno = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovilUno.resultado, Retorno.Resultado.OK);
										
		//Lo asigno a la esquina B
		Retorno retornoAsignacionUno = sistema.asignarUbicacionMovil("ABC1234", 1.51, 1.21);
		assertEquals(retornoAsignacionUno.resultado, Retorno.Resultado.OK);
				
		//Busco el movil mas cercano marcando el radio como -10
		Retorno retornoMovilMasCercano = sistema.verMovilesEnRadio(1.555, 1.222, 0);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.ERROR_1);
		
		//Compruebo que ademas de devolverme el Retorno que esperaba, 
		//EL/LOS MOVILES QUE ME DEVOLVIO, SON LOS QUE YO SE QUE CORRESPONDEN. NI UNO MAS NI UNO MENOS.
		assertEquals("", Sistema.mensaje_movilesEnRadio);
	}
	
	@Test
	public void movilEnRadio_noExisteEsquina() {
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
				
		//Busco el movil mas cercano pasando una esquina inexistente
		Retorno retornoMovilMasCercano = sistema.verMovilesEnRadio(1.555, 1.222, 100);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.ERROR_2);
		
		//Compruebo que ademas de devolverme el Retorno que esperaba, 
		//EL/LOS MOVILES QUE ME DEVOLVIO, SON LOS QUE YO SE QUE CORRESPONDEN. NI UNO MAS NI UNO MENOS.
		assertEquals("", Sistema.mensaje_movilesEnRadio);
	}
	
	@Test
	public void movilEnRadio_unMovilFueraDelRadio() {
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		
		//Registro 5 esquinas
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
		
		//Cuento las esquinas del sistema
		assertEquals(5,sistema.getListaEsquinas().largo());
		
		//Registro los tramos
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_D = sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);
		assertEquals(retornoRegistrarTramo_A_D.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_B_C = sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		assertEquals(retornoRegistrarTramo_B_C.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_C_E = sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		assertEquals(retornoRegistrarTramo_C_E.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_D_E = sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		assertEquals(retornoRegistrarTramo_D_E.resultado, Retorno.Resultado.OK);
		
		//Cuento los tramos (deben ser el doble de la cantidad de esquinas porque son bidireccionales)
		assertEquals(10,sistema.getListaTramos().largo());
		
		//Registro movil uno
		Retorno retornoMovilUno = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovilUno.resultado, Retorno.Resultado.OK);
										
		//Lo asigno a la esquina D
		Retorno retornoAsignacionUno = sistema.asignarUbicacionMovil("ABC1234", 1.54, 1.25);
		assertEquals(retornoAsignacionUno.resultado, Retorno.Resultado.OK);
				
		//Busco el movil mas cercano con radio corto
		Retorno retornoMovilMasCercano = sistema.verMovilesEnRadio(1.555, 1.222, 3);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.OK);
		
		//Compruebo que ademas de devolverme el Retorno que esperaba, 
		//EL/LOS MOVILES QUE ME DEVOLVIO, SON LOS QUE YO SE QUE CORRESPONDEN. NI UNO MAS NI UNO MENOS.
		assertEquals("", Sistema.mensaje_movilesEnRadio);
	}
	
	@Test
	public void movilEnRadio_noHayMoviles() {
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		
		//Registro 5 esquinas
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
		
		//Cuento las esquinas del sistema
		assertEquals(5,sistema.getListaEsquinas().largo());
		
		//Registro los tramos
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_D = sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);
		assertEquals(retornoRegistrarTramo_A_D.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_B_C = sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		assertEquals(retornoRegistrarTramo_B_C.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_C_E = sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		assertEquals(retornoRegistrarTramo_C_E.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_D_E = sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		assertEquals(retornoRegistrarTramo_D_E.resultado, Retorno.Resultado.OK);
		
		//Cuento los tramos (deben ser el doble de la cantidad de esquinas porque son bidireccionales)
		assertEquals(10,sistema.getListaTramos().largo());
		
		//Busco el movil mas cercano a la esquina uno (sin haber registrado moviles)
		Retorno retornoMovilMasCercano = sistema.movilMasCercano(1.555, 1.222);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.OK);
		
		//Compruebo que ademas de devolverme el Retorno que esperaba, 
		//EL MOVIL QUE ME DEVOLVIO ES EL QUE YO SE QUE ESTA MAS CERCA. Y NO OTRO.
		assertEquals("", Sistema.mensaje_movilMasCercano);
	}
	
	@Test
	public void movilEnRadio_returnUnMovil(){
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
		
		//Registro 5 esquinas
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
		
		//Cuento las esquinas del sistema
		assertEquals(5,sistema.getListaEsquinas().largo());
		
		//Registro los tramos
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_D = sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);
		assertEquals(retornoRegistrarTramo_A_D.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_B_C = sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		assertEquals(retornoRegistrarTramo_B_C.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_C_E = sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		assertEquals(retornoRegistrarTramo_C_E.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_D_E = sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		assertEquals(retornoRegistrarTramo_D_E.resultado, Retorno.Resultado.OK);
		
		//Cuento los tramos (deben ser el doble de la cantidad de esquinas porque son bidireccionales)
		assertEquals(10,sistema.getListaTramos().largo());
		
		//Registro movil uno
		Retorno retornoMovilUno = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovilUno.resultado, Retorno.Resultado.OK);
										
		//Lo asigno a la esquina B
		Retorno retornoAsignacionUno = sistema.asignarUbicacionMovil("ABC1234", 1.51, 1.21);
		assertEquals(retornoAsignacionUno.resultado, Retorno.Resultado.OK);
				
		//Busco el movil mas cercano con radio generoso
		Retorno retornoMovilMasCercano = sistema.verMovilesEnRadio(1.555, 1.222, 100);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.OK);
		
		//Compruebo que ademas de devolverme el Retorno que esperaba, 
		//EL/LOS MOVILES QUE ME DEVOLVIO, SON LOS QUE YO SE QUE CORRESPONDEN. NI UNO MAS NI UNO MENOS.
		assertEquals("ABC1234;1;46110887 | ", Sistema.mensaje_movilesEnRadio);
	}

	@Test
	public void movilEnRadio_returnMasDeUnMovil(){
		//Creo el sistema
		Sistema sistema = new Sistema();
		sistema.inicializarSistema(5);
				
		//Registro 5 esquinas
		Retorno retornoRegistrarEsquinaUno = sistema.registrarEsquina(1.555, 1.222);
		assertEquals(retornoRegistrarEsquinaUno.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaDos = sistema.registrarEsquina(1.51, 1.21);
		assertEquals(retornoRegistrarEsquinaDos.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaTres = sistema.registrarEsquina(1.52, 1.221);
		assertEquals(retornoRegistrarEsquinaTres.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCuatro = sistema.registrarEsquina(1.53, 1.24);
		assertEquals(retornoRegistrarEsquinaCuatro.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarEsquinaCinco = sistema.registrarEsquina(1.54, 1.25);
		assertEquals(retornoRegistrarEsquinaCinco.resultado, Retorno.Resultado.OK);
				
		//Cuento las esquinas del sistema
		assertEquals(5,sistema.getListaEsquinas().largo());
				
		//Registro los tramos
		Retorno retornoRegistrarTramo_A_B = sistema.registrarTramo(1.555, 1.222, 1.51, 1.21, 1);
		assertEquals(retornoRegistrarTramo_A_B.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_A_D = sistema.registrarTramo(1.555, 1.222, 1.53, 1.24, 5);
		assertEquals(retornoRegistrarTramo_A_D.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_B_C = sistema.registrarTramo(1.51, 1.21, 1.52, 1.221, 2);
		assertEquals(retornoRegistrarTramo_B_C.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_C_E = sistema.registrarTramo(1.52, 1.221, 1.54, 1.25, 3);
		assertEquals(retornoRegistrarTramo_C_E.resultado, Retorno.Resultado.OK);
		Retorno retornoRegistrarTramo_D_E = sistema.registrarTramo(1.53, 1.24, 1.54, 1.25, 2);
		assertEquals(retornoRegistrarTramo_D_E.resultado, Retorno.Resultado.OK);
				
		//Cuento los tramos (deben ser el doble de la cantidad de esquinas porque son bidireccionales)
		assertEquals(10,sistema.getListaTramos().largo());
				
		//Registro movil uno
		Retorno retornoMovilUno = sistema.registrarMovil("ABC1234", "46110887");
		assertEquals(retornoMovilUno.resultado, Retorno.Resultado.OK);
												
		//Lo asigno a la esquina B
		Retorno retornoAsignacionUno = sistema.asignarUbicacionMovil("ABC1234", 1.51, 1.21);
		assertEquals(retornoAsignacionUno.resultado, Retorno.Resultado.OK);
		
		//Registro movil dos
		Retorno retornoMovilDos = sistema.registrarMovil("BCD2345", "43553319");
		assertEquals(retornoMovilDos.resultado, Retorno.Resultado.OK);
														
		//Lo asigno a la esquina E
		Retorno retornoAsignacionDos = sistema.asignarUbicacionMovil("BCD2345", 1.54, 1.25);
		assertEquals(retornoAsignacionDos.resultado, Retorno.Resultado.OK);
						
		//Busco el movil mas cercano con radio generoso
		Retorno retornoMovilMasCercano = sistema.verMovilesEnRadio(1.555, 1.222, 100);
		assertEquals(retornoMovilMasCercano.resultado, Retorno.Resultado.OK);
				
		//Compruebo que ademas de devolverme el Retorno que esperaba, 
		//EL/LOS MOVILES QUE ME DEVOLVIO, SON LOS QUE YO SE QUE CORRESPONDEN. NI UNO MAS NI UNO MENOS.
		assertEquals("ABC1234;1;46110887 | BCD2345;6;43553319 | ", Sistema.mensaje_movilesEnRadio);
	}
	
}
