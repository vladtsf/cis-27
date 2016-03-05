package lab1.ArithmeticExpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

// this implementation does not support negative values
public class PostfixExpression extends Expression {
    private ArrayList<String> output;
    
    // since the program is created for educational purposes,
    // the case when expression contains more than one consecutive spaces
    // will not be handled.
    public PostfixExpression(String expression) {
        output = new ArrayList<>(Arrays.asList(expression.split(" ")));
    }
    
    public PostfixExpression(InfixExpression expression) {
        output = new ArrayList<>();
        Stack<String> buffer = new Stack<>();
        
        ArrayList<String> tokens = parseInput(expression.toString());
                    
        for(String input : tokens) {
            switch(input) {
                case "(":
                    buffer.push(input);
                    break;
                case ")":
                    String top = buffer.pop();
                    while(!top.equals("(")) {
                        output.add(top);
                        top = buffer.pop();
                    }
                    break;
                case "+":                
                case "-":
                case "*":
                case "/":
                    // if the input is an operator, pup the stack until the top is lower precedence
                    while(!buffer.empty() && 
                        Operators.getPrecedence(buffer.peek()) >= Operators.getPrecedence(input)) {
                        output.add(buffer.pop());
                    }

                    // push the operator                
                    buffer.push(input);
                    break;                    
                default:
                    // if the input is an operand, write to output
                    output.add(input);
                    break;
            }
        }
        
        while(!buffer.empty()) {
            output.add(buffer.pop());
        }
    }
    
    // the method converts an expression string to a list of tokens
    // this is necessary to support multi-digit numbers   
    private ArrayList<String> parseInput(String expression) {
        ArrayList<String> result = new ArrayList<>();
        
        String token = "";
        
        for(char input : expression.toCharArray()) {
            // ignore spaces because they don't have any value to us           
            if(input == ' ') {
                continue;
            }
            
            if(isNumeric(input)) {
                // if it's a digit, keep on forming a number token               
                token += input;
            } else if(token.length() > 0) {
                // if it's an operator, but there's a number token, finalize the token          
                result.add(token);
                result.add("" + input);
                token = "";
            } else {
                result.add("" + input);
            }
        }
        
        // if there's the last token left, add it        
        if(token.length() > 0) {
            result.add(token);
        }

        return result;
    }
    
    @Override
    public double evaluate() {
        return 0.0;
    }

    @Override
    public String toString() {
        return String.join(" ", output);
    }
}
