package lang.operation.comparison;

public class OpNe extends ComparisonOperation {
    public OpNe() {
        super();
        name = "ne";
    }

    @Override
    protected boolean compareNumber(double x, double y) {
        return x != y;
    }

    @Override
    protected boolean compareWord(String x, String y) {
        return x.compareTo(y) != 0;
    }
}
