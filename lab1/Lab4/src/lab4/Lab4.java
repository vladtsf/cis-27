/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab4.digraphs.*;

/**
 *
 * @author vladt
 */
public class Lab4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        cc();
    }
    
    private static void cc() {
        try {
            Digraph G = Digraph.createFrom(new FileInputStream("./graph591.txt"));
            KosarajuSharirSCC cc = new KosarajuSharirSCC(G);
            
            int[] ids = cc.id();
            
            // results visualisation            
            ArrayList<Integer>[] comps = (ArrayList<Integer>[]) new ArrayList[cc.count()];
            
            for(int i = 0; i < cc.count(); i++) {
                comps[i] = new ArrayList<>();
            }
            
            for(int v = 0; v < G.V(); v++) {
                comps[ids[v]].add(v);
            }
            
            for(int i = 0; i < cc.count(); i++) {
                System.out.println(i + ":" + Arrays.toString(comps[i].toArray()));
            }

            
        } catch (Exception ex) {
            if(ex instanceof IOException) {
                System.out.println("File format is corrupt");
            } else if(ex instanceof FileNotFoundException) {
                System.out.println("File 'graph591.txt' doesn't exist");
            }
            
        } 
    }
    
}
