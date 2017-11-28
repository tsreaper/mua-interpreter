package lang.element;

import exception.element.InvalidExpressionException;
import exception.element.InvalidNumberException;
import util.Util;

public class MuaNumber extends MuaElement {
    private double value;

    public MuaNumber(String value) throws InvalidNumberException {
        try {
            // Raw number
            this.value = Double.valueOf(value);
        } catch (NumberFormatException e1) {
            try {
                // Expression
                this.value = Util.calcExpression(value);
            } catch (InvalidExpressionException e2) {
                throw new InvalidNumberException(value);
            }
        }
    }

    @Override
    public String getValue() {
        return String.valueOf(value);
    }
}
