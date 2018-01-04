package exception.element;

import exception.MuaException;

public class EmptyWordException extends MuaException {
    public EmptyWordException(String op) {
        super("Cannot apply operation `" + op + "` on an empty word.", "EmptyWordException");
    }
}
