package lang.operation.control;

import lang.element.MuaElement;
import lang.operation.Operation;

public class OpStop extends Operation {
    public OpStop() {
        super();
        operandNum = 0;
        name = "stop";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();
        return null;
    }
}
