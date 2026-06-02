package sistemaViajes;

//Álvaro Germán Pérez Hernández - 234980
import dominio.Pasajero;
import dominio.Aeropuerto;
import dominio.Vuelo;
import tads.tadlista.ListaNodosSimple;
import tads.Nodo;
import tads.tadpila.Pila;

public class ImplementacionSistema implements Sistema {

    private ListaNodosSimple<Pasajero> listaPasajerSimple;
    private ListaNodosSimple<Aeropuerto> listaAeropuertosSimple;
    private ListaNodosSimple<Vuelo> listaVuelosSimple;
    private ListaNodosSimple<Pasajero> listaPlatino;
    private ListaNodosSimple<Pasajero> listaFrecuente;
    private ListaNodosSimple<Pasajero> listaEstandar;
    private ListaNodosSimple<Pasajero> listaEsporadico;

    @Override
    public Retorno inicializarSistema() {

        listaPasajerSimple = new ListaNodosSimple();
        listaAeropuertosSimple = new ListaNodosSimple();
        listaVuelosSimple = new ListaNodosSimple();
        listaPlatino = new ListaNodosSimple<>();
        listaFrecuente = new ListaNodosSimple<>();
        listaEstandar = new ListaNodosSimple<>();
        listaEsporadico = new ListaNodosSimple<>();

        return Retorno.ok();
    }

    @Override
    public Retorno registrarPasajero(String cedula, String nombre, int edad, Categoria categoria) {

        Pasajero pasajero = new Pasajero(cedula, nombre, edad, categoria);

        if (cedula == null || cedula.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || categoria == null) {
            return Retorno.error1();
        }

        if (!cedula.matches("^([1-9]\\.\\d{3}\\.\\d{3}-\\d|\\d{3}\\.\\d{3}-\\d)$")) {
            return Retorno.error2(); //Se uso IA GPT para hacer la validación de la cédula al formato Uruguayo.
        }

        if (edad < 0) {
            return Retorno.error3();
        }

        if (listaPasajerSimple.existeElemento(pasajero)) {
            return Retorno.error4();
        } else {

            listaPasajerSimple.agregarOrd(pasajero);

            if (categoria == Categoria.PLATINO) {
                listaPlatino.agregarOrd(pasajero);
            } else if (categoria == Categoria.FRECUENTE) {
                listaFrecuente.agregarOrd(pasajero);
            } else if (categoria == Categoria.ESTANDAR) {
                listaEstandar.agregarOrd(pasajero);
            } else if (categoria == Categoria.ESPORADICO) {
                listaEsporadico.agregarOrd(pasajero);
            }
            return Retorno.ok("Pasajero registrado exitosamente");
        }
    }

    @Override
    public Retorno buscarPasajero(String cedula) {

        if (!cedula.matches("^([1-9]\\.\\d{3}\\.\\d{3}-\\d|\\d{3}\\.\\d{3}-\\d)$")) {
            return Retorno.error1();
        }
        Nodo<Pasajero> aux = listaPasajerSimple.getInicio();
        while (aux != null) {
            if (aux.getDato().getCedula().equals(cedula)) {
                return Retorno.ok(aux.getDato().toString());
            }
            aux = aux.getSiguiente();
        }
        return Retorno.error2();
    }

    @Override
    public Retorno listarPasajerosAscendente() {

        String pasajeros = "";
        Nodo<Pasajero> aux = listaPasajerSimple.getInicio();
        while (aux != null) {
            if (!pasajeros.isEmpty()) {
                pasajeros += "|";
            }
            pasajeros += aux.getDato().toString();
            aux = aux.getSiguiente();
        }
        return Retorno.ok(pasajeros);
    }

    @Override
    public Retorno listarPasajerosDescendente() {

        Pila<Pasajero> pila = new Pila<>();
        Nodo<Pasajero> aux = listaPasajerSimple.getInicio();
        while (aux != null) {
            pila.apilar(aux.getDato());
            aux = aux.getSiguiente();
        }
        String pasajeros = "";
        while (!pila.esVacia()) {
            if (!pasajeros.isEmpty()) {
                pasajeros += "|";
            }
            pasajeros += pila.top().toString();
            pila.desapilar();
        }
        return Retorno.ok(pasajeros);
    }

