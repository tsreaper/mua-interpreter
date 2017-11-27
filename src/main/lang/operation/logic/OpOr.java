package lang.operation.logic;

import lang.element.MuaBool;
import lang.element.MuaElement;
import lang.operation.Operation;

public class OpOr extends Operation {
    public OpOr() {
        super();
        operandNum = 2;
        name = "or";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "bool");
        MuaElement b = getOperand(1, "bool");

        return new MuaBool(Boolean.valueOf(a.getValue()) || Boolean.valueOf(b.getValue()));
    }
}
