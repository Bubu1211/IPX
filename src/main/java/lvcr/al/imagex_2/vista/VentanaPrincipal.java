package lvcr.al.imagex_2.vista;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import lvcr.al.imagex_2.matrices.Matriz;
import lvcr.al.imagex_2.procesadores.ImageProcessor;

public class VentanaPrincipal extends javax.swing.JFrame {

    private PanelImagen panelImagen;
    private ImageProcessor imageProcessor;
    private boolean blocked = false;

    public VentanaPrincipal() {
        initComponents();
        panelImagen = new PanelImagen();
        panelImagen.setBounds(0, 0, 800, 800);
        
        panel.add(panelImagen, java.awt.BorderLayout.CENTER);
        panelImagen.setContenedor(panel);
        panelImagen.pintar();
        imageProcessor = new ImageProcessor();
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
        jMenu3 = new javax.swing.JMenu();
        itemBinarizacionEscalaGrises = new javax.swing.JMenuItem();

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

        jMenu3.setText("Binarizar");

        itemBinarizacionEscalaGrises.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemBinarizacionEscalaGrises.setText("Binarización escalas de grises");
        itemBinarizacionEscalaGrises.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBinarizacionEscalaGrisesActionPerformed(evt);
            }
        });
        jMenu3.add(itemBinarizacionEscalaGrises);

        jMenu2.add(jMenu3);

        jMenuBar1.add(jMenu2);

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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
