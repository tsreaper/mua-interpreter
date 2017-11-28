package lang.operation;

import exception.operation.NoSuchFunctionException;
import lang.element.MuaElement;
import lang.element.MuaList;
import lang.element.MuaNumber;
import lang.element.MuaWord;
import lang.operation.arithmetic.*;
import lang.operation.comparison.*;
import lang.operation.control.*;
import lang.operation.function.*;
import lang.operation.io.*;
import lang.operation.logic.*;
import lang.operation.namespace.*;
import service.namespace.NamespaceService;

import java.util.Arrays;
import java.util.List;

public class OperationUtil {
    static private final List<String> OPERATIONS = Arrays.asList(
            "make", "thing", "erase", "isname",
            "print", "read", "readlinst",
            "add", "sub", "mul", "div", "mod",
            "eq", "gt", "lt",
            "and", "or", "not",
            "run", "repeat", "stop",
            "output"
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

    static public OpFunction getFunction(String value) throws NoSuchFunctionException {
        // Check if the given name is a function
        if (!NamespaceService.getService().isName(value)) {
            throw new NoSuchFunctionException(value);
        }

        MuaElement element = NamespaceService.getService().getBoundedElement(value);
        if (!(element instanceof MuaList)) {
            throw new NoSuchFunctionException(value);
        }

        MuaList list = (MuaList) element;
        if (list.size() != 2 || !(list.get(0) instanceof MuaList) || !(list.get(1) instanceof MuaList)) {
            throw new NoSuchFunctionException(value);
        }

        MuaList params = (MuaList) list.get(0);
        for (int i = 0; i < params.size(); i++) {
            if (!(params.get(i) instanceof MuaWord)) {
                throw new NoSuchFunctionException(value);
            }
        }

        // It's a function. Return OpFunction
        return new OpFunction(value, params, (MuaList) list.get(1));
    }

    static public Operation getOperation(String value) {
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
            case "run":
                // run = repeat 1
                Operation opRun = new OpRepeat();
                opRun.addOperand(new MuaNumber("1"));
                return opRun;
            case "repeat":
                return new OpRepeat();
            case "stop":
                return new OpStop();

            // Function operation
            case "output":
                return new OpOutput();

            // Must be : operation
            default:
                assert value.charAt(0) == ':';

                Operation opColon = new OpThing();
                opColon.addOperand(new MuaWord(value.substring(1)));
                return opColon;
        }
    }
}
