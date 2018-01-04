package lang.operation.math;

import lang.element.MuaElement;
import lang.element.MuaNumber;
import lang.operation.Operation;

public class OpRandom extends Operation {
    public OpRandom() {
        super();
        operandNum = 1;
        name = "random";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "number");

        return new MuaNumber(String.valueOf(Math.random() * Double.valueOf(a.getValue())));
    }
}
