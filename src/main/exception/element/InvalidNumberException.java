package exception.element;

import exception.MuaException;

public class InvalidNumberException extends MuaException {
    public InvalidNumberException(String value) {
        super("Invalid number `" + value + "`.", "InvalidNumberException");
    }
}
