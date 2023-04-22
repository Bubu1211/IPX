package lvcr.al.imagex_2.procesadores;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import lvcr.al.imagex_2.matrices.Matriz;
import lvcr.al.imagex_2.procesadores.estructuras.Celda;
import lvcr.al.imagex_2.procesadores.estructuras.Estructura;
import lvcr.al.imagex_2.procesadores.estructuras.ImageObject;

/*
 * Clase encargada de procesar las imagenes 
 * @author César Ricardo Lazcano Valdez
 * La clase almacena en su matriz la imagen original, sobre la imagen original se realizan los operadores
 * a menos que el usuario indique que la imagen que se ve en pantalla sea la nueva original
 */
public class ImageProcessor {

    private Matriz<Integer> matriz;
    private int altoImagen;
    private int anchoImagen;
    private boolean matrizBinarizada;
    private Muestra muestra;

    public static final int BLACK = -16777216;
    public static final int WHITE = -1;
    public static final int CYAN = -16711681;

    public ImageProcessor() {
        matrizBinarizada = false;
        muestra = new Muestra();
    }

    /**
     * Convierte una imagen contenida en un objeto file (archivo) en una matriz
     * de enteros que representan los colores encontrados en cada pixel
     *
     * @param file archivo .png, .jpg, etc. que contiene la imagen que se desea
     * procesar
     */
    public void abrirImagen(File file) {
        InputStream input;
        ImageInputStream imageInput;
        try {
            ///Abre la imagen a partir del archivo que se recupero 
            input = new FileInputStream(file);
            imageInput = ImageIO.createImageInputStream(input);
            BufferedImage imagen = ImageIO.read(imageInput);

            ///determina medidas de la imagen en pixeles
            altoImagen = imagen.getHeight();
            anchoImagen = imagen.getWidth();

            ///Inicializa la matriz de colores 
            this.matriz = new Matriz<>(altoImagen, anchoImagen, -1);
            ///Recupera pixel por pixel de la imagen en su modo RGB
            for (int f = 0; f < altoImagen; f++) {
                for (int c = 0; c < anchoImagen; c++) {
                    matriz.set(f, c, imagen.getRGB(c, f));
                }
            }
            matrizBinarizada = false;

        } catch (FileNotFoundException ex) {
//            throw new ImageOpenException("Error al buscar el archivo \n" + ex.getMessage());
            System.out.println("Error archivo no encontrado \n" + ex.getMessage());
        } catch (IOException ex) {
//            throw new ImageOpenException(ex.getMessage());
            System.out.println("Error IOExceptin \n" + ex.getMessage());
        }
    }

    /**
     *
     * @return la matriz de enteros que representa a la imagen rgb
     */
    public Matriz<Integer> getMatriz() {
        return matriz;
    }

    /**
     * Cambiar la matriz de enteros representativos de los colores de la imagen
     * en esencia, cambia la imagen contenida en este procesador
     *
     * @param matriz Nueva matriz que representará a la imagen
     */
    public void setMatriz(Matriz<Integer> matriz) {
        this.matriz = matriz;
    }

    /**
     *
     * @param matriz matriz de enteros representantes del color, es la imagen a
     * la que se le aplicará la binarización
     * @return matriz de bytes que representan la binarización hecha
     */
    public Matriz<Byte> bynaryBwMatrizColores(Matriz<Integer> matriz) {
        var m = new Matriz<Byte>(altoImagen, anchoImagen, (byte) 0);

        for (int i = 0; i < altoImagen; i++) {
            for (int j = 0; j < anchoImagen; j++) {
                if (matriz.get(i, j) == BLACK) {
                    m.a[i][j] = (byte) 1;
                } else {
                    m.a[i][j] = (byte) 0;
                }
            }
        }
        matrizBinarizada = true;
        return m;
    }

    /**
     * Para aplicar se necesita tener en escala de grises la imagen (sino,
     * aplicar antes filtro de escala de grises) Si la binarizacion es superior
     * entonces: Si el valor del pixel es menor a la escala entonces la
     * binarización cae en 0 Si el valor del pixel es mayor o igual a la escala
     * entonces la binarizacion cae en 1 Si la imagen no esta en escala de
     * grises entonces solo se Si la binarizacion es inferior entonces se
     * realiza al reves usará el color rojo, pero el resultado será erroneo al
     * esperado
     *
     * @param escala_grey escala de gris la cual se tomará para hacer la
     * binarización
     * @param superior indica si la binarización es superior o inferior, si es
     * True entonces la binarizacion se realiza como superior
     * @return matriz binarizada
     */
    public Matriz<Byte> binary_grey(int escala_grey, boolean superior) {
        var m = new Matriz<Byte>(altoImagen, anchoImagen, (byte) 0);

        byte v = superior ? (byte) 1 : (byte) 0;
        byte v1 = v == 1 ? (byte) 0 : (byte) 1;

        for (int i = 0; i < altoImagen; i++) {
            for (int j = 0; j < anchoImagen; j++) {
                int escala = (((int) matriz.a[i][j]) >> 16) & 0xFF;
                if (escala >= 0 && escala <= escala_grey) {
                    m.set(i, j, v1);
                } else if (escala > escala_grey && escala <= 255) {
                    m.set(i, j, v);
                } else {
                    m.set(i, j, (byte) -1);
                }
            }
        }
        return m;
    }

