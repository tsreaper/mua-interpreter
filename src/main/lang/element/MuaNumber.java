package lang.element;

import exception.element.InvalidNumberException;

public class MuaNumber extends MuaElement {
    private double value;

    public MuaNumber(String value) throws InvalidNumberException {
        try {
            this.value = Double.valueOf(value);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(value);
        }
    }

    @Override
    public String getValue() {
        return String.valueOf(value);
    }
}
