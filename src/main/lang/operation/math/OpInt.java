package lang.operation.math;

import lang.element.MuaElement;
import lang.element.MuaNumber;
import lang.operation.Operation;

public class OpInt extends Operation {
    public OpInt() {
        super();
        operandNum = 1;
        name = "int";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "number");

        return new MuaNumber(String.valueOf(Math.floor(Double.valueOf(a.getValue()))));
    }
}
