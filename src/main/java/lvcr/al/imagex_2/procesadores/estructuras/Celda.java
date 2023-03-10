/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lvcr.al.imagex_2.procesadores.estructuras;


/**
 * Clase que representa las celdas o posiciones de la estrucutra
 */
public class Celda {

    public int x;
    public int y;

    public Celda(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Celda add(Celda b) {
        return new Celda(this.x + b.x, this.y + b.y);
    }

    @Override
    public int hashCode() {
        int hash = (int) Math.random() * 100;
        hash = 37 * hash + this.x;
        hash = 37 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Celda c = (Celda) obj;
        return (c.x == this.x && c.y == this.y);
    }
    
    

}
