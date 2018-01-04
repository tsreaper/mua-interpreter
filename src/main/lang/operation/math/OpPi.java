package lang.operation.math;

import lang.element.MuaElement;
import lang.element.MuaNumber;
import lang.operation.Operation;

public class OpPi extends Operation {
    public OpPi() {
        super();
        operandNum = 0;
        name = "pi";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        return new MuaNumber("3.1415926535");
    }
}
