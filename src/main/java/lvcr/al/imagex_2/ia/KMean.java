package lvcr.al.imagex_2.ia;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import lvcr.al.imagex_2.matrices.Matriz;

public class KMean {

    private Cluster[] clusters;
    private static final int EUCLIDEAN_RGB = 1;
    private static final int SUPERVISADO = 1;

    public KMean() {
    }

    /**
     * Aplica el algoritmo Kmeans para agrupar los colores y sus posici
     *
     * @param algoritmo
     * @param imagen
     * @param centroides
     */
    public void kmeanSupervisado(int algoritmo, Matriz<Color> imagen, Color centroides[]) {
        selectCluster(SUPERVISADO, centroides);

        switch (algoritmo) {
            case EUCLIDEAN_RGB ->
                kmeanEuclideanRgb(imagen);
        }
    }

    private void selectCluster(int Tipo, Color centroides[]) {
        switch (Tipo) {
            case SUPERVISADO -> {
                clusters = new Cluster[centroides.length];
                for (int i = 0; i < clusters.length; i++) {
                    clusters[i].centroide = centroides[i];
                }
            }
        }
    }

    private void kmeanEuclideanRgb(Matriz<Color> imagen) {
        Color[][] img = imagen.get();
        int r = 0, g = 0, b = 0, ik;
        Color col;
        double  menor = 0, distancia = 0;
        for (int f = 0; f < imagen.getFilas(); f++) {
            for (int c = 0; c < imagen.getColumnas(); c++) {
                ik = 0;
                menor = distanciaEuclidiana(clusters[0].centroide, img[f][c]);
                for (int cl = 0; cl < clusters.length; cl++) {
                    distancia = distanciaEuclidiana(clusters[cl].centroide, img[f][c]);
                    if(distancia < menor) {
                        menor = distancia;
                        ik = cl;
                    }
                }
                clusters[ik].add(img[f][c], f, c);
            }
        }
        
        
    }
    
    private double distanciaEuclidiana(Color centroide, Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        double d1 = Math.pow(Math.abs(centroide.getRed() - r), 2);
        double d2 = Math.pow(Math.abs(centroide.getGreen() - g), 2);
        double d3 = Math.pow(Math.abs(centroide.getBlue() - b), 2);
        double raiz = d1 + d2 + d3;
        return Math.sqrt(raiz);
    }
    
}

class Cluster {

    List<OKMean> cluster;
    Color centroide;

    public Cluster(Color centroide) {
        cluster = new ArrayList<>();
        this.centroide = centroide;
    }

    public void add(Color color, int x, int y) {
        cluster.add(new OKMean(color, x, y));
    }

    class OKMean {

        Color color;
        int x;
        int y;

        public OKMean(Color color, int x, int y) {
            this.color = color;
            this.x = x;
            this.y = y;
        }

    }
}


