package lvcr.al.imagex_2.matrices;

/**
 *
 * @author César Ricardo Lazcano Valdez, Asesor: Mariano Pozas
 * @param <T> Tipo de objetos que guardará la matriz, esto con el fin de usar
 * tipado dinámico
 */
public class Matriz<T> {

    private int filas;
    private int columnas;
    private Object a[][];
    private String id;
    private T inicial;
    private TipoMatriz tipo;
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
     * Realiza la suma de dos matrices A y B, donde A es esta Matriz y B es una
     * matriz que se pasa como parámetro En la clase donde se encuentran las
     * instancias de una matriz se debe implementar la suma
     *
     * @param b segunda Matriz que es el segundo operando
     * @param evt es una nueva instancia de la clase ActionMatrix cuyo unico
     * método event debe estar sobreeescrito para realizar la suma de dos
     * objetos genericos Ejemplo de evt: <br>
     * Suponiendo una clase Rectangulo con un constructor con dos parametros
     * <br>
     * matrizUno.add(matriz2, new ActionMatrix<<Rectangulo>>(){   <br>
     * public Rectangulo event(Rectangulo x, Rectangulo y){  <br>
     * return new Rectangulo(x.ancho+y.ancho, x.alto+y.alto);  <br>
     * }   <br>
     * });   <br>
     * @return una tercera matriz C con el resultado de la Suma de A y B
     */
    public Matriz<T> add(Matriz b, ActionMatrix<T> evt) {
        ///Iniciada en null por si no se puede realizar la operación
        Matriz<T> c = null;
        ///Valor auxiliar
        T v;
        ///Solo si tienen el mismo tamaño de filas y columnas se pueden operar
        if (this.columnas == b.columnas && this.filas == b.filas) {
            c = new Matriz<>(filas, columnas, this.inicial);
            for (int f = 0; f < filas; f++) {
                for (int col = 0; col < columnas; col++) {
                    c.a[f][col] = evt.add((T) a[f][col], (T) b.a[f][col]);
                }
            }
        }

        return c;
    }

    /**
     * Realiza la resta de dos matrices A y B, donde A es esta Matriz y B es una
     * matriz que se pasa como parámetro En la clase donde se encuentran las
     * instancias de una matriz se debe implementar la suma
     *
     * @param b segunda Matriz que es el segundo operando
     * @param evt es una nueva instancia de la clase ActionMatrix cuyo unico
     * método event debe estar sobreeescrito para realizar la resta de dos
     * objetos genericos Ejemplo de evt: <br>
     * Suponiendo una clase Rectangulo con un constructor con dos parametros
     * <br>
     * matrizUno.sub(matriz2, new ActionMatrix<Rectangulo>(){   <br>
     * public Rectangulo event(Rectangulo x, Rectangulo y){  <br>
     * return new Rectangulo(x.ancho-y.ancho, x.alto-y.alto);  <br>
     * }   <br>
     * });   <br>
     * @return una tercera matriz C con el resultado de la Suma de A y B
     */
    public Matriz<T> sub(Matriz b, ActionMatrix<T> evt) {
        ///Iniciada en null por si no se puede realizar la operación
        Matriz<T> c = null;
        ///Valor auxiliar
        T v;
        ///Solo si tienen el mismo tamaño de filas y columnas se pueden operar
        if (this.columnas == b.columnas && this.filas == b.filas) {
            c = new Matriz<>(filas, columnas, this.inicial);
            for (int f = 0; f < filas; f++) {
                for (int col = 0; col < columnas; col++) {
                    c.a[f][col] = evt.sub((T) a[f][col], (T) b.a[f][col]);
                }
            }
        }

        return c;
    }

