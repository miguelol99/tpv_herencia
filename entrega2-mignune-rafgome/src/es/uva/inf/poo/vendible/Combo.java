package es.uva.inf.poo.vendible;

import java.util.ArrayList;
import java.util.List;

/**
 * Tipo abstracto de datos que representa un combo de productos. Extiende a la
 * clase Vendible.
 * 
 * @author mignune
 * @author rafgome
 *
 */
public class Combo extends Vendible {

	public static final String ERROR_PRODUCTO_NULL = "El producto no puede ser null";

	private List<Producto> productos;
	private List<Integer> cantidades;
	private int stock;

	/**
	 * Inicializa un combo con codigo, nombre y descripcion.
	 * 
	 * @param codigo      codigo asociado al combo
	 * @param nombre      nombre asociado al combo
	 * @param descripcion descripcion asociada al combo
	 */
	public Combo(String codigo, String nombre, String descripcion) {
		super(codigo, nombre, descripcion);

		productos = new ArrayList<>();
		cantidades = new ArrayList<>();
		stock = 1;
	}

	/**
	 * Devuelve el precio del combo. Este es igual a la suma total del valor de los
	 * productos que lo forman con un 10% de descuento.
	 * 
	 * @return el precio del combo.
	 */
	public double getPrecio() {
		double importe = 0;

		for (int i = 0; i < productos.size(); i++) {
			Producto p = productos.get(i);
			int cantidad = cantidades.get(i);

			importe = importe + (p.getPrecio() * cantidad);
		}

		return importe * 0.9;
	}

	/**
	 * Devuelve el stock del combo
	 * 
	 * @return el stock del combo
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Cambia el stock del combo. Los productos que lo formandeben de contar contar
	 * con suficientes unidades.
	 * 
	 * @param stock stock que se quiere establecer
	 * @throws java.lang.IllegalArgumentException si el stock es menor que 0
	 * @throws java.lang.IllegalArgumentException si algun producto no tiene las
	 *         unidades suficientes
	 */
	public void setStock(int stock) {
		if (stock < 0)
			throw new IllegalArgumentException("El stock no puede ser menor que cero");

		int indice = 0;

		for (Producto p : productos) {
			int cantidad = cantidades.get(indice);

			if ((cantidad * stock) > p.getStock())
				throw new IllegalArgumentException(
						"El producto" + p.getNombre() + "no dispone de unidades suficientes");

			indice++;
		}

		this.stock = stock;
	}

	/**
	 * Añade un producto al combo en determinada cantidad
	 * 
	 * @param p        producto que se quiere añadir. No puede ser null. No puede
	 *                 estar contenido en el combo
	 * @param unidades unidades que se quieren añadir. No puede ser menor que 1 ni
	 *                 mayor que el stock del producto.
	 * @throws java.lang.IllegalArgumentException si el producto es null
	 * @throws java.lang.IllegalArgumentException si el combo ya contiene el
	 *         producto
	 * @throws java.lang.IllegalArgumentException si las unidades son menores que 1
	 *         o mayores que el stock del producto
	 */
	public void addProducto(Producto p, int unidades) {
		if (p == null)
			throw new IllegalArgumentException(ERROR_PRODUCTO_NULL);

		if (productos.contains(p))
			throw new IllegalArgumentException("El producto no puede estar ya contenido");

		if (unidades < 1 || (unidades * stock) > p.getStock())
			throw new IllegalArgumentException("La unidades no pueden ser menor que 1 o mayor que el stock");

		productos.add(p);
		cantidades.add(unidades); 
	}

	/**
	 * Elimina un producto del combo
	 * 
	 * @param p producto que se quiere eliminar. No puede ser null. Debe estar
	 *          contenido en el combo.
	 * @throws java.lang.IllegalArgumentException si el producto es null
	 * @throws java.lang.IllegalArgumentException si el combo no contiene el
	 *         producto
	 */
	public void removeProducto(Producto p) {
		if (p == null)
			throw new IllegalArgumentException(ERROR_PRODUCTO_NULL);

		if (!productos.contains(p))
			throw new IllegalArgumentException("El producto debe estar ya contenido");

		int indice = productos.indexOf(p);
		productos.remove(p);
		cantidades.remove(indice);
	} 

	/**
	 * Cambia las unidades de un producto dentro del combo
	 * 
	 * @param p          producto que se quiere modificar. No puede ser null. Debe
	 *                   estr contenido en el combo
	 * @param nuevoStock stock que se quiere establecer. No puede ser menor que 0.
	 *                   No puede ser mayor que el stock del producto.
	 * @throws java.lang.IllegalArgumentException si el producto es null
	 * @throws java.lang.IllegalArgumentException si el combo no contiene el
	 *         producto
	 * @throws java.lang.IllegalArgumentException si el nuevo stock es negativo
	 * @throws java.lang.IllegalArgumentException si el producto no dispone de stock
	 *         suficiente
	 */
	public void setStockDeUnProducto(Producto p, int nuevoStock) {
		if (p == null)
			throw new IllegalArgumentException(ERROR_PRODUCTO_NULL);

		if (!productos.contains(p))
			throw new IllegalArgumentException("El producto debe estar ya contenido");

		if (nuevoStock < 0)
			throw new IllegalArgumentException("El stock no puede ser menor que cero");

		if ((nuevoStock * this.stock) > p.getStock())
			throw new IllegalArgumentException("El producto no dispone de unidades suficientes");

		int indice = productos.indexOf(p);

		cantidades.set(indice, nuevoStock);
	}

	/**
	 * Devuelve una lista con los productos del combo
	 * 
	 * @return una lista con los productos del combo
	 */
	public List<Producto> getProductos() {
		return productos;
	}

	/**
	 * Devuelve una lista con las cantidades de los productos del combo
	 * 
	 * @return una lista con las cantidades de los productos del combo
	 */
	public List<Integer> getCantidades() {
		return cantidades;
	}
}
