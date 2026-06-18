package sistemaViajes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test11_RealizarReserva {

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
        s.registrarPasajero("1.000.000-9", "P9", 20, Categoria.ESTANDAR);
    }

    @Test
    public void realizarReservaOk() {
        retorno = s.realizarReserva("LA100", "3.335.321-2");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = s.realizarReserva("LA100", "6.430.147-9");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        // Vuelo abierto también acepta reservas
        s.abrirVuelo("LA100");
        retorno = s.realizarReserva("LA100", "935.457-7");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void realizarReservaError01() {
        retorno = s.realizarReserva("", "3.335.321-2");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.realizarReserva(null, "3.335.321-2");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.realizarReserva("LA100", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.realizarReserva("LA100", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.realizarReserva("   ", "3.335.321-2");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.realizarReserva("LA100", "   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void realizarReservaError02() {
        retorno = s.realizarReserva("LA100", "123456");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = s.realizarReserva("LA100", "0.335.321-2");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void realizarReservaError03() {
        retorno = s.realizarReserva("XX999", "3.335.321-2");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    public void realizarReservaError04() {
        retorno = s.realizarReserva("LA100", "1.234.567-8");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    public void realizarReservaError05() {
        s.abrirVuelo("LA100");
        s.cerrarVuelo("LA100");
        retorno = s.realizarReserva("LA100", "3.335.321-2");
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
    }

    @Test
    public void realizarReservaError06() {
        s.realizarReserva("LA100", "3.335.321-2");
        retorno = s.realizarReserva("LA100", "3.335.321-2");
        assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado());
    }

    @Test
    public void realizarReservaError07() {
        // Capacidad 10, limite overbooking = ceil(10 * 1.1) = 11
        s.registrarPasajero("1.000.000-1", "P1", 20, Categoria.ESTANDAR);
        s.registrarPasajero("1.000.000-2", "P2", 20, Categoria.ESTANDAR);
        s.registrarPasajero("1.000.000-3", "P3", 20, Categoria.ESTANDAR);
        s.registrarPasajero("1.000.000-4", "P4", 20, Categoria.ESTANDAR);
        s.registrarPasajero("1.000.000-5", "P5", 20, Categoria.ESTANDAR);
        s.registrarPasajero("1.000.000-6", "P6", 20, Categoria.ESTANDAR);
        s.registrarPasajero("1.000.000-7", "P7", 20, Categoria.ESTANDAR);
        s.registrarPasajero("1.000.000-8", "P8", 20, Categoria.ESTANDAR);
        s.realizarReserva("LA100", "3.335.321-2");
        s.realizarReserva("LA100", "6.430.147-9");
        s.realizarReserva("LA100", "935.457-7");
        s.realizarReserva("LA100", "1.000.000-1");
        s.realizarReserva("LA100", "1.000.000-2");
        s.realizarReserva("LA100", "1.000.000-3");
        s.realizarReserva("LA100", "1.000.000-4");
        s.realizarReserva("LA100", "1.000.000-5");
        s.realizarReserva("LA100", "1.000.000-6");
        s.realizarReserva("LA100", "1.000.000-7");
        s.realizarReserva("LA100", "1.000.000-8");
        retorno = s.realizarReserva("LA100", "1.000.000-9");
        assertEquals(Retorno.Resultado.ERROR_7, retorno.getResultado());

    }

    // TESTS CÁTEDRA
    
//     @Before
//    public void setUp() {
//        s.inicializarSistema();
//        s.registrarAeropuerto("MVD", "Aeropuerto de Carrasco");
//        s.registrarAeropuerto("EZE", "Ezeiza");
//        s.registrarVuelo("MVD", "EZE", "AR123", 2, 230);
//        s.registrarPasajero("1.111.111-1", "Ana", 25, Categoria.FRECUENTE);
//        s.registrarPasajero("2.222.222-2", "Bruno", 40, Categoria.PLATINO);
//        s.registrarPasajero("3.333.333-3", "Carlos", 30, Categoria.ESTANDAR);
//        s.registrarPasajero("935.457-7", "Diana", 50, Categoria.ESPORADICO);
//    }
//
//    @Test
//    public void realizarReservaOkEnProgramadoYAbierto() {
//        retorno = s.realizarReserva("AR123", "1.111.111-1");
//        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
//
//        s.abrirVuelo("AR123");
//        retorno = s.realizarReserva("AR123", "2.222.222-2");
//        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
//    }
//
//    @Test
//    public void realizarReservaErroresBasicos() {
//        assertEquals(Retorno.Resultado.ERROR_1, s.realizarReserva(null, "1.111.111-1").getResultado());
//        assertEquals(Retorno.Resultado.ERROR_1, s.realizarReserva("AR123", "").getResultado());
//        assertEquals(Retorno.Resultado.ERROR_2, s.realizarReserva("AR123", "1.11.111-1").getResultado());
//        assertEquals(Retorno.Resultado.ERROR_3, s.realizarReserva("NO_EXISTE", "1.111.111-1").getResultado());
//        assertEquals(Retorno.Resultado.ERROR_4, s.realizarReserva("AR123", "4.444.444-4").getResultado());
//    }
}
