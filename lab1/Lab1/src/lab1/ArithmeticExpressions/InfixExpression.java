package lab1.ArithmeticExpressions;

public class InfixExpression extends Expression {
    private String expression;
    
    public InfixExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return this.expression;
    }
    
    @Override
    public double evaluate() {
        return 0.0;
    }
}
