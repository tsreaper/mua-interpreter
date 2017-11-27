package lang.operation.arithmetic;

public class OpAdd extends ArithmeticOperation {
    public OpAdd() {
        super();
        name = "add";
    }

    @Override
    protected double calculate(double x, double y) {
        return x + y;
    }
}
