package interpretation;

import exception.MuaException;
import exception.operation.OperandNumberException;
import lang.namespace.NamespaceManager;

public class Interpreter {
    static public boolean isInteractive = false;
    static private Tokenizer tokenizer = new Tokenizer(true);
    static private MuaRunner runner = new MuaRunner();

    static {
        NamespaceManager.addNamespace();
    }

    private Interpreter() {
    }

    static public boolean finished() {
        return tokenizer.finished() && runner.finished();
    }

    static public void finishedThrowException() throws OperandNumberException {
        runner.finishedThrowException();
    }

    static public void interpret(String line) throws MuaException {
        try {
            tokenizer.tokenize(line);

            while (tokenizer.hasNext()) {
                runner.run(tokenizer.getElement(), isInteractive);
            }
        } catch (MuaException e) {
            clear();
            throw e;
        }
    }

    static public void clear() {
        tokenizer.clear();
        runner.clear();
    }
}
