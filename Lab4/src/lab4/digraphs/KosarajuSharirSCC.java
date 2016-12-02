package lab4.digraphs;

public class KosarajuSharirSCC {
    private final boolean[] marked;
    private final int[] id;
    private int count;
    private final Digraph G;
    
    public KosarajuSharirSCC(Digraph G) {
        this.G = G;
        marked = new boolean[G.V()];
        id = new int[G.V()];
        
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        
        for(int s : order.reversePost()) {
            if(!marked[s]) {
                dfs(s);
                count++;
            }
        }
    }
    
    private void dfs(int v) {
        marked[v] = true;
        id[v] = count;
        
        for(int w : G.adj(v)) {
            if(!marked[w]) {
                dfs(w);
            }
        }
    }
    
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }
    
    public int[] id() {
        return id;
    }
    
    public int id(int v) {
        return id[v];
    }
    
    public int count() {
        return count;
    }
}


/*
Stongly Connected Components in the Graph on p. 591
0:[21]
1:[2, 5, 6, 8, 9, 11, 12, 13, 15, 16, 18, 19, 22, 23, 25, 26, 28, 29, 30, 31, 32, 33, 34, 35, 37, 38, 39, 40, 42, 43, 44, 46, 47, 48, 49]
2:[41]
3:[7]
4:[3, 4, 17, 20, 24, 27, 36]
5:[14]
6:[45]
7:[1]
8:[0]
9:[10]
*/