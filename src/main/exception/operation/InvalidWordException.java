package exception.operation;

import exception.MuaException;

public class InvalidWordException extends MuaException {
    public InvalidWordException(String word) {
        super("Invalid word `" + word + "` as `" + word + "` is an operation.", "InvalidWordException");
    }
}
