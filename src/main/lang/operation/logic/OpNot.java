package lang.operation.logic;

import lang.element.MuaBool;
import lang.element.MuaElement;
import lang.operation.Operation;

public class OpNot extends Operation {
    public OpNot() {
        super();
        operandNum = 1;
        name = "not";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "bool");

        return new MuaBool(!Boolean.valueOf(a.getValue()));
    }
}
