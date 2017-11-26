package exception;

public class MuaException extends RuntimeException {
    private String type;

    public MuaException(String message, String type) {
        super(message);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
