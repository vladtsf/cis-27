package ABCUtilityCompany;

import lab4.lib.In;

public class Consultant {
    private EdgeWeightedGraph G;
    private KruskalMST mst;
    
    
    public Consultant() {
        G = new EdgeWeightedGraph(new In("./wgraph.txt"));
        mst = new KruskalMST(G);
    }
    
    public void minimizePath() {
        for(Edge e : mst.edges()) {
            System.out.println(e);
        }
    }
    
    public void totalLength() {
        double length = 0;

        for(Edge e : mst.edges()) {
            length += e.weight();
        }
        
        System.out.println(length);
    }
}
