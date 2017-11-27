package lang.operation.namespace;

import exception.operation.OperandTypeException;
import lang.element.MuaBool;
import lang.element.MuaElement;
import lang.operation.Operation;
import service.namespace.NamespaceService;

public class OpIsName extends Operation {
    public OpIsName() {
        super();
        operandNum = 1;
        name = "isname";
    }

    @Override
    public MuaElement execute() throws OperandTypeException {
        checkOperandNum();

        MuaElement a = getOperand(0, "word");

        return new MuaBool(NamespaceService.getService().isName(a.getValue()));
    }
}