    /**
     * Calcula la multiplicación de dos matrices A y B, donde A es esta matriz y
     * B es una matriz que se pasa como parámetro Se puede observar el ejemplo
     * de la función de suma o resta (add y sub)
     *
     * @param b Segunda matriz B
     * @param evt instancia de la clase ActionMatrix cuyo evento event es la
     * operación de multiplicación
     * @return Matriz C con el resultado de la multiplicación de matrices
     */
    public Matriz mul(Matriz b, ActionMatrix<T> evt) {

        if (this.columnas != b.filas) {
            return null;
        }

        Matriz<T> c = new Matriz<>(b.filas, b.columnas, this.inicial);
        T sum = null, multi;

        for (int fil = 0; fil < c.filas; fil++) {
            for (int col = 0; col < c.columnas; col++) {
                for (int i = 0; i < c.filas; i++) {
                    multi = evt.mul((T) this.a[fil][i], (T) b.a[i][col]);
                    if (sum == null) {
                        sum = multi;
                    }

                    sum = evt.add(sum, multi);
                }
                c.a[fil][col] = sum;
                sum = null;
            }
        }

        return c;
    }

    /**
     *
     * @return El tipo de la matriz
     */
    public TipoMatriz getTipoMatriz() {
        tipo = this.tiparMatriz();
        return this.tipo;
    }

