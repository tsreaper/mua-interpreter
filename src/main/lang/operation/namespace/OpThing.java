package lang.operation.namespace;

import exception.operation.InvalidNameException;
import exception.operation.OperandTypeException;
import lang.element.MuaElement;
import lang.operation.Operation;
import service.namespace.NamespaceService;

public class OpThing extends Operation {
    public OpThing() {
        super();
        operandNum = 1;
        name = "thing";
    }

    @Override
    public MuaElement execute() throws OperandTypeException, InvalidNameException {
        checkOperandNum();

        MuaElement a = getOperand(0, "word");
        if (!NamespaceService.getService().isName(a.getValue())) {
            throw new InvalidNameException(a.getValue());
        }

        return NamespaceService.getService().getBoundedElement(a.getValue());
    }
}
