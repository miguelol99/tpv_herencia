package es.uva.inf.poo.tpv;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uva.inf.poo.comanda.Comanda;
import es.uva.inf.poo.vendible.Producto;
import fabricante.externo.tarjetas.TarjetaMonedero;

public class TPVTest {
	
	private static final String CREDENCIAL_INICIALIZACION = "A156Bv09_1zXo894";
	private static final String CREDENCIAL_DESCONTAR_SALDO = "6Z1y00Nm31aA-571";
	 
	@Test
	public void testInicializarValido() {
		TPV tpv = new TPV("a");
		assertNotNull(tpv);		
	}  
	
	@Test(expected=IllegalArgumentException.class)
	public void testInicializarCodigoNoValido() {
		@SuppressWarnings("unused")
		TPV tpv = new TPV(null);		
	} 
	
	@Test
	public void testNewComandaLocalPrimeraVezValido() {
		TPV tpv = new TPV("a");		
		tpv.newComandaLocal("a");
		assertEquals(Comanda.Estado.ABIERTO, tpv.getComandaActual().getEstado());
	}
	
	@Test
	public void testNewComandaLocalAnteriorCerradaValido() {
		TPV tpv = new TPV("a");		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.getComandaActual().setEstado(Comanda.Estado.CERRADO);
		tpv.newComandaLocal("a");
		assertEquals(Comanda.Estado.ABIERTO, tpv.getComandaActual().getEstado());
	}

	@Test
	public void testNewComandaLocalAnteriorAnuladaValido() {
		TPV tpv = new TPV("a");		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.ANULADO);
		tpv.newComandaLocal("a");
		assertEquals(Comanda.Estado.ABIERTO, tpv.getComandaActual().getEstado());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testNewComandaLocalAnteriorAbierta() {
		TPV tpv = new TPV("a");
		tpv.newComandaLocal("a");
		tpv.newComandaLocal("a"); 
	}
	
