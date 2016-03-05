package lab1.ArithmeticExpressions;

public class InfixExpression extends Expression {
    private final String expression;
    
    public InfixExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return this.expression;
    }
    
    @Override
    public double evaluate() {
        return new PostfixExpression(this).evaluate();
    }
}
