package es.uva.inf.poo.vendible;

import static org.junit.Assert.*;

import org.junit.Test;

public class VendibleTest {
	public static final double ERROR_ADMISIBLE = 0.001;
	  
	@Test 
	public void testInicializarValido() {
		Vendible p = new Producto("a","b","c",1,0.1);		
		assertNotNull(p);
	} 

	@SuppressWarnings("unused") 
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarNombreNoValido() {
		Vendible p = new Producto("a",null,"c",0,0.01);
	} 
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarDescripcionNoValida() {
		Vendible p = new Producto("a","b",null,0,0.01);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarPrecioNoValido() {
		Vendible p = new Producto("a","b","c",0,0);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarStockNoValido() {
		Vendible p = new Producto("a","b","c",-1,0.01);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarCodigoNoValido() {
		Vendible p = new Producto(null,"b","c",0,01); 
	}
	
	@Test
	public void testGetNombre() {
		Vendible p = new Producto("a","b","c",1,0.1);
		assertEquals("b", p.getNombre());
	}
	
	@Test
	public void testGetDescripcion() {
		Vendible p = new Producto("a","b","c",1,0.1);
		assertEquals("c", p.getDescripcion());
	}
	
	@Test
	public void testGetPrecio() {
		Vendible p = new Producto("a","b","c",1,0.01);
		assertEquals(0.01, p.getPrecio(), ERROR_ADMISIBLE);
	}
	
	@Test
	public void testGetStock() {
		Vendible p = new Producto("a","b","c",1,0.1);
		assertEquals(1, p.getStock());
	} 
	
	@Test
	public void testSetStockValido() {
		Vendible p = new Producto("a","b","c",1,0.1);
		p.setStock(1);
		assertEquals(1, p.getStock());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetStockNoValido() {
		Vendible p = new Producto("a","b","c",1,0.1);
		p.setStock(-1);
	}
	
	@Test
	public void testGetCodigo() {
		Vendible p = new Producto("a","b","c",1,0.01);
		assertEquals("a", p.getCodigo());
	} 


}
