package lang.operation.comparison;

public class OpLt extends ComparisonOperation {
    public OpLt() {
        super();
        name = "lt";
    }

    @Override
    protected boolean compareNumber(double x, double y) {
        return x < y;
    }

    @Override
    protected boolean compareWord(String x, String y) {
        return x.compareTo(y) < 0;
    }
}
