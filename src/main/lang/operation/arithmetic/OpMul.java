package lang.operation.arithmetic;

import lang.operation.arithmetic.ArithmeticOperation;

public class OpMul extends ArithmeticOperation {
    public OpMul() {
        super();
        name = "mul";
    }

    @Override
    protected double calculate(double x, double y) {
        return x * y;
    }
}
