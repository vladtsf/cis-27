package lab4.digraphs;

import java.io.*;
import java.util.LinkedList;

// simplified version of the Digraph class from the book
public class Digraph {
    private final int V;
    private int E;
    private final LinkedList<Integer>[] adj;
    
    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        
        for(int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }
    
    public int V() {
        return V;
    }
    
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }
    
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
       
    public static Digraph createFrom(FileInputStream fis) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
        String[] vertices;
        
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());
        int v, w;

        Digraph g = new Digraph(V);
        
        while((line = br.readLine()) != null) {
            vertices = line.split("\\s+");
            v = Integer.parseInt(vertices[0]);
            w = Integer.parseInt(vertices[1]);
            g.addEdge(v, w);
        }

        return g;
    }
    
    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }
}
