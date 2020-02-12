package es.uva.inf.poo.comanda;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import es.uva.inf.poo.vendible.Vendible;

/**
 * Tipo de dato abstracto que representa una comanda de vendibles. Aporta
 * funcionalidad de modificación, inserción y borrado de vendibles. El numero de
 * unidades de cada vendible en la comanda se almacena en la lista cantidad.
 * 
 * @author mignune
 * @author rafgome
 * 
 */ 
public class Comanda {

	public enum Estado {
		ABIERTO, PAGADO, CERRADO, ANULADO
	}

	private static final String ERROR_VENDIBLE_NULL = "El vendible no puede ser null";
	private static final String ERROR_COMANDA_ABIERTA = "La comanda debe estar abierta";
	private static final String ERROR_VENDIBLE_NO_CONTENIDO = "La comanda debe contener el vendible";

	private String codigo;
	private Estado estado;
	private List<Vendible> vendibles;
	private List<Integer> cantidad;
	private double importe;
	private LocalDate fecha;
	private LocalTime hora;

	/**
	 * Inicialización de una comanda con codigo.
	 * 
	 * @param codigo Código de la nueva comanda que se va a crear. No pude ser null.
	 * @throws java.lang.IllegalArgumentException Si el codigo es null.
	 */
	public Comanda(String codigo) {
		if (codigo == null)
			throw new IllegalArgumentException("El codigo no puede ser null");

		this.codigo = codigo;
		vendibles = new ArrayList<>();
		cantidad = new ArrayList<>();
		estado = Estado.ABIERTO;
		fecha = LocalDate.now();
		hora = LocalTime.now();
	}

	/**
	 * Añade un vendible en determinada cantidad a la comanda. La comanda debe estar
	 * abierta.
	 * 
	 * @param v        Vendible que se quiere añadir. No puede ser null.
	 * @param unidades Unidades del vendible que se quiere añadir. No puede ser
	 *                 menor que 1 ni mayor que el stock del vendible.
	 * @throws java.lang.IllegalStateException Si la comanda no esta abierta.
	 * @throws java.lang.IllegalArgumentException Si el vendible es null.
	 * @throws java.lang.IllegalArgumentException Si las unidades son menores que 1
	 *         o mayores que el stock del vendible.
	 */
	public void addVendible(Vendible v, int unidades) {
		if (estado != Estado.ABIERTO)
			throw new IllegalStateException(ERROR_COMANDA_ABIERTA);

		if (v == null)
			throw new IllegalArgumentException(ERROR_VENDIBLE_NULL);

		if (unidades < 1 || unidades > v.getStock())
			throw new IllegalArgumentException("La unidades no pueden ser menor que 1 o mayor que el stock");

		vendibles.add(v);
		cantidad.add(unidades);
	}

	/**
	 * Elimina un vendible de la comanda. La comanda debe estar abierta.
	 * 
	 * @param v Vendible que se quiere eliminar. Ha de encontrarse en la comanda. No
	 *          puede ser null.
	 * @throws java.lang.IllegalStateException Si la comanda no esta abierta.
	 * @throws java.lang.IllegalArgumentException Si el vendible es null
	 * @throws java.lang.IllegalArgumentException Si el vendible no esta contenido
	 *         en la comanda
	 */
	public void removeVendible(Vendible v) {
		if (estado != Estado.ABIERTO)
			throw new IllegalStateException(ERROR_COMANDA_ABIERTA);

		if (v == null)
			throw new IllegalArgumentException(ERROR_VENDIBLE_NULL);

		if (!vendibles.contains(v))
			throw new IllegalArgumentException(ERROR_VENDIBLE_NO_CONTENIDO);

		int indice = vendibles.indexOf(v);
		vendibles.remove(v);
		cantidad.remove(indice);
	}

	/**
	 * Devuelve la cantidad de un vendible en la comanda.
	 * 
	 * @param v Vendible del que se quiere saber la cantidad. Ha de estar en la
	 *          comanda. No puede ser null.
	 * @throws java.lang.IllegalArgumentException Si el vendible no se encuentra en
	 *         la comanda.
	 * @throws java.lang.IllegalArgumentException Si el vendible es null.
	 * @return Cantidad de unidades del vendible en la comanda.
	 */
	public int getCantidadDeUnVendible(Vendible v) {
		if (v == null)
			throw new IllegalArgumentException(ERROR_VENDIBLE_NULL);

		if (!vendibles.contains(v))
			throw new IllegalArgumentException(ERROR_VENDIBLE_NO_CONTENIDO);

		int indice = vendibles.indexOf(v);
		return cantidad.get(indice);
	}

