package es.uva.inf.poo.vendible;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComboTest {

	@Test
	public void testInicializarValido() {
		Combo c = new Combo("a", "b", "c");
		assertNotNull(c);
		assertEquals(1, c.getStock());
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarCodigoNoValido() {
		Combo c = new Combo(null, "b", "c");
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarNombreNoValido() {
		Combo c = new Combo("a", null, "c");
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarDescripcionNoValida() {
		Combo c = new Combo("a", "b", null);
	}

	@Test
	public void testGetPrecioValido() {
		Combo c = new Combo("a", "b", "c");
		Producto p = new Producto("a", "b", "c", 5, 0.01);
		c.addProducto(p, 3);
		assertEquals((0.01 * 3) * 0.9, c.getPrecio(), 0.001);

	}

	@Test
	public void testGetStock() {
		Combo c = new Combo("a", "b", "c");
		assertEquals(1, c.getStock());
	}

	@Test
	public void testSetStockValido() {
		Combo c = new Combo("a", "b", "c");
		Producto p = new Producto("a", "b", "c", 2, 0.01);
		c.addProducto(p, 1);
		c.setStock(2);
		assertEquals(2, c.getStock());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetStockProductosNoSuficientes() {
		Combo c = new Combo("a", "b", "c");
		Producto p = new Producto("a", "b", "c", 2, 0.01);
		Producto p2 = new Producto("a", "b", "c", 1, 0.01);
		c.addProducto(p, 1);
		c.addProducto(p2, 1);
		c.setStock(2);
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetStockStockNegativo() {
		Combo c = new Combo("a", "b", "c");
		c.setStock(-1);
	} 
	 
	@Test
	public void testAddProductoValido() {
		Combo c = new Combo("a", "b", "c");
		Producto p = new Producto("a", "b", "c", 1, 0.01);
		c.addProducto(p, 1);
		assertTrue(c.getProductos().contains(p));
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddProductoNull() {
		Combo c = new Combo("a", "b", "c");
		c.addProducto(null, 1);
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddProductoYaContenido() {
		Combo c = new Combo("a", "b", "c");
		Producto p = new Producto("a", "b", "c", 2, 0.01);
		c.addProducto(p, 1);
		c.addProducto(p, 1);
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddProductoSinUnidadesSuficientes() {
		Combo c = new Combo("a", "b", "c");
		Producto p = new Producto("a", "b", "c", 2, 0.01);
		c.addProducto(p, 3);
	} 
	
	@Test
	public void testRemoveProductoValido() {
		Combo c = new Combo("a", "b", "c");
		Producto p = new Producto("a", "b", "c", 1, 0.01);
		c.addProducto(p, 1);
		c.removeProducto(p);
		assertFalse(c.getProductos().contains(p));
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveProductoNull() {
		Combo c = new Combo("a", "b", "c");
		c.removeProducto(null);
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveProductoNOContenido() {
		Combo c = new Combo("a", "b", "c");
		Producto p = new Producto("a", "b", "c", 2, 0.01);
		c.removeProducto(p);
	}  
	 
	@Test
	public void testSetStockDeUnProductoValido() {
		Combo c = new Combo("a", "b", "c");
		Producto p = new Producto("a", "b", "c", 2, 0.01);
		c.addProducto(p, 1);
		c.setStockDeUnProducto(p, 2);
		assertTrue(p.getStock() == c.getCantidades().get(0));
	}  
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetStockDeUnProductoNull() {
		Combo c = new Combo("a", "b", "c");
		Producto p = new Producto("a", "b", "c", 2, 0.01);
		c.addProducto(p, 1);
		c.setStockDeUnProducto(null, 2);
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetStockDeUnProductoNoContenido() {
		Combo c = new Combo("a", "b", "c");
		Producto p = new Producto("a", "b", "c", 2, 0.01);
		c.setStockDeUnProducto(p, 2);
	} 
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetStockDeUnProductoSinUnidadesSuficientes() {
		Combo c = new Combo("a", "b", "c");
		Producto p = new Producto("a", "b", "c", 2, 0.01);
		c.addProducto(p, 1);
		c.setStockDeUnProducto(p, 3);
	}  
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetStockDeUnProductoUnidadesNegativas() {
		Combo c = new Combo("a", "b", "c");
		Producto p = new Producto("a", "b", "c", 2, 0.01);
		c.addProducto(p, 1);
		c.setStockDeUnProducto(p, -2);
	} 
	
	

}
