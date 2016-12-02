package lab3.spellchecker;

import java.util.ArrayList;
import java.util.HashSet;

public class Suggestions {
    private final String pattern;
    private final Words dictionary;
    private final char[] alphabet = "abcdefghijklmnopqrstuvwxyz'0123456789".toCharArray();
    
    public ArrayList<String> suggest() {
        HashSet<String> suggestions = new HashSet<>();
        
        letterRemoval(suggestions);
        letterAddition(suggestions);
        adjacentCharacters(suggestions);
        
        return new ArrayList<>(suggestions);
    }
    
    private void letterRemoval(HashSet<String> suggestions) {
        String last = pattern.substring(0, pattern.length() - 1);
        String first = pattern.substring(1);
        
        if(dictionary.hasWord(first))
            suggestions.add(first);
        
        if(dictionary.hasWord(last))
            suggestions.add(last);
    } 
    
    private void letterAddition(HashSet<String> suggestions) {
        for(char ch : alphabet) {
            if(dictionary.hasWord(ch + pattern))
                suggestions.add(ch + pattern);
            
            if(dictionary.hasWord(pattern + ch)) 
                suggestions.add(pattern + ch);
        }
    } 
    
    private void adjacentCharacters(HashSet<String> suggestions) {
        for(int i = 1; i < pattern.length(); i++) {
            char[] split = pattern.toCharArray();
            char tmp = split[i];
            split[i] = split[i-1];
            split[i-1] = tmp;
            
            if(dictionary.hasWord(new String(split)))
                suggestions.add(new String(split));
        }
    }
    
    public Suggestions(Words dictionary, String pattern) {
        this.pattern = pattern;
        this.dictionary = dictionary;
    }
}

// OUTPUT
//Spellchecking time!
//
//Enter a word:
//ovie
//I don't know this word... Did you mean:
//vie
//movie
//
//Enter a word:
//worda
//I don't know this word... Did you mean:
//word
//
//Enter a word:
//aword
//I don't know this word... Did you mean:
//word
//
//Enter a word:
//owrd
//I don't know this word... Did you mean:
//word
//
//Enter a word:
//[]0129
//Please enter one word. No funny stuff, alright?
//Enter a word: