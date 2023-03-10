package lvcr.al.imagex_2.vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import lvcr.al.imagex_2.matrices.Matriz;
import lvcr.al.imagex_2.procesadores.ImageProcessor;

/**
 * @author Lazcano Valdez César Ricardo
 */
public final class PanelImagen extends JPanel {

    private int escala;
    private int width;
    private int height;
    private Matriz<Color> matriz;
    private JPanel contenedor;
    int n = 0;
    private Point pointA;
    private Point pointB;
    private boolean setMuestra;
    private ImageProcessor imageProcessor;

    public PanelImagen(ImageProcessor ip, JPanel contenedor) {

        System.out.println("Creando panel de imagen...");

        escala = 1;
        width = 0;
        height = 0;

        initComponents();
        pintar();

        matriz = null;
        setMuestra = false;
        this.contenedor = contenedor;
        this.imageProcessor = ip;
    }

    public void setContenedor(JPanel contenedor) {
        this.contenedor = contenedor;
    }

    public void setMuestra(boolean b) {
        this.setMuestra = b;
    }

    public void pintarImagen(Matriz<Color> m) {
        this.matriz = m;
        repaint();
    }

    public void pintar() {
        repaint();
    }

    public Matriz<Color> getMatriz() {
        return this.matriz;
    }

    @Override
    public void paint(Graphics g) {
//        System.out.println("Pintando: ");
        super.paint(g);
        Graphics2D gc = (Graphics2D) g;

        if (matriz == null) {
            System.out.println("matriz vacía no se puede pintar");
        } else {
//            Thread t = new Thread() {
//                @Override
//                public void run() {
                    gc.clearRect(0, 0, width, height);
                    width = matriz.getColumnas();
                    height = matriz.getFilas();

                    contenedor.setSize(width * escala, height * escala);
//            System.out.println("Width panel : " + this.getWidth() + ", height: " + this.getHeight());

                    int posX = 0;
                    int posY = 0;

                    for (int f = 0; f < width; f++) {
                        posX = 0;
                        for (int c = 0; c < height; c++) {
                            //codigo de prueba

                            //fin codifo de prueba
                            gc.setColor(matriz.get(f, c));
                            gc.fillRect(posX, posY, escala, escala);

                            posX += escala;
                        }
                        posY += escala;
                    }
//                }
//            };
//            t.start();
            System.out.println("Pintando en ejecución");
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
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
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
        System.out.println("(" + punto.x + ", " + punto.y + ")");
        //hacia delante -1
        //hacia abajo 1
        switch (rotacion) {
            case -1 ->
                ++escala;
            case 1 ->
                --escala;
        }
        if (escala == 0) {
            escala = 1;
        }
        this.pintar();
    }//GEN-LAST:event_formMouseWheelMoved

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        System.out.println("Click sobre panel Imagen...");
    }//GEN-LAST:event_formMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        System.out.println("Coordenadas presionado pressed...");
        var x = evt.getX();
        var y = evt.getY();
        System.out.print("x = " + x);
        System.out.println(", y = " + y);
        pointA = new Point(x, y);

    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        System.out.println("Coordenadas boton soltado");
        var x = evt.getX();
        var y = evt.getY();
        System.out.print("x = " + x);
        System.out.println(", y = " + y);
        pointB = new Point(x, y);

        if (setMuestra) {
            int filaA = (pointA.y / escala);
            int filaB = (pointB.y / escala);
            int colA = (pointA.x / escala);
            int colB = (pointB.x / escala);

//            setMuestra();
            imageProcessor.setMuestra(filaA, colA, filaB, colB);
            setMuestra = false;
        }

    }//GEN-LAST:event_formMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
