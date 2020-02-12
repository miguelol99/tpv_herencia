package es.uva.inf.poo.vendible;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductoTest {

	public static final double ERROR_ADMISIBLE = 0.001;
	  
	@Test
	public void testInicializarValido() {
		Producto p = new Producto("a","b","c",1,0.01);		
		assertNotNull(p);
	} 

	@SuppressWarnings("unused") 
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarNombreNoValido() {
		Producto p = new Producto("a",null,"c",1,0.01);
	} 
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarDescripcionNoValida() {
		Producto p = new Producto("a","b",null,1,0.01);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarPrecioNoValido() {
		Producto p = new Producto("a","b","c",1,0);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarStockNoValido() {
		Producto p = new Producto("a","b","c",-1,1.01);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarCodigoNoValido() {
		Producto p = new Producto(null,"b","c",1,0.01); 
	}
	
	@Test
	public void testGetNombre() {
		Producto p = new Producto("a","b","c",1,0.01);
		assertEquals("b", p.getNombre());
	}
	
	@Test
	public void testGetDescripcion() {
		Producto p = new Producto("a","b","c",1,0.01);
		assertEquals("c", p.getDescripcion());
	}
	
	@Test
	public void testGetPrecio() {
		Producto p = new Producto("a","b","c",1,0.01);
		assertEquals(0.01, p.getPrecio(), ERROR_ADMISIBLE);
	}
	
	@Test
	public void testSetPrecioValido() {
		Producto p = new Producto("a","b","c",1,0.01);
		p.setPrecio(0.02);
		assertEquals(0.02, p.getPrecio(), ERROR_ADMISIBLE);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPrecioIgualACero() {
		Producto p = new Producto("a","b","c",1,0.01);
		p.setPrecio(0);
	}
	
	@Test
	public void testGetStock() {
		Producto p = new Producto("a","b","c",1,0.01);
		assertEquals(1, p.getStock());
	} 
	
	@Test
	public void testSetStockValido() {
		Producto p = new Producto("a","b","c",1,0.01);
		p.setStock(1);
		assertEquals(1, p.getStock());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetStockNoValido() {
		Producto p = new Producto("a","b","c",1,0.01);
		p.setStock(-1);
	}
	
	@Test
	public void testGetCodigo() {
		Producto p = new Producto("a","b","c",1,0.01);
		assertEquals("a", p.getCodigo());
	} 


}
