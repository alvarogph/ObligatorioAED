package tads.tadcola;

public interface ICola<T> {

    public boolean esVacia();

    public void encolar(T n);

    public void desencolar();

    public T front();

    public void vaciar();

    public int cantElementos();

    public void mostrar();
}
