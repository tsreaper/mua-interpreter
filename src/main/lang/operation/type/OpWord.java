package lang.operation.type;

import lang.element.MuaElement;
import lang.element.MuaWord;
import lang.operation.Operation;

public class OpWord extends Operation {
    public OpWord() {
        super();
        operandNum = 2;
        name = "word";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        MuaElement a = getOperand(0, "word");
        MuaElement b = getOperand(1, "word|number|bool");

        return new MuaWord(a.getValue() + b.getValue());
    }
}
