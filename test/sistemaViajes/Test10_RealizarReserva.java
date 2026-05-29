package sistemaViajes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test10_RealizarReserva {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @Before
    public void setUp() { //HACER NUEVOS TESTS CUANDO ESTE PRONTO REALIZAR RESERVA Y REALIZAR CHECK-IN.
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
}
