package sistemaViajes;

//Álvaro Germán Pérez Hernández - 234980
import dominio.Pasajero;
import dominio.Aeropuerto;
import dominio.Vuelo;
import tads.tadlista.ListaNodosSimple;
import tads.Nodo;
import tads.tadpila.Pila;

public class ImplementacionSistema implements Sistema {

    private ListaNodosSimple<Pasajero> listaPasajeros;
    private ListaNodosSimple<Aeropuerto> listaAeropuertos;
    private ListaNodosSimple<Vuelo> listaVuelos;
    private ListaNodosSimple<Pasajero> listaPlatino;
    private ListaNodosSimple<Pasajero> listaFrecuente;
    private ListaNodosSimple<Pasajero> listaEstandar;
    private ListaNodosSimple<Pasajero> listaEsporadico;

    @Override
    public Retorno inicializarSistema() {

        listaPasajeros = new ListaNodosSimple<>();
        listaAeropuertos = new ListaNodosSimple<>();
        listaVuelos = new ListaNodosSimple<>();
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
            return Retorno.error2(); //Se uso IA GPT para consultar la forma de validación de una cédula en formato Uruguayo.
        }

        if (edad < 0) {
            return Retorno.error3();
        }

        if (listaPasajeros.existeElemento(pasajero)) {
            return Retorno.error4();
        } else {

            listaPasajeros.agregarOrd(pasajero);

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

        if (cedula == null || !cedula.matches("^([1-9]\\.\\d{3}\\.\\d{3}-\\d|\\d{3}\\.\\d{3}-\\d)$")) {
            return Retorno.error1();
        }
        Nodo<Pasajero> aux = listaPasajeros.getInicio();
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
    public Retorno listarPasajerosDescendente() {

        Pila<Pasajero> pila = new Pila<>();
        Nodo<Pasajero> aux = listaPasajeros.getInicio();
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

        ListaNodosSimple<Pasajero> listaPasajerosCat;

        if (categoria == Categoria.PLATINO) {
            listaPasajerosCat = listaPlatino;
        } else if (categoria == Categoria.FRECUENTE) {
            listaPasajerosCat = listaFrecuente;
        } else if (categoria == Categoria.ESTANDAR) {
            listaPasajerosCat = listaEstandar;
        } else {
            listaPasajerosCat = listaEsporadico;
        }
        String pasajeros = "";
        Nodo<Pasajero> aux = listaPasajerosCat.getInicio();
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

        if (listaAeropuertos.existeElemento(aeropuerto)) {
            return Retorno.error2();
        } else {

            listaAeropuertos.agregarInicio(aeropuerto);
            return Retorno.ok("Aeropuerto registrado exitosamente");
        }
    }

    @Override
    public Retorno obtenerAeropuerto(String codigo) {

        if (codigo == null || codigo.trim().isEmpty()) {
            return Retorno.error1();
        }
        Aeropuerto aeropuerto = listaAeropuertos.obtenerElemento(new Aeropuerto(codigo, ""));
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
        if (!listaAeropuertos.existeElemento(aeropuertoOrigen)) {
            return Retorno.error3();
        }

        Aeropuerto aeropuertoDestino = new Aeropuerto(codigoAeropuertoDestino, "");
        if (!listaAeropuertos.existeElemento(aeropuertoDestino)) {
            return Retorno.error4();
        }

        Vuelo vuelo = new Vuelo(codigoAeropuertoOrigen, codigoAeropuertoDestino, codigoDeVuelo, capacidad, costoEnDolares);
        if (listaVuelos.existeElemento(vuelo)) {
            return Retorno.error5();
        }

        listaVuelos.agregarOrd(vuelo);
        return Retorno.ok("Vuelo registrado exitosamente");

    }

    @Override
    public Retorno obtenerInformacionDeVuelo(String codigoDeVuelo) {
        if (codigoDeVuelo == null || codigoDeVuelo.trim().isEmpty()) {
            return Retorno.error1();
        }
        Nodo<Vuelo> aux = listaVuelos.getInicio();
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

        Nodo<Vuelo> aux = listaVuelos.getInicio();

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
        Nodo<Vuelo> aux = listaVuelos.getInicio();
        while (aux != null) {
            Vuelo vuelo = aux.getDato();
            if (vuelo.getCodigoDeVuelo().equals(codigoDeVuelo)) {
                if (vuelo.getEstado() != Estado.ABIERTO) {
                    return Retorno.error3();
                }
                vuelo.setEstado(Estado.CERRADO);
                Aeropuerto aeropuertoOrigen = listaAeropuertos.obtenerElemento(new Aeropuerto(vuelo.getCodigoAeropuertoOrigen(), ""));
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
        Vuelo vuelo = listaVuelos.obtenerElemento(vueloBuscado);
        if (vuelo == null) {
            return Retorno.error3();
        }
        Pasajero pasajeroBuscado = new Pasajero(cedula, "", 0, Categoria.ESPORADICO);
        Pasajero pasajero = listaPasajeros.obtenerElemento(pasajeroBuscado);
        if (pasajero == null) {
            return Retorno.error4();
        }
        if (vuelo.getEstado() != Estado.PROGRAMADO && vuelo.getEstado() != Estado.ABIERTO) {
            return Retorno.error5();
        }
        if (vuelo.tieneReserva(pasajero) || vuelo.tieneCheckIn(pasajero)) {
            return Retorno.error6();
        }
        int limiteReservas = (int) Math.ceil(vuelo.getCapacidad() * 1.1); // Esta funcion se la pedi a la IA Claude

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
        Vuelo vuelo = listaVuelos.obtenerElemento(vueloBuscado);
        if (vuelo == null) {
            return Retorno.error3();
        }
        Pasajero pasajeroBuscado = new Pasajero(cedula, "", 0, Categoria.ESPORADICO);
        Pasajero pasajero = listaPasajeros.obtenerElemento(pasajeroBuscado);
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

        if (codigoAeropuerto == null || codigoAeropuerto.trim().isEmpty()) {
            return Retorno.error1();
        }

        Aeropuerto aeropuertoBuscado = new Aeropuerto(codigoAeropuerto, "");
        Aeropuerto aeropuerto = listaAeropuertos.obtenerElemento(aeropuertoBuscado);

        if (aeropuerto == null) {
            return Retorno.error2();
        }

        if (aeropuerto.getVuelosPendientes().cantElementos() == 0) {
            return Retorno.error3();
        }

        Vuelo vuelo = aeropuerto.getVuelosPendientes().front();
        vuelo.setEstado(Estado.FINALIZADO);
        aeropuerto.getVuelosPendientes().desencolar();

        String codigoVuelo = vuelo.getCodigoDeVuelo();
        int vuelosCola = aeropuerto.getVuelosPendientes().cantElementos();

        return Retorno.ok(codigoVuelo, vuelosCola);
    }

    @Override
    public Retorno consultaDisponibilidad(int[][] matriz, int cantidad, Clase unaClase) {

        if (cantidad <= 0) {
            return Retorno.error1();
        }
        int filaDesde;
        int filaHasta;

        if (unaClase == Clase.PRIMERA) {
            filaDesde = 0;
            filaHasta = 2;
        } else if (unaClase == Clase.EJECUTIVA) {
            filaDesde = 3;
            filaHasta = 9;
        } else {
            filaDesde = 10;
            filaHasta = 25;
        }

        int columnas = matriz[0].length;
        char[] letrasColumna = {'A', 'B', 'C', 'D', 'E', 'F'};
        String resultado = "";
        int opcionesDispo = 0;

        for (int i = filaDesde; i <= filaHasta; i++) {
            int libresSeguidos = 0;
            for (int j = 0; j < columnas; j++) {
                if (matriz[i][j] == 0) {
                    libresSeguidos++;
                } else {
                    libresSeguidos = 0;
                }
                if (libresSeguidos >= cantidad) {
                    String opcion = "";
                    for (int k = j - cantidad + 1; k <= j; k++) {
                        char letra = letrasColumna[k];
                        int numeroFila = i + 1;
                        String asiento = letra + "" + numeroFila;
                        if (!opcion.isEmpty()) {
                            opcion += "-";
                        }
                        opcion += asiento;
                    }
                    if (!resultado.isEmpty()) {
                        resultado += "|";
                    }
                    resultado += opcion;
                    opcionesDispo++;
                }
            }
        }
        
        return Retorno.ok(resultado, opcionesDispo);
    }

}