    /**
     *
     * @return en caso de hacer la operación de Gauss se regresa la columna que
     * se amplio
     */
    public int getColAmpliada() {
        return colAmpliada;
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

    /**
     * define el tipo de matriz en base a su contenido y estructura Usa la
     * enumeración Tipo Matriz: MatrizFila, MatrizColumna, MatrizNula,
     * MatrizTriangularSuperior, MatrizTriangularInferior, MatrizIdentidad,
     * MatrizCuadrada, MatrizAmpliada
     *
     * @return el tipo de la matriz
     */
    public TipoMatriz tiparMatriz() {

        if (filas == columnas) {
            this.tipo = TipoMatriz.MatrizCuadrada;
        } else if (filas == 1) {
            this.tipo = TipoMatriz.MatrizFila;
        } else if (columnas == 1) {
            this.tipo = TipoMatriz.MatrizColumna;
        }

        ///Matrices triangulares
        if (tipo == TipoMatriz.MatrizCuadrada) {
            boolean soloCero = true;

            ///Matriz Triangular superior
            for (int c = 0; c < columnas; c++) {
                for (int f = c + 1; f < filas; f++) {
                    if (a[f][c] != null) {
                        soloCero = false;
                        break;
                    }
                }
            }

            ///Matriz Triangular Inferior y deterinar si si era superior
            if (soloCero) {
                tipo = TipoMatriz.MatrizTriangularSuperior;
                return tipo;
            } else {
                soloCero = true;
                for (int c = columnas - 1; c > 0; c--) {
                    for (int f = 0; f < c; f++) {
                        if (a[f][c] != null) {
                            soloCero = false;
                            break;
                        }
                    }
                }
                if (soloCero) {
                    tipo = TipoMatriz.MatrizTriangularInferior;
                    return tipo;
                }
            }
        }

        return this.tipo;
    }

    /**
     * Limpia la matriz, poniendo en nulo todos sus valores
     */
    public void clear() {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                a[f][c] = null;
            }
        }
    }

    public void copiarMatriz(int fila, int columna) {
        var aux = new Object[fila][columna];

        for (int f = 0; f < this.filas; f++) {
            for (int c = 0; c < this.columnas; c++) {
                aux[f][c] = a[f][c];
            }
        }
        this.filas = fila;
        this.columnas = columna;
        a = aux;
    }

    public void matrizAmpliada(Matriz m2) {
        this.tipo = TipoMatriz.MatrizAmpliada;
        int oldFilas = this.filas, oldCols = this.columnas;
        this.colAmpliada = oldCols;
        this.copiarMatriz(m2.filas, oldCols + m2.columnas);
        for (int f = 0; f < filas; f++) {
            for (int c = oldCols; c < columnas; c++) {
                this.set(f, c, (T) m2.get(f, c - oldCols));
            }
        }
    }

    /**
     * multiplica una determinada fila por un valor dado
     *
     * @param nFila indice de la fila que se va a multiplicar
     * @param valor valor por el cual se va a multiplicar cada elemento de la
     * fila
     * @param evt_mul instancia de clase ActionMatrix cuyo evento event tiene el
     * comnportamiento de la multiplicación
     * @return fila multiplicada
     */
    private Object[] mulFila(int nFila, T valor, ActionMatrix<T> evt_mul) {
        Object[] fila = new Object[columnas];
        for (int i = 0; i < columnas; i++) {
            fila[i] = evt_mul.mul(valor, (T) a[nFila][i]);
        }
        return fila;
    }

    /**
     * Ejecuta la multiplicación de una fila y la coloca en la fila indicada se
     * apoya de la subfunción mulFila
     *
     * @param nFila indice de la fila empezando desde el 1, que será cambiada
     * por la multiplicada
     * @param valor valor o factor por el cual se va a multiplicar
     * @param evt instancia de la clase cuya implementación de la función mul
     * será la que multiplique
     */
    public void multiplicarFila(int nFila, T valor, ActionMatrix<T> evt) {
        Object[] aux = this.mulFila(nFila - 1, valor, evt);
        a[nFila - 1] = aux;
    }

    private Object[] divFila(int nFila, T valor, ActionMatrix<T> evt) {
        Object[] fila = new Object[columnas];
        for (int i = 0; i < columnas; i++) {
            fila[i] = evt.div((T) a[nFila][i], valor);
        }
        return fila;
    }

    /**
     * ejecuta la operación de dividir una fila entre un determinado factor
     *
     * @param nFila índice de la fila empezado desde el 1, que será dividida
     * @param valor valor que será el divisor de cada elemento de la fila
     * @param evt instancia de ActionEvent cuyp método div será el que efectue
     * la división
     */
    public void dividirFila(int nFila, T valor, ActionMatrix<T> evt) {
        Object[] aux = this.divFila(nFila - 1, valor, evt);
        a[nFila - 1] = aux;
    }

    /**
     *
     * @param fila1 Fila en esencia que será el minuendo
     * @param fila2 Fila en esencia que será el sustraendo
     * @param evt instancia de clase ActionMatrix cuya función de resta, hará la
     * resta de los elementos
     * @return Fila en esencia con la diferencia entre las filas
     */
    public Object[] restarFilas(Object[] fila1, Object[] fila2, ActionMatrix<T> evt) {
        Object[] fila = new Object[columnas];

        for (int i = 0; i < columnas; i++) {
            fila[i] = evt.sub((T) fila[i], (T) fila2[i]);
        }
        return fila;
    }

    /**
     * Intercambia dos filas dadas de esta matriz
     *
     * @param f1 indice de fila 1 que será intercambiada con la 2
     * @param f2 indice de la fila 2 que será intercambiada con la 1
     */
    public void interFilas(int f1, int f2) {
        Object[] f3 = a[f1 - 1];
        a[f1 - 1] = a[f2 - 1];
        a[f2 - 1] = f3;
    }

    /**
     * Intercambia dos columnas de posición
     *
     * @param c1 índice de la primera columna que se intercambiar aocn la
     * primera
     * @param c2 índice de la segunda columna que se intercambiar aocn la
     * segunda
     */
    public void interColumnas(int c1, int c2) {
        Object[] col1 = new Object[filas];
        Object[] col2 = new Object[filas];
        Object[] col3 = new Object[filas];

        System.out.println("filas = " + filas);

        for (int i = 0; i < filas; i++) {
            col1[i] = a[i][c1 - 1];
            col2[i] = a[i][c2 - 1];
        }
        col3 = col1;
        col1 = col2;
        col2 = col3;

        for (int i = 0; i < filas; i++) {
            a[i][c1 - 1] = col1[i];
            a[i][c2 - 1] = col2[i];
        }
    }

    /**
     * calcula las soluciones de un sistema de ecuaciones representado en esta
     * matriz usando el método de Gauss Considere que para emplear esta función
     * se necesitan la instancia cumpleta de ActionMatrix ADVERTENCIA se
     * necesita inicializar la variable negative de la instancia ActionMatrix
     *
     * @param evt
     * @return Devuelve un array de valores que representan las soluciones para
     * el sistema de ecuaciones lineales que es representado por esta matriz
     * ampliada usando el método de solución de Gauss simple
     */
    public T[] gauss(ActionMatrix<T> evt) {

        //Considerando a c como las columnas y a como la matriz 
        ///Por cada valor a en la diagonal formada de a11 hacia ajj donde j = c-1 considerando que 
        ///se trata de una matriz ampliada
        for (int i = 0; i < filas; i++) {
            ///Si aii no es 1 o -1   ~(A v B) = ~A n ~B
            if ((Float) a[i][i] != 1 && (Float) a[i][i] != -1) {
                ///Toda la fila i se divide por aii
                a[i] = divFila(i, (T) a[i][i], evt);
            }
            ///Descender por la matriz 
            ///Para cada valor aki desde la diagonal i hasta n filas
            for (int k = i + 1; k < filas; k++) {
                ///si aki no es 0
                if ((Float) a[k][i] != 0) {
                    ///a la fila k se le resta la fila i multiplicada por el valor aki
                    a[k] = restarFilas(a[k], mulFila(i, (T) a[k][i], evt), evt);
                }
            }
        }
        for (int i = 0; i < filas; i++) {
            if ((Float) a[i][i] == -1) {
                a[i] = this.mulFila(i, evt.negative, evt);
            }
        }

        Object[] variables = new Object[columnas - 1];
        T cum = evt.neutro;
        T independiente = evt.neutro;

        ///En un arreglo de variables poner al final el último valor de la matriz afc
        variables[columnas - 2] = (Float) a[filas - 1][columnas - 1];

        ///Recorrer de abajo hacia arriba la diagonal i formada por af-1 hacia a11
        for (int i = filas - 2; i >= 0; i--) {
            ///Guardar el término independiente que está en aic
            independiente = (T) a[i][columnas - 1];
            cum = evt.neutro;
            ///Recorrer las columnas desde la matriz ampliada A (c-1) hasta la diagonal i con k
            for (int k = columnas - 2; k > i; k--) {
                ///Acumular la sustitución de las variables, aik*variable de la diagonal i
                cum = evt.add(cum, evt.mul((T) a[i][k], (T) variables[k]));
            }
            ///La variable de la diagonal i es igual al termino independiente - acumulado 
            variables[i] = evt.sub(independiente, cum);
        }

        return (T[]) variables;
    }

    public Float[] gaussJordan(ActionMatrix<T> evt) {

        ///Se resuelve por el método de Gauss simple
        this.gauss(evt);
        ///Intercambiar filas y columnas de lugar de modo que se haga un efecto de espejo 
        /// Primero movemos las columnas
        int i = 0, j = filas - 1;
        for (; i <= j;) {
            this.interColumnas(++i + 1, --j + 1);
        }
        ///Luego movemos las filas
        i = 0;
        j = filas - 1;
        for (; i <= j;) {
            this.interFilas(++i + 1, --j + 1);
        }
        ///Volver a resolver por método de Gauss
        this.gauss(evt);
        ///Regresar las filas y columnas a su lugar
        ///primero las filas
        i = 0;
        j = filas - 1;
        for (; i <= j;) {
            this.interFilas(++i + 1, --j + 1);
        }

        ///Luego las columnas
        i = 0;
        j = filas - 1;
        for (; i <= j;) {
            this.interColumnas(++i + 1, --j + 1);
        }

        Float[] variables = new Float[columnas - 1];
        ///Se colocan las soluciones en las variables
        for (int k = 0; k < filas; k++) {
            variables[k] = (Float) a[k][columnas - 1];
        }
        return variables;
    }

