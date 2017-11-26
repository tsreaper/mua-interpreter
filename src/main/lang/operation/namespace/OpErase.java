package lang.operation.namespace;

import exception.operation.OperandTypeException;
import lang.element.MuaElement;
import lang.namespace.NamespaceManager;
import lang.operation.Operation;

public class OpErase extends Operation {
    public OpErase() {
        super();
        operandNum = 1;
        name = "erase";
    }

    @Override
    public MuaElement execute() throws OperandTypeException {
        assert canExecute();

        MuaElement a = getOperand(0, "word");
        NamespaceManager.eraseBoundedElement(a.getValue());

        return null;
    }
}
