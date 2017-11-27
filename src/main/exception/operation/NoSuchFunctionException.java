package exception.operation;

import exception.MuaException;

public class NoSuchFunctionException extends MuaException {
    public NoSuchFunctionException(String function) {
        super("No such function called `" + function + "`.", "NoSuchFunctionException");
    }
}
