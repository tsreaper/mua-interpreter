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
    static private MuaRunner runner = new MuaRunner();

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
        return tokenizer.finished() && runner.finished();
    }

    static public void interpret(String line) throws MuaException {
        try {
            tokenizer.tokenize(line);

            while (tokenizer.hasNext()) {
                MuaElement result = runner.run(tokenizer.getElement());
                if (result != null && isInteractive) {
                    System.out.println(result.getValue());
                }
            }
        } catch (MuaException e) {
            clear();
            throw e;
        }
    }

    static private void clear() {
        tokenizer.clear();
        runner.clear();
    }
}
