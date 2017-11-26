package lang.operation.logic;

import lang.element.MuaBool;
import lang.element.MuaElement;
import lang.operation.Operation;

public class OpAnd extends Operation {
    public OpAnd() {
        super();
        operandNum = 2;
        name = "and";
    }

    @Override
    public MuaElement execute() {
        assert canExecute();

        MuaElement a = getOperand(0, "bool");
        MuaElement b = getOperand(1, "bool");

        return new MuaBool(Boolean.valueOf(a.getValue()) && Boolean.valueOf(b.getValue()));
    }
}
