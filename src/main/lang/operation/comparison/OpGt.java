package lang.operation.comparison;

public class OpGt extends ComparisonOperation {
    public OpGt() {
        super();
        name = "gt";
    }

    @Override
    protected boolean compareNumber(double x, double y) {
        return x > y;
    }

    @Override
    protected boolean compareWord(String x, String y) {
        return x.compareTo(y) > 0;
    }
}
