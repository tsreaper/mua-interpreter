package lang.operation.math;

import lang.element.MuaElement;
import lang.element.MuaNumber;
import lang.operation.Operation;

public class OpSqrt extends Operation {
    public OpSqrt() {
        super();
        operandNum = 1;
        name = "sqrt";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "number");

        return new MuaNumber(String.valueOf(Math.sqrt(Double.valueOf(a.getValue()))));
    }
}
