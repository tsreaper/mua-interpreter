package exception.element;

import exception.MuaException;

public class InvalidExpressionException extends MuaException {
    public InvalidExpressionException(String hint) {
        super("Invalid expression. " + hint, "InvalidExpressionException");
    }
}
