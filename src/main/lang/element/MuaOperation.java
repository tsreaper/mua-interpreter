package lang.element;

import exception.operation.NoSuchOperationException;
import lang.operation.OperationUtil;

public class MuaOperation extends MuaElement {
    private String value;

    public MuaOperation(String value) throws NoSuchOperationException {
        if (OperationUtil.isOperation(value)) {
            this.value = value;
        } else {
            throw new NoSuchOperationException(value);
        }
    }

    @Override
    public String getValue() {
        return value;
    }
}