    @Override
    public Retorno listarPasajerosPorCategoría(Categoria categoria) {

        ListaNodosSimple<Pasajero> listaPasajeros;

        if (categoria == Categoria.PLATINO) {
            listaPasajeros = listaPlatino;
        } else if (categoria == Categoria.FRECUENTE) {
            listaPasajeros = listaFrecuente;
        } else if (categoria == Categoria.ESTANDAR) {
            listaPasajeros = listaEstandar;
        } else {
            listaPasajeros = listaEsporadico;
        }
        String pasajeros = "";
        Nodo<Pasajero> aux = listaPasajeros.getInicio();
        while (aux != null) {
            if (!pasajeros.isEmpty()) {
                pasajeros += "|";
            }
            pasajeros += aux.getDato().toString();
            aux = aux.getSiguiente();
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
        Aeropuerto aeropuerto = listaAeropuertosSimple.obtenerElemento(new Aeropuerto(codigo, ""));
        if (aeropuerto == null) {
            return Retorno.error2();
        }
        return Retorno.ok(aeropuerto.toString(), aeropuerto.getVuelosPendientes().cantElementos());
    }

    @Override
    public Retorno registrarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, int capacidad, int costoEnDolares) {

        if (capacidad <= 0 || costoEnDolares <= 0) {
            return Retorno.error1();
        }

        if (codigoAeropuertoOrigen == null || codigoAeropuertoOrigen.trim().isEmpty()
                || codigoAeropuertoDestino == null || codigoAeropuertoDestino.trim().isEmpty()
                || codigoDeVuelo == null || codigoDeVuelo.trim().isEmpty()) {
            return Retorno.error2();
        }

        Aeropuerto aeropuertoOrigen = new Aeropuerto(codigoAeropuertoOrigen, "");
        if (!listaAeropuertosSimple.existeElemento(aeropuertoOrigen)) {
            return Retorno.error3();
        }

        Aeropuerto aeropuertoDestino = new Aeropuerto(codigoAeropuertoDestino, "");
        if (!listaAeropuertosSimple.existeElemento(aeropuertoDestino)) {
            return Retorno.error4();
        }

        Vuelo vuelo = new Vuelo(codigoAeropuertoOrigen, codigoAeropuertoDestino, codigoDeVuelo, capacidad, costoEnDolares);
        if (listaVuelosSimple.existeElemento(vuelo)) {
            return Retorno.error5();
        }

        listaVuelosSimple.agregarOrd(vuelo);
        return Retorno.ok("Vuelo registrado exitosamente");

    }

    @Override
    public Retorno obtenerInformacionDeVuelo(String codigoDeVuelo) {
        if (codigoDeVuelo == null || codigoDeVuelo.trim().isEmpty()) {
            return Retorno.error1();
        }
        Nodo<Vuelo> aux = listaVuelosSimple.getInicio();
        while (aux != null) {
            if (aux.getDato().getCodigoDeVuelo().equals(codigoDeVuelo)) {
                return Retorno.ok(aux.getDato().toString());
            }
            aux = aux.getSiguiente();
        }
        return Retorno.error2();
    }

    @Override
    public Retorno abrirVuelo(String codigoDeVuelo) {

        if (codigoDeVuelo == null || codigoDeVuelo.trim().isEmpty()) {
            return Retorno.error1();
        }

        Nodo<Vuelo> aux = listaVuelosSimple.getInicio();

        while (aux != null) {
            if (aux.getDato().getCodigoDeVuelo().equals(codigoDeVuelo)) {

                if (aux.getDato().getEstado() != Estado.PROGRAMADO) {
                    return Retorno.error3();
                } else {

                    aux.getDato().setEstado(Estado.ABIERTO);
                    return Retorno.ok();
                }
            }

            aux = aux.getSiguiente();
        }

        return Retorno.error2();
    }

