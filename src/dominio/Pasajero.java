/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.Objects;
import sistemaViajes.Categoria;

/**
 *
 * @author alvar
 */
public class Pasajero implements Comparable<Pasajero> {

    private String cedula;
    private String nombre;
    private int edad;
    private Categoria categoria;

    public Pasajero(String cedula, String nombre, int edad, Categoria categoria) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.categoria = categoria;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int compareTo(Pasajero pasajero) {
        String cedula
                = this.cedula.replace(".", "")
                        .replace("-", "");

        String cedula2
                = pasajero.cedula.replace(".", "")
                        .replace("-", "");

        int cedula1Nro = Integer.parseInt(cedula);
        int cedula2Nro = Integer.parseInt(cedula2);

        return Integer.compare(cedula1Nro, cedula2Nro);
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
        final Pasajero other = (Pasajero) obj;
        return Objects.equals(this.cedula, other.cedula);
    }

    @Override
    public String toString() {
        return cedula + ";" + nombre + ";" + edad + ";" + categoria.getTexto();
    }

}
