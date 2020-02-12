package es.uva.inf.poo.vendible;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class PromoTest {

	@Test
	public void testInicializarValido() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();

		Promo pr = new Promo("a", "b", "c",inicio,fin,0.01);
		assertNotNull(pr);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarCodigoNoValido() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo(null, "b", "c",inicio,fin,0.01);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarNombreNoValido() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", null, "c",inicio,fin,0.01);
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarDescripcionNoValida() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", null,inicio,fin,0.01);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarFechaInicioNoValida() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",null,fin,0.01);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarFechaFinalNoValida() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,null,0.01);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarPrecioNoValida() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,fin,0);
	}

	@Test
	public void testGetPrecioValido() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,fin,0.01);
		Producto p = new Producto("a", "b", "c", 5, 0.01);
		pr.add(p);
		assertEquals(0.01, pr.getPrecio(), 0.001);

	}

	@Test
	public void testGetStock() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,fin,0.01);
		assertEquals(1, pr.getStock());
	}

	@Test
	public void testSetStockValido() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,fin,0.01);
		Producto p = new Producto("a", "b", "c", 2, 0.01);
		pr.add(p);
		pr.setStock(2);
		assertEquals(2, pr.getStock());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetStockProductosNoSuficientes() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,fin,0.01);
		Producto p = new Producto("a", "b", "c", 2, 0.01);
		Producto p2 = new Producto("a", "b", "c", 1, 0.01);
		pr.add(p);
		pr.add(p2);
		pr.setStock(2);
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetStockStockNegativo() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,fin,0.01);
		pr.setStock(-1);
	} 
	 
	@Test
	public void testAddProductoValido() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,fin,0.01);
		Producto p = new Producto("a", "b", "c", 1, 0.01);
		pr.add(p);
		assertTrue(pr.getProductos().contains(p));
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddProductoNull() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,fin,0.01);
		pr.add(null); 
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddProductoYaContenido() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,fin,0.01);
		Producto p = new Producto("a", "b", "c", 2, 0.01);
		pr.add(p);
		pr.add(p);
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddProductoSinUnidadesSuficientes() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,fin,0.01);
		Producto p = new Producto("a", "b", "c", 0, 0.01);
		pr.add(p);
	} 

	@Test
	public void testRemoveValido() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,fin,0.01);
		Producto p = new Producto("a", "b", "c", 1, 0.01);
		pr.add(p);
		pr.remove(p);
		assertTrue(pr.getProductos().isEmpty());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveProductoNull() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,fin,0.01);
		Producto p = new Producto("a", "b", "c", 0, 0.01);
		pr.add(p);
		pr.remove(null);
	}  
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveProductNoContenido() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,fin,0.01);
		Producto p = new Producto("a", "b", "c", 0, 0.01);
		pr.remove(p);
	}
	
	@Test
	public void testGetFechaDeInicio() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,fin,0.01);
		assertTrue(pr.getFechaInicio() == inicio);
	}
	
	@Test
	public void testGetFechaFinal() {
		LocalDate inicio = LocalDate.now();
		LocalDate fin = LocalDate.now();
		Promo pr = new Promo("a", "b", "c",inicio,fin,0.01);
		assertTrue(pr.getFechaFinal() == fin);
	}
}
