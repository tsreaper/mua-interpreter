package exception.element;

import exception.MuaException;

public class EmptyListException extends MuaException {
    public EmptyListException(String op) {
        super("Cannot apply operation `" + op + "` on an empty list.", "EmptyListException");
    }
}
