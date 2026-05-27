package sistemaViajes;

import sistemaViajes.Retorno;
import sistemaViajes.ImplementacionSistema;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sistemaViajes.Sistema;

public class Test08_ObtenerVuelo {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

   @Before
    public void setUp() {
        s.inicializarSistema();
        s.registrarAeropuerto("MVD", "Carrasco");
        s.registrarAeropuerto("EZE", "Ministro Pistarini");
        s.registrarVuelo("MVD", "EZE", "LA100", 150, 200);
    }

    @Test
    public void obtenerInformacionDeVueloOk() {
        retorno = s.obtenerInformacionDeVuelo("LA100");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("MVD;EZE;LA100;150;200;PROGRAMADO;0;0", retorno.getValorString());
    }

    @Test
    public void obtenerInformacionDeVueloError01() {
        retorno = s.obtenerInformacionDeVuelo("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.obtenerInformacionDeVuelo(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.obtenerInformacionDeVuelo("   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void obtenerInformacionDeVueloError02() {
        retorno = s.obtenerInformacionDeVuelo("XX999");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
}
