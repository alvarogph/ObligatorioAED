package tads.tadpila;

public interface IPila<T> {

    public boolean esVacia();

    public void apilar(T n);

    public void desapilar();

    public T top();

    public void vaciar();

    public int cantElementos();

    //public void mostrar();
}
