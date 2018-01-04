package lang.operation.type;

import lang.element.MuaElement;
import lang.element.MuaList;
import lang.operation.Operation;

public class OpJoin extends Operation {
    public OpJoin() {
        super();
        operandNum = 2;
        name = "join";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaList a = (MuaList) getOperand(0, "list");
        MuaElement b = getOperand(1, "?");

        MuaList ret = new MuaList();
        for (int i = 0; i < a.size(); i++) {
            ret.appendElement(a.get(i));
        }
        ret.appendElement(b);

        return ret;
    }
}
