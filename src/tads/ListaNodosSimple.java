/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author alvar
 */
public class ListaNodosSimple<T extends Comparable> implements IListaSimple<T> {

    private Nodo<T> inicio;
    private Nodo fin;
    private int cantidad;

    public ListaNodosSimple() {
        this.inicio = null;
        this.fin = null;
        this.cantidad = 0;
    }

    public boolean esVacia() {
        return this.cantidad == 0;
    }

    @Override
    public void agregarInicio(T n) {
        Nodo<T> nuevo = new Nodo(n);
        nuevo.setSiguiente(this.inicio);
        inicio = nuevo;

        if (this.esVacia()) {
            this.fin = nuevo;
        }

        this.cantidad++;
    }

    public void agregarFinal(T n) {

        if (this.esVacia()) {
            agregarInicio(n);
        } else {
            Nodo<T> nuevo = new Nodo(n);
            nuevo.setSiguiente(null);

            this.fin.setSiguiente(nuevo);
            this.fin = nuevo;

            this.cantidad++;
        }
    }

    @Override
    public void mostrar() {
        Nodo<T> aux = this.inicio;

        while (aux != null) {
            System.out.print(aux.getDato() + " ");
            aux = aux.getSiguiente();
        }

        System.out.println("");
    }

    @Override
    public void vaciar() {
        this.inicio = null;
        this.fin = null;
        this.cantidad = 0;
    }

    @Override
    public boolean existeElemento(T t) {

        Nodo aux = inicio;
        boolean existe = false;

        while (aux != null && !existe) {
            if (aux.getDato().equals(t)) {
                existe = true;
            }

            aux = aux.getSiguiente();
        }
        return existe;
    }

    @Override
    public T obtenerElementoIndice(int indice) {

        Nodo<T> aux = inicio;
        int contador = 0;

        while (aux != null) {
            if (contador == indice) {
                return aux.getDato();
            }
            aux = aux.getSiguiente();
            contador++;
        }
        return null;
    }

    @Override
    public void eliminarInicio() {
        if (!this.esVacia()) {
            Nodo<T> aBorrar = this.inicio;
            this.inicio = this.inicio.getSiguiente();
            aBorrar.setSiguiente(null);
            this.cantidad--;
        }
    }

    @Override
    public void eliminarFinal() {
        if (!this.esVacia()) {

            Nodo<T> aux = this.inicio;

            if (aux.getSiguiente() == null) { // Tiene solo un nodo
                this.vaciar();
            } else {
                while (aux.getSiguiente().getSiguiente() != null) {
                    aux = aux.getSiguiente();
                }

                this.fin = aux;
                aux.setSiguiente(null);
                this.cantidad--;
            }

        }

    }

    public void insertarEnPos(int pos, T elemento) {

        int cantidadElementos = cantidad;

        if (pos < 0 || pos > cantidadElementos) {
            return;
        }

        if (pos == 0) {
            agregarInicio(elemento);
        } else if (pos == cantidadElementos) {
            agregarFinal(elemento);
        } else {

            Nodo<T> nuevo = new Nodo<T>(elemento);
            Nodo<T> aux = inicio;
            int contador = 0;

            while (contador < pos - 1) {
                aux = aux.getSiguiente();
                contador++;
            }
            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
        }
    }

    public void eliminarEnPos(int pos) {

        int cantidadElementos = cantidad;

        if (pos < 0 || pos >= cantidadElementos) {
            System.out.println("La posicion no es valida");
            return;
        }

        if (pos == 0) {
            eliminarInicio();
        } else if (pos == cantidadElementos - 1) {
            eliminarFinal();

        } else {

            Nodo<T> aux = inicio;
            int contador = 0;

            while (contador < pos - 1) {
                aux = aux.getSiguiente();
                contador++;
            }
            aux.setSiguiente(aux.getSiguiente().getSiguiente());
        }
    }

    public void eliminarElemento(T t) {

        if (esVacia()) {
            return;
        }

        if (inicio.getDato().equals(t)) {
            eliminarInicio();
            return;
        }

        Nodo<T> aux = inicio;
        while (aux.getSiguiente() != null && !aux.getSiguiente().getDato().equals(t)) {
            aux = aux.getSiguiente();
        }
        aux.setSiguiente(aux.getSiguiente().getSiguiente());
    }

    public void agregarOrd(T n) {
        // Si es el primer elemento o el que quiero insertar es más chico que el primer elemento
        if (this.esVacia() || n.compareTo(this.inicio.getDato()) <= 0) {
            this.agregarInicio(n);
            // Si el elemento que quiero insertar es mayor al último lo pongo al final
        } else if (n.compareTo(this.fin.getDato()) >= 0) {
            this.agregarFinal(n);
            // Lo debo agregar en medio de la lista
        } else {
            Nodo<T> nuevo = new Nodo(n);
            Nodo<T> aux = this.inicio;

            while (aux.getSiguiente().getDato().compareTo(n) < 0) {
                aux = aux.getSiguiente();
            }

            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);

            this.cantidad++;
        }
    }

    @Override
    public int cantidadElementos() {
        return this.cantidad;
    }
    
    public T obtenerElemento(T t) {

    Nodo aux = inicio;

    while (aux != null) {

        if (aux.getDato().equals(t)) {
            return t; 
        }

        aux = aux.getSiguiente();
    }

    return null;
}

}
