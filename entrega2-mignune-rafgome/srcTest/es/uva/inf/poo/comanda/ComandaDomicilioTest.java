package es.uva.inf.poo.comanda;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uva.inf.poo.vendible.Producto;
import es.uva.inf.poo.vendible.Vendible;

public class ComandaDomicilioTest {

	@Test 
	public void testInicializarZonaUnoValido() {
		ComandaDomicilio c = new ComandaDomicilio("a","b",1, 2);
		assertNotNull(c);
		assertEquals(0.5, c.getCuota(), 0.001);
	}
	
	@Test 
	public void testInicializarZonaDosValido() {
		ComandaDomicilio c = new ComandaDomicilio("a","b",2, 2);
		assertNotNull(c);
		assertEquals(1, c.getCuota(), 0.001);
	}
	
	@Test 
	public void testInicializarRestoZonaValido() {
		ComandaDomicilio c = new ComandaDomicilio("a","b",3, 2);
		assertNotNull(c);
		assertEquals(2.5, c.getCuota(), 0.001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarCodigoNoValido() {
		@SuppressWarnings("unused")
		ComandaDomicilio c = new ComandaDomicilio(null,"b",1, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarDireccionNoValido() {
		@SuppressWarnings("unused")
		ComandaDomicilio c = new ComandaDomicilio("a",null,1, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarZonaNegativa() {
		@SuppressWarnings("unused")
		ComandaDomicilio c = new ComandaDomicilio("a","b",-1, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarBolsasNegativas() {
		@SuppressWarnings("unused")
		ComandaDomicilio c = new ComandaDomicilio("a","b",1, -1);
	}
	
	@Test
	public void testgetImporteValido() { 
		ComandaDomicilio c = new ComandaDomicilio("a","b",1, 2);
		Vendible v = new Producto("a","b","c",5,0.01);
		c.addVendible(v, 1);
		assertEquals(v.getPrecio() + (c.getNumBolsas() * 0.05) + c.getCuota(),c.getImporte(),0.001);
	}  
	
	@Test
	public void testgetImporteComandaAnuladaValido() {
		ComandaDomicilio c = new ComandaDomicilio("a","b",1, 2);
		Vendible v = new Producto("a","b","c",5,1);
		c.addVendible(v, 1);
		c.setEstado(Comanda.Estado.ANULADO);
		assertEquals(0,c.getImporte(),0.001);
	} 
	
	@Test
	public void testGetDireccionValido() {
		ComandaDomicilio c = new ComandaDomicilio("a","b",1, 2);
		assertEquals("b", c.getDireccion());
	}
 
}
