package lang.operation.type;

import lang.element.MuaElement;
import lang.element.MuaList;
import lang.element.MuaWord;
import lang.operation.Operation;

public class OpFirst extends Operation {
    public OpFirst() {
        super();
        operandNum = 1;
        name = "first";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "word|list");

        if (a instanceof MuaWord) {
            return new MuaWord(a.getValue().substring(0, 1));
        } else if (a instanceof MuaList) {
            return ((MuaList) a).get(0);
        }
        // Impossible
        assert false;
        return null;
    }
}
