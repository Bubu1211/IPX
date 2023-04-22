package lvcr.al.imagex_2.vista;

import java.io.File;
import javax.swing.JFileChooser;
import lvcr.al.imagex_2.ia.Cluster;
import lvcr.al.imagex_2.ia.KMean;
import lvcr.al.imagex_2.ia.OKMean;
import lvcr.al.imagex_2.procesadores.ImageProcessor;
import lvcr.al.imagex_2.procesadores.analisis.histograma.Histograma;
import lvcr.al.imagex_2.procesadores.estructuras.Estructura;

public class VentanaPrincipal extends javax.swing.JFrame {

    private PanelImagen panelImagen;
    private ImageProcessor imageProcessor;

    public VentanaPrincipal() {
        initComponents();
        
        this.setExtendedState(MAXIMIZED_BOTH);
        
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuImagen = new javax.swing.JMenu();
        itemAbrirImagen = new javax.swing.JMenuItem();
        itemMenuBinarizar = new javax.swing.JMenu();
        itemBinarizacionEscalaGrises = new javax.swing.JMenuItem();
        itemBinarizacionBw = new javax.swing.JMenuItem();
        itemMenuOperaciones = new javax.swing.JMenu();
        itemDilatacion = new javax.swing.JMenuItem();
        itemErosion = new javax.swing.JMenuItem();
        itemMenuFiltros = new javax.swing.JMenu();
        itemFiltroAddRojo = new javax.swing.JMenuItem();
        itemFiltroAddVerde = new javax.swing.JMenuItem();
        itemFiltroAddAzul = new javax.swing.JMenuItem();
        itemFiltroDeleteRojo = new javax.swing.JMenuItem();
        itemFiltroDeleteVerde = new javax.swing.JMenuItem();
        itemFiltroDeleteAzul = new javax.swing.JMenuItem();
        menuTools = new javax.swing.JMenu();
        muestra = new javax.swing.JMenuItem();
        crearHistograma = new javax.swing.JMenuItem();
        menuIa = new javax.swing.JMenu();
        itemKmeans = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setLayout(new java.awt.BorderLayout());
        jScrollPane1.setViewportView(panel);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        menuArchivo.setText("Archivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Abrir proyecto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuArchivo.add(jMenuItem1);

        jMenuBar1.add(menuArchivo);

        menuImagen.setText("Imagen");

        itemAbrirImagen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemAbrirImagen.setText("Abrir Imagen");
        itemAbrirImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAbrirImagenActionPerformed(evt);
            }
        });
        menuImagen.add(itemAbrirImagen);

        itemMenuBinarizar.setText("Binarizar");

        itemBinarizacionEscalaGrises.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemBinarizacionEscalaGrises.setText("Binarización escalas de grises");
        itemBinarizacionEscalaGrises.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBinarizacionEscalaGrisesActionPerformed(evt);
            }
        });
        itemMenuBinarizar.add(itemBinarizacionEscalaGrises);

        itemBinarizacionBw.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemBinarizacionBw.setText("Binarizar Blanco y negro");
        itemBinarizacionBw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBinarizacionBwActionPerformed(evt);
            }
        });
        itemMenuBinarizar.add(itemBinarizacionBw);

        menuImagen.add(itemMenuBinarizar);

        itemMenuOperaciones.setText("Operaciones");

        itemDilatacion.setText("Dilatar");
        itemDilatacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDilatacionActionPerformed(evt);
            }
        });
        itemMenuOperaciones.add(itemDilatacion);

        itemErosion.setText("Erosion");
        itemErosion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemErosionActionPerformed(evt);
            }
        });
        itemMenuOperaciones.add(itemErosion);

        menuImagen.add(itemMenuOperaciones);

        itemMenuFiltros.setText("Filtros");

        itemFiltroAddRojo.setText("Agregar Rojo");
        itemFiltroAddRojo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFiltroAddRojoActionPerformed(evt);
            }
        });
        itemMenuFiltros.add(itemFiltroAddRojo);

        itemFiltroAddVerde.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemFiltroAddVerde.setText("Agregar Verde");
        itemFiltroAddVerde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFiltroAddVerdeActionPerformed(evt);
            }
        });
        itemMenuFiltros.add(itemFiltroAddVerde);

        itemFiltroAddAzul.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemFiltroAddAzul.setText("Agregar Azul");
        itemFiltroAddAzul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFiltroAddAzulActionPerformed(evt);
            }
        });
        itemMenuFiltros.add(itemFiltroAddAzul);

        itemFiltroDeleteRojo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemFiltroDeleteRojo.setText("Eliminar Rojo");
        itemFiltroDeleteRojo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFiltroDeleteRojoActionPerformed(evt);
            }
        });
        itemMenuFiltros.add(itemFiltroDeleteRojo);

        itemFiltroDeleteVerde.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemFiltroDeleteVerde.setText("Eliminar Verde");
        itemFiltroDeleteVerde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFiltroDeleteVerdeActionPerformed(evt);
            }
        });
        itemMenuFiltros.add(itemFiltroDeleteVerde);

        itemFiltroDeleteAzul.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemFiltroDeleteAzul.setText("Eliminar Azul");
        itemFiltroDeleteAzul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFiltroDeleteAzulActionPerformed(evt);
            }
        });
        itemMenuFiltros.add(itemFiltroDeleteAzul);

        menuImagen.add(itemMenuFiltros);

        jMenuBar1.add(menuImagen);

        menuTools.setText("Tools");

        muestra.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        muestra.setText("Seleccionar muestra");
        muestra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraActionPerformed(evt);
            }
        });
        menuTools.add(muestra);

        crearHistograma.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        crearHistograma.setText("Crear Histograma");
        crearHistograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearHistogramaActionPerformed(evt);
            }
        });
        menuTools.add(crearHistograma);

        jMenuBar1.add(menuTools);

        menuIa.setText("IA");

        itemKmeans.setText("KMeans");
        itemKmeans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemKmeansActionPerformed(evt);
            }
        });
        menuIa.add(itemKmeans);

        jMenuBar1.add(menuIa);

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
        //Obtener escala de un slider 
        var binarizado = imageProcessor.binary_grey(121, true);
        var imagenBinarizada = imageProcessor.binaryBwToImagen(binarizado);
        panelImagen.pintarImagen(imagenBinarizada);
    }//GEN-LAST:event_itemBinarizacionEscalaGrisesActionPerformed

    private void itemDilatacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDilatacionActionPerformed
        // TODO add your handling code here:
        //Dilatacion de una imagen binarizada
        
        Thread t = new Thread(){
            @Override
            public void run(){
                var m = imageProcessor.dilatacion(panelImagen.getMatriz(), ImageProcessor.BLACK);
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
                var m = imageProcessor.erosion(panelImagen.getMatriz(), ImageProcessor.BLACK, Estructura.VECINO_4);
                panelImagen.pintarImagen(m);
            }
        };
        t.start();
    }//GEN-LAST:event_itemErosionActionPerformed

    private void muestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraActionPerformed
        // TODO add your handling code here:
        panelImagen.setMuestra(true);
    }//GEN-LAST:event_muestraActionPerformed

    private void crearHistogramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearHistogramaActionPerformed
        // TODO add your handling code here:
        //obtener la muestra : una matriz más pequeña de ImageProcessor
        var muestraMatriz = this.imageProcessor.getMuestra();
        //dar a una clase Histograma la matriz
        //Cargar el histograma
        Histograma histograma = new Histograma();
        histograma.applyHistogramaFilas(muestraMatriz);
        //mostrar en consola el histograma actual
        histograma.printHistograma();
        //mostrar la ventana con los histogramas
        histograma.viewHistograma();
        histograma.runView();
    }//GEN-LAST:event_crearHistogramaActionPerformed

    private void itemBinarizacionBwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBinarizacionBwActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemBinarizacionBwActionPerformed

    private void itemFiltroAddRojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFiltroAddRojoActionPerformed
        // TODO add your handling code here:
        panelImagen.pintarImagen(imageProcessor.filterAddRed());
        System.out.println("Filtro Azul");
    }//GEN-LAST:event_itemFiltroAddRojoActionPerformed

    private void itemFiltroAddVerdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFiltroAddVerdeActionPerformed
        // TODO add your handling code here:
        panelImagen.pintarImagen(imageProcessor.filterAddGreen());
        System.out.println("Filtro Azul");
    }//GEN-LAST:event_itemFiltroAddVerdeActionPerformed

    private void itemFiltroAddAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFiltroAddAzulActionPerformed
        // TODO add your handling code here:
        panelImagen.pintarImagen(imageProcessor.filterAddBlue());
        System.out.println("Filtro Azul");
    }//GEN-LAST:event_itemFiltroAddAzulActionPerformed

    private void itemFiltroDeleteRojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFiltroDeleteRojoActionPerformed
        panelImagen.pintarImagen(imageProcessor.deleteRed());
        System.out.println("Filtro Azul");
    }//GEN-LAST:event_itemFiltroDeleteRojoActionPerformed

    private void itemFiltroDeleteVerdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFiltroDeleteVerdeActionPerformed
        // TODO add your handling code here:
        panelImagen.pintarImagen(imageProcessor.deleteGreen());
        System.out.println("Filtro Azul");
    }//GEN-LAST:event_itemFiltroDeleteVerdeActionPerformed

    private void itemFiltroDeleteAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFiltroDeleteAzulActionPerformed
        // TODO add your handling code here:
        panelImagen.pintarImagen(imageProcessor.deleteBlue());
        System.out.println("Filtro Azul");
    }//GEN-LAST:event_itemFiltroDeleteAzulActionPerformed

    private void itemKmeansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemKmeansActionPerformed
        // TODO add your handling code here:
        //KMeans 
        KMean k = new KMean();
        Integer[] c = {ImageProcessor.BLACK, ImageProcessor.WHITE};
        k.kmeanSupervisado(KMean.SUPERVISADO, imageProcessor.getMatriz(), c );
        for (Cluster clu : k.clusters) {
            System.out.println("Centroide: "+clu.centroide);
            for(OKMean ok : clu.cluster){
                o
            }
        }
    }//GEN-LAST:event_itemKmeansActionPerformed

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
    private javax.swing.JMenuItem crearHistograma;
    private javax.swing.JMenuItem itemAbrirImagen;
    private javax.swing.JMenuItem itemBinarizacionBw;
    private javax.swing.JMenuItem itemBinarizacionEscalaGrises;
    private javax.swing.JMenuItem itemDilatacion;
    private javax.swing.JMenuItem itemErosion;
    private javax.swing.JMenuItem itemFiltroAddAzul;
    private javax.swing.JMenuItem itemFiltroAddRojo;
    private javax.swing.JMenuItem itemFiltroAddVerde;
    private javax.swing.JMenuItem itemFiltroDeleteAzul;
    private javax.swing.JMenuItem itemFiltroDeleteRojo;
    private javax.swing.JMenuItem itemFiltroDeleteVerde;
    private javax.swing.JMenuItem itemKmeans;
    private javax.swing.JMenu itemMenuBinarizar;
    private javax.swing.JMenu itemMenuFiltros;
    private javax.swing.JMenu itemMenuOperaciones;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuIa;
    private javax.swing.JMenu menuImagen;
    private javax.swing.JMenu menuTools;
    private javax.swing.JMenuItem muestra;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
