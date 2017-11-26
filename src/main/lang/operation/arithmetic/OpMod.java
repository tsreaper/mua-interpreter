package lang.operation.arithmetic;

import lang.operation.arithmetic.ArithmeticOperation;

public class OpMod extends ArithmeticOperation {
    public OpMod() {
        super();
        name = "mod";
    }

    @Override
    protected double calculate(double x, double y) {
        return x % y;
    }
}
