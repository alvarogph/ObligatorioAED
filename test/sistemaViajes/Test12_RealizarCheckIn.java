package sistemaViajes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test12_RealizarCheckIn {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @Before
    public void setUp() {
        s.inicializarSistema();
        s.registrarAeropuerto("MVD", "Carrasco");
        s.registrarAeropuerto("EZE", "Ministro Pistarini");
        s.registrarVuelo("MVD", "EZE", "LA100", 2, 200);
        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);
        s.registrarPasajero("6.430.147-9", "Nicolas", 30, Categoria.ESTANDAR);
        s.registrarPasajero("935.457-7", "Maria", 82, Categoria.PLATINO);
        s.realizarReserva("LA100", "3.335.321-2");
        s.realizarReserva("LA100", "6.430.147-9");
        s.abrirVuelo("LA100");
    }

    @Test
    public void realizarCheckInOk() {
        retorno = s.realizarCheckIn("LA100", "3.335.321-2");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = s.realizarCheckIn("LA100", "6.430.147-9");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void realizarCheckInVerificaEstado() {
        s.realizarCheckIn("LA100", "3.335.321-2");
        retorno = s.obtenerInformacionDeVuelo("LA100");
        assertEquals("MVD:EZE;LA100;2;200;Abierto;2;1", retorno.getValorString());
    }

    @Test
    public void realizarCheckInError01() {
        retorno = s.realizarCheckIn("", "3.335.321-2");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.realizarCheckIn(null, "3.335.321-2");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.realizarCheckIn("LA100", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.realizarCheckIn("LA100", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.realizarCheckIn("   ", "3.335.321-2");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.realizarCheckIn("LA100", "   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void realizarCheckInError02() {
        retorno = s.realizarCheckIn("LA100", "123456");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = s.realizarCheckIn("LA100", "0.335.321-2");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void realizarCheckInError03() {
        retorno = s.realizarCheckIn("XX999", "3.335.321-2");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    public void realizarCheckInError04() {
        retorno = s.realizarCheckIn("LA100", "1.234.567-8");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    public void realizarCheckInError05() {
        // Vuelo en estado PROGRAMADO no acepta check-in
        s.registrarVuelo("MVD", "EZE", "LA200", 10, 200);
        s.realizarReserva("LA200", "3.335.321-2");
        retorno = s.realizarCheckIn("LA200", "3.335.321-2");
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
    }

    @Test
    public void realizarCheckInError06() {
        // Pasajero sin reserva
        retorno = s.realizarCheckIn("LA100", "935.457-7");
        assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado());
    }

    @Test
    public void realizarCheckInError07() {
        // Pasajero que ya hizo check-in
        s.realizarCheckIn("LA100", "3.335.321-2");
        retorno = s.realizarCheckIn("LA100", "3.335.321-2");
        assertEquals(Retorno.Resultado.ERROR_7, retorno.getResultado());
    }

    @Test
    public void realizarCheckInError08() {
        // Capacidad 2, ambos hacen check-in, tercero no puede
        s.realizarReserva("LA100", "935.457-7");
        s.realizarCheckIn("LA100", "3.335.321-2");
        s.realizarCheckIn("LA100", "6.430.147-9");
        retorno = s.realizarCheckIn("LA100", "935.457-7");
        assertEquals(Retorno.Resultado.ERROR_8, retorno.getResultado());
    }

    // TESTS CÁTEDRA
//     @Before
//    public void setUp() {
//        s.inicializarSistema();
//        s.registrarAeropuerto("MVD", "Aeropuerto de Carrasco");
//        s.registrarAeropuerto("EZE", "Ezeiza");
//        s.registrarVuelo("MVD", "EZE", "AR123", 1, 230);
//        s.registrarPasajero("1.111.111-1", "Ana", 25, Categoria.FRECUENTE);
//        s.registrarPasajero("2.222.222-2", "Bruno", 40, Categoria.PLATINO);
//        s.registrarPasajero("3.333.333-3", "Carlos", 30, Categoria.ESTANDAR);
//    }
//
//    @Test
//    public void realizarCheckInOk() {
//        s.realizarReserva("AR123", "1.111.111-1");
//        s.abrirVuelo("AR123");
//
//        retorno = s.realizarCheckIn("AR123", "1.111.111-1");
//
//        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
//        assertEquals("MVD:EZE;AR123;1;230;Abierto;1;1", s.obtenerInformacionDeVuelo("AR123").getValorString());
//    }
//
//    @Test
//    public void realizarCheckInErroresBasicos() {
//        assertEquals(Retorno.Resultado.ERROR_1, s.realizarCheckIn(null, "1.111.111-1").getResultado());
//        assertEquals(Retorno.Resultado.ERROR_1, s.realizarCheckIn("AR123", "").getResultado());
//        assertEquals(Retorno.Resultado.ERROR_2, s.realizarCheckIn("AR123", "1.11.111-1").getResultado());
//        assertEquals(Retorno.Resultado.ERROR_3, s.realizarCheckIn("NO_EXISTE", "1.111.111-1").getResultado());
//        assertEquals(Retorno.Resultado.ERROR_4, s.realizarCheckIn("AR123", "3.444.333-3").getResultado());
//        assertEquals(Retorno.Resultado.ERROR_5, s.realizarCheckIn("AR123", "3.333.333-3").getResultado());
//        s.abrirVuelo("AR123");
//        assertEquals(Retorno.Resultado.ERROR_6, s.realizarCheckIn("AR123", "3.333.333-3").getResultado());
//        s.realizarReserva("AR123", "1.111.111-1");
//        s.realizarCheckIn("AR123", "1.111.111-1");
//        assertEquals(Retorno.Resultado.ERROR_7, s.realizarCheckIn("AR123", "1.111.111-1").getResultado());
//
//    }
}
