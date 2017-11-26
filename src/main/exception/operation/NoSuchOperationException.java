package exception.operation;

import exception.MuaException;

public class NoSuchOperationException extends MuaException {
    public NoSuchOperationException(String operation) {
        super("No such operation called `" + operation + "`.", "NoSuchOperationException");
    }
}
