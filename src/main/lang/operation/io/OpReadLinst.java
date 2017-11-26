package lang.operation.io;

import exception.element.UnclosedListException;
import interpretation.MuaScanner;
import interpretation.Tokenizer;
import lang.element.MuaElement;
import lang.element.MuaList;
import lang.operation.Operation;

public class OpReadLinst extends Operation {
    public OpReadLinst() {
        super();
        operandNum = 0;
        name = "readlinst";
    }

    @Override
    public MuaElement execute() throws UnclosedListException {
        assert canExecute();

        String line = MuaScanner.nextLine();
        Tokenizer tokenizer = new Tokenizer(false);
        tokenizer.tokenize(line);

        MuaList list = new MuaList();
        while (tokenizer.hasNext()) {
            MuaElement element = tokenizer.getElement();
            list.appendElement(element);
        }

        return list;
    }
}
