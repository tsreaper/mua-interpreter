package lang.element;

import lang.operation.ICanExecute;

public abstract class MuaElement implements ICanExecute {
    public abstract String getValue();

    public void addOperand(ICanExecute operand) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public boolean canExecute() {
        return true;
    }

    public MuaElement execute() {
        return this;
    }
}
