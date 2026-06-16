package sistemaViajes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test04_ListarPasajeros { //Este test fue hecho antes que se subieran los test provistos por la cátedra, 
                                        //por lo que no estan desagregados. Ascendente, Descendente y Por Categoría están aquí.

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @Before
    public void setUp() {
        s.inicializarSistema();
    }

    @Test
    public void listarPasajerosVacio() {
        retorno = s.listarPasajerosAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    public void listarPasajerosAscendenteSoloUnUsuario() {
        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);
        retorno = s.listarPasajerosAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("3.335.321-2;Juan;45;Esporádico", retorno.getValorString());
    }

    @Test
    public void listarPasajerosAscendenteIngresoOrdenado() {
        s.registrarPasajero("935.457-7", "Maria", 82, Categoria.PLATINO);
        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);
        s.registrarPasajero("6.430.147-9", "Nicolas", 0, Categoria.ESTANDAR);
        retorno = s.listarPasajerosAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("935.457-7;Maria;82;Platino|3.335.321-2;Juan;45;Esporádico|6.430.147-9;Nicolas;0;Estándar", retorno.getValorString());
    }

    @Test
    public void listarPasajerosAscendenteIngresoDesordenado() {
        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);
        s.registrarPasajero("6.430.147-9", "Nicolas", 0, Categoria.ESTANDAR);
        s.registrarPasajero("935.457-7", "Maria", 82, Categoria.PLATINO);
        retorno = s.listarPasajerosAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("935.457-7;Maria;82;Platino|3.335.321-2;Juan;45;Esporádico|6.430.147-9;Nicolas;0;Estándar", retorno.getValorString());
    }

    @Test
    public void listarPasajerosDescendenteSoloUnUsuario() {
        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);
        retorno = s.listarPasajerosAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("3.335.321-2;Juan;45;Esporádico", retorno.getValorString());
    }

    @Test
    public void listarPasajerosDescendenteIngresoOrdenado() {

        s.registrarPasajero("6.430.147-9", "Nicolas", 0, Categoria.ESTANDAR);
        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);
        s.registrarPasajero("935.457-7", "Maria", 82, Categoria.PLATINO);
        retorno = s.listarPasajerosDescendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("6.430.147-9;Nicolas;0;Estándar|3.335.321-2;Juan;45;Esporádico|935.457-7;Maria;82;Platino", retorno.getValorString());
    }

    @Test
    public void listarPasajerosDescendenteIngresoDesordenado() {
        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);
        s.registrarPasajero("6.430.147-9", "Nicolas", 0, Categoria.ESTANDAR);
        s.registrarPasajero("935.457-7", "Maria", 82, Categoria.PLATINO);
        retorno = s.listarPasajerosDescendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("6.430.147-9;Nicolas;0;Estándar|3.335.321-2;Juan;45;Esporádico|935.457-7;Maria;82;Platino", retorno.getValorString());
    }

    @Test
    public void listarPasajerosCategoriaUnUsuario() {
        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);
        retorno = s.listarPasajerosPorCategoría(Categoria.ESPORADICO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("3.335.321-2;Juan;45;Esporádico", retorno.getValorString());
    }

    @Test
    public void listarPasajerosCategoriaNoexiste() {
        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);
        s.registrarPasajero("6.430.147-9", "Nicolas", 0, Categoria.ESTANDAR);
        s.registrarPasajero("935.457-7", "Maria", 82, Categoria.PLATINO);

        retorno = s.listarPasajerosPorCategoría(Categoria.FRECUENTE);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    public void listarPasajerosCategoriaIngresoOrdenado() {

        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESTANDAR);
        s.registrarPasajero("6.430.147-9", "Nicolas", 0, Categoria.ESTANDAR);
        s.registrarPasajero("935.457-7", "Maria", 82, Categoria.PLATINO);
        retorno = s.listarPasajerosPorCategoría(Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("3.335.321-2;Juan;45;Estándar|6.430.147-9;Nicolas;0;Estándar", retorno.getValorString());
    }

    @Test
    public void listarPasajerosCategoriaIngresoDesordenado() {

        s.registrarPasajero("6.430.147-9", "Nicolas", 0, Categoria.ESTANDAR);
        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESTANDAR);
        s.registrarPasajero("935.457-7", "Maria", 82, Categoria.PLATINO);
        retorno = s.listarPasajerosPorCategoría(Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("3.335.321-2;Juan;45;Estándar|6.430.147-9;Nicolas;0;Estándar", retorno.getValorString());
    }
    
    // TESTS CÁTEDRA
    
