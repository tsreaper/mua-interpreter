package lang.operation.io;

import exception.element.UnclosedListException;
import interpreter.Tokenizer;
import lang.element.MuaElement;
import lang.element.MuaList;
import lang.operation.Operation;
import service.scanner.ScannerService;

public class OpReadLinst extends Operation {
    public OpReadLinst() {
        super();
        operandNum = 0;
        name = "readlinst";
    }

    @Override
    public MuaElement execute() throws UnclosedListException {
        checkOperandNum();

        String line = ScannerService.getService().nextLine();
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
