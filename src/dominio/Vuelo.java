/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.Objects;
import sistemaViajes.Estado;

/**
 *
 * @author alvar
 */
public class Vuelo implements Comparable<Vuelo> {

    private String codigoAeropuertoOrigen;
    private String codigoAeropuertoDestino;
    private String codigoDeVuelo;
    private int capacidad;
    private int costoEnDolares;
    private Estado estado;
    private int cantidadDeReservas;
    private int cantidadDePasajerosConfirmados;

    public Vuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, int capacidad, int costoEnDolares) {
        this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
        this.codigoAeropuertoDestino = codigoAeropuertoDestino;
        this.codigoDeVuelo = codigoDeVuelo;
        this.capacidad = capacidad;
        this.costoEnDolares = costoEnDolares;
        this.estado = estado.PROGRAMADO;
        this.cantidadDeReservas = 0;
        this.cantidadDePasajerosConfirmados = 0;
    }

    public String getCodigoAeropuertoOrigen() {
        return codigoAeropuertoOrigen;
    }

    public void setCodigoAeropuertoOrigen(String codigoAeropuertoOrigen) {
        this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
    }

    public String getCodigoAeropuertoDestino() {
        return codigoAeropuertoDestino;
    }

    public void setCodigoAeropuertoDestino(String codigoAeropuertoDestino) {
        this.codigoAeropuertoDestino = codigoAeropuertoDestino;
    }

    public String getCodigoDeVuelo() {
        return codigoDeVuelo;
    }

    public void setCodigoDeVuelo(String codigoDeVuelo) {
        this.codigoDeVuelo = codigoDeVuelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCostoEnDolares() {
        return costoEnDolares;
    }

    public void setCostoEnDolares(int costoEnDolares) {
        this.costoEnDolares = costoEnDolares;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int compareTo(Vuelo o) {
        return this.codigoDeVuelo.compareTo(o.codigoDeVuelo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vuelo other = (Vuelo) obj;
        return Objects.equals(this.codigoDeVuelo, other.codigoDeVuelo);
    }

    @Override
    public String toString() {

        return codigoAeropuertoOrigen + ";"
                + codigoAeropuertoDestino + ";"
                + codigoDeVuelo + ";"
                + capacidad + ";"
                + costoEnDolares + ";"
                + estado + ";"
                + cantidadDeReservas + ";"
                + cantidadDePasajerosConfirmados;
    }

}
