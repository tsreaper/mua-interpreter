package lang.operation.control;

import exception.MuaException;
import interpreter.MuaRunner;
import lang.element.MuaElement;
import lang.element.MuaList;
import lang.operation.Operation;

public class OpRepeat extends Operation {
    private MuaRunner runner;

    public OpRepeat() {
        super();
        operandNum = 2;
        name = "repeat";
        runner = new MuaRunner();
    }

    @Override
    public MuaElement execute() throws MuaException {
        checkOperandNum();

        MuaElement a = getOperand(0, "number");
        MuaList b = (MuaList) getOperand(1, "list");
        int repeatTimes = Double.valueOf(a.getValue()).intValue();

        for (int i = 0; i < repeatTimes; i++) {
            for (int j = 0; j < b.size(); j++) {
                runner.add(b.get(j));
            }
            runner.run(false);

            if (runner.shouldStop()) {
                break;
            }
        }

        return null;
    }
}
