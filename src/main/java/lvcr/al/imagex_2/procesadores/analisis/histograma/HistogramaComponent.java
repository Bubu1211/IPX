package lvcr.al.imagex_2.procesadores.analisis.histograma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import javax.swing.JFrame;

public class HistogramaComponent extends javax.swing.JPanel {
    
    private HashMap<Integer, Integer> frecuencias;

    public HistogramaComponent() {
        initComponents();
    }

    public HashMap<Integer, Integer> getFrecuencias() {
        return frecuencias;
    }

    public void setFrecuencias(HashMap<Integer, Integer> frecuencias) {
        this.frecuencias = frecuencias;
    }
    
    public void createHistogramaC(){
        repaint();
    }
    
    @Override
    public void paint(Graphics g){
        
        int widthG = 530;
        Point origen = new Point(10, 110);
        
        Graphics2D gc = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.drawLine(10, 10, origen.x, origen.y); //eje y
        g.drawLine(10, origen.y, 10+widthG, origen.y);          //eje x
        
        if(frecuencias != null){
            System.out.println("Pintando componente frecuencias");
            //obtener la mayor de las frecuencias
            var valores = frecuencias.values().toArray();
            int mayorFrecuencia = 0;
            for(int i = 0; i<valores.length; i++){
                if(((int)valores[i]) > mayorFrecuencia)
                    mayorFrecuencia = (int) valores[i];
            }
            
            
            g.drawString(mayorFrecuencia+"", 10, 10);
            
            int unidad_h = 100 / mayorFrecuencia ;
            int unidad_w = widthG / frecuencias.size();
            System.out.println("unidad_w = " + unidad_w);
            System.out.println("unidad_h = " + unidad_h);
            System.out.println("size: "+frecuencias.size());
            
            var keySet = frecuencias.keySet();
            int i = 1;
            int key = 0;
            int punto = 0;
            Color color;
            var iterator = keySet.iterator();
            int f;
            while(iterator.hasNext()){
                key = iterator.next();
                color = new Color(key);
                f = frecuencias.get(key);
                System.out.println("color: "+color + ", frecuencia : "+f);
                punto = origen.x + (unidad_w * i);
                g.setColor(Color.blue);
                g.drawLine(punto, origen.y, punto, origen.y-f*unidad_h);
                g.setColor(color);
                g.fillRect(punto-12, origen.y+2, 25, 25);
                g.setColor(Color.BLACK);
                g.drawRect(punto-12, origen.y+2, 25, 25);
                i++;
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(550, 120));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String[] args) {
        JFrame j = new JFrame();
        j.getContentPane().add(new HistogramaComponent());
        j.setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    // End of variables declaration//GEN-END:variables
}
