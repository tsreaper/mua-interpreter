package lang.operation.control;

import interpreter.MuaRunner;
import lang.element.MuaElement;
import lang.element.MuaList;
import lang.operation.Operation;
import service.GlobalSettings;

public class OpIf extends Operation {
    private MuaRunner runner;
    private MuaRunner parentRunner;

    public OpIf() {
        super();
        operandNum = 3;
        name = "if";

        runner = new MuaRunner(false, GlobalSettings.interactive);
        parentRunner = null;
    }

    public void setParentRunner(MuaRunner runner) {
        parentRunner = runner;
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "bool");
        MuaList b = (MuaList) getOperand(1, "list");
        MuaList c = (MuaList) getOperand(2, "list");

        if (Boolean.valueOf(a.getValue())) {
            for (int i = 0; i < b.size(); i++) {
                runner.add(b.get(i));
            }
        } else {
            for (int i = 0; i < c.size(); i++) {
                runner.add(c.get(i));
            }
        }
        runner.run();

        if (runner.shouldStop()) {
            parentRunner.stop();
        }

        return null;
    }
}
