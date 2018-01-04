package lang.operation.type;

import lang.element.MuaBool;
import lang.element.MuaElement;
import lang.element.MuaNumber;
import lang.operation.Operation;

public class OpIsNumber extends Operation {
    public OpIsNumber() {
        super();
        operandNum = 1;
        name = "isnumber";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "?");

        return new MuaBool(a instanceof MuaNumber);
    }
}
