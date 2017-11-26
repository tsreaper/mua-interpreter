package lang.operation.arithmetic;

import lang.operation.arithmetic.ArithmeticOperation;

public class OpSub extends ArithmeticOperation {
    public OpSub() {
        super();
        name = "sub";
    }

    @Override
    protected double calculate(double x, double y) {
        return x - y;
    }
}
