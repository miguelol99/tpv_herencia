package es.uva.inf.poo.vendible;

/**
 * Tipo abstracto de datos que representa un objeto vendible. Consta de codigo
 * nombre y descripcion.
 * 
 * @author mignune
 * @author rafgome
 */
public abstract class Vendible {

	private String codigo;
	private String nombre;
	private String descripcion;
 
	/**
	 * Inicializa un vendible con codigo, nombre y descripcion especificados;
	 * 
	 * @param codigo      El codigo del vendible. No puede ser null.
	 * @param nombre      El nombre asociado al vendible. No puede ser null.
	 * @param descripcion Descripcion asociada al vendible. No puede ser null.
	 * @throws java.lang.IllegalArgumentException Si el codigo es null.
	 * @throws java.lang.IllegalArgumentException Si el nombre es null.
	 * @throws java.lang.IllegalArgumentException Si la descripcion es null.
	 */ 
	public Vendible(String codigo, String nombre, String descripcion) {
		if (codigo == null)
			throw new IllegalArgumentException("El codigo es null");

		if (nombre == null)
			throw new IllegalArgumentException("El nombre es null");

		if (descripcion == null)
			throw new IllegalArgumentException("La descripcion es null");

		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigo = codigo;
	}

	/**
	 * Devuelve el codigo del vendible
	 * 
	 * @return codigo El codigo del vendible
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Devuelve el nombre del vendible
	 * 
	 * @return El nombre del vendble
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Devuelve la descripcion del vendible
	 * 
	 * @return La descripcion del vendible
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Devuelve el precio del vendible
	 * 
	 * @return El precio del vendible
	 */
	public abstract double getPrecio();

	/**
	 * Devuelve el número de unidades disponibles del vendible.
	 * 
	 * @return El stock del producto.
	 */
	public abstract int getStock();

	/**
	 * Cambia el stock del vendble.
	 * 
	 * @param stock El stock que se quiere establecer. No puede ser menor que 0;
	 */
	public abstract void setStock(int stock);

}
