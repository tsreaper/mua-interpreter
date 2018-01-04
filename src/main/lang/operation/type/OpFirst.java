package lang.operation.type;

import exception.element.EmptyListException;
import exception.element.EmptyWordException;
import lang.element.MuaElement;
import lang.element.MuaList;
import lang.element.MuaWord;
import lang.operation.Operation;

public class OpFirst extends Operation {
    public OpFirst() {
        super();
        operandNum = 1;
        name = "first";
    }

    @Override
    public MuaElement execute() throws EmptyWordException, EmptyListException {
        checkOperandNum();

        MuaElement a = getOperand(0, "word|list");

        if (a instanceof MuaWord) {
            if (a.getValue().length() == 0) {
                throw new EmptyWordException(name);
            }
            return new MuaWord(a.getValue().substring(0, 1));
        } else if (a instanceof MuaList) {
            if (((MuaList) a).size() == 0) {
                throw new EmptyListException(name);
            }
            return ((MuaList) a).get(0);
        }
        // Impossible
        assert false;
        return null;
    }
}
