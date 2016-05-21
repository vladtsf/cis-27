/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import ABCUtilityCompany.Consultant;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
//        cc();
        ABC();
    }
    
    private static void ABC() {
        System.out.println("The ABC Utility Company");
        ABCUtilityCompany.Consultant abcman = new Consultant();
        System.out.println("Edges in the MST:");
        abcman.minimizePath();
        System.out.println("Total length of the cable:");
        abcman.totalLength();
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
            
            System.out.println("Stongly Connected Components in the Graph on p. 591");
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
