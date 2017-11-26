package exception.operation;

import exception.MuaException;

public class OperandTypeException extends MuaException {
    public OperandTypeException(String op, String expected, String found) {
        super(
                "Expecting operand of type `" + expected + "` for operation `" +
                        op + "`, but found `" + found + "`.",
                "OperandTypeException"
        );
    }
}
