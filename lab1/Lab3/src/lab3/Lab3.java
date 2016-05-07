package lab3;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import lab3.spellchecker.Words;
import java.util.regex.Pattern;
import lab3.spellchecker.Suggestions;


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
        
        System.out.println("\n\nSpellchecking time!\n");
        // spellchecker
        spellcheck();
    }
    
    private static void spellcheck() {
//        Scanner s = new Scanner(System.in);

        try {
            Words words = new Words();
            words.read(); // words are now ready
//            System.out.println(words.hasWord("wowowowow"));
//            System.out.println(words.hasWord("porn"));
            
            while(true) {
                Scanner s = new Scanner(System.in);
                
                System.out.println("Enter a word:");
                
                try {
                    String word = s.next(Words.acceptable);
                    
                    if(words.hasWord(word)) {
                        System.out.println("no mistakes found");
                    } else {
                        System.out.println("I don't know this word... Did you mean:");
                        
                        new Suggestions(words, word).suggest().stream().forEach((suggestion) -> {
                            System.out.println(suggestion);
                        });
                        
                        System.out.println("");
                    }
                    
                } catch(InputMismatchException e) {
                    System.out.println("Please enter one word. No funny stuff, alright?");
                }
            }
            
            
        } catch (IOException ex) {
            System.out.println("Sorry, something went wrong with reading the word dictionary.");
        }
    }
    
}
