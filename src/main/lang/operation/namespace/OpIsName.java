package lang.operation.namespace;

import exception.operation.OperandTypeException;
import lang.element.MuaBool;
import lang.element.MuaElement;
import lang.namespace.NamespaceManager;
import lang.operation.Operation;

public class OpIsName extends Operation {
    public OpIsName() {
        super();
        operandNum = 1;
        name = "isname";
    }

    @Override
    public MuaElement execute() throws OperandTypeException {
        assert canExecute();

        MuaElement a = getOperand(0, "word");

        return new MuaBool(NamespaceManager.isName(a.getValue()));
    }
}
