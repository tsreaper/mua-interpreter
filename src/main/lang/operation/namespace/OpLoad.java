package lang.operation.namespace;

import lang.element.MuaElement;
import lang.operation.Operation;
import service.namespace.NamespaceService;
import util.Util;

import java.util.HashMap;

public class OpLoad extends Operation {
    public OpLoad() {
        super();
        operandNum = 1;
        name = "load";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "word");

        HashMap<String, MuaElement> wordMap = Util.mnsRead(a.getValue());
        for (HashMap.Entry<String, MuaElement> entry : wordMap.entrySet()) {
            NamespaceService.getService().bindElement(entry.getKey(), entry.getValue());
        }

        return null;
    }
}
