package SentinelAlarmCompany;

import java.util.ArrayList;
import lab4.lib.In;

public class Consultant {
    private static final ArrayList<Integer> STATIONS = new ArrayList<Integer>() {{
        add(7);
        add(14);
        add(25);
        add(28);
    }};
    
    private final Customer[] customers;
    
    static class Customer {
        private final int id;
        private int servedBy;
        private double distanceFromStation;
        private Iterable<DirectedEdge> path;
        
        public Customer(int id) {
            this.id = id;
            this.distanceFromStation = Double.POSITIVE_INFINITY;
        }
        
        public void tryToServeBy(int stationId, BellmanFordSP sp) {
            if(sp.distTo(id) < distanceFromStation) {
                servedBy = stationId;
                distanceFromStation = sp.distTo(id);
                path = sp.pathTo(id);
            }
        }
        
        public int id() {
            return id;
        }
        
        public int station() {
            return servedBy;
        }
        
        public double distance() {
            return distanceFromStation;
        }
        
        public String path() {
            String res = "";
            boolean first = true;
            
            for(DirectedEdge e : path) {
                if(first) {
                    res += e.from();
                    first = false;
                }
                
                res += "->" + e.to();
            }
            
            return res;
        }
        
    }
    
    public Consultant() {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In("./wgraph.txt"));
        customers = new Customer[G.V()];
        
        // initialize the customer database
        for(int cid = 0; cid < G.V(); cid++) {
            if(!STATIONS.contains(cid)) {
                customers[cid] = new Customer(cid);
            }
        }
        
        for(DirectedEdge edge : G.edges()) {
            // so that we can go both ways
            G.addEdge(new DirectedEdge(edge.to(), edge.from(), edge.weight()));            
        }
        
        for(int station : STATIONS) {
            BellmanFordSP sp = new BellmanFordSP(G, station);
            
            for(Customer c : customers) {
                if(c != null) {
                    c.tryToServeBy(station, sp);
                }
            }
        }
    }
    
    public void showServiceTable() {
        System.out.println("Customers");
        System.out.println("id\tstation\tresponse\troute");
        
        for(Customer c : customers) {
            if(c != null) {
                System.out.println(
                    c.id + "\t" + 
                    c.station() + "\t" + 
                    c.distance() + "\t\t" + 
                    c.path()
                );
                        
            }
        }
    }
}

/*

OUTPUT:

Customers
id	station	response	route
0	7	52.0		7->5->6->0
1	7	28.0		7->5->1
2	14	30.0		14->3->2
3	14	15.0		14->3
4	7	31.0		7->4
5	7	15.0		7->5
6	7	35.0		7->5->6
8	14	30.0		14->3->8
9	14	46.0		14->3->9
10	14	44.0		14->12->10
11	28	99.0		28->35->41->45->11
12	14	22.0		14->12
13	14	64.0		14->15->13
15	14	36.0		14->15
16	7	25.0		7->16
17	7	20.0		7->17
18	14	36.0		14->21->18
19	14	23.0		14->19
20	14	35.0		14->20
21	14	16.0		14->21
22	28	30.0		28->22
23	28	18.0		28->23
24	25	25.0		25->24
26	25	30.0		25->26
27	28	43.0		28->27
29	25	40.0		25->24->29
30	25	30.0		25->30
31	25	21.0		25->31
32	7	30.0		7->32
33	25	85.0		25->37->33
34	28	30.0		28->34
35	28	25.0		28->35
36	25	63.0		25->30->36
37	25	50.0		25->37
38	25	61.0		25->31->38
39	25	80.0		25->37->39
40	28	60.0		28->35->40
41	28	55.0		28->35->41
42	28	50.0		28->34->42
43	28	67.0		28->34->43
44	28	74.0		28->34->42->44
45	28	78.0		28->35->41->45
46	28	88.0		28->35->40->46
47	28	82.0		28->34->43->47
48	28	103.0		28->35->40->46->48
49	25	105.0		25->37->33->49

> If you could build only one station that minimizes the total cost of the
cable used, which node would you choose for your field station.

In terms of cable, please see the results for The ABC Utility Company.

*/
