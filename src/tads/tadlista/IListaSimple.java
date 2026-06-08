/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tads.tadlista;

/**
 *
 * @author alvar
 */
public interface IListaSimple<T> {

    public void agregarInicio(T t);

   // public void mostrar();

    public int cantidadElementos();

    public boolean esVacia();

    public void vaciar();

    public boolean existeElemento(T t);

    public T obtenerElementoIndice(int indice);

    public void agregarFinal(T t);

    public void eliminarInicio();

    public void eliminarFinal();

    public void eliminarElemento(T t);

    public void agregarOrd(T n);

}
