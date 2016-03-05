package lab1.ArithmeticExpressions;

public class ArithmeticExpressionEvaluator {
    private final PostfixExpression postfix;
    private final InfixExpression infix;
    
    public ArithmeticExpressionEvaluator(String expression) {
        infix = new InfixExpression(expression);
        postfix = new PostfixExpression(infix);
    }
    
    public void evaluate() {
        System.out.format("Infix: %s\n", infix.toString());
        System.out.format("Postfix: %s\n", postfix.toString());
        System.out.format("Result: %.2f%n\n", postfix.evaluate());
    }
}