    @Override
    public Retorno cerrarVuelo(String codigoDeVuelo) {

        if (codigoDeVuelo == null || codigoDeVuelo.trim().isEmpty()) {
            return Retorno.error1();
        }
        Nodo<Vuelo> aux = listaVuelosSimple.getInicio();
        while (aux != null) {
            Vuelo vuelo = aux.getDato();
            if (vuelo.getCodigoDeVuelo().equals(codigoDeVuelo)) {
                if (vuelo.getEstado() != Estado.ABIERTO) {
                    return Retorno.error3();
                }
                vuelo.setEstado(Estado.CERRADO);
                Aeropuerto aeropuertoOrigen = listaAeropuertosSimple.obtenerElemento(new Aeropuerto(vuelo.getCodigoAeropuertoOrigen(), ""));
                aeropuertoOrigen.getVuelosPendientes().encolar(vuelo);
                String confirmados = "";
                Nodo<Pasajero> auxPas = vuelo.getPasajerosConfirmados().getInicio();
                while (auxPas != null) {
                    if (!confirmados.isEmpty()) {
                        confirmados += "|";
                    }
                    confirmados += auxPas.getDato().toString();
                    auxPas = auxPas.getSiguiente();
                }
                
                int sinConfirmar = vuelo.getPasajerosConReserva().cantidadElementos() - vuelo.getPasajerosConfirmados().cantidadElementos();
                
                return Retorno.ok(confirmados, sinConfirmar);
            }
            
            aux = aux.getSiguiente();
        }
        return Retorno.error2();
    }

    @Override
    public Retorno realizarReserva(String codigoDeVuelo, String cedula) {

        if (codigoDeVuelo == null || codigoDeVuelo.trim().isEmpty()
                || cedula == null || cedula.trim().isEmpty()) {
            return Retorno.error1();
        }
        if (!cedula.matches("^([1-9]\\.\\d{3}\\.\\d{3}-\\d|\\d{3}\\.\\d{3}-\\d)$")) {
            return Retorno.error2();
        }

        Vuelo vueloBuscado = new Vuelo("", "", codigoDeVuelo, 1, 1);
        Vuelo vuelo = listaVuelosSimple.obtenerElemento(vueloBuscado);
        if (vuelo == null) {
            return Retorno.error3();
        }
        Pasajero pasajeroBuscado = new Pasajero(cedula, "", 0, Categoria.ESPORADICO);
        Pasajero pasajero = listaPasajerSimple.obtenerElemento(pasajeroBuscado);
        if (pasajero == null) {
            return Retorno.error4();
        }
        if (vuelo.getEstado() != Estado.PROGRAMADO && vuelo.getEstado() != Estado.ABIERTO) {
            return Retorno.error5();
        }
        if (vuelo.tieneReserva(pasajero) || vuelo.tieneCheckIn(pasajero)) {
            return Retorno.error6();
        }
        int limiteReservas = (int) Math.ceil(vuelo.getCapacidad() * 1.1); // Esta funcion se la pedi a Claude.

        if (vuelo.getPasajerosConReserva().cantidadElementos() >= limiteReservas) {
            return Retorno.error7();
        }
        vuelo.getPasajerosConReserva().agregarOrd(pasajero);
        return Retorno.ok();
    }

    @Override
    public Retorno realizarCheckIn(String codigoDeVuelo, String cedula) {

        if (codigoDeVuelo == null || codigoDeVuelo.trim().isEmpty()
                || cedula == null || cedula.trim().isEmpty()) {
            return Retorno.error1();
        }
        if (!cedula.matches("^([1-9]\\.\\d{3}\\.\\d{3}-\\d|\\d{3}\\.\\d{3}-\\d)$")) {
            return Retorno.error2();
        }
        Vuelo vueloBuscado = new Vuelo("", "", codigoDeVuelo, 1, 1);
        Vuelo vuelo = listaVuelosSimple.obtenerElemento(vueloBuscado);
        if (vuelo == null) {
            return Retorno.error3();
        }
        Pasajero pasajeroBuscado = new Pasajero(cedula, "", 0, Categoria.ESPORADICO);
        Pasajero pasajero = listaPasajerSimple.obtenerElemento(pasajeroBuscado);
        if (pasajero == null) {
            return Retorno.error4();
        }
        if (vuelo.getEstado() != Estado.ABIERTO) {
            return Retorno.error5();
        }

        if (!vuelo.tieneReserva(pasajero)) {
            return Retorno.error6();
        }

        if (vuelo.tieneCheckIn(pasajero)) {
            return Retorno.error7();
        }

        if (vuelo.getPasajerosConfirmados().cantidadElementos() >= vuelo.getCapacidad()) {
            return Retorno.error8();
        }

        vuelo.getPasajerosConfirmados().agregarOrd(pasajero);
        return Retorno.ok();
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
