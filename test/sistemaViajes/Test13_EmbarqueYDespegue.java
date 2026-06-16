package sistemaViajes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test13_EmbarqueYDespegue {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @Before
    public void setUp() { //EN ESTA IMPLEMENTACIÓN NO INCLUÍ LOS TESTS DE LA CÁTEDRA PORQUE RECIBÍAN CODIGO DE VUELO Y NO DE AEROPUERTO 
                          // COMO SOLICITABA LA LETRA.
        
        
        
        s.inicializarSistema();
        s.registrarAeropuerto("MVD", "Carrasco");
        s.registrarAeropuerto("EZE", "Ministro Pistarini");
        s.registrarVuelo("MVD", "EZE", "LA100", 10, 200);
        s.registrarVuelo("MVD", "EZE", "LA200", 10, 200);
        s.abrirVuelo("LA100");
        s.cerrarVuelo("LA100");
        s.abrirVuelo("LA200");
        s.cerrarVuelo("LA200");
    }

    @Test
    public void embarqueYDespegueOk() {
        retorno = s.embarqueYDespegueDeVuelo("MVD");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("LA100", retorno.getValorString());
        assertEquals(1, retorno.getValorEntero());

        retorno = s.embarqueYDespegueDeVuelo("MVD");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("LA200", retorno.getValorString());
        assertEquals(0, retorno.getValorEntero());
    }

    @Test
    public void embarqueYDespegueError01() {
        retorno = s.embarqueYDespegueDeVuelo("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.embarqueYDespegueDeVuelo(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.embarqueYDespegueDeVuelo("   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void embarqueYDespegueError02() {
        retorno = s.embarqueYDespegueDeVuelo("GRU");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void embarqueYDespegueError03() {
        retorno = s.embarqueYDespegueDeVuelo("EZE");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
    
  
}
