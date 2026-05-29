package sistemaViajes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test09_AbrirVuelo {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @Before
    public void setUp() { //HACER NUEVOS TESTS CUANDO ESTE PRONTO REALIZAR RESERVA Y REALIZAR CHECK-IN.
        s.inicializarSistema();
        s.registrarAeropuerto("MVD", "Carrasco");
        s.registrarAeropuerto("EZE", "Ministro Pistarini");
        s.registrarVuelo("MVD", "EZE", "LA100", 150, 200);
        s.registrarVuelo("MVD", "EZE", "LA200", 100, 300);
    }

    @Test
    public void abrirVueloOk() {
        retorno = s.abrirVuelo("LA100");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = s.abrirVuelo("LA200");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void abrirVueloError01() {
        retorno = s.abrirVuelo("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.abrirVuelo(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.abrirVuelo("   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void abrirVueloError02() {
        retorno = s.abrirVuelo("XX999");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void abrirVueloError03() {
        
        s.abrirVuelo("LA100");
        retorno = s.abrirVuelo("LA100");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}

