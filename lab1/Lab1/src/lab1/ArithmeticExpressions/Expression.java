package lab1.ArithmeticExpressions;

import java.util.Arrays;
import java.util.List;

public abstract class Expression implements IExpression {
    public static List<Character> Operands = Arrays.asList(
        '1', '2', '3', '4', '5', '6', '7'
    );
    
    public static boolean isNumeric(Character ch) {
        try {
            Integer.parseInt(ch.toString());
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    
    public static class Operators {
        public static int getPrecedence(String operator) {
            switch(operator) {
                case "*":
                    return 3;
                case "/":
                    return 3;
                case "+":
                    return 2;
                case "-":
                    return 2;
                case "(":
                    return 1;
                default:
                    return -1;
            }
            
        }
    }    
}
