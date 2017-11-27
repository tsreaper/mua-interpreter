package interpretation;

import exception.MuaException;
import exception.operation.OperandNumberException;
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

    public void finishedThrowException() throws OperandNumberException {
        if (operations.size() > 0) {
            assert !operations.get(0).canExecute();
            operations.get(0).execute();
        }
    }

    public void clear() {
        operations.clear();
    }

    public MuaElement run(MuaElement element, boolean isInteractive) throws MuaException {
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
                if (isInteractive) {
                    System.out.println(result.getValue());
                }
                return result;
            }
        }

        return null;
    }
}
