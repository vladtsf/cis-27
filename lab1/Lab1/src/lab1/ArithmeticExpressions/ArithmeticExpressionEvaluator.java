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

// Output
/*
2. Stacks - Evaluating Arithmetic Expressions
Infix: (300+23)*(43-21)/(84+7)
Postfix: 300 23 + 43 21 - * 84 7 + /
Result: 78.09

Infix: (4+8)*(6-5)/((3-2)*(2+2))
Postfix: 4 8 + 6 5 - * 3 2 - 2 2 + * /
Result: 3.00
*/