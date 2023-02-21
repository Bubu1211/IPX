package lvcr.al.imagex_2.matrices;

import java.util.ArrayList;

/**
 * @author César Ricardo Lazcano Valdez
 */
public class Matriz <T>{
    
    private int filas;
    private int columnas;
    private Object a[][];
    private String id;
    private T inicial;
//    private TipoMatriz tipo;
    private int colAmpliada;
    private static int id_char = 63;
    
    /**
     *
     * @param filas número de filas que tendrá la matriz
     * @param columnas número de columnas que tendrá la columna
     * @param inicial valor inicial o por default de los elementos de la lista
     */
    public Matriz(int filas, int columnas, T inicial) {
        this.filas = filas;
        this.columnas = columnas;
        this.a = new Object[filas][columnas];
        this.inicial = inicial;
        inicializar();
        this.id = (char) Matriz.id_char++ + "";
    }

    /**
     *
     * @param id identificador para la matriz
     * @param filas número de filas que contendrá la matriz
     * @param columnas número de columnas que contendrá la matriz
     * @param inicial valor inicial o por default para los elementos de la lista
     */
    public Matriz(String id, int filas, int columnas, T inicial) {
        this.filas = filas;
        this.columnas = columnas;
        this.a = new Object[filas][columnas];
        this.id = id;
        this.inicial = inicial;
        inicializar();
    }

    /**
     * Pone un valor en una determinada posición Las posiciones empiezan desde 0
     *
     * @param f fila donde se desea insertar el dato
     * @param c columna donde se desea insertar el dato
     * @param data dato o valor que desea insertar en la matriz
     */
    public void set(int f, int c, T data) {
        if (f >= 0 && f < this.filas) {
            if (c >= 0 && c < this.columnas) {
                this.a[f][c] = data;
            }
        }
    }

    public int getFilas() {
        return this.filas;
    }

    public int getColumnas() {
        return this.columnas;

    }

    public String getId() {
        return this.id;
    }

    /**
     * Regresa el dato que está en la posición dada por f y c las posiciones se
     * deben dar empezando de 0
     *
     * @param f índice de la fila
     * @param c índice de la columna
     * @return retorna el objeto o el valor que este en esa posición
     */
    public T get(int f, int c) {
        if (f >= 0 && f < this.filas) {
            if (c >= 0 && c < this.columnas) {
                return (T) this.a[f][c];
            }
        }
        return null;
    }

    /**
     * Inicializa la matriz con valores nulos
     */
    public final void inicializar() {
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                a[i][j] = this.inicial;
            }
        }
    }
    
    /**
     * Toma una arreglo bidimensional del tipo generico T y lo introduce en esta
     * instancia del objeto Matriz asgina por si solo el tamaño de las filas y
     * las columnas
     *
     * @warning reinicia el arreglo interno a, si se tenía datos anteriores se
     * eliminan
     * @param x arreglo bidimensional de tipo generico T
     */
    public void setMatriz(T[][] x) {
        this.filas = x.length;
        this.columnas = x[0].length;

        this.a = new Object[filas][columnas];

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                a[f][c] = x[f][c];
            }
        }
    }
    
    /**
     * pone en celdas aleatorias el valor v
     *
     * @param n número de veces que el valor v debe aparecer
     * @param v valor generico que se desea introducir aleatoriamente
     */
    public void llenadoAleatorio(int n, T v) {
        int fila = 0, columna = 0;
        if (n < this.getNElementos()) {
            for (int i = 0; i < n; i++) {
                fila = (int) (Math.random() * filas);
                columna = (int) (Math.random() * columnas);
                if (this.a[fila][columna] == v) {
                    i--;
                } else {
                    this.a[fila][columna] = v;
                }
            }
        }
    }

    /**
     * esta función revisa la matriz y guarda todos los valores que esten
     * contenidos No se repiten los valores Para que funcione el tipo T con el
     * que se trabje debe tener un método equals sobreescrito
     *
     * @return arreglo con los valores sin repetir, que están contenidos en la
     * matriz
     */
    public T[] valores() {
        ArrayList<T> valores = new ArrayList<>();

        T valor = null;
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                valor = (T) this.a[f][c];
                if (!valores.contains(valor)) {
                    valores.add(valor);
                }
            }
        }
        return (T[]) valores.toArray();
    }

    /**
     * imprime en consola los datos en orden y en forma de matriz
     */
    public void printConsole() {
        Thread t = new Thread() {
            @Override
            public void run() {
                for (int f = 0; f < filas; f++) {
                    for (int c = 0; c < columnas; c++) {
                        System.out.print(a[f][c] + ", ");
                    }
                    System.out.println("");
                }
            }
        };
        t.start();
    }

    /**
     * forma de string de la matriz
     *
     * @warning ADVERTENCIA para matrices grande puede presentar un
     * desbordamiento de memoria
     * @return cadena con la representacion de esta matriz
     */
    @Override
    public String toString() {
        String mensaje = id + "\n";
        for (int f = 0; f < this.filas; f++) {
            for (int c = 0; c < this.columnas; c++) {
                mensaje += a[f][c] + " ";
            }
            mensaje += "\n";
        }
        return mensaje;
    }

    /**
     * Muestra el tamaño de la matriz en su representación algebraica filas X
     * columnas
     *
     * @return el tamaño de la matriz en representación matemáticas
     */
    public String getSize() {
        return this.filas + "x" + this.columnas;
    }

    /**
     * Calcula el número de elementos contenidos en la matriz con: Filas x
     * Columnas
     *
     * @return número de elementos en la matriz
     */
    public int getNElementos() {
        return filas * columnas;
    }

    
}
