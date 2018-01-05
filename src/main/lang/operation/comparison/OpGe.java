package lang.operation.comparison;

public class OpGe extends ComparisonOperation {
    public OpGe() {
        super();
        name = "ge";
    }

    @Override
    protected boolean compareNumber(double x, double y) {
        return x >= y;
    }

    @Override
    protected boolean compareWord(String x, String y) {
        return x.compareTo(y) >= 0;
    }
}
