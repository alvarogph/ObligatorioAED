package sistemaViajes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test10_CerrarVuelo {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @Before
    public void setUp() {
        s.inicializarSistema();
        s.registrarAeropuerto("MVD", "Carrasco");
        s.registrarAeropuerto("EZE", "Ministro Pistarini");
        s.registrarVuelo("MVD", "EZE", "LA100", 10, 200);
        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);
        s.registrarPasajero("6.430.147-9", "Nicolas", 30, Categoria.ESTANDAR);
        s.registrarPasajero("935.457-7", "Maria", 82, Categoria.PLATINO);
        s.realizarReserva("LA100", "3.335.321-2");
        s.realizarReserva("LA100", "6.430.147-9");
        s.realizarReserva("LA100", "935.457-7");
        s.abrirVuelo("LA100");
        s.realizarCheckIn("LA100", "3.335.321-2");
        s.realizarCheckIn("LA100", "6.430.147-9");
    }

    @Test
    public void cerrarVueloOk() {
        retorno = s.cerrarVuelo("LA100");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        // Juan y Nicolas hicieron check-in, ordenados por cedula ascendente
        assertEquals("3.335.321-2;Juan;45;Esporádico|6.430.147-9;Nicolas;30;Estándar", retorno.getValorString());
        // Maria reservo pero no confirmo
        assertEquals(1, retorno.getValorEntero());
    }

    @Test
    public void cerrarVueloError01() {
        retorno = s.cerrarVuelo("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.cerrarVuelo(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.cerrarVuelo("   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void cerrarVueloError02() {
        retorno = s.cerrarVuelo("XX999");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void cerrarVueloError03() {
        // Vuelo en estado PROGRAMADO
        s.registrarVuelo("MVD", "EZE", "LA200", 10, 200);
        retorno = s.cerrarVuelo("LA200");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        // Vuelo ya cerrado
        s.cerrarVuelo("LA100");
        retorno = s.cerrarVuelo("LA100");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
    
    // TESTS CÁTEDRA
    
//     @Before
//    public void setUp() {
//        s.inicializarSistema();
//        s.registrarAeropuerto("MVD", "Aeropuerto de Carrasco");
//        s.registrarAeropuerto("EZE", "Ezeiza");
//        s.registrarVuelo("MVD", "EZE", "AR123", 3, 230);
//        s.registrarPasajero("3.333.333-3", "Carlos", 30, Categoria.ESTANDAR);
//        s.registrarPasajero("1.111.111-1", "Ana", 25, Categoria.FRECUENTE);
//        s.registrarPasajero("2.222.222-2", "Bruno", 40, Categoria.PLATINO);
//    }
//
//    @Test
//    public void cerrarVueloOkRetornaConfirmadosOrdenadosYNoConfirmados() {
//        s.realizarReserva("AR123", "3.333.333-3");
//        s.realizarReserva("AR123", "1.111.111-1");
//        s.realizarReserva("AR123", "2.222.222-2");
//        s.abrirVuelo("AR123");
//        s.realizarCheckIn("AR123", "3.333.333-3");
//        s.realizarCheckIn("AR123", "1.111.111-1");
//
//        retorno = s.cerrarVuelo("AR123");
//
//        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
//        assertEquals("1.111.111-1;Ana;25;Frecuente|3.333.333-3;Carlos;30;Estándar", retorno.getValorString());
//        assertEquals(1, retorno.getValorEntero());
//    }
//
//    @Test
//    public void cerrarVueloErrores() {
//        assertEquals(Retorno.Resultado.ERROR_1, s.cerrarVuelo(null).getResultado());
//        assertEquals(Retorno.Resultado.ERROR_1, s.cerrarVuelo("").getResultado());
//        assertEquals(Retorno.Resultado.ERROR_2, s.cerrarVuelo("NO_EXISTE").getResultado());
//        assertEquals(Retorno.Resultado.ERROR_3, s.cerrarVuelo("AR123").getResultado());
//    }
}
