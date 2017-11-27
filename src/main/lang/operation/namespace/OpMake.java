package lang.operation.namespace;

import exception.operation.InvalidWordException;
import exception.operation.OperandTypeException;
import lang.element.MuaElement;
import lang.operation.Operation;
import lang.operation.OperationUtil;
import service.namespace.NamespaceService;

public class OpMake extends Operation {
    public OpMake() {
        super();
        operandNum = 2;
        name = "make";
    }

    @Override
    public MuaElement execute() throws OperandTypeException, InvalidWordException {
        checkOperandNum();

        MuaElement a = getOperand(0, "word");
        MuaElement b = getOperand(1, "?");
        if (OperationUtil.isOperation(a.getValue())) {
            throw new InvalidWordException(a.getValue());
        }
        NamespaceService.getService().bindElement(a.getValue(), b);

        return null;
    }
}
