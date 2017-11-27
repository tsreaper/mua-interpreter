package lang.operation;

import exception.operation.NoSuchOperationException;
import lang.element.MuaWord;
import lang.operation.arithmetic.*;
import lang.operation.comparison.*;
import lang.operation.control.OpRepeat;
import lang.operation.io.*;
import lang.operation.logic.*;
import lang.operation.namespace.*;

import java.util.Arrays;
import java.util.List;

public class OperationUtil {
    static private final List<String> OPERATIONS = Arrays.asList(
            "make", "thing", "erase", "isname",
            "print", "read", "readlinst",
            "add", "sub", "mul", "div", "mod",
            "eq", "gt", "lt",
            "and", "or", "not",
            "repeat"
    );

    private OperationUtil() {
    }

    static public boolean isOperation(String value) {
        if (value.length() == 0) {
            return false;
        } else if (value.charAt(0) == ':') {
            return true;
        }
        return OPERATIONS.contains(value);
    }

    static public Operation getOperation(String value) throws NoSuchOperationException {
        switch (value) {
            // Namespace operation
            case "make":
                return new OpMake();
            case "thing":
                return new OpThing();
            case "erase":
                return new OpErase();
            case "isname":
                return new OpIsName();

            // IO operation
            case "print":
                return new OpPrint();
            case "read":
                return new OpRead();
            case "readlinst":
                return new OpReadLinst();

            // Arithmetic operation
            case "add":
                return new OpAdd();
            case "sub":
                return new OpSub();
            case "mul":
                return new OpMul();
            case "div":
                return new OpDiv();
            case "mod":
                return new OpMod();

            // Comparison operation
            case "eq":
                return new OpEq();
            case "gt":
                return new OpGt();
            case "lt":
                return new OpLt();

            // Logic operation
            case "and":
                return new OpAnd();
            case "or":
                return new OpOr();
            case "not":
                return new OpNot();

            // Control operation
            case "repeat":
                return new OpRepeat();

            default:
                // Check for : operation
                if (value.charAt(0) == ':') {
                    Operation op = new OpThing();
                    op.addOperand(new MuaWord(value.substring(1)));
                    return op;
                }

                // No such operation
                throw new NoSuchOperationException(value);
        }
    }
}
