package lang.operation.namespace;

import lang.element.MuaElement;
import lang.operation.Operation;
import service.namespace.NamespaceService;
import util.Util;

import java.util.HashMap;

public class OpSave extends Operation {
    public OpSave() {
        super();
        operandNum = 1;
        name = "save";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "word");

        HashMap<String, MuaElement> wordMap = new HashMap<>();
        for (String key : NamespaceService.getService().getKeys()) {
            wordMap.put(key, NamespaceService.getService().getBoundedElement(key));
        }
        Util.mnsWrite(a.getValue(), wordMap);

        return null;
    }
}
