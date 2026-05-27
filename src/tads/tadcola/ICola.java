package tads.tadcola;

public interface ICola<T> {
    
    /*
    PRE: -
    POS: Retotrna true si la cola tiene 0 elementos, false en caso contrario
    */
    public boolean esVacia();
    
    public void encolar(T n);
    
    public void desencolar();
    
    public T front();
    
    public void vaciar();
    
    public int cantElementos();
    
    
    // Solamente a modo de desarrollo, no es una operación válida
    public void mostrar();
}
