package lang.operation.comparison;

import exception.operation.InvalidArithmeticOperandException;
import exception.operation.OperandTypeException;
import lang.element.MuaBool;
import lang.element.MuaElement;
import lang.element.MuaNumber;
import lang.operation.Operation;

abstract public class ComparisonOperation extends Operation {
    ComparisonOperation() {
        super();
        operandNum = 2;
    }

    @Override
    public MuaElement execute() throws OperandTypeException, InvalidArithmeticOperandException {
        assert canExecute();

        MuaElement a = getOperand(0, "number|word");
        MuaElement b = getOperand(1, "number|word");
        if (a instanceof MuaNumber || b instanceof MuaNumber) {
            double x, y;
            try {
                x = Double.valueOf(a.getValue());
            } catch (NumberFormatException e) {
                throw new InvalidArithmeticOperandException(name, a.getValue());
            }
            try {
                y = Double.valueOf(b.getValue());
            } catch (NumberFormatException e) {
                throw new InvalidArithmeticOperandException(name, b.getValue());
            }
            return new MuaBool(compareNumber(x, y));
        } else {
            return new MuaBool(compareWord(a.getValue(), b.getValue()));
        }
    }

    protected abstract boolean compareNumber(double x, double y);

    protected abstract boolean compareWord(String x, String y);
}