	@Test(expected=IllegalStateException.class)
	public void testNewComandaLocalAnteriorPagada() {
		TPV tpv = new TPV("a");
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO); 
		tpv.newComandaLocal("a"); 
	}
	
	@Test
	public void testNewComandaDomicilioPrimeraVezValido() {
		TPV tpv = new TPV("a");		
		tpv.newComandaDomicilio("a", "b", 1, 2);
		assertEquals(Comanda.Estado.ABIERTO, tpv.getComandaActual().getEstado());
	}
	
	@Test
	public void testNewComandaDomicilioAnteriorCerradaValido() {
		TPV tpv = new TPV("a");		
		tpv.newComandaDomicilio("a", "b", 1, 2);
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.getComandaActual().setEstado(Comanda.Estado.CERRADO);
		tpv.newComandaDomicilio("a", "b", 1, 2);
		assertEquals(Comanda.Estado.ABIERTO, tpv.getComandaActual().getEstado());
	}

	@Test
	public void testNewComandaDomicilioAnteriorAnuladaValido() {
		TPV tpv = new TPV("a");		
		tpv.newComandaDomicilio("a", "b", 1, 2);
		tpv.getComandaActual().setEstado(Comanda.Estado.ANULADO);
		tpv.newComandaDomicilio("a", "b", 1, 2);
		assertEquals(Comanda.Estado.ABIERTO, tpv.getComandaActual().getEstado());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testNewComandaDomicilioAnteriorAbierta() {
		TPV tpv = new TPV("a");
		tpv.newComandaDomicilio("a", "b", 1, 2);
		tpv.newComandaDomicilio("a", "b", 1, 2);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testNewComandaDomicilioAnteriorPagada() {
		TPV tpv = new TPV("a");
		tpv.newComandaDomicilio("a", "b", 1, 2);
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO); 
		tpv.newComandaDomicilio("a", "b", 1, 2);
	}
	
	@Test 
	public void testCobrarComandaActualValido() {
		TPV tpv = new TPV("a");
		TarjetaMonedero t = new TarjetaMonedero(CREDENCIAL_INICIALIZACION, 1);
		Producto p = new Producto("a","b","c",3,0.01);
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().addVendible(p, 1);	
		tpv.cobrarComandaActual(t, CREDENCIAL_DESCONTAR_SALDO);
		
		assertEquals(0.99, t.getSaldoActual(), 0.001);
		assertEquals(Comanda.Estado.PAGADO, tpv.getComandaActual().getEstado());
	}  
	
	@Test(expected=IllegalStateException.class)
	public void testCobrarComandaActuaNoExiste() {
		TPV tpv = new TPV("a");
		TarjetaMonedero t = new TarjetaMonedero(CREDENCIAL_INICIALIZACION, 1);
		
		tpv.cobrarComandaActual(t, CREDENCIAL_DESCONTAR_SALDO);
	}  
	
	@Test(expected=IllegalStateException.class)
	public void testCobrarComandaActualEstadoPagado() {
		TPV tpv = new TPV("a");
		TarjetaMonedero t = new TarjetaMonedero(CREDENCIAL_INICIALIZACION, 1);
		Producto p = new Producto("a","b","c",3,0.01);
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().addVendible(p, 1);
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO); 
		 
		tpv.cobrarComandaActual(t, CREDENCIAL_DESCONTAR_SALDO);
	}  
	
	@Test(expected=IllegalStateException.class)
	public void testCobrarComandaActualEstadoCerrado() {
		TPV tpv = new TPV("a");
		TarjetaMonedero t = new TarjetaMonedero(CREDENCIAL_INICIALIZACION, 1);
		Producto p = new Producto("a","b","c",3,0.01);
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().addVendible(p, 1);
		tpv.getComandaActual().setEstado(Comanda.Estado.CERRADO); 
		 
		tpv.cobrarComandaActual(t, CREDENCIAL_DESCONTAR_SALDO);
	}  
	
	@Test(expected=IllegalStateException.class)
	public void testCobrarComandaActualEstadoAnulado() {
		TPV tpv = new TPV("a");
		TarjetaMonedero t = new TarjetaMonedero(CREDENCIAL_INICIALIZACION, 1);
		Producto p = new Producto("a","b","c",3,0.01);
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().addVendible(p, 1);
		tpv.getComandaActual().setEstado(Comanda.Estado.ANULADO); 
		 
		tpv.cobrarComandaActual(t, CREDENCIAL_DESCONTAR_SALDO);
	}  
	
	@Test(expected=IllegalArgumentException.class)
	public void testCobrarComandaActualTarjetaNull() {
		TPV tpv = new TPV("a");
		Producto p = new Producto("a","b","c",3,0.01);
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().addVendible(p, 1);
		
		tpv.cobrarComandaActual(null, CREDENCIAL_DESCONTAR_SALDO);
	}   
	
	@Test(expected=IllegalArgumentException.class)
	public void testCobrarComandaActualCredencialNull() {
		TPV tpv = new TPV("a");
		TarjetaMonedero t = new TarjetaMonedero(CREDENCIAL_INICIALIZACION, 1);
		Producto p = new Producto("a","b","c",3,0.01);
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().addVendible(p, 1);
		
		tpv.cobrarComandaActual(t, null);
	}   
	 
	@Test
	public void testCerrarComandaActualValido() {
		TPV tpv = new TPV("a");
		 
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.cerrarComandaActual();
		
		assertEquals(Comanda.Estado.CERRADO, tpv.getComandaActual().getEstado());
	}

	@Test(expected=IllegalStateException.class) 
	public void testCerrarComandaActualNoExiste() {
		TPV tpv = new TPV("a");
		
		tpv.cerrarComandaActual();
	}
	
	@Test(expected=IllegalStateException.class) 
	public void testCerrarComandaEstadoAbierto() {
		TPV tpv = new TPV("a");
		 
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.ABIERTO);
		tpv.cerrarComandaActual();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testCerrarComandaEstadoCerrado() {
		TPV tpv = new TPV("a");
		 
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.getComandaActual().setEstado(Comanda.Estado.CERRADO);
		tpv.cerrarComandaActual();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testCerrarComandaEstadoAnulado() {
		TPV tpv = new TPV("a");
		 
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.ANULADO);
		tpv.cerrarComandaActual();
	}

	@Test
	public void testAnularComandaActualValido() {
		TPV tpv = new TPV("a");
		 
		tpv.newComandaLocal("a");
		tpv.anularComandaActual();
		
		assertEquals(Comanda.Estado.ANULADO, tpv.getComandaActual().getEstado());
	}
	
	@Test(expected=IllegalStateException.class) 
	public void testanularComandaActualNoExiste() {
		TPV tpv = new TPV("a");
		
		tpv.anularComandaActual();
	}


	@Test(expected=IllegalStateException.class)
	public void testAnularComandaActualEstadoPagado() { 
		TPV tpv = new TPV("a");
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.anularComandaActual();
	}
	

	@Test(expected=IllegalStateException.class)
	public void testAnularComandaActualEstadoCerrado() { 
		TPV tpv = new TPV("a");
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.getComandaActual().setEstado(Comanda.Estado.CERRADO);

		tpv.anularComandaActual();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testAnularComandaActualEstadoAnulado() { 
		TPV tpv = new TPV("a");
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.ANULADO);

		tpv.anularComandaActual();
	}



	@Test
	public void testAddComandasPagadasValido() {
		TPV tpv = new TPV("a"); 
		
		tpv.newComandaLocal("a"); 
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.addComandasPagadas();
		
		assertTrue(tpv.getComandasPagadas().contains(tpv.getComandaActual()));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testAddComandasPagadasComandaNull() {
		TPV tpv = new TPV("a");
		
		tpv.addComandasPagadas();
	}
	 
	@Test(expected=IllegalStateException.class)
	public void testAddComandasPagadasEstadoAbierto() {
		TPV tpv = new TPV("a");
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.ABIERTO);
		tpv.addComandasPagadas();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testAddComandasPagadasEstadoCerrado() {
		TPV tpv = new TPV("a");
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.CERRADO);
		tpv.addComandasPagadas();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testAddComandasPagadasEstadoAnulado() {
		TPV tpv = new TPV("a");
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.ANULADO);
		tpv.addComandasPagadas();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testAddComandasCerradasValido() {
		TPV tpv = new TPV("a");
		 
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.getComandaActual().setEstado(Comanda.Estado.ANULADO);
		tpv.addComandasCerradas();
		
		assertTrue(tpv.getComandasCerradas().contains(tpv.getComandaActual()));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testAddComandasCerradasComandaNull() {
		TPV tpv = new TPV("a");
		
		tpv.addComandasCerradas();
	}

	@Test(expected=IllegalStateException.class)
	public void testAddComandasCerradasEstadoAbierto() {
		TPV tpv = new TPV("a");
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.ABIERTO);
		tpv.addComandasCerradas();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testAddComandasCerradasEstadoPagado() {
		TPV tpv = new TPV("a");
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.addComandasCerradas();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testAddComandasCerradasEstadoAnulado() {
		TPV tpv = new TPV("a");
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.ANULADO);
		tpv.addComandasCerradas();
	}
 
	@Test
	public void testAddComandasAnuladasValido() {
		TPV tpv = new TPV("a");
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.ANULADO);
		tpv.addComandasAnuladas();
		
		assertTrue(tpv.getComandasAnuladas().contains(tpv.getComandaActual()));
	} 
	
	@Test(expected=IllegalStateException.class)
	public void testAddComandasAnuladasComandaNull() {
		TPV tpv = new TPV("a");
		
		tpv.addComandasAnuladas();
	}


	@Test(expected=IllegalStateException.class)
	public void testAddComandasAnuladasEstadoAbierto() {
		TPV tpv = new TPV("a");
		 
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.ABIERTO);
		tpv.addComandasAnuladas();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testAddComandasAnuladasEstadoPagado() {
		TPV tpv = new TPV("a");
		 
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.addComandasAnuladas();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testAddComandasAnuladasEstadoCerrado() {
		TPV tpv = new TPV("a");
		 
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.getComandaActual().setEstado(Comanda.Estado.CERRADO);
		tpv.addComandasAnuladas();
	}


	@Test
	public void actualizarAlmacenValido() {
		TPV tpv = new TPV("a");
		TarjetaMonedero t = new TarjetaMonedero(CREDENCIAL_INICIALIZACION, 1);
		Producto p = new Producto("a","b","c",1,0.01);
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().addVendible(p, 1);	
		tpv.cobrarComandaActual(t, CREDENCIAL_DESCONTAR_SALDO);
		
		assertEquals(0, p.getStock());
		
	}
	
	@Test(expected=IllegalStateException.class)
	public void testActualizarAlmacenEstadoAbierto() {
		TPV tpv = new TPV("a"); 
		
		tpv.newComandaLocal("a");
		tpv.actualizarAlmacen();
	} 
	
	@Test(expected=IllegalStateException.class)
	public void testActualizarAlmacenEstadoCerrado() {
		TPV tpv = new TPV("a"); 
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.getComandaActual().setEstado(Comanda.Estado.CERRADO);
		tpv.actualizarAlmacen();
	} 
	
	@Test(expected=IllegalStateException.class)
	public void testActualizarAlmacenEstadoAnulado() {
		TPV tpv = new TPV("a"); 
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.ANULADO);
		tpv.actualizarAlmacen();
	} 

	@Test
	public void testGetImporteValido() {
		TPV tpv = new TPV("a");
		Producto p = new Producto("a","b","c",3,0.01);
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().addVendible(p, 2);	
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.addComandasPagadas();
		
		assertEquals(0.02, tpv.getImporte(), 0.001);
	}  

	@Test
	public void testGetComandasDeHoyEstadoPagado() {
		TPV tpv = new TPV("a"); 
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.addComandasPagadas();
		
		assertEquals(tpv.getComandasPagadas(), tpv.getComandasDeHoy(Comanda.Estado.PAGADO));
	} 
	
	@Test
	public void testGetComandasDeHoyEstadoCerrado() {
		TPV tpv = new TPV("a");
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.getComandaActual().setEstado(Comanda.Estado.CERRADO);
		tpv.addComandasCerradas(); 
		
		assertEquals(tpv.getComandasCerradas(), tpv.getComandasDeHoy(Comanda.Estado.CERRADO));
	}
	
	@Test
	public void testGetComandasDeHoyEstadoAnulado() {
		TPV tpv = new TPV("a");
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.ANULADO);
		tpv.addComandasAnuladas();
		
		assertEquals(tpv.getComandasAnuladas(), tpv.getComandasDeHoy(Comanda.Estado.ANULADO));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetComandasDeHoyEstadoNull() {
		TPV tpv = new TPV("a"); 
		
		tpv.getComandasDeHoy(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetComandasDeHoyEstadoAbierto() {
		TPV tpv = new TPV("a");
		
		tpv.getComandasDeHoy(Comanda.Estado.ABIERTO);
	}
	
	@Test
	public void testGetCodigo() {
		TPV tpv = new TPV("a");
		
		assertEquals("a", tpv.getCodigo());
	}
	
	@Test
	public void testGetComandaActual() {
		TPV tpv = new TPV("a");
		
		tpv.newComandaLocal("a");
		assertNotNull(tpv.getComandaActual());
	}
	
	@Test
	public void testGetComandasCerradas() {
		TPV tpv = new TPV("a");
		
		tpv.newComandaLocal("a");
		tpv.getComandaActual().setEstado(Comanda.Estado.PAGADO);
		tpv.getComandaActual().setEstado(Comanda.Estado.CERRADO);
		tpv.addComandasCerradas();
		
		assertTrue(tpv.getComandasCerradas().contains(tpv.getComandaActual()));
	}
	
	
	
	
}


