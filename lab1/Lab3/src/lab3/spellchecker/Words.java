package lab3.spellchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Words {
    private String path = "words.txt";
    
    private HashMap<String, Boolean> dictionary;
    
    public void read() throws IOException {
        dictionary = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
               dictionary.put(line, true);
            }
        }        
    }
    
    public boolean isWord(String word) {
        return dictionary.get(word) != null;
    }
}
