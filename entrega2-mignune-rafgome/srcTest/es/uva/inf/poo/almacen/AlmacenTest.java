package es.uva.inf.poo.almacen;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uva.inf.poo.vendible.Combo;
import es.uva.inf.poo.vendible.Producto;
import es.uva.inf.poo.vendible.Vendible;

public class AlmacenTest {
	@Test
	public void testInicializarValido() {
		Almacen a = new Almacen();
		assertNotNull(a); 
	}

	@Test
	public void testAddVendibleProductoValido() {
		Almacen a = new Almacen();
		Vendible p = new Producto("a", "b", "c", 1, 0.1);
		a.addVendible(p);
		assertTrue(a.exist(p));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddVendibleProductoNull() {
		Almacen a = new Almacen();
		a.addVendible(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddVendibleProductoActualmenteContenido() {
		Almacen a = new Almacen();
		Vendible p = new Producto("a", "b", "c", 1, 0.1);
		a.addVendible(p);
		a.addVendible(p);
	}

	@Test
	public void testRemoveVendibleProductoValido() {
		Almacen a = new Almacen();
		Vendible p = new Producto("a", "b", "c", 1, 0.1);
		a.addVendible(p);
		a.removeVendible(p);
		assertFalse(a.exist(p));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveVendibleProductoNull() {
		Almacen a = new Almacen();
		Vendible p = new Producto("a", "b", "c", 1, 0.1);
		a.addVendible(p);
		a.removeVendible(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveVendibleProductoNoContenido() {
		Almacen a = new Almacen();
		Vendible p = new Producto("a", "b", "c", 1, 0.1);
		a.removeVendible(p);
	}

	@Test
	public void testExistProductoValidoFalse() {
		Almacen a = new Almacen();
		Vendible p = new Producto("a", "b", "c", 1, 0.1);
		assertFalse(a.exist(p));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExistProductoNull() {
		Almacen a = new Almacen();
		assertTrue(a.exist(null));
	}

	@Test
	public void testGetStockProductoValido() {
		Almacen a = new Almacen();
		Vendible p = new Producto("a", "b", "c", 1, 0.1);
		a.addVendible(p);
		assertEquals(1, a.getStock(p));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetStockVendibleProductoNull() {
		Almacen a = new Almacen();
		a.getStock(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetStockVendibleProductoNoContenido() {
		Almacen a = new Almacen();
		Vendible p = new Producto("a", "b", "c", 1, 0.1);
		a.getStock(p);
	}

	@Test
	public void testSetStockProductoValido() {
		Almacen a = new Almacen();
		Vendible p = new Producto("a", "b", "c", 1, 0.1);
		a.addVendible(p);
		a.setStock(p, 1);
		assertEquals(1, a.getStock(p));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetStockVendibleProductoNull() {
		Almacen a = new Almacen();
		a.setStock(null, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetStockVendibleProductoNoContenido() {
		Almacen a = new Almacen();
		Vendible p = new Producto("a", "b", "c", 1, 0.1);
		a.setStock(p, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetStockStockProductoNegativo() {
		Almacen a = new Almacen();
		Vendible p = new Producto("a", "b", "c", 1, 0.1);
		a.addVendible(p);
		a.setStock(p, -1);
	}

	@Test
	public void testAddVendibleComboValido() {
		Almacen a = new Almacen();
		Vendible c = new Combo("a", "b", "c");
		a.addVendible(c);
		assertTrue(a.exist(c));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddVendibleComboActualmenteContenido() {
		Almacen a = new Almacen();
		Vendible c = new Combo("a", "b", "c");
		a.addVendible(c);
		a.addVendible(c);
	}

	@Test
	public void testRemoveVendibleComboValido() {
		Almacen a = new Almacen();
		Vendible c = new Combo("a", "b", "c");
		a.addVendible(c);
		a.removeVendible(c);
		assertFalse(a.exist(c));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveVendibleComboNull() {
		Almacen a = new Almacen();
		Vendible c = new Combo("a", "b", "c");
		a.addVendible(c);
		a.removeVendible(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveVendibleComboNoContenido() {
		Almacen a = new Almacen();
		Vendible c = new Combo("a", "b", "c");
		a.removeVendible(c);
	}

	@Test
	public void testExistComboValidoFalse() {
		Almacen a = new Almacen();
		Vendible c = new Combo("a", "b", "c");
		assertFalse(a.exist(c));
	}

	@Test
	public void testGetStockComboValido() {
		Almacen a = new Almacen();
		Vendible c = new Combo("a", "b", "c");
		a.addVendible(c);
		assertEquals(1, a.getStock(c));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetStockVendibleComboNoContenido() {
		Almacen a = new Almacen();
		Vendible c = new Combo("a", "b", "c");
		a.getStock(c);
	}

	@Test
	public void testSetStockComboValido() {
		Almacen a = new Almacen();
		Vendible c = new Combo("a", "b", "c");
		a.addVendible(c);
		a.setStock(c, 1);
		assertEquals(1, a.getStock(c));
	}


	@Test(expected = IllegalArgumentException.class)
	public void testSetStockVendibleComboNoContenido() {
		Almacen a = new Almacen();
		Vendible c = new Combo("a", "b", "c");
		a.setStock(c, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetStockStockComboNegativo() {
		Almacen a = new Almacen();
		Vendible c = new Combo("a", "b", "c");
		a.addVendible(c);
		a.setStock(c, -1);
	}

}
