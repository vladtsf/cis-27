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
