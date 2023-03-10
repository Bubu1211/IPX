package lvcr.al.imagex_2.procesadores;

import java.awt.Color;
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

    private Matriz<Color> matriz;
    private int altoImagen;
    private int anchoImagen;
    private boolean matrizBinarizada;
    private Muestra muestra;

    public ImageProcessor() {
        matrizBinarizada = false;
        muestra = new Muestra();
    }

    public void abrirImagen(File file) {
        System.out.println("Abre imagen como matriz de colores desde ImagenProcessor");
        InputStream input;
        ImageInputStream imageInput;
        try {
            ///Abre la imagen a partir del archivo que se recupero 
            input = new FileInputStream(file);
            imageInput = ImageIO.createImageInputStream(input);
            BufferedImage imagen = ImageIO.read(imageInput);

            ///determina medidas de la imagen en pixeles
            altoImagen = imagen.getHeight();
            System.out.println("altoImagen IP= " + altoImagen);
            anchoImagen = imagen.getWidth();
            System.out.println("AnchoImagen IP= " + anchoImagen);

            ///Inicializa la matriz de colores 
            this.matriz = new Matriz<>(altoImagen, anchoImagen, new Color(0, 0, 0));
            java.awt.Color col;
            ///Recupera pixel por pixel de la imagen en su modo RGB
            for (int f = 0; f < altoImagen; f++) {
                for (int c = 0; c < anchoImagen; c++) {
                    col = new java.awt.Color(imagen.getRGB(c, f));
                    matriz.set(f, c, col);
                }
            }
//            System.out.println("Matriz de colores creada al abrir la imagen: ");
//            matriz.printConsole();
            matrizBinarizada = false;

        } catch (FileNotFoundException ex) {
//            throw new ImageOpenException("Error al buscar el archivo \n" + ex.getMessage());
            System.out.println("Error archivo no encontrado");
        } catch (IOException ex) {
//            throw new ImageOpenException(ex.getMessage());
            System.out.println("Error IOExceptin");
        }
    }

    public Matriz<Color> getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz<Color> matriz) {
        this.matriz = matriz;
    }

    public Matriz<Byte> bynaryBwMatrizColores(Matriz<Color> matriz) {
        var m = new Matriz<>(altoImagen, anchoImagen, (byte) 0);

        for (int i = 0; i < altoImagen; i++) {
            for (int j = 0; j < anchoImagen; j++) {
                Color color;
                if (matriz.get(i, j).equals(Color.BLACK)) {
                    m.set(i, j, (byte) 1);
                } else {
                    m.set(i, j, (byte) 0);
                }
            }
        }
//        System.out.println("Matriz Binarizada");
//        m.printConsole();
        matrizBinarizada = true;
        return m;
    }

    public Matriz<Byte> binary_black_white() {

        int newFilas = this.matriz.getFilas();
        int newColumnas = this.matriz.getColumnas();

        var m = new Matriz<Byte>(newFilas, newColumnas, (byte) 0);

        System.out.println("binary_black_white altoImagen = " + newFilas);
        System.out.println(" binary_black_white anchoImagen = " + newColumnas);

        for (int i = 0; i < newFilas; i++) {
            for (int j = 0; j < newColumnas; j++) {
                if (Color.BLACK.equals(matriz.get(i, j))) {
                    m.set(i, j, (byte) 1);
                } else {
                    m.set(i, j, (byte) 0);
                }
            }
        }
        return m;
    }

    public Matriz<Byte> binary_grey(int escala_grey) {
        Color c;
        int escala;
        var m = new Matriz<Byte>(altoImagen, anchoImagen, (byte) 0);
        byte v = 0;
        for (int i = 0; i < altoImagen; i++) {
            for (int j = 0; j < anchoImagen; j++) {
                c = matriz.get(i, j);
                if (c.getRed() == c.getBlue() && c.getRed() == c.getGreen()) {
                    escala = c.getRed();
                    if (escala >= 0 && escala <= escala_grey) {
                        m.set(i, j, (byte) 1);
                    } else if (escala > escala_grey && escala <= 255) {
                        m.set(i, j, (byte) 0);
                    } else {
                        m.set(i, j, (byte) -1);
                    }
                }
            }
        }

        return m;
    }

    public Matriz<Color> binaryBwToImagen(Matriz<Byte> m) {
        Matriz<Color> out = new Matriz(m.getFilas(), m.getColumnas(), new Color(0, 0, 0));
        System.out.println("Iniciando binarización en el procesador de imagenes...");

        var fils = m.getFilas();
        var columns = m.getColumnas();

        for (int f = 0; f < fils; f++) {
            for (int c = 0; c < columns; c++) {
                if (m.get(f, c) == 1) {
                    out.set(f, c, Color.BLACK);
                } else {
                    out.set(f, c, Color.WHITE);
                }
            }
        }
        System.out.println("Terminando de binarizar por escala de grises en el procesador de imagenes");
        return out;
    }

    public Matriz<Color> deleteRed() {
        Color color;
        var fils = matriz.getFilas();
        var cols = matriz.getColumnas();
        var r = new Matriz<Color>(this.altoImagen, this.anchoImagen, new Color(0, 0, 0));

        for (int f = 0; f < fils; f++) {
            for (int c = 0; c < cols; c++) {
                color = matriz.get(f, c);
                r.set(f, c, new Color(0, color.getGreen(), color.getBlue()));
            }
        }
        return r;
    }

    public Matriz<Color> filterAddRed() {
        Color color;
        var filas = matriz.getFilas();
        var cols = matriz.getColumnas();
        var r = new Matriz<Color>(this.altoImagen, this.anchoImagen, new Color(0, 0, 0));

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < cols; c++) {
                color = matriz.get(f, c);
                r.set(f, c, new Color(color.getGreen(), color.getBlue(), 1));
            }
        }
        return r;
    }

    public Matriz<Color> filterAddGreen() {
        Color color;
        var filas = matriz.getFilas();
        var cols = matriz.getColumnas();
        var r = new Matriz<Color>(this.altoImagen, this.anchoImagen, new Color(0, 0, 0));
        System.out.println("Filtro addGreen ImagenProcessor inicia ");
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < cols; c++) {
                color = matriz.get(f, c);
                r.set(f, c, color);
            }
        }
        System.out.println("Filtro addGreen ImagenProcessor termina ");
        return r;
    }

    public Matriz<Color> filterAddBlue() {
        System.out.println("Filtro azul origin: ImagenProcessor");
        Color color;
        var filas = matriz.getFilas();
        var cols = matriz.getColumnas();
        var r = new Matriz<Color>(this.altoImagen, this.anchoImagen, new Color(0, 0, 0));

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < cols; c++) {
                color = matriz.get(f, c);
                r.set(f, c, color);
            }
        }
        return r;
    }

    public Matriz<Color> dilatacion(Matriz<Color> m, Color v) {
        var e = Estructura.VECINO_4;
        var mc = new Matriz<Color>(m.getFilas() + e.getIncFila(),
                m.getColumnas() + e.getIncCol(),
                new Color(255, 255, 255));

        Color aux;
        int fila, col;
        int filas = m.getFilas();
        int columnas = m.getColumnas();

        System.out.println("v = " + v);

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

    public Matriz<Color> erosion(Matriz<Color> m,Color c, Estructura e) {

        var objetosResultantes = new ArrayList<ImageObject>();

        ArrayList<ImageObject> objetos = m.cumulos(c, Color.CYAN);

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
    
    public Matriz<Color> matrizFromObjetos(ArrayList<ImageObject> objetos){
        var m = new Matriz<Color>(objetos.get(0).getAlto(), objetos.get(0).getAncho(), Color.WHITE);

        m.inicializar();

        ImageObject o;
        Celda c;
        for(int i = 0; i<objetos.size(); i++){
            o = objetos.get(i);
            for(int j = 0; j<o.getPosiciones().size(); j++){
                c = o.getPosiciones().get(j);
                m.set(c.y, c.x, Color.BLACK);
            }
        }
        return m;
    }
    
    public Matriz<Color> getMuestra(){
        var m = this.matriz.getSubMatriz(muestra.filaA, muestra.columnaA, muestra.filaB, muestra.columnaB);
        return m;
    }
    
    public void setMuestra(int filaA, int colA, int filaB, int colB){
        this.muestra = new Muestra();
        muestra.setA(filaA, colA);
        muestra.setB(filaB, colB);
        System.out.println("Muestra = " + muestra);
    }

    class Muestra{
        int filaA;
        int filaB;
        int columnaA;
        int columnaB;
        
        public void setA(int filaA, int colA){
            this.filaA = filaA;
            this.columnaA = colA;
        }
        
        public void setB(int filaB, int colB){
            this.filaB = filaB;
            this.columnaB = colB;
        }

        @Override
        public String toString() {
            return "Muestra{" + "filaA=" + filaA + ", filaB=" + filaB + ", columnaA=" + columnaA + ", columnaB=" + columnaB + '}';
        }
    }
}
