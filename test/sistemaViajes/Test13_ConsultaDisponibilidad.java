package sistemaViajes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test13_ConsultaDisponibilidad {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @Before
    public void setUp() {
        s.inicializarSistema();
    }

    @Test
    public void consultaDisponibilidadError01() {
        int[][] matriz = new int[26][6];
        retorno = s.consultaDisponibilidad(matriz, 0, Clase.EJECUTIVA);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.consultaDisponibilidad(matriz, -1, Clase.EJECUTIVA);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void consultaDisponibilidadOk() {
        // Matriz 26 filas x 6 columnas (A-F), todo ocupado
        int[][] matriz = new int[26][6];
        for (int fila = 0; fila < 26; fila++) {
            for (int col = 0; col < 6; col++) {
                matriz[fila][col] = 1;
            }
        }
        // Liberamos fila 4 (indice 3), columnas A-E (0-4)
        for (int col = 0; col <= 4; col++) {
            matriz[3][col] = 0;
        }
        // Fila 4 (Ejecutiva), cantidad 3 -> A4-B4-C4|B4-C4-D4|C4-D4-E4
        retorno = s.consultaDisponibilidad(matriz, 3, Clase.EJECUTIVA);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(3, retorno.getValorEntero());
        assertEquals("A4-B4-C4|B4-C4-D4|C4-D4-E4", retorno.getValorString());
    }
}
