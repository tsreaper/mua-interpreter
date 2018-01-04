package lang.operation.type;

import lang.element.MuaElement;
import lang.element.MuaList;
import lang.operation.Operation;

public class OpList extends Operation {
    public OpList() {
        super();
        operandNum = 2;
        name = "list";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "?");
        MuaElement b = getOperand(1, "?");

        MuaList ret = new MuaList();
        ret.appendElement(a);
        ret.appendElement(b);

        return ret;
    }
}
