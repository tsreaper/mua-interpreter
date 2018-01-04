package lang.operation.type;

import lang.element.MuaBool;
import lang.element.MuaElement;
import lang.operation.Operation;

public class OpIsBool extends Operation {
    public OpIsBool() {
        super();
        operandNum = 1;
        name = "isbool";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "?");

        return new MuaBool(a instanceof MuaBool);
    }
}
