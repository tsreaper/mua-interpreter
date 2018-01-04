package exception.namespace;

import exception.MuaException;

public class NotInFunctionException extends MuaException {
    public NotInFunctionException() {
        super("No function namespace found.", "NotInFunctionException");
    }
}
