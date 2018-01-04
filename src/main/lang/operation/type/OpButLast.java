package lang.operation.type;

import lang.element.MuaElement;
import lang.element.MuaList;
import lang.element.MuaWord;
import lang.operation.Operation;

public class OpButLast extends Operation {
    public OpButLast() {
        super();
        operandNum = 1;
        name = "butlast";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "word|list");

        if (a instanceof MuaWord) {
            int len = a.getValue().length();
            return new MuaWord(a.getValue().substring(0, len - 1));
        } else if (a instanceof MuaList) {
            int len = ((MuaList) a).size();
            MuaList ret = new MuaList();
            for (int i = 0; i < len - 1; i++) {
                ret.appendElement(((MuaList) a).get(i));
            }
            return ret;
        }
        // Impossible
        assert false;
        return null;
    }
}