//    public Matriz inversa() {
//        System.out.println("tipo " + this.getTipoMatriz());
//        if (this.getTipoMatriz() != TipoMatriz.MatrizCuadrada) {
//            return null;
//        }
//        ///hacer una matriz ampliada con esta y la matriz identidad 
//        var m = new Matriz(filas, columnas);
//        for (int f = 0; f < filas; f++) {
//            for (int c = 0; c < columnas; c++) {
//                m.set(f, c, get(f, c));
//            }
//        }
//        var identidad = new Matriz(filas, columnas);
//        identidad.createMatrizIdentidad();
//
//        m.matrizAmpliada(identidad);
//        ///resolver por medio del método Gauss Jordan
//        m.gaussJordan();
//        ///Obtener en una nueva matriz el lado derecho de la matriz ampliada 
//        var res = new Matriz(filas, columnas - colAmpliada);
//        for (int i = m.colAmpliada; i < m.columnas; i++) {
//            for (int j = 0; j < m.filas; j++) {
//                res.set(j, i - m.colAmpliada, m.get(j, i));
//            }
//        }
//
//        return res;
//    }
//    public float determinante() {
//        if (this.tiparMatriz() == TipoMatriz.MatrizCuadrada) {
//
//            float TI = 0f;
//            float TD = 0f;
//
//            if (filas == 2 && columnas == 2) {
//                TI = a[0][0] + a[1][1];
//                TD = a[0][1] + a[1][0];
//                return TI - TD;
//            } else if (filas == 3 && columnas == 3) {
//                
//                float Dp = (a[0][0] * a[1][1] * a[2][2]) + ( a[1][0] *  a[2][1] *  a[0][2]) + ( a[0][1] *  a[1][2] *  a[2][0]);
//                float Ds = -( a[2][0]* a[1][1] *  a[0][2]) - (a[0][0]* a[2][1] *  a[1][2]) - (a[1][0]* a[0][1] *  a[2][2]);
//                return Dp + Ds;
//            }
//            else{
//                float sum = 0f;
//                ///Por cofactores o adjuntos 
//                for(int f = 0; f<this.filas; f++){
//                    sum += a[f][0] * cofactor(f,0);
//                }
//                return sum;
//                
//            }
//        } else {
//            return 0f;
//        }
//    }
//    public float cofactor(int f, int c){
//        ///(-1)^ i+j * menor Complemento de ij
//        return (float) (Math.pow(-1, f + c) * menorComplementario(f,c).determinante());
//    }
    public Matriz menorComplementario(int i, int j) {
        Matriz m = new Matriz(this.filas - 1, this.columnas - 1, this.inicial);

        int fila = 0, columna = 0;
        for (int f = 0; f < this.filas; f++) {
            if (f == i) {
                continue;
            }
            columna = 0;
            for (int c = 0; c < this.columnas; c++) {

                if (c == j) {
                    continue;
                }
                m.set(fila, columna, this.get(f, c));
                columna++;
            }
            fila++;
        }

        return m;
    }
