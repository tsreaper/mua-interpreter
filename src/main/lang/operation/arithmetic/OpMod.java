package lang.operation.arithmetic;

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
