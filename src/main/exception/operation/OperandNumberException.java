package exception.operation;

import exception.MuaException;

public class OperandNumberException extends MuaException {
    public OperandNumberException(String operation, int expected, int found) {
        super(
                "Expecting " + expected + " operands for operation `" + operation +
                        "`, but found " + found + ".", "OperandNumberException");
    }
}
