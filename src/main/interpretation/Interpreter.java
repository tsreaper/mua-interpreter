package interpretation;

import exception.MuaException;
import lang.element.MuaElement;
import lang.element.MuaOperation;
import lang.namespace.NamespaceManager;
import lang.operation.ICanExecute;
import lang.operation.OperationUtil;

import java.util.ArrayList;

public class Interpreter {
    static private Tokenizer tokenizer = new Tokenizer(true);
    static private ArrayList<ICanExecute> operations = new ArrayList<>();

    static private boolean isInteractive = false;

    static {
        NamespaceManager.addNamespace();
    }

    private Interpreter() {
    }

    static public void setIsInteractive(boolean value) {
        isInteractive = value;
    }

    static public boolean finished() {
        return tokenizer.finished() && operations.size() == 0;
    }

    static public void interpret(String line) throws MuaException {
        try {
            tokenizer.tokenize(line);

            while (tokenizer.hasNext()) {
                MuaElement element = tokenizer.getElement();
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
                    if (result != null && isInteractive) {
                        System.out.println(result.getValue());
                    }
                }
            }
        } catch (MuaException e) {
            clear();
            throw e;
        }
    }

    static private void clear() {
        tokenizer.clear();
        operations.clear();
    }
}