    /**
     * Convierte una matriz que fue binarizada por sus escalas de blanco y negro
     * o de grises en una matriz de enteros que representan colores
     *
     * @param m la matriz binarizada
     * @return Matriz de enteros que representan una imagen en blanco y negro
     */
    public Matriz<Integer> binaryBwToImagen(Matriz<Byte> m) {
        Matriz<Integer> out = new Matriz(m.getFilas(), m.getColumnas(), BLACK);

        int fils = m.getFilas();
        int columns = m.getColumnas();

        for (int f = 0; f < fils; f++) {
            for (int c = 0; c < columns; c++) {
                if (m.get(f, c) == 1) {
                    out.a[f][c] = BLACK;
                } else {
                    out.a[f][c] = WHITE;
                }
            }
        }
        return out;
    }

    /**
     * Aplica un filtro de eliminación de rojo a la imagen contenida en este
     * procesador \n El valor Red de la escala RGB de cada pixel es disminuido a
     * 0
     *
     * @return Matriz de enteros que representa la imagen resultante de aplicar
     * el filtro
     */
    public Matriz<Integer> deleteRed() {
        int color;
        var fils = matriz.getFilas();
        var cols = matriz.getColumnas();
        var r = new Matriz<Integer>(this.altoImagen, this.anchoImagen, BLACK);

        for (int f = 0; f < fils; f++) {
            for (int c = 0; c < cols; c++) {
                color = ((int) matriz.a[f][c]);
                r.set(f, c, (((color >> 8) & 0xFF) << 8) | (color & 0xFF));
            }
        }
        return r;
    }

    /**
     * Aplica un filtro de eliminación de verde a la imagen contenida en este
     * procesador \n El valor Green de la escala RGB de cada pixel es disminuido
     * a 0
     *
     * @return Matriz de enteros que representa la imagen resultante de aplicar
     * el filtro
     */
    public Matriz<Integer> deleteGreen() {
        int color;
        var fils = matriz.getFilas();
        var cols = matriz.getColumnas();
        var r = new Matriz<Integer>(this.altoImagen, this.anchoImagen, BLACK);

        for (int f = 0; f < fils; f++) {
            for (int c = 0; c < cols; c++) {
                color = ((int) matriz.a[f][c]);
                r.set(f, c,
                        (((color >> 16) & 0xFF) << 16)
                        | ((color) & 0xFF)
                );
            }
        }
        return r;
    }

    /**
     * Aplica un filtro de eliminación de azul a la imagen contenida en este
     * procesador \n El valor Blue de la escala RGB de cada pixel es disminuido
     * a 0
     *
     * @return Matriz de enteros que representa la imagen resultante de aplicar
     * el filtro
     */
    public Matriz<Integer> deleteBlue() {
        int color;
        var fils = matriz.getFilas();
        var cols = matriz.getColumnas();
        var r = new Matriz<Integer>(this.altoImagen, this.anchoImagen, BLACK);

        for (int f = 0; f < fils; f++) {
            for (int c = 0; c < cols; c++) {
                color = ((int) matriz.a[f][c]);
                r.set(f, c,
                        (((color >> 16) & 0xFF) << 16
                        | ((color >> 8) & 0xFF) << 8)
                );
            }
        }
        return r;
    }

    /**
     * Aplica un filtro de agregación de rojo a la imagen contenida en este procesador \n El valor Red de la escala
     * RGB de cada pixel es aumentado en 255
     * @return Matriz de enteros que representa la imagen resultante de aplicar el filtro
     */
    public Matriz<Integer> filterAddRed() {
        int color;
        var fils = matriz.getFilas();
        var cols = matriz.getColumnas();
        var r = new Matriz<Integer>(this.altoImagen, this.anchoImagen, BLACK);

        for (int f = 0; f < fils; f++) {
            for (int c = 0; c < cols; c++) {
                color = ((int) matriz.a[f][c]);
                r.set(f, c, (255 << 16  | ((color >> 8) & 0xFF) << 8) | (color & 0xFF) );
            }
        }
        return r;
    }


