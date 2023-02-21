package lvcr.al.imagex_2.vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import lvcr.al.imagex_2.matrices.Matriz;

/**
 * @author Lazcano Valdez César Ricardo
 */
public final class PanelImagen extends javax.swing.JPanel {

    private int escala;
    private int width;
    private int height;
    private Matriz<Color> matriz;
    int n = 0;
    
    public PanelImagen() {
        System.out.println("Creando panel de imagen...");
        
        escala = 1;
        width = 0;
        height = 0;
        
        initComponents();
        pintar();
        
        matriz = null;
    }
    
    public void pintarImagen(Matriz<Color> m){
        this.matriz = m;
        repaint();
    }
    
    public void pintar(){
        repaint();
    }
    
    @Override
    public void paint(Graphics g){
        System.out.println("Pintando: ");
        super.paint(g);
        Graphics2D gc = (Graphics2D) g;
        
        int posX = 0;
        int posY = 0;
        
        //codio de prueba
        Color colores[] = {Color.BLUE, Color.RED, Color.CYAN, Color.BLACK};
        
        //fin codigo prueba
        
        if(matriz == null){
            System.out.println("");
        } else{
            gc.clearRect(0, 0, width, height);
            width = matriz.getColumnas();
            height = matriz.getFilas();
            
            for(int f = 0; f<width; f++){
            posX = 0;
            for(int c = 0; c<height; c++){
                //codigo de prueba
                
                //fin codifo de prueba
                gc.setColor(matriz.get(f,c));
                gc.fillRect(posX, posY, escala, escala);
                
                posX += escala;
            }
            posY += escala;
        }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                formMouseWheelMoved(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    //Eventon de rueda del raton
    private void formMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_formMouseWheelMoved
        
        var rotacion = evt.getWheelRotation();
        var punto = evt.getPoint();
        
        System.out.println("rotacion = " + rotacion);
        System.out.print("Punto: ");
        System.out.println("("+punto.x+", "+punto.y+")");
        //hacia delante -1
        //hacia abajo 1
        switch(rotacion){
            case -1 -> ++escala;
            case 1 -> --escala;
        }
        if(escala == 0){
            escala = 1;
        }
        this.pintar();
    }//GEN-LAST:event_formMouseWheelMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
