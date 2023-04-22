/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lvcr.al.imagex_2.ia;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class Cluster {

    public List<OKMean> cluster;
    public Integer centroide;

    public Cluster(Integer centroide) {
        cluster = new ArrayList<>();
        this.centroide = centroide;
    }

    public void add(Integer color, int x, int y) {
        cluster.add(new OKMean(color, x, y));
    }

    
}
