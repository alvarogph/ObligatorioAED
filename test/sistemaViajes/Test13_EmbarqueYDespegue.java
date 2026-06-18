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
    
    //TESTS CÁTEDRA
    
//    @Before
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
//    public void embarqueYDespegueOk() {
//        s.realizarReserva("AR123", "1.111.111-1");        
//        s.realizarReserva("AR123", "2.222.222-2");
//        
//        s.abrirVuelo("AR123");
//        s.realizarCheckIn("AR123", "1.111.111-1");    
//        
//        s.cerrarVuelo("AR123");
//        retorno = s.embarqueYDespegueDeVuelo("MVD");
//        
//        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
//        assertEquals("MVD:EZE;AR123;1;230;Finalizado;2;1", s.obtenerInformacionDeVuelo("AR123").getValorString());
//    }
//
//    @Test
//    public void embarqueYDespegueErroresBasicos() {
//        assertEquals(Retorno.Resultado.ERROR_1, s.embarqueYDespegueDeVuelo(null).getResultado());
//        assertEquals(Retorno.Resultado.ERROR_1, s.embarqueYDespegueDeVuelo("").getResultado());
//        assertEquals(Retorno.Resultado.ERROR_1, s.embarqueYDespegueDeVuelo("    ").getResultado());
//        assertEquals(Retorno.Resultado.ERROR_2, s.embarqueYDespegueDeVuelo("NO_EXISTE").getResultado());
//        assertEquals(Retorno.Resultado.ERROR_3, s.embarqueYDespegueDeVuelo("EZE").getResultado());     
//    }
//
//    
//  
}