    /**
     * Aplica un filtro de agregación de verde a la imagen contenida en este procesador \n El valor Green de la escala
     * RGB de cada pixel es aumentado en 255
     * @return Matriz de enteros que representa la imagen resultante de aplicar el filtro
     */
    public Matriz<Integer> filterAddGreen() {
        int color;
        var fils = matriz.getFilas();
        var cols = matriz.getColumnas();
        var r = new Matriz<Integer>(this.altoImagen, this.anchoImagen, BLACK);

        for (int f = 0; f < fils; f++) {
            for (int c = 0; c < cols; c++) {
                color = ((int) matriz.a[f][c]);
                r.set(f, c, (((color>>16) & 0xFF ) << 16  | 255 << 8) | (color & 0xFF) );
            }
        }
        return r;
    }
    
    /**
     * Aplica un filtro de agregación de azul a la imagen contenida en este procesador \n El valor Blue de la escala
     * RGB de cada pixel es aumentado en 255
     * @return Matriz de enteros que representa la imagen resultante de aplicar el filtro
     */
    public Matriz<Integer> filterAddBlue() {
        int color;
        var fils = matriz.getFilas();
        var cols = matriz.getColumnas();
        var r = new Matriz<Integer>(this.altoImagen, this.anchoImagen, BLACK);

        for (int f = 0; f < fils; f++) {
            for (int c = 0; c < cols; c++) {
                color = ((int) matriz.a[f][c]);
                r.set(f, c, (((color>>16) & 0xFF ) << 16  | ((color>>8) & 0xFF ) << 8) | (255) );
            }
        }
        return r;
    }
    
    public Matriz<Integer> dilatacion(Matriz<Integer> m, Integer v) {
        var e = Estructura.VECINO_4;
        var mc = new Matriz<Integer>(m.getFilas() + e.getIncFila(),
                m.getColumnas() + e.getIncCol(),
                WHITE);

        Integer aux;
        int fila, col;
        int filas = m.getFilas();
        int columnas = m.getColumnas();

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                aux = m.get(f, c);
//                System.out.println("aux = " + aux);
                if (aux.equals(v)) {
                    for (Celda celda : e.getCeldas()) {
                        //sumar  a + b
                        fila = f + celda.y;
                        col = c + celda.x;
                        ///poner v en la posicion en la matriz c
                        if (fila >= 0 && fila < filas && col >= 0 && col < columnas) {
                            mc.set(fila, col, v);
                        }
                        mc.set(f, c, v);
                    }
                }
            }
        }
        return mc;
    }

    public Matriz<Integer> erosion(Matriz<Integer> m, Integer c, Estructura e) {

        var objetosResultantes = new ArrayList<ImageObject>();

        ArrayList<ImageObject> objetos = m.cumulos(c, CYAN);

        for (ImageObject o : objetos) {

            boolean pertenece;
            Celda eval;

            var res = new ImageObject(m.getFilas(), m.getColumnas());
            for (Celda a : o.getPosiciones()) {
                pertenece = true;
                for (Celda b : e.getCeldas()) {
                    eval = a.add(b);
                    if (!o.contains(eval)) {
                        pertenece = false;
                        break;
                    }
                }
                if (pertenece) {
                    res.add(a);
                }
            }
            objetosResultantes.add(res);
        }

        var mc = this.matrizFromObjetos(objetosResultantes);
        return mc;
    }

    public Matriz<Integer> matrizFromObjetos(ArrayList<ImageObject> objetos) {
        var m = new Matriz<Integer>(objetos.get(0).getAlto(), objetos.get(0).getAncho(), WHITE);

        m.inicializar();

        ImageObject o;
        Celda c;
        for (int i = 0; i < objetos.size(); i++) {
            o = objetos.get(i);
            for (int j = 0; j < o.getPosiciones().size(); j++) {
                c = o.getPosiciones().get(j);
                m.set(c.y, c.x, BLACK);
            }
        }
        return m;
    }

    public Matriz<Integer> getMuestra() {
        var m = this.matriz.getSubMatriz(muestra.filaA, muestra.columnaA, muestra.filaB, muestra.columnaB);
        return m;
    }

    public void setMuestra(int filaA, int colA, int filaB, int colB) {
        this.muestra = new Muestra();
        muestra.setA(filaA, colA);
        muestra.setB(filaB, colB);
        System.out.println("Muestra = " + muestra);
    }

    class Muestra {

        int filaA;
        int filaB;
        int columnaA;
        int columnaB;

        public void setA(int filaA, int colA) {
            this.filaA = filaA;
            this.columnaA = colA;
        }

        public void setB(int filaB, int colB) {
            this.filaB = filaB;
            this.columnaB = colB;
        }

        @Override
        public String toString() {
            return "Muestra{" + "filaA=" + filaA + ", filaB=" + filaB + ", columnaA=" + columnaA + ", columnaB=" + columnaB + '}';
        }
    }
}
