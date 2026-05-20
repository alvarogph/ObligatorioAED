package sistemaViajes;

import sistemaViajes.Retorno;
import sistemaViajes.ImplementacionSistema;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sistemaViajes.Sistema;

public class Test05_RegistrarAeropuerto {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @Before
    public void setUp() {
        s.inicializarSistema();
    }

    @Test
    public void registrarAeropuertoOk() {

        retorno = s.registrarAeropuerto("MVD", "Carrasco");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarAeropuerto("PTY", "Tocumen");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarAeropuerto("EZE", "Ministro Pistarini");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void registrarAeropuertoError01() {
        retorno = s.registrarAeropuerto("", "Carrasco");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarAeropuerto("MVD", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarAeropuerto(null, "Carrasco");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarAeropuerto("MVD", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarAeropuerto("   ", "Carrasco");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarAeropuerto("MVD", "   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void registrarAeropuertoError02() {

        s.registrarAeropuerto("MVD", "Carrasco");
        s.registrarAeropuerto("PTY", "Tocumen");
        s.registrarAeropuerto("EZE", "Ministro Pistarini");

        retorno = s.registrarAeropuerto("MVD", "Otro nombre");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarAeropuerto("PTY", "Otro");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarAeropuerto("EZE", "Nombre distinto");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
}
