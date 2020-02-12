package es.uva.inf.poo.almacen;

import java.util.ArrayList;
import java.util.List;

import es.uva.inf.poo.vendible.Vendible;

/**
 * Tipo abstracto de datos que representa un almacen de vendibles. Aporta
 * funcionalidades para añadir y eliminar vendibles, asi como para conocer y
 * modificar su stock.
 * 
 * @author mignune
 * @author rafgome
 * 
 */
public class Almacen {

	private static final String ERROR_VENDIBLE_NULL = "El vendible no puede ser null";
	private static final String ERROR_NO_CONTENIDO = "El almacen no contiene el vendible";

	private List<Vendible> vendibles;

	/**
	 * Inicializa un almacen vacio
	 */
	public Almacen() {
		vendibles = new ArrayList<>();
	}

	/**
	 * Añade un vendible al almacen.
	 * 
	 * @param v Vendiv¡ble que se quiere añadir. No puede estar contenido en el
	 *          almacen. No puede ser null
	 * @throws IllegalArgumentException Si el Vendible es null
	 * @throws IllegalArgumentException Si el vendible ya esta contenido en el
	 *                                  almacen.
	 */
	public void addVendible(Vendible v) {
		if (v == null)
			throw new IllegalArgumentException(ERROR_VENDIBLE_NULL);

		if (vendibles.contains(v))
			throw new IllegalArgumentException("El almacen ya contiene el vendible");

		vendibles.add(v);
	}

	/**
	 * Elimina un vendible del almacen
	 * 
	 * @param v vendible que se quiere eliminar. Debe estar contenido en el almacen.
	 *          No puede ser null.
	 * @throws IllegalArgumentException Si el vendible es null
	 * @throws IllegalArgumentException Si el vendible no esta contenido en el
	 *                                  almacen
	 */
	public void removeVendible(Vendible v) {
		if (v == null)
			throw new IllegalArgumentException(ERROR_VENDIBLE_NULL);

		if (!vendibles.contains(v))
			throw new IllegalArgumentException(ERROR_NO_CONTENIDO);

		vendibles.remove(v);
	}

	/**
	 * Devuelve true si el vendible esta contenido en el almacen.
	 * 
	 * @param p Vendible cuya presencia se quiere probar. No puede ser null
	 * @return true si el vendible especificado esta contenido en el almacen
	 * @throws IllegalArgumentException Si el vendible es null
	 */
	public boolean exist(Vendible p) {
		if (p == null)
			throw new IllegalArgumentException(ERROR_VENDIBLE_NULL);

		return vendibles.contains(p);
	}

	/**
	 * Devuelve el stock del vendible especificado
	 * 
	 * @param v Vendible del que se quiere saber su stock. Debe de estar contenido
	 *          en el almacen. No puede ser null.
	 * @return el stock del vendible especificado
	 * @throws IllegalArgumentException Si el vendible es null
	 * @throws IllegalArgumentException Si el vendible no esta contenido en el
	 *                                  almacen
	 */
	public int getStock(Vendible v) {
		if (v == null)
			throw new IllegalArgumentException(ERROR_VENDIBLE_NULL);

		if (!vendibles.contains(v))
			throw new IllegalArgumentException(ERROR_NO_CONTENIDO);

		return v.getStock();
	}

	/**
	 * Establece un nuevo stock para un vendible del almacen.
	 * 
	 * @param v     Vendible al que se le quiere cambiar el stock. Debe de estar
	 *              contenido en el almacen. No puede ser null
	 * @param stock Cantidad a la que se quiere establecer. No puede ser menor que
	 *              0.
	 * @throws IllegalArgumentException Si el vendible es null
	 * @throws IllegalArgumentException Si el vendible no esta contenido en el
	 *                                  almacen
	 * @throws IllegalArgumentException Si el Stock es menor que 0
	 * @see Vendible#setStock(int)
	 */
	public void setStock(Vendible v, int stock) {
		if (v == null)
			throw new IllegalArgumentException(ERROR_VENDIBLE_NULL);

		if (!vendibles.contains(v))
			throw new IllegalArgumentException(ERROR_NO_CONTENIDO);

		if (stock < 0)
			throw new IllegalArgumentException("El stock no puede ser negativo");

		v.setStock(stock);
	}
}
