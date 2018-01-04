package lang.operation.type;

import lang.element.MuaElement;
import lang.element.MuaList;
import lang.operation.Operation;

public class OpSentence extends Operation {
    public OpSentence() {
        super();
        operandNum = 2;
        name = "sentence";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaList a = (MuaList) getOperand(0, "list");
        MuaList b = (MuaList) getOperand(1, "list");

        MuaList ret = new MuaList();
        for (int i = 0; i < a.size(); i++) {
            ret.appendElement(a.get(i));
        }
        for (int i = 0; i < b.size(); i++) {
            ret.appendElement(b.get(i));
        }

        return ret;
    }
}
