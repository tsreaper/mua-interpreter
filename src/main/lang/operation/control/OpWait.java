package lang.operation.control;

import lang.element.MuaElement;
import lang.operation.Operation;

public class OpWait extends Operation {
    public OpWait() {
        super();
        operandNum = 1;
        name = "wait";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "number");

        try {
            Thread.sleep((int) Double.parseDouble(a.getValue()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
