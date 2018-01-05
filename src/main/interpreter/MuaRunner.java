package interpreter;

import exception.MuaException;
import lang.element.MuaElement;
import lang.element.MuaOperation;
import lang.operation.ICanExecute;
import lang.operation.OperationUtil;
import lang.operation.control.OpStop;

import java.util.ArrayList;

public class MuaRunner {
    private ArrayList<MuaElement> elements;
    private ArrayList<ICanExecute> stack;

    private boolean flowInput;
    private boolean interactive;
    private boolean shouldStop;

    public MuaRunner(boolean flowInput, boolean interactive) {
        elements = new ArrayList<>();
        stack = new ArrayList<>();

        this.flowInput = flowInput;
        this.interactive = interactive;
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

    public void add(MuaElement element) {
        elements.add(element);
    }

    public ArrayList<MuaElement> run() throws MuaException {
        ArrayList<MuaElement> ret = new ArrayList<>();

        if (shouldStop) {
            return ret;
        }

        while (elements.size() > 0) {
            // Change element to operation
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

            // Run operations in stack
            while (stack.size() > 1 && stack.get(stack.size() - 1).canExecute()) {
                ICanExecute operation = stack.remove(stack.size() - 1);
                stack.get(stack.size() - 1).addOperand(operation);
            }

            if (stack.size() == 1 && stack.get(0).canExecute()) {
                MuaElement result = stack.remove(0).execute();
                ret.add(result);
                if (result != null && interactive) {
                    // Print result if needed
                    System.out.println(result.getValue());
                }
            }
        }

        if (!flowInput) {
            // Not flow input, force to execute the remaining operations
            forceExecute();
        }

        return ret;
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
