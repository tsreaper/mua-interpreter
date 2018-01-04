package lang.operation.type;

import lang.element.MuaBool;
import lang.element.MuaElement;
import lang.element.MuaList;
import lang.operation.Operation;

public class OpIsList extends Operation {
    public OpIsList() {
        super();
        operandNum = 1;
        name = "islist";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "?");

        return new MuaBool(a instanceof MuaList);
    }
}
