package lang.operation.namespace;

import exception.namespace.NotInFunctionException;
import lang.element.MuaElement;
import lang.operation.Operation;
import service.namespace.NamespaceService;

public class OpExport extends Operation {
    public OpExport() {
        super();
        operandNum = 0;
        name = "export";
    }

    @Override
    public MuaElement execute() throws NotInFunctionException {
        checkOperandNum();

        if (NamespaceService.getService().isGlobal()) {
            throw new NotInFunctionException();
        }
        NamespaceService.getService().export();

        return null;
    }
}
