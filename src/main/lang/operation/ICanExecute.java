package lang.operation;

import lang.element.MuaElement;

public interface ICanExecute {
    void addOperand(ICanExecute operand);

    boolean canExecute();

    MuaElement execute();
}
