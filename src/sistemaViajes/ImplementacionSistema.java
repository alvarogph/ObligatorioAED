package sistemaViajes;

//Álvaro Germán Pérez Hernández - 234980
import dominio.Pasajero;
import dominio.Aeropuerto;
import tads.tadlista.ListaNodosSimple;
import tads.Nodo;

//testGitsdsds
public class ImplementacionSistema implements Sistema {

    private ListaNodosSimple<Pasajero> listaPasajerSimple;
    private ListaNodosSimple<Aeropuerto> listaAeropuertosSimple;

    @Override
    public Retorno inicializarSistema() {

        listaPasajerSimple = new ListaNodosSimple();
        listaAeropuertosSimple = new ListaNodosSimple();
        return Retorno.ok();
    }

    @Override
    public Retorno registrarPasajero(String cedula, String nombre, int edad, Categoria categoria) {

        Pasajero pasajero = new Pasajero(cedula, nombre, edad, categoria);

        if (cedula == null || cedula.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || categoria == null) {
            return Retorno.error1();
        }

        if (!cedula.matches("^([1-9]\\.\\d{3}\\.\\d{3}-\\d|\\d{3}\\.\\d{3}-\\d)$")) {
            return Retorno.error2();
        }

        if (edad < 0) {
            return Retorno.error3();
        }

        if (listaPasajerSimple.existeElemento(pasajero)) {
            return Retorno.error4();
        } else {

            listaPasajerSimple.agregarOrd(pasajero);
            return Retorno.ok("Pasajero registrado exitosamente");
        }
    }

    @Override
    public Retorno buscarPasajero(String cedula) {

        if (!cedula.matches("^([1-9]\\.\\d{3}\\.\\d{3}-\\d|\\d{3}\\.\\d{3}-\\d)$")) {
            return Retorno.error1();
        }

        for (int i = 0; i < listaPasajerSimple.cantidadElementos(); i++) {

            Pasajero p = listaPasajerSimple.obtenerElementoIndice(i);

            if (p.getCedula().equals(cedula)) {
                return Retorno.ok(p.toString());
            }
        }

        return Retorno.error2();

    }

    @Override
    public Retorno listarPasajerosAscendente() {

        if (listaPasajerSimple.esVacia()) {
            return Retorno.ok("");
        }

        String pasajeros = "";

        for (int i = 0; i < listaPasajerSimple.cantidadElementos(); i++) {

            Pasajero p = listaPasajerSimple.obtenerElementoIndice(i);

            if (i > 0) {
                pasajeros += "|";
            }

            pasajeros += p.toString();
        }

        return Retorno.ok(pasajeros);
    }

    @Override
    public Retorno listarPasajerosDescendente() {

        if (listaPasajerSimple.esVacia()) {
            return Retorno.ok("");
        }

        String pasajeros = "";

        for (int i = listaPasajerSimple.cantidadElementos() - 1; i >= 0; i--) {

            Pasajero p = listaPasajerSimple.obtenerElementoIndice(i);

            if (i < listaPasajerSimple.cantidadElementos() - 1) {
                pasajeros += "|";
            }

            pasajeros += p.toString();
        }

        return Retorno.ok(pasajeros);
    }

    @Override
    public Retorno listarPasajerosPorCategoría(Categoria unaCategoria) {

        String pasajeros = "";

        for (int i = 0; i < listaPasajerSimple.cantidadElementos(); i++) {

            Pasajero p = listaPasajerSimple.obtenerElementoIndice(i);

            if (p.getCategoria().equals(unaCategoria)) {

                if (!pasajeros.equals("")) {
                    pasajeros += "|";
                }

                pasajeros += p.toString();
            }
        }

        if (pasajeros.equals("")) {
            return Retorno.ok("No existen pasajeros para la categoria seleccionada");
        }

        return Retorno.ok(pasajeros);
    }

    @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {

        Aeropuerto aeropuerto = new Aeropuerto(codigo, nombre);

        if (codigo == null || codigo.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            return Retorno.error1();
        }

        if (listaAeropuertosSimple.existeElemento(aeropuerto)) {
            return Retorno.error2();
        } else {

            listaAeropuertosSimple.agregarInicio(aeropuerto);
            return Retorno.ok("Aeropuerto registrado exitosamente");
        }
    }

    @Override
    public Retorno obtenerAeropuerto(String codigo) {

        if (codigo == null || codigo.trim().isEmpty()) {
            return Retorno.error1();
        }

        for (int i = 0; i < listaAeropuertosSimple.cantidadElementos(); i++) {

            Aeropuerto aeropuerto = listaAeropuertosSimple.obtenerElementoIndice(i);

            if (aeropuerto.getCodigo().equals(codigo)) {

                return Retorno.ok(aeropuerto.toString());
            }
        }

        return Retorno.error2();
    }

    @Override
    public Retorno registrarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, int capacidad, int costoEnDolares) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno obtenerInformacionDeVuelo(String codigoDeVuelo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno abrirVuelo(String codigoDeVuelo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno cerrarVuelo(String codigoDeVuelo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno realizarReserva(String codigoDeVuelo, String cedula) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno realizarCheckIn(String codigoDeVuelo, String cedula) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno embarqueYDespegueDeVuelo(String codigoAeropuerto) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno consultaDisponibilidad(int[][] matriz, int cantidad, Clase unaClase) {
        return Retorno.noImplementada();
    }

}
