package lab3;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab3.spellchecker.Words;

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
        
        // spellchecker
        try {
            Words words = new Words();
            words.read();
            System.out.println(words.isWord("wowowowow"));
            System.out.println(words.isWord("porn"));
        } catch (IOException ex) {
            System.out.println("Sorry, something went wrong with reading the word dictionary.");
        }
    }
    
}
