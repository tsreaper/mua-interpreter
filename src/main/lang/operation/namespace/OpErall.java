package lang.operation.namespace;

import lang.element.MuaElement;
import lang.operation.Operation;
import service.namespace.NamespaceService;

public class OpErall extends Operation {
    public OpErall() {
        super();
        operandNum = 0;
        name = "erall";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        NamespaceService.getService().eraseAll();

        return null;
    }
}
