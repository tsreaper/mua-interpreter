package lang.operation.type;

import lang.element.MuaBool;
import lang.element.MuaElement;
import lang.element.MuaList;
import lang.element.MuaWord;
import lang.operation.Operation;

public class OpIsEmpty extends Operation {
    public OpIsEmpty() {
        super();
        operandNum = 1;
        name = "isempty";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "word|list");

        if (a instanceof MuaWord) {
            return new MuaBool(a.getValue().length() == 0);
        } else if (a instanceof MuaList) {
            return new MuaBool(((MuaList) a).size() == 0);
        }
        return new MuaBool(false);
    }
}
