package interpretation;

import exception.MuaException;
import lang.element.MuaElement;
import lang.element.MuaOperation;
import lang.operation.ICanExecute;
import lang.operation.OperationUtil;

import java.util.ArrayList;

public class MuaRunner {
    private ArrayList<ICanExecute> operations;

    public MuaRunner() {
        operations = new ArrayList<>();
    }

    public boolean finished() {
        return operations.size() == 0;
    }

    public void clear() {
        operations.clear();
    }

    public MuaElement run(MuaElement element) throws MuaException {
        if (element instanceof MuaOperation) {
            operations.add(OperationUtil.getOperation(element.getValue()));
        } else {
            operations.add(element);
        }

        while (operations.size() > 1 && operations.get(operations.size() - 1).canExecute()) {
            ICanExecute operation = operations.remove(operations.size() - 1);
            operations.get(operations.size() - 1).addOperand(operation);
        }

        if (operations.size() == 1 && operations.get(0).canExecute()) {
            MuaElement result = operations.remove(0).execute();
            if (result != null) {
                return result;
            }
        }

        return null;
    }
}