	/**
	 * Cambia la cantidad de un vendible en la comanda. La comanda debe estar
	 * abierta.
	 * 
	 * @param v        El vendible al que se le quiere cambiar la cantidad. Ha de
	 *                 encontrarse en la comanda. No puede ser null.
	 * @param unidades Unidades que se quieren establecer. No puede ser menor que 1
	 *                 ni mayor que el stock del vendible.
	 * @throws java.lang.IllegalStateException Si la comanda no esta abierta
	 * @throws java.lang.IllegalArgumentException Si el vendible es null.
	 * @throws java.lang.IllegalArgumentException Si el vendible no esta contenido
	 *         en la comanda.
	 * @throws java.lang.IllegalArgumentException Si las unidades son menores que 1
	 *         o mayores que el stock del producto.
	 */
	public void setCantidadDeUnVendible(Vendible v, int unidades) {
		if (estado != Estado.ABIERTO)
			throw new IllegalStateException(ERROR_COMANDA_ABIERTA);

		if (v == null)
			throw new IllegalArgumentException(ERROR_VENDIBLE_NULL);

		if (!vendibles.contains(v))
			throw new IllegalArgumentException(ERROR_VENDIBLE_NO_CONTENIDO);

		if (unidades < 1 || unidades > v.getStock())
			throw new IllegalArgumentException(
					"La unidades deben ser mayor o igual que 1 y menor o igual que el stock");

		int indice = vendibles.indexOf(v);
		cantidad.set(indice, unidades);
	}

	/**
	 * Devuelve el codigo identificador de la comanda
	 * 
	 * @return El codigo identificador de la comanda
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Devuelve el estado actual de la comanda
	 * 
	 * @return El estado de la comanda
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * Cambia el estado de la comanda, solo si actualmente está abierta o pagada. En
	 * el caso de que actualmente este pagada, el nuevo estado solo puede ser
	 * cerrado. En el caso de que actualmente este abierta, el nuevo estado no puede
	 * ser cerrado. Almacena en la variable importe el precio de los vendibles antes
	 * de cambiar de estado.
	 * 
	 * @param estado Nuevo estado que se quiere asignar. No puede ser null.
	 * @throws java.lang.IllegalArgumentException Si el estado es null.
	 * @throws java.lang.IllegalStateException Si el estado esta anulado o cerrado
	 * @throws java.lang.IllegalStateException Si el estado actual de la comanda es
	 *         pagado y el nuevo estado no es cerrado
	 * @throws java.lang.IllegalStateException Si el estado actual de la comanda es
	 *         abierto y el nuevo estado es cerrado
	 */
	public void setEstado(Estado estado) {
		if (estado == null)
			throw new IllegalArgumentException("El estado no puede ser null");

		if (this.estado == Estado.ANULADO || this.estado == Estado.CERRADO)
			throw new IllegalStateException("El estado actual debe ser abierto o pagado");

		if (this.estado == Estado.PAGADO && estado != Estado.CERRADO)
			throw new IllegalStateException("Una comanda pagada solo puede cambiar a cerrada");

		if (this.estado == Estado.ABIERTO && estado == Estado.CERRADO)
			throw new IllegalStateException("Una comanda abierta no puede cambiar a cerrada");

		this.importe = getImporte();
		this.estado = estado;
	}

	/**
	 * Devuelve una lista con los vendibles contenidos en la comanda
	 * 
	 * @return Una lista que contiene los vendibles
	 */
	public List<Vendible> getVendibles() {
		return vendibles;
	}

	/**
	 * Devuelve una lista con las unidades de cada vendible en la comanda.
	 * 
	 * @return Una lista que contiene las cantidades de los vendibles
	 */
	public List<Integer> getCantidad() {
		return cantidad;
	}

	/**
	 * Devuelve el precio de total de los vendibles presentes en la comanda.
	 * 
	 * @return el precio total de los vendibles de la comanda. Si la comanda ya ha
	 *         sido pagada devuelve el importe antes de que se pagase. Si la comanda
	 *         esta anulada devuelve 0.
	 */
	public double getImporte() {
		double total = 0;
		int indice = 0;

		if (estado == Estado.ANULADO)
			return 0;

		else if (estado == Estado.PAGADO || estado == Estado.CERRADO)
			return this.importe;

		for (Vendible v : vendibles) {
			total = total + (v.getPrecio() * cantidad.get(indice));
			indice++;
		}
		return total;
	} 

	/**
	 * Devuelve la fecha de creacion de la comanda en formato LocalDate
	 * 
	 * @return la fecha de creacion de la comanda en formato LocalDate
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Devuelve la hora de creacion de la comanda en formato LocalDate
	 * 
	 * @return la hora de creacion de la comanda en formato LocalDate
	 */
	public LocalTime getHora() {
		return hora;
	}
}
