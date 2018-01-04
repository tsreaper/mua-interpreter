package lang.operation.type;

import lang.element.MuaElement;
import lang.element.MuaList;
import lang.element.MuaWord;
import lang.operation.Operation;

public class OpButFirst extends Operation {
    public OpButFirst() {
        super();
        operandNum = 1;
        name = "butfirst";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "word|list");

        if (a instanceof MuaWord) {
            int len = a.getValue().length();
            return new MuaWord(a.getValue().substring(1, len));
        } else if (a instanceof MuaList) {
            int len = ((MuaList) a).size();
            MuaList ret = new MuaList();
            for (int i = 1; i < len; i++) {
                ret.appendElement(((MuaList) a).get(i));
            }
            return ret;
        }
        // Impossible
        assert false;
        return null;
    }
}
