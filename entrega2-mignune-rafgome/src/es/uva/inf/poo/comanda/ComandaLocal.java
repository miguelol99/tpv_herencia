package es.uva.inf.poo.comanda;

/**
 * Tipo de dato abstracto que representa una comanda de vendibles . Aporta
 * funcionalidad de modificación, inserción y borrado de vendibles. El numero de
 * unidades de cada vendible en la comanda se almacena en la lista cantidad.
 * Extiende la clase comanda
 * 
 * @author mignune
 * @author rafgome
 *
 */
public class ComandaLocal extends Comanda {

	/**
	 * Inicializa una comanda local con codigo.
	 * 
	 * @param codigo Codigo asociado a la comanda
	 */
	public ComandaLocal(String codigo) {
		super(codigo);
	}
}
