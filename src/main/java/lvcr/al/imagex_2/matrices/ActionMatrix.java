package lvcr.al.imagex_2.matrices;

public abstract class ActionMatrix<T> {

    public T negative;
    /**
     * representa el valor 0 o neutro de dicho tipo T por ejemplo de Integer seria 0 
     * de una clase Rectangulo podría ser Rectangulo(0,0)
     */
    public T neutro;
    
    public abstract T add(T x, T y) ;
    public abstract T sub(T x, T y);
    public abstract T mul(T x, T y);
    public abstract T div(T x, T y) ;
    public abstract T addOne(T x);
    public abstract T restOne(T x);
    public abstract T negative(T x);
    public abstract T inverse(T x);
}
