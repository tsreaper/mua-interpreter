package lang.operation.namespace;

import exception.operation.OperandTypeException;
import lang.element.MuaElement;
import lang.operation.Operation;
import service.namespace.NamespaceService;

public class OpErase extends Operation {
    public OpErase() {
        super();
        operandNum = 1;
        name = "erase";
    }

    @Override
    public MuaElement execute() throws OperandTypeException {
        checkOperandNum();

        MuaElement a = getOperand(0, "word");
        NamespaceService.getService().eraseBoundedElement(a.getValue());

        return null;
    }
}
