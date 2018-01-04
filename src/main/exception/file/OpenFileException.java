package exception.file;

import exception.MuaException;

public class OpenFileException extends MuaException {
    public OpenFileException(String filename) {
        super("Failed to open file `" + filename + "`.", "OpenFileException");
    }
}
