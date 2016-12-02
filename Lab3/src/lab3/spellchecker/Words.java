package lab3.spellchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Words {
    private final String path = "words.txt";
    public static Pattern acceptable = Pattern.compile("(\\w|\\'|\\d)+");
    
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
    
    public boolean hasWord(String word) {
        return dictionary.get(word) != null;
    }
}
