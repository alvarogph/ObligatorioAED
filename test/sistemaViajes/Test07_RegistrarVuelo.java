package sistemaViajes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test07_RegistrarVuelo {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @Before
    public void setUp() {
        
        s.inicializarSistema();

        s.registrarAeropuerto("MVD", "Carrasco");
        s.registrarAeropuerto("EZE", "Ministro Pistarini");
        s.registrarAeropuerto("PTY", "Tocumen");

    }

    @Test
    public void registrarVueloOk() {
        retorno = s.registrarVuelo("MVD", "EZE", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = s.registrarVuelo("EZE", "PTY", "LA200", 80, 350);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = s.registrarVuelo("MVD", "EZE", "LA300", 200, 180);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void registrarVueloError01() {
        retorno = s.registrarVuelo("MVD", "EZE", "LA100", 0, 200);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.registrarVuelo("MVD", "EZE", "LA100", -1, 200);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.registrarVuelo("MVD", "EZE", "LA100", 150, 0);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.registrarVuelo("MVD", "EZE", "LA100", 150, -1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.registrarVuelo("MVD", "EZE", "LA100", 0, 0);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void registrarVueloError02() {
        retorno = s.registrarVuelo("", "EZE", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = s.registrarVuelo(null, "EZE", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = s.registrarVuelo("   ", "EZE", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = s.registrarVuelo("MVD", "", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = s.registrarVuelo("MVD", null, "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = s.registrarVuelo("MVD", "   ", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = s.registrarVuelo("MVD", "EZE", "", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = s.registrarVuelo("MVD", "EZE", null, 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = s.registrarVuelo("MVD", "EZE", "   ", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void registrarVueloError03() {
        retorno = s.registrarVuelo("GRU", "EZE", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        retorno = s.registrarVuelo("XXX", "MVD", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        // Cuando ninguno existe, origen se valida primero
        retorno = s.registrarVuelo("GRU", "SCL", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    public void registrarVueloError04() {
        retorno = s.registrarVuelo("MVD", "GRU", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
        retorno = s.registrarVuelo("EZE", "XXX", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    public void registrarVueloError05() {
        s.registrarVuelo("MVD", "EZE", "LA100", 150, 200);
        retorno = s.registrarVuelo("MVD", "EZE", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
        retorno = s.registrarVuelo("MVD", "EZE", "LA100", 200, 300);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
        retorno = s.registrarVuelo("EZE", "PTY", "LA100", 100, 150);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
    }
}
