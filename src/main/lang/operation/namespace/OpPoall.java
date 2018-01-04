package lang.operation.namespace;

import lang.element.MuaElement;
import lang.operation.Operation;
import service.namespace.NamespaceService;
import util.Util;

import java.util.HashMap;

public class OpPoall extends Operation {
    public OpPoall() {
        super();
        operandNum = 0;
        name = "poall";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        HashMap<String, MuaElement> wordMap = new HashMap<>();
        for (String key : NamespaceService.getService().getAllNamespaceKeys()) {
            wordMap.put(key, NamespaceService.getService().getBoundedElement(key));
        }
        System.out.print(Util.showNamespace(wordMap));

        return null;
    }
}
