package lvcr.al.imagex_2.vista;

import java.io.File;
import javax.swing.JFileChooser;
import lvcr.al.imagex_2.procesadores.ImageProcessor;
import lvcr.al.imagex_2.procesadores.estructuras.Estructura;

public class VentanaPrincipal extends javax.swing.JFrame {

    private PanelImagen panelImagen;
    private ImageProcessor imageProcessor;

    public VentanaPrincipal() {
        initComponents();
        imageProcessor = new ImageProcessor();
        panelImagen = new PanelImagen(imageProcessor, panel);
        panelImagen.setBounds(0, 0, 800, 800);
        
        panel.add(panelImagen, java.awt.BorderLayout.CENTER);
        panelImagen.setContenedor(panel);
        panelImagen.pintar();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemAbrirImagen = new javax.swing.JMenuItem();
        itemBinarizar = new javax.swing.JMenu();
        itemBinarizacionEscalaGrises = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        itemDilatacion = new javax.swing.JMenuItem();
        itemErosion = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        muestra = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setLayout(new java.awt.BorderLayout());
        jScrollPane1.setViewportView(panel);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Archivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Abrir proyecto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Imagen");

        itemAbrirImagen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemAbrirImagen.setText("Abrir Imagen");
        itemAbrirImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAbrirImagenActionPerformed(evt);
            }
        });
        jMenu2.add(itemAbrirImagen);

        itemBinarizar.setText("Binarizar");

        itemBinarizacionEscalaGrises.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemBinarizacionEscalaGrises.setText("Binarización escalas de grises");
        itemBinarizacionEscalaGrises.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBinarizacionEscalaGrisesActionPerformed(evt);
            }
        });
        itemBinarizar.add(itemBinarizacionEscalaGrises);

        jMenu2.add(itemBinarizar);

        jMenu3.setText("Operaciones");

        itemDilatacion.setText("Dilatar");
        itemDilatacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDilatacionActionPerformed(evt);
            }
        });
        jMenu3.add(itemDilatacion);

        itemErosion.setText("Erosion");
        itemErosion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemErosionActionPerformed(evt);
            }
        });
        jMenu3.add(itemErosion);

        jMenu2.add(jMenu3);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Tools");

        muestra.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        muestra.setText("Seleccionar muestra");
        muestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraActionPerformed(evt);
            }
        });
        jMenu4.add(muestra);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //presionar el botón
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.panelImagen.pintar();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void itemAbrirImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAbrirImagenActionPerformed
        // TODO add your handling code here:
        //Mostrar JFileChooser 
        JFileChooser fileChooser = new JFileChooser("G:/Mi unidad/Universidad/proyectos/Image X/Imagenes imageX");
        int seleccion = fileChooser.showSaveDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            //Recuperar archivo
            File fichero = fileChooser.getSelectedFile();
            //enviar archivo a ImageProcessor
            imageProcessor.abrirImagen(fichero);
            //Pintar Imagen
            panelImagen.pintarImagen(imageProcessor.getMatriz());
            
        }
    }//GEN-LAST:event_itemAbrirImagenActionPerformed

    private void itemBinarizacionEscalaGrisesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBinarizacionEscalaGrisesActionPerformed
        // TODO add your handling code here:
        
        var binarizado = imageProcessor.binary_grey(121);
        var imagenBinarizada = imageProcessor.binaryBwToImagen(binarizado);
        panelImagen.pintarImagen(imagenBinarizada);
    }//GEN-LAST:event_itemBinarizacionEscalaGrisesActionPerformed

    private void itemDilatacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDilatacionActionPerformed
        // TODO add your handling code here:
        //Dilatacion de una imagen binarizada
        
        Thread t = new Thread(){
            @Override
            public void run(){
                var m = imageProcessor.dilatacion(panelImagen.getMatriz(), java.awt.Color.BLACK);
                panelImagen.pintarImagen(m);
            }
        };
        t.start();
    }//GEN-LAST:event_itemDilatacionActionPerformed

    private void itemErosionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemErosionActionPerformed
        // TODO add your handling code here:
        //Erosion de la imagen
        
        Thread t = new Thread(){
            @Override
            public void run(){
                var m = imageProcessor.erosion(panelImagen.getMatriz(), java.awt.Color.BLACK, Estructura.VECINO_4);
                panelImagen.pintarImagen(m);
            }
        };
        t.start();
    }//GEN-LAST:event_itemErosionActionPerformed

    private void muestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraActionPerformed
        // TODO add your handling code here:
        panelImagen.setMuestra(true);
    }//GEN-LAST:event_muestraActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | 
                javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });

//        var m = new Matriz<Integer>(5,5,0);
//        m.llenadoAleatorio(10, 1);
//        m.printConsole();
//        int nCumulos = m.cumulos(1, 5);
//        System.out.println("nCumulos = " + nCumulos);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemAbrirImagen;
    private javax.swing.JMenuItem itemBinarizacionEscalaGrises;
    private javax.swing.JMenu itemBinarizar;
    private javax.swing.JMenuItem itemDilatacion;
    private javax.swing.JMenuItem itemErosion;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem muestra;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