//    @Test
//    public void listarPasajerosVacio() {
//        retorno = s.listarPasajerosAscendente();
//        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
//        assertEquals("", retorno.getValorString());
//    }
//
//    @Test
//    public void listarPasajerosAscendenteSoloUnUsuario() {
//        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);
//        retorno = s.listarPasajerosAscendente();
//        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
//        assertEquals("3.335.321-2;Juan;45;Esporádico", retorno.getValorString());
//    }
//
//    @Test
//    public void listarPasajerosAscendenteIngresoOrdenado() {
//        s.registrarPasajero("935.457-7", "Maria", 82, Categoria.PLATINO);
//        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);        
//        s.registrarPasajero("6.430.147-9", "Nicolas", 0, Categoria.ESTANDAR);
//        retorno = s.listarPasajerosAscendente();
//        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
//        assertEquals("935.457-7;Maria;82;Platino|3.335.321-2;Juan;45;Esporádico|6.430.147-9;Nicolas;0;Estándar", retorno.getValorString());
//    }
//
//    @Test
//    public void listarPasajerosAscendenteIngresoDesordenado() {
//        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);
//        s.registrarPasajero("6.430.147-9", "Nicolas", 0, Categoria.ESTANDAR);
//         s.registrarPasajero("935.457-7", "Maria", 82, Categoria.PLATINO);        
//        retorno = s.listarPasajerosAscendente();
//        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
//        assertEquals("935.457-7;Maria;82;Platino|3.335.321-2;Juan;45;Esporádico|6.430.147-9;Nicolas;0;Estándar", retorno.getValorString());
//    }
//    
//     @Test
//    public void listarPasajerosDescendenteSoloUnUsuario() {
//        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);
//        retorno = s.listarPasajerosDescendente();
//        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
//        assertEquals("3.335.321-2;Juan;45;Esporádico", retorno.getValorString());
//    }
//
//    @Test
//    public void listarPasajerosDescendenteIngresoOrdenado() {
//        s.registrarPasajero("6.430.147-9", "Nicolas", 0, Categoria.ESTANDAR);
//        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);        
//        s.registrarPasajero("935.457-7", "Maria", 82, Categoria.PLATINO);
//        retorno = s.listarPasajerosDescendente();
//        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
//        assertEquals("6.430.147-9;Nicolas;0;Estándar|3.335.321-2;Juan;45;Esporádico|935.457-7;Maria;82;Platino", retorno.getValorString());
//    }
//
//    @Test
//    public void listarPasajerosDescendenteIngresoDesordenado() {
//        s.registrarPasajero("3.335.321-2", "Juan", 45, Categoria.ESPORADICO);
//        s.registrarPasajero("6.430.147-9", "Nicolas", 0, Categoria.ESTANDAR);
//         s.registrarPasajero("935.457-7", "Maria", 82, Categoria.PLATINO);        
//        retorno = s.listarPasajerosDescendente();
//        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
//        assertEquals("6.430.147-9;Nicolas;0;Estándar|3.335.321-2;Juan;45;Esporádico|935.457-7;Maria;82;Platino", retorno.getValorString());
//    }
//    
//      @Test
//    public void listarCategoriaSinPasajerosRetornaVacio() {
//        retorno = s.listarPasajerosPorCategoría(Categoria.FRECUENTE);
//        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
//        assertEquals("", retorno.getValorString());
//    }
//
//    @Test
//    public void listarCategoriaFiltraYOrdenaPorCedulaAscendente() {
//        s.registrarPasajero("4.985.345-4", "Ana", 25, Categoria.FRECUENTE);
//        s.registrarPasajero("935.457-7", "Zoe", 40, Categoria.PLATINO);
//        s.registrarPasajero("1.345.345-4", "Alberto", 62, Categoria.FRECUENTE);
//
//        retorno = s.listarPasajerosPorCategoría(Categoria.FRECUENTE);
//
//        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
//        assertEquals("1.345.345-4;Alberto;62;Frecuente|4.985.345-4;Ana;25;Frecuente", retorno.getValorString());
//    }
}
