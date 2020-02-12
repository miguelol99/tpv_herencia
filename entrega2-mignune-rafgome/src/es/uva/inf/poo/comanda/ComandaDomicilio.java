package es.uva.inf.poo.comanda;

/**
 * Tipo de dato abstracto que representa una comanda de vendibles a domicilio.
 * Aporta funcionalidad de modificación, inserción y borrado de vendibles. El
 * numero de unidades de cada vendible en la comanda se almacena en la lista
 * cantidad. Extiende la clase Comanda.
 * 
 * @author mignune
 * @author rafgome
 *
 */
public class ComandaDomicilio extends Comanda {

	private String direccion;
	private int numBolsas;
	private double cuota;

	/**
	 * Inicializa una comanda a domicilio con codigo, direccion de entrega, zona de
	 * la direccion y numero de bolsas que se desean.
	 * 
	 * @param codigo    Codigo asociado a la comanda a domicilio
	 * @param direccion Direccion de entrega de la comanda
	 * @param zona      Zona donde se encuentra la direccion de entrega
	 * @param numBolsas Numero de bolsas que se desea utilizar
	 * @throws IllegalArgumentException si la direccion es null
	 * @throws IllegalArgumentException si la zona es menor que 1
	 * @throws IllegalArgumentException si el numero de bolsas es menor que 0
	 */
	public ComandaDomicilio(String codigo, String direccion, int zona, int numBolsas) {
		super(codigo); 

		if (direccion == null)
			throw new IllegalArgumentException("La direccion no puede ser null");

		if (zona < 1)
			throw new IllegalArgumentException("La zona no puede ser negativa");

		if (numBolsas < 0)
			throw new IllegalArgumentException("El numero de bolsas no puede ser negativo");

		this.direccion = direccion;
		this.numBolsas = numBolsas;
		if (zona == 1)
			cuota = 0.5;

		else if (zona == 2)
			cuota = 1;

		else
			cuota = 2.5;
	} 

	/**
	 * Devuelve el precio total de los vendibles de la comanda más un extra en
	 * funcion al numero de bolsas y a la zona de entrega.
	 * 
	 * @return el precio total de los vendibles de la comanda mas un extra. Si la
	 *         comanda ya ha sido pagada devuelve el importe antes de que se pagase.
	 *         Si la comanda esta anulada devuelve 0.
	 */ 
	@Override
	public double getImporte() {
		if (super.getImporte() == 0)
			return 0;

		return super.getImporte() + (numBolsas * 0.05) + cuota;
	}  

	/**
	 * Devuelve la direccion de entrega de la comanda
	 * 
	 * @return la direccion de entrega
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Devuelve el numero de bolsas pedidas por el cliente
	 * 
	 * @return el numero de bolsas pedidas
	 */
	public int getNumBolsas() {
		return numBolsas;
	}
 
	/**
	 * Devuelve la cuota asociada a la zona de la direccion de entrega
	 * 
	 * @return la cuota asociada a la zona de la direccion de entrega
	 */
	public double getCuota() {
		return cuota;
	}
}
