package lang.operation.namespace;

import exception.operation.InvalidWordException;
import exception.operation.OperandTypeException;
import lang.element.MuaElement;
import lang.namespace.NamespaceManager;
import lang.operation.OperationUtil;
import lang.operation.Operation;

public class OpMake extends Operation {
    public OpMake() {
        super();
        operandNum = 2;
        name = "make";
    }

    @Override
    public MuaElement execute() throws OperandTypeException, InvalidWordException {
        assert canExecute();

        MuaElement a = getOperand(0, "word");
        MuaElement b = getOperand(1, "?");
        if (OperationUtil.isOperation(a.getValue())) {
            throw new InvalidWordException(a.getValue());
        }
        NamespaceManager.bindElement(a.getValue(), b);

        return null;
    }
}
