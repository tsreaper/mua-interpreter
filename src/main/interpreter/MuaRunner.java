package interpreter;

import exception.MuaException;
import exception.operation.NoSuchFunctionException;
import lang.element.MuaElement;
import lang.element.MuaOperation;
import lang.operation.ICanExecute;
import lang.operation.OperationUtil;
import lang.operation.control.OpStop;
import service.GlobalSettings;

import java.util.ArrayList;

public class MuaRunner {
    private ArrayList<MuaElement> elements;
    private ArrayList<ICanExecute> stack;
    private boolean shouldStop;

    public MuaRunner() {
        elements = new ArrayList<>();
        stack = new ArrayList<>();
        shouldStop = false;
    }

    public boolean finished() {
        return (elements.size() == 0 && stack.size() == 0) || shouldStop;
    }

    public boolean shouldStop() {
        return shouldStop;
    }

    public void clear() {
        elements.clear();
        stack.clear();
        shouldStop = false;
    }

    public void add(MuaElement element) throws NoSuchFunctionException {
        elements.add(element);
    }

    public void run(boolean flowInput) throws MuaException {
        if (shouldStop) {
            return;
        }

        while (elements.size() > 0) {
            MuaElement element = elements.remove(0);
            if (element instanceof MuaOperation) {
                if (((MuaOperation) element).isOperation()) {
                    stack.add(OperationUtil.getOperation(element.getValue()));
                } else {
                    stack.add(OperationUtil.getFunction(element.getValue()));
                }
            } else {
                stack.add(element);
            }

            // Check for `stop` operation
            if (stack.get(stack.size() - 1) instanceof OpStop) {
                shouldStop = true;
                break;
            }

            while (stack.size() > 1 && stack.get(stack.size() - 1).canExecute()) {
                ICanExecute operation = stack.remove(stack.size() - 1);
                stack.get(stack.size() - 1).addOperand(operation);
            }

            if (stack.size() == 1 && stack.get(0).canExecute()) {
                MuaElement result = stack.remove(0).execute();
                if (result != null && GlobalSettings.interactive) {
                    System.out.println(result.getValue());
                }
            }
        }

        if (!flowInput) {
            // Not flow input, force to execute the remaining operations
            forceExecute();
        }
    }

    void forceExecute() throws MuaException {
        if (shouldStop) {
            return;
        }

        for (int i = stack.size() - 1; i >= 0; i--) {
            assert !stack.get(i).canExecute();
            stack.get(i).execute();
        }
    }
}
