package lvcr.al.imagex_2.matrices.estructuras;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author Bubu
 * define una matriz estructurante contiene un indice central y las celdas son 
 * las posiciones con respecto al indice central
 * ejemplo para la matriz estructurante del 4 vecino las celdas serian:
 * (-1, 0), (0, 1), (+1, 0), (0, 1)
 */
public class Estructura{
    
    private int filaCentral;
    private int colCentral;
    private int incFila;
    private int incCol;
    private java.util.ArrayList<Celda> celdas;

    public Estructura(int incFila, int incCol) {
        this.celdas = new java.util.ArrayList<Celda>();
        this.incFila = incFila;
        this.incCol = incCol;
    }
    
    public Estructura(int incFila, int incCol, Celda ...c){
        this(incFila, incCol);
        this.setCeldas(c);
    }
    
    public void setCelda(int f, int c){
        this.celdas.add(new Celda(f, c));
    }
    
    
    
    public void setCeldas(Celda ...c){
        for(Celda ce : c){
            this.celdas.add(ce);
        }
    }

    public int getIncFila() {
        return incFila;
    }

    public int getIncCol() {
        return incCol;
    }

    public ArrayList<Celda> getCeldas() {
        return celdas;
    }
    
    public static final Estructura VECINO_4 = new Estructura(
            2, 2, 
            new  Celda(-1, 0), 
            new Celda(0,1), 
            new Celda(1, 0),
            new Celda(0, -1));
    
    
    
}
