/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lvcr.al.imagex_2.procesadores.estructuras;

import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class ImageObject {
    private final ArrayList<Celda> posiciones;
    private int alto;
    private int ancho;

    public ImageObject(int alto, int ancho) {
        posiciones = new ArrayList<>();
        this.alto = alto;
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
    
    
    
    public void add(Celda c){
        this.posiciones.add(c);
    }

    public ArrayList<Celda> getPosiciones() {
        return posiciones;
    }
    
    public boolean contains(Celda c){
        return this.posiciones.contains(c);
    }
}
