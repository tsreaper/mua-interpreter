package lang.operation.io;

import lang.element.MuaElement;
import lang.operation.Operation;

public class OpPrint extends Operation {
    public OpPrint() {
        super();
        operandNum = 1;
        name = "print";
    }

    @Override
    public MuaElement execute() {
        assert canExecute();

        MuaElement a = getOperand(0, "?");
        System.out.println(a.getValue());

        return null;
    }
}
