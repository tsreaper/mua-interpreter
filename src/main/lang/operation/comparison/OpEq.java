package lang.operation.comparison;

public class OpEq extends ComparisonOperation {
    public OpEq() {
        super();
        name = "eq";
    }

    @Override
    protected boolean compareNumber(double x, double y) {
        return x == y;
    }

    @Override
    protected boolean compareWord(String x, String y) {
        return x.equals(y);
    }
}
