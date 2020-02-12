package es.uva.inf.poo.vendible;

/**
 * Tipo abstracto de datos que representa un producto. Extiende la clase
 * Vendible.
 * 
 * @author mignune
 * @author rafgome
 * 
 */
public class Producto extends Vendible {

	private int stock;
	private double precio;

	/**
	 * Inicializa un producto con nombre, descripcion, stock y precio.
	 * 
	 * @param codigo      codigo del producto
	 * @param nombre      nombre del producto
	 * @param descripcion descripcion del producto
	 * @param stock       stock del producto
	 * @param precio      precio del producto
	 * @throws java.lang.IllegalArgumentException si el stock es menor que 1
	 * @throws java.lang.IllegalArgumentException si el precio es menor o igual a 0
	 */
	public Producto(String codigo, String nombre, String descripcion, int stock, double precio) {
		super(codigo, nombre, descripcion);

		if (stock < 1)
			throw new IllegalArgumentException("El stock no puede ser menor que 1");

		if (precio <= 0)
			throw new IllegalArgumentException("El precio no puede ser menor o igual a cero");

		this.stock = stock;
		this.precio = precio;
	}

	/**
	 * Devuelve el precio del producto
	 * 
	 * @return el precio del producto
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Devuelve el stock del producto
	 * 
	 * @return el stock del producto
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Cambia el stock del producto
	 * 
	 * @param stock el stock que se quiere establecer
	 * @throws java.lang.IllegalArgumentException si el stock es negativo
	 */
	public void setStock(int stock) {
		if (stock < 0)
			throw new IllegalArgumentException();

		this.stock = stock;
	}

	/**
	 * Cambia el precio del producto
	 * 
	 * @param precio precio que se quiere establecer
	 * @throws java.lang.IllegalArgumentException si el precio es menor o igual a 0
	 */
	public void setPrecio(double precio) {
		if (precio <= 0)
			throw new IllegalArgumentException("El precio no puede ser menor o igual que cero");

		this.precio = precio;
	}
}
