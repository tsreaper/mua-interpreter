package lang.operation.arithmetic;

public class OpDiv extends ArithmeticOperation {
    public OpDiv() {
        super();
        name = "div";
    }

    @Override
    protected double calculate(double x, double y) {
        return x / y;
    }
}
