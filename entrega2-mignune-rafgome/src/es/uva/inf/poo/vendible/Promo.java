package es.uva.inf.poo.vendible;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Tipo abstracto de datos que representa una promocion de productos. Extiende
 * la clase Vendible.
 * 
 * @author mignune
 * @author rafgome
 *
 */
public class Promo extends Vendible {

	private List<Producto> productos;
	private LocalDate inicio;
	private LocalDate fin;
	private double precio;
	private int stock;

	/**
	 * Inicializa una promocion con codigo, nombre, descripcion, fecha de inicio,
	 * fecha de final y precio.
	 * 
	 * @param codigo      el codigo asociado a la promocion
	 * @param nombre      el nombre asociado a la promocion
	 * @param descripcion la descripcion asociada a la promocion
	 * @param inicio      la fecha de inicio de la promocion
	 * @param fin         la fecha de finalizacion de la promocion
	 * @param precio      el precio de la promocion
	 * @throws java.lang.IllegalArgumentException si el inicio es null
	 * @throws java.lang.IllegalArgumentException si la fecha de inicio es null
	 * @throws java.lang.IllegalArgumentException si la fecha de finalizacion es
	 *         null
	 * @throws java.lang.IllegalArgumentException si el precio es menor o igual a 0
	 */
	public Promo(String codigo, String nombre, String descripcion,
			LocalDate inicio, LocalDate fin, double precio) {
		super(codigo, nombre, descripcion);

		if (inicio == null)
			throw new IllegalArgumentException("La fecha de inicio no puede ser null");

		if (fin == null)
			throw new IllegalArgumentException("La fecha de finalizacion no puede ser null");

		if (precio <= 0)
			throw new IllegalArgumentException("El precio ha de ser mayor que cero");

		productos = new ArrayList<>();
		this.inicio = inicio;
		this.fin = fin;
		this.precio = precio;
		this.stock = 1;
	}

	/**
	 * Devuelve el stock de la promo
	 * 
	 * @return el stock de la promo
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Devuelve el precio de la promo
	 * 
	 * @return el precio de la promo
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Cambia el stock de la promocion. Los productos que la forman deben disponer
	 * de unidades suficientes
	 * 
	 * @param stock stock que se quiere establecer. No puede ser negativo.
	 * @throws java.lang.IllegalArgumentException si el stock es negativo
	 * @throws java.lang.IllegalArgumentException si algun producto no dispone de
	 *         unidades suficientes
	 */
	public void setStock(int stock) {
		if (stock < 0) 
			throw new IllegalArgumentException("El stock no puede ser negativo");

		for (Producto producto : productos) {
			if (stock > producto.getStock())
				throw new IllegalArgumentException(
						"El producto" + producto.getNombre() + "no dispone de unidades suficientes");
		}

		this.stock = stock;
	}

	/**
	 * Añade un producto a la promocion
	 * 
	 * @param p producto que se quiere añadir. No puede ser null. Debe disponer de
	 *          unidades suficientes.
	 * @throws java.lang.IllegalArgumentException si el producto es null
	 * @throws java.lang.IllegalArgumentException si el producto ya esta contenido
	 * @throws java.lang.IllegalArgumentException si el producto no dispone de
	 *         unidades suficientes.
	 */
	public void add(Producto p) {
		if (p == null)
			throw new IllegalArgumentException("El producto no puede ser null");
		
		if(productos.contains(p))
			throw new IllegalArgumentException("El producto no puede estar contenido en la promo");

		if (stock > p.getStock())
			throw new IllegalArgumentException("El producto no dispone de unidades suficientes");

		productos.add(p);
	}

	/**
	 * Elimina un producto de la promocion
	 * 
	 * @param p producto que se quiere eliminar. No puede ser null. Debe de estar
	 *          contenido en la promocion
	 * @throws java.lang.IllegalArgumentException si el producto es null
	 * @throws java.lang.IllegalArgumentException si la promocion no contine al
	 *         producto.
	 */
	public void remove(Producto p) {
		if (p == null) 
			throw new IllegalArgumentException("El producto no puede ser null");

		if (!productos.contains(p))
			throw new IllegalArgumentException("El producto debe esatar contenido en la promocion");

		productos.remove(p);
	}

	/**
	 * Devuelve la fecha en la que comienza la promocion (incluida)
	 * 
	 * @return la fecha en la que comienza la promocion
	 */
	public LocalDate getFechaInicio() {
		return inicio;
	}

	/**
	 * Devuelve la fecha en la que finalia la promocion (sin incluir)
	 * 
	 * @return la fecha en la que finaliza la promocion
	 */
	public LocalDate getFechaFinal() {
		return fin;
	}
	
	/**
	 * Devuelve los productos que forman la promocion
	 * @return los productos que forman la promocion
	 */
	public List<Producto> getProductos(){
		return productos;
	}

}
