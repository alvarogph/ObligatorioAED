package sistemaViajes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test06_ObtenerAeropuerto {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @Before
    public void setUp() {
        s.inicializarSistema();
        s.registrarAeropuerto("MVD", "Carrasco");
        s.registrarAeropuerto("EZE", "Ministro Pistarini");
    }

    @Test
    public void obtenerAeropuertoOk() {
        retorno = s.obtenerAeropuerto("MVD");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("MVD;Carrasco", retorno.getValorString());
        assertEquals(0, retorno.getValorEntero());

        retorno = s.obtenerAeropuerto("EZE");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("EZE;Ministro Pistarini", retorno.getValorString());
        assertEquals(0, retorno.getValorEntero());
    }

    public void obtenerAeropuertoOkConVuelosEnCola() {
        s.registrarVuelo("MVD", "EZE", "LA100", 10, 200);
        s.registrarVuelo("MVD", "EZE", "LA200", 10, 200);
        s.abrirVuelo("LA100");
        s.cerrarVuelo("LA100");
        s.abrirVuelo("LA200");
        s.cerrarVuelo("LA200");
        retorno = s.obtenerAeropuerto("MVD");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("MVD;Carrasco", retorno.getValorString());
        assertEquals(2, retorno.getValorEntero());
    }

    @Test
    public void obtenerAeropuertoError01() {
        retorno = s.obtenerAeropuerto("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.obtenerAeropuerto(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.obtenerAeropuerto("   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void obtenerAeropuertoError02() {
        retorno = s.obtenerAeropuerto("GRU");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = s.obtenerAeropuerto("XXX");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

}
