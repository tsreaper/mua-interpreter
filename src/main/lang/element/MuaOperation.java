package lang.element;

import lang.operation.OperationUtil;

public class MuaOperation extends MuaElement {
    private String value;
    private boolean isOperation;

    public MuaOperation(String value) {
        this.value = value;
        isOperation = OperationUtil.isOperation(value);
    }

    @Override
    public String getValue() {
        return value;
    }

    public boolean isOperation() {
        return isOperation;
    }
}
