/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.Objects;
import tads.tadcola.Cola;

/**
 *
 * @author alvar
 */
public class Aeropuerto implements Comparable<Aeropuerto> {

    private String codigo;
    private String nombre;
    private Cola<Vuelo> vuelosPendientes;

    public Aeropuerto(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.vuelosPendientes = new Cola<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cola<Vuelo> getVuelosPendientes() {
        return vuelosPendientes;
    }

    @Override
    public int compareTo(Aeropuerto t) {
        return this.codigo.compareTo(t.codigo);
    }

    @Override
    public boolean equals(Object obj) { //Este equals, utilizado en todas las clases se lo solicité a la IA.
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aeropuerto other = (Aeropuerto) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

    @Override
    public String toString() {
        return this.codigo + ";" + this.nombre;
    }

}
