package lang.operation.control;

import lang.element.MuaElement;
import lang.element.MuaNumber;
import lang.operation.Operation;

public class OpIf extends Operation {
    public OpIf() {
        super();
        operandNum = 3;
        name = "if";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "bool");
        MuaElement b = getOperand(1, "list");
        MuaElement c = getOperand(2, "list");

        OpRepeat repeat = new OpRepeat();
        repeat.addOperand(new MuaNumber("1"));
        if (Boolean.valueOf(a.getValue())) {
            repeat.addOperand(b);
        } else {
            repeat.addOperand(c);
        }
        repeat.execute();

        return null;
    }
}
