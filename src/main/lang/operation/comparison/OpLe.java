package lang.operation.comparison;

public class OpLe extends ComparisonOperation {
    public OpLe() {
        super();
        name = "le";
    }

    @Override
    protected boolean compareNumber(double x, double y) {
        return x <= y;
    }

    @Override
    protected boolean compareWord(String x, String y) {
        return x.compareTo(y) <= 0;
    }
}
