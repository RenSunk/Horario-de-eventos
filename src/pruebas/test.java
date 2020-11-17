package pruebas;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import mundo.Evento;
import mundo.Horario;

class test extends TestCase{

	private Horario mihorario;
	
	public void ecenario1(){
		mihorario = new Horario();
		mihorario.leerdatos();
	}
	
	public void ecenario2() {
		mihorario = new Horario();
		mihorario.leerdatos();
		mihorario.crear_evento(2, 2, new Evento("prueba", "en algun lugar", "", ""));
	}
	
	@Test
	public void leerdatostest() {
		ecenario1();
		assertEquals(true ,mihorario.leerdatos());
	}
	
	@Test
	public void PruebaDeBobjetotest(){
		ecenario1();
		assertSame(mihorario.día_mes_menos_eventos(), mihorario.getDia());
	}
	
	@Test
	public void evento_existentetest() {
		ecenario1();
		assertTrue(mihorario.ver_eventos_prueba(4, 18).equals("Shinanime"));
	}
	
	@Test
	public void crear_eventotest() {
		ecenario2();
		assertEquals("prueba", mihorario.ver_eventos_prueba(2, 2));
	}
	
	@Test
	public void evento_no_existentetest() {
		ecenario1();
		assertFalse(mihorario.ver_eventos_prueba(2, 3).equals("Shinanime"));
	}
	
	@Test
	public void iniciardatostest() {
		ecenario1();
		assertNotNull(mihorario);
	}
	@Test
	public void cuantos_eventos_díatest() {
		ecenario1();
		assertEquals(0, mihorario.cuantos_eventos_día(0));
	}
	@Test
	public void cuantos_eventos_horatest() {
		ecenario1();
		assertEquals(0,mihorario.cuantos_eventos_hora(1));
	}
	@Test
	public void totalventostest() {
		ecenario1();
		assertEquals(3,mihorario.total_eventos());
	}
	@Test
	public void día_mes_menos_eventostest() {
		ecenario1();
		assertEquals(31,mihorario.día_mes_menos_eventos());
	}
}
