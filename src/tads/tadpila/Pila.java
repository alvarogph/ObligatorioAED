package tads.tadpila;

import tads.Nodo;

public class Pila<T> implements IPila<T> {
    
    private Nodo<T> pila;
    private int cantidad;

    public Pila() {
        this.pila = null;
        this.cantidad = 0;
    }

    @Override
    public boolean esVacia() {  
         return this.cantidad == 0;
    }

    @Override
    public void apilar(T n) {
        Nodo<T> nuevo = new Nodo(n);
        nuevo.setSiguiente(this.pila);
        pila = nuevo;
        this.cantidad++;
    }

    @Override
    public void desapilar() {
        if(!this.esVacia()){
            if(this.cantidad == 1){
                this.vaciar();
            }else{
                Nodo<T> aBorrar = this.pila;
                this.pila = this.pila.getSiguiente();
                aBorrar.setSiguiente(null);
                this.cantidad--;
            }
        }
    }

    @Override
    public T top() {
        return this.pila.getDato();
    }
    
    @Override
    public void vaciar() {
        this.pila = null;
        this.cantidad = 0;
    }

    @Override
    public int cantElementos() {
        return this.cantidad;
    }

    @Override
    public void mostrar() {
        Nodo<T> aux = this.pila;
        
        while(aux != null){
            System.out.print(aux.getDato() + " ");
            aux = aux.getSiguiente();
        }
        
        System.out.println("");
    }
    
}
