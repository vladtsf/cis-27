package lab4.digraphs;

import java.util.ArrayDeque;
import java.util.Deque;


public class DepthFirstOrder {
    private final boolean[] marked;
    private final Deque<Integer> reversePost;
    private final Digraph G;
    
    public DepthFirstOrder(Digraph G) {
        this.G = G;
        reversePost = new ArrayDeque<>();
        marked = new boolean[G.V()];
        
        for(int v = 0; v < G.V(); v++) {
            if(!marked[v]) {
                dfs(v);
            }
        }
    }
    
    private void dfs(int v) {
        marked[v] = true;
        
        for(int w : G.adj(v)) {
            if(!marked[w]) {
                dfs(w);
            }
        }
        
        reversePost.push(v);
    }
    
    public Deque<Integer> reversePost() {
        return reversePost;
    }
}
