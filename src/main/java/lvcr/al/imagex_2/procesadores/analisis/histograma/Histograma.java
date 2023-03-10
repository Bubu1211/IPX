package lvcr.al.imagex_2.procesadores.analisis.histograma;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import lvcr.al.imagex_2.matrices.Matriz;

public class Histograma {
    
    private HistogramaView view;
    private List<HistogramaComponent> componentes;
    private List<HashMap<Integer, Integer>> frecuencias;   //
    
    public Histograma(){
        view = new HistogramaView();
        frecuencias = new ArrayList<>();
        componentes = new ArrayList<>();
    }
    
    public void applyHistogramaFilas(Matriz<Color> m){
        HashMap<Integer, Integer> hash = null;
        Integer rgb = 0, fre = 0;
        for(int f = 0; f<m.getFilas(); f++){
            hash = new HashMap<>();
            for(int c = 0; c<m.getColumnas(); c++){
                rgb = m.get(f, c).getRGB();
                fre = hash.get(rgb);
                if(fre == null){
                    hash.put(rgb, 1);
                } else{
                    hash.replace(rgb, fre + 1);
                }
            }
            frecuencias.add(hash);
        }
    }
    
    public void runView(){
        //Crear componentes y meterlos en un view funcional que es una pantalla aparte
        view.setVisible(true);
    }
    
    public void viewHistograma(){
        componentes = new ArrayList<>();
        var it = frecuencias.iterator();
        HistogramaComponent c = null;
        HashMap<Integer, Integer> h = null;
        this.view = new HistogramaView();
        while(it.hasNext()){
            h = it.next();
            c = new HistogramaComponent();
            c.setFrecuencias(h);
            c.createHistogramaC();
            componentes.add(c);
            view.addHistogramaComponent(c);
        }
    }
    
    public void printHistograma(){
        Color color = null;
        Set<Integer> keys = null;
        int f = 0;
        for(var hash : frecuencias){
            System.out.println("\n\nFrecuencias: ");
            keys = hash.keySet();
            for(var k : keys){
                f = hash.get(k);
                color = new Color(k);
                System.out.println(color +": "+f);
            }
        }
    }
    
}
