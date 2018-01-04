package lang.operation.function;

import interpreter.MuaRunner;
import lang.element.MuaElement;
import lang.element.MuaList;
import lang.operation.Operation;
import service.GlobalSettings;
import service.namespace.NamespaceService;

public class OpFunction extends Operation {
    private MuaList params;
    private MuaList operations;
    private MuaRunner runner;

    public OpFunction(String name, MuaList params, MuaList operations) {
        super();
        this.operandNum = params.size();
        this.name = name;
        this.params = params;
        this.operations = operations;
        runner = new MuaRunner(false, GlobalSettings.interactive);
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        // Add and initialize new namespace
        NamespaceService.getService().addNamespace(NamespaceService.getService().getNamespaceByKey(name));
        for (int i = 0; i < operandNum; i++) {
            NamespaceService.getService().bindElement(params.get(i).getValue(), getOperand(i, "?"));
        }

        // Run function
        for (int i = 0; i < operations.size(); i++) {
            runner.add(operations.get(i));
        }
        runner.run();

        // Return from function
        return NamespaceService.getService().removeNamespace();
    }
}
