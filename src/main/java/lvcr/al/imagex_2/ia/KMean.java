package lvcr.al.imagex_2.ia;

import java.util.ArrayList;
import java.util.List;
import lvcr.al.imagex_2.matrices.Matriz;

public class KMean {

    public Cluster[] clusters;
    public  static final int EUCLIDEAN_RGB = 1;
    public static final int SUPERVISADO = 1;

    public KMean() {
    }

    /**
     * Aplica el algoritmo Kmeans para agrupar los colores y sus posici
     *
     * @param algoritmo
     * @param imagen
     * @param centroides
     */
    public void kmeanSupervisado(int algoritmo, Matriz<Integer> imagen, Integer centroides[]) {
        selectCluster(SUPERVISADO, centroides);

        switch (algoritmo) {
            case EUCLIDEAN_RGB ->
                kmeanEuclideanRgb(imagen);
        }
    }

    private void selectCluster(int Tipo, Integer centroides[]) {
        switch (Tipo) {
            case SUPERVISADO -> {
                clusters = new Cluster[centroides.length];
                for (int i = 0; i < clusters.length; i++) {
                    clusters[i].centroide = centroides[i];
                }
            }
        }
    }

    private void kmeanEuclideanRgb(Matriz<Integer> imagen) {
        Integer[][] img = imagen.get();
        int r = 0, g = 0, b = 0, ik;
        Integer col;
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
    
    private double distanciaEuclidiana(Integer centroide, Integer color) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color) & 0xFF;

        double d1 = Math.pow(Math.abs(((centroide >> 16) & 0xFF) - r), 2);
        double d2 = Math.pow(Math.abs(((centroide >> 8) & 0xFF) - g), 2);
        double d3 = Math.pow(Math.abs(((centroide) & 0xFF) - b), 2);
        double raiz = d1 + d2 + d3;
        return Math.sqrt(raiz);
    }
    



}


