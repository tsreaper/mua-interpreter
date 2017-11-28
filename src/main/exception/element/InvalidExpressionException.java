package exception.element;

import exception.MuaException;

public class InvalidExpressionException extends MuaException {
    public InvalidExpressionException(String exp) {
        super("Invalid expression `" + exp + "`.", "InvalidExpressionException");
    }
}
