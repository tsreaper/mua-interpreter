package lang.operation.arithmetic;

import exception.operation.InvalidArithmeticOperandException;
import exception.operation.OperandTypeException;
import lang.element.MuaElement;
import lang.element.MuaNumber;
import lang.operation.Operation;

abstract public class ArithmeticOperation extends Operation {
    ArithmeticOperation() {
        super();
        operandNum = 2;
    }

    @Override
    public MuaElement execute() throws OperandTypeException, InvalidArithmeticOperandException {
        checkOperandNum();

        MuaElement a = getOperand(0, "number|word");
        MuaElement b = getOperand(1, "number|word");
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

        return new MuaNumber(String.valueOf(calculate(x, y)));
    }

    protected abstract double calculate(double x, double y);
}
