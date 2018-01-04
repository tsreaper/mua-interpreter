package interpreter;

import exception.MuaException;
import exception.operation.OperandNumberException;
import service.GlobalSettings;
import service.namespace.NamespaceService;

public class Interpreter {
    private Tokenizer tokenizer;
    private MuaRunner runner;
    private boolean shouldStop;

    public Interpreter() {
        tokenizer = new Tokenizer(true);
        runner = new MuaRunner(true, GlobalSettings.interactive);
        shouldStop = false;

        NamespaceService.getService().addNamespace(null);
    }

    public boolean finished() {
        return tokenizer.finished() && runner.finished();
    }

    public boolean shouldStop() {
        return shouldStop;
    }

    public void forceExecute() throws OperandNumberException {
        runner.forceExecute();
    }

    public void interpret(String line) throws MuaException {
        try {
            tokenizer.tokenize(line);
            while (tokenizer.hasNext()) {
                runner.add(tokenizer.getElement());
            }
            runner.run();

            if (runner.shouldStop()) {
                shouldStop = true;
            }
        } catch (MuaException e) {
            clear();
            throw e;
        }
    }

    private void clear() {
        tokenizer.clear();
        runner.clear();
    }
}
