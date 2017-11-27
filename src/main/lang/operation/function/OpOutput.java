package lang.operation.function;

import lang.element.MuaElement;
import lang.operation.Operation;
import service.namespace.NamespaceService;

public class OpOutput extends Operation {
    public OpOutput() {
        super();
        operandNum = 1;
        name = "output";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "?");
        NamespaceService.getService().setReturnValue(a);

        return null;
    }
}
