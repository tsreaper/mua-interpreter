package lang.operation.type;

import lang.element.MuaBool;
import lang.element.MuaElement;
import lang.element.MuaWord;
import lang.operation.Operation;

public class OpIsWord extends Operation {
    public OpIsWord() {
        super();
        operandNum = 1;
        name = "isword";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "?");

        return new MuaBool(a instanceof MuaWord);
    }
}
