package lab3;

public class Lab3 {

    
    public static void main(String[] args) {
        
        // Red-Black Tree
        RedBlackBST<Integer, Integer> tree = new RedBlackBST<>();
        
        for(int i = 0; i < 60; i++) {
            tree.put(i, i);
        }
        
        System.out.println(tree.toString());
        
        for(int i = 0; i < 20; i++) {
            tree.delete(i);
        }
        
        System.out.println(tree.toString());
    }
    
}
