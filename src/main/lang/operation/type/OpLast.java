package lang.operation.type;

import lang.element.MuaElement;
import lang.element.MuaList;
import lang.element.MuaWord;
import lang.operation.Operation;

public class OpLast extends Operation {
    public OpLast() {
        super();
        operandNum = 1;
        name = "last";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "word|list");

        if (a instanceof MuaWord) {
            int len = a.getValue().length();
            return new MuaWord(a.getValue().substring(len - 1, len));
        } else if (a instanceof MuaList) {
            int len = ((MuaList) a).size();
            return ((MuaList) a).get(len - 1);
        }
        // Impossible
        assert false;
        return null;
    }
}
