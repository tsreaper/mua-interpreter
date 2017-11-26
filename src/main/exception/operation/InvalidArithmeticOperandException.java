package exception.operation;

import exception.MuaException;

public class InvalidArithmeticOperandException extends MuaException {
    public InvalidArithmeticOperandException(String operation, String operand) {
        super(
                "Invalid operand `" + operand + "` for arithmetic operation `" + operation + "`.",
                "InvalidArithmeticOperandException"
        );
    }
}
