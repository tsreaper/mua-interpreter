package exception.operation;

import exception.MuaException;

public class InvalidNameException extends MuaException {
    public InvalidNameException(String name) {
        super("`" + name + "` is not a name.", "InvalidNameException");
    }
}
