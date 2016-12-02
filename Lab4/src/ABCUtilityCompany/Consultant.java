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

/*

> OUTPUT:

The ABC Utility Company
Edges in the MST:
1-5 13.00000
41-43 14.00000
43-47 15.00000
18-32 15.00000
17-32 15.00000
5-7 15.00000
46-48 15.00000
3-8 15.00000
3-14 15.00000
2-3 15.00000
24-29 15.00000
14-21 16.00000
8-9 16.00000
0-6 17.00000
23-28 18.00000
1-6 18.00000
18-21 20.00000
33-49 20.00000
39-46 20.00000
26-31 20.00000
7-17 20.00000
4-5 20.00000
40-45 20.00000
34-42 20.00000
11-45 21.00000
25-31 21.00000
12-14 22.00000
10-12 22.00000
14-19 23.00000
41-45 23.00000
42-44 24.00000
33-38 25.00000
19-25 25.00000
33-39 25.00000
17-23 25.00000
15-20 25.00000
37-38 25.00000
36-39 25.00000
28-35 25.00000
27-34 25.00000
7-16 25.00000
34-41 25.00000
24-25 25.00000
40-46 28.00000
17-22 28.00000
13-15 28.00000
10-13 28.00000
30-37 30.00000
28-34 30.00000
Total length of the cable:
1035.0

> If you could build only one field station that minimizes the total cost of the cable used, which node would you choose for your field station?

a) In terms of cable usage, it doesn't matter which node is the station.
b) In terms of convenience, I would choose the node that's "in the middle" of the graph.
One of the ways to programmatically determine which node should be the station is
1. Go through the list of vertices and build a binary tree whose root is the current vertex.
2. Compare the binary trees in terms of their height. 
2.1. The lowest tree will be balanced better than the others. 
3. So, choose the root of this tree to be the station.

*/