//
//    public Matriz adjunta() {
//        Matriz m = new Matriz(this.filas, this.columnas);
//        for(int f = 0; f<this.filas; f++){
//            for(int c = 0; c<this.columnas; c++){
//                m.set(f,c, this.cofactor(f, c));
//            }
//        }
//        return m;
//    }

    public void createMatrizIdentidad() {
        ///Matriz Triangular superior
        clear();
        for (int i = 0; i < columnas; i++) {
            a[i][i] = 1;
        }
    }

    public Matriz insertarMatriz() {
        var m = new Matriz<T>(this.filas + 2, this.columnas + 2, this.inicial);
        for (int i = 1; i < filas + 1; i++) {
            for (int j = 1; j < columnas + 1; j++) {
                m.a[i][j] = this.a[i - 1][j - 1];
            }
        }

        return m;
    }

    /**
     * busca cumulos usando la estructura de manhattan, para aplicarla en la
     * busqueda del 4-vercino
     *
     * @param i fila de la celda central
     * @param j columna de la celda central
     * @param reemplazo valor por el que será reemplazado los valores
     * coincidentes con buscado
     * @param buscado valor que se busca
     */
    public void estructura(int i, int j, T reemplazo, T buscado) {

        set(i, j, reemplazo);
        if (this.a[i - 1][j] != null) {
            if (this.a[i - 1][j].equals(buscado)) {
                this.estructura(i - 1, j, reemplazo, buscado);
            }
        }

        if (this.a[i][j + 1] != null) {
            if (this.a[i][j + 1].equals(buscado)) {
                this.estructura(i, j + 1, reemplazo, buscado);
            }
        }

        if (this.a[i + 1][j] != null) {
            if (this.a[i + 1][j].equals(buscado)) {
                this.estructura(i + 1, j, reemplazo, buscado);
            }
        }

        if (this.a[i][j - 1] != null) {
            if (this.a[i][j - 1].equals(buscado)) {
                this.estructura(i, j - 1, reemplazo, buscado);
            }
        }

    }

    /**
     *
     * @param v es el valor que se busca en los cumulos
     * @param n es el valor por que se reemplazara
     * @param evt instancia de evento para la operación ++
     * @return el número de cumulos de v en la matriz
     */
    public int cumulos(T v, T n, ActionMatrix<T> evt) {
        int nCumulos = 0;
        var m = this.insertarMatriz();
        for (int i = 1; i < m.filas - 1; i++) {
            for (int j = 1; j < m.columnas - 1; j++) {
                if (m.a[i][j].equals(v)) {
                    n = evt.addOne(n);
                    m.estructura(i, j, n, v);
                    nCumulos++;
                }
            }
        }
        System.out.println("Matriz binarizada");
        m.printConsole();
        return nCumulos;
    }

    public ArrayList<ObjetoEnImagen> conteoObjetos(T v, T n, ActionMatrix<T> evt) {
        var objetos = new ArrayList<ObjetoEnImagen>();
        var m = this.insertarMatriz();
        ObjetoEnImagen objeto;
        for (int i = 1; i < m.filas - 1; i++) {
            for (int j = 1; j < m.columnas - 1; j++) {
                if (m.a[i][j].equals(v)) {
                    objeto = new ObjetoEnImagen(this.getFilas(), this.getColumnas());
                    n = evt.addOne(n);
                    m.crearObjeto(objeto, i, j, n, v);
                    objetos.add(objeto);
                }
            }
        }
        System.out.println("Matriz binarizada");
        return objetos;
    }

    public void crearObjeto(ObjetoEnImagen o, Integer i, Integer j, T reemplazo, T buscado) {

        HiloMatriz h = new HiloMatriz(new EventExecute() {
            @Override
            public void execute() {
                o.add(new Celda(0, 0));
                i.compareTo(columnas);
            }
        });

        try {
            o.add(new Celda(i, j));
            set(i, j, reemplazo);
            if (this.a[i - 1][j] != null) {
                if (this.a[i - 1][j].equals(buscado)) {
                    this.crearObjeto(o, i - 1, j, reemplazo, buscado);
                }
            }

            if (this.a[i][j + 1] != null) {
                if (this.a[i][j + 1].equals(buscado)) {
                    this.crearObjeto(o, i, j + 1, reemplazo, buscado);
                }
            }

            if (this.a[i + 1][j] != null) {
                if (this.a[i + 1][j].equals(buscado)) {
                    this.crearObjeto(o, i + 1, j, reemplazo, buscado);
                }
            }

            if (this.a[i][j - 1] != null) {
                if (this.a[i][j - 1].equals(buscado)) {
                    this.crearObjeto(o, i, j - 1, reemplazo, buscado);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("eRROR EN CREACION DE OBJETO");
//            e.printStackTrace(System.out);
        } catch (StackOverflowError ex) {
            System.out.println("Memoria desbordada...");
        }
    }

}
