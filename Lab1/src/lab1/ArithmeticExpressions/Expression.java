package lab1.ArithmeticExpressions;

public abstract class Expression implements IExpression {
    protected static boolean isNumeric(Character ch) {
        return isNumeric(ch.toString());
    }
    
    protected static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    
    protected static double performOperation(double left, String operator, double right) {
        switch(operator) {
            case "*":
                return left * right;
            case "/":
                return left / right;
            case "+":
                return left + right;
            case "-":
                return left - right;
        }
        
        return Double.NaN;
    }
    
    protected static class Operators {
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
