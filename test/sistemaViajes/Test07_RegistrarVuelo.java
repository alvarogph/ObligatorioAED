package sistemaViajes;

import sistemaViajes.Retorno;
import sistemaViajes.ImplementacionSistema;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import sistemaViajes.Sistema;

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

        // Mismo origen/destino, distinto código
        retorno = s.registrarVuelo("MVD", "EZE", "LA300", 200, 180);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void registrarVueloError01_parametrosIntInvalidos() {
        // capacidad <= 0
        retorno = s.registrarVuelo("MVD", "EZE", "LA100", 0, 200);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "EZE", "LA100", -1, 200);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        // costoEnDolares <= 0
        retorno = s.registrarVuelo("MVD", "EZE", "LA100", 150, 0);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "EZE", "LA100", 150, -1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        // ambos <= 0
        retorno = s.registrarVuelo("MVD", "EZE", "LA100", 0, 0);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void registrarVueloError02_parametrosStringInvalidos() {
        // codigoAeropuertoOrigen
        retorno = s.registrarVuelo("", "EZE", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarVuelo(null, "EZE", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarVuelo("   ", "EZE", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        // codigoAeropuertoDestino
        retorno = s.registrarVuelo("MVD", "", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", null, "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "   ", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        // codigoDeVuelo
        retorno = s.registrarVuelo("MVD", "EZE", "", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "EZE", null, 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarVuelo("MVD", "EZE", "   ", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void registrarVueloError03_aeropuertoOrigenNoExiste() {
        retorno = s.registrarVuelo("GRU", "EZE", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarVuelo("XXX", "MVD", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    public void registrarVueloError04_aeropuertoDestinoNoExiste() {
        retorno = s.registrarVuelo("MVD", "GRU", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

        retorno = s.registrarVuelo("EZE", "XXX", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    public void registrarVueloError04_amboAeropuertosNoExisten() {
        // Origen se valida primero, debe retornar ERROR_3
        retorno = s.registrarVuelo("GRU", "SCL", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    public void registrarVueloError05_codigoVueloDuplicado() {
        s.registrarVuelo("MVD", "EZE", "LA100", 150, 200);

        // Mismo código, mismos datos
        retorno = s.registrarVuelo("MVD", "EZE", "LA100", 150, 200);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());

        // Mismo código, distintos datos
        retorno = s.registrarVuelo("MVD", "EZE", "LA100", 200, 300);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());

        // Mismo código, distintos aeropuertos
        retorno = s.registrarVuelo("EZE", "PTY", "LA100", 100, 150);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
    }

}

