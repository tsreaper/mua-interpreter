package exception.element;

import exception.MuaException;

public class UnclosedListException extends MuaException {
    public UnclosedListException() {
        super("Unclosed List.", "UnclosedListException");
    }
}
