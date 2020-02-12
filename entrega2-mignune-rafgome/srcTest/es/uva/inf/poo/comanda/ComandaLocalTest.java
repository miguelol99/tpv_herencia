package es.uva.inf.poo.comanda;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComandaLocalTest {

	@Test
	public void testInicializarConCodigoValido() {
		Comanda c= new ComandaLocal("a");
		assertNotNull(c);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInicializarConCodigoNoValido() {
		@SuppressWarnings("unused")
		Comanda c= new ComandaLocal(null);
	}

}
