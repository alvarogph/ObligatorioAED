package tads.tadpila;

public interface IPila<T> {
    
    /*
    PRE: -
    POS: Retotrna true si la pila tiene 0 elementos, false en caso contrario
    */
    public boolean esVacia();
    
    public void apilar(T n);
    
    public void desapilar();
    
    /*
    PRE: La pila no está vacía
    POS: Retorna el elemento que está en la cima de la pila
    */
    public T top();
    
    public void vaciar();
    
    public int cantElementos();
    

    
    // Solamente a modo de desarrollo, no es una operación válida
    public void mostrar();
}
