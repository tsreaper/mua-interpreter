package lang.operation.io;

import exception.element.InvalidNumberException;
import interpretation.MuaScanner;
import lang.element.MuaElement;
import lang.element.MuaNumber;
import lang.element.MuaWord;
import lang.operation.Operation;

public class OpRead extends Operation {
    public OpRead() {
        super();
        operandNum = 0;
        name = "read";
    }

    @Override
    public MuaElement execute() {
        checkOperandNum();

        String token = MuaScanner.next();

        try {
            return new MuaNumber(token);
        } catch (InvalidNumberException e) {
            return new MuaWord(token);
        }
    }
}
