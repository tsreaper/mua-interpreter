package lang.operation;

import exception.operation.NoSuchFunctionException;
import lang.element.*;
import lang.operation.arithmetic.*;
import lang.operation.comparison.*;
import lang.operation.control.*;
import lang.operation.function.*;
import lang.operation.io.*;
import lang.operation.logic.*;
import lang.operation.math.*;
import lang.operation.namespace.*;
import lang.operation.type.*;
import service.namespace.NamespaceService;

import java.util.Arrays;
import java.util.List;

public class OperationUtil {
    static private final List<String> OPERATIONS = Arrays.asList(
            "make", "thing", "erase", "isname",
            "save", "load", "erall", "poall", "export",
            "print", "read", "readlinst",
            "add", "sub", "mul", "div", "mod",
            "eq", "gt", "lt", "le", "ge", "ne",
            "and", "or", "not",
            "run", "repeat", "stop", "if", "wait",
            "output",
            "random", "sqrt", "int", "pi",
            "isnumber", "isword", "islist", "isbool",
            "isempty", "word", "sentence", "list", "join",
            "first", "last", "butfirst", "butlast"
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

        // Check params
        MuaList params = (MuaList) list.get(0);
        for (int i = 0; i < params.size(); i++) {
            MuaElement param = params.get(i);
            // Params should not start with "
            if (!(param instanceof MuaOperation && !isOperation(param.getValue()))) {
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
            case "save":
                return new OpSave();
            case "load":
                return new OpLoad();
            case "erall":
                return new OpErall();
            case "poall":
                return new OpPoall();
            case "export":
                return new OpExport();

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
            case "le":
                return new OpLe();
            case "ge":
                return new OpGe();
            case "ne":
                return new OpNe();

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
            case "if":
                return new OpIf();
            case "wait":
                return new OpWait();

            // Function operation
            case "output":
                return new OpOutput();

            // Built-in Math operation
            case "random":
                return new OpRandom();
            case "sqrt":
                return new OpSqrt();
            case "int":
                return new OpInt();
            case "pi":
                return new OpPi();

            // Type operation
            case "isnumber":
                return new OpIsNumber();
            case "isword":
                return new OpIsWord();
            case "islist":
                return new OpIsList();
            case "isbool":
                return new OpIsBool();
            case "isempty":
                return new OpIsEmpty();
            case "word":
                return new OpWord();
            case "sentence":
                return new OpSentence();
            case "list":
                return new OpList();
            case "join":
                return new OpJoin();
            case "first":
                return new OpFirst();
            case "last":
                return new OpLast();
            case "butfirst":
                return new OpButFirst();
            case "butlast":
                return new OpButLast();

            // Must be : operation
            default:
                assert value.charAt(0) == ':';

                Operation opColon = new OpThing();
                opColon.addOperand(new MuaWord(value.substring(1)));
                return opColon;
        }
    }

    static public ArithmeticOperation getArithmeticOperation(char c) throws UnsupportedOperationException {
        switch (c) {
            case '+':
                return new OpAdd();
            case '-':
                return new OpSub();
            case '*':
                return new OpMul();
            case '/':
                return new OpDiv();
            case '%':
                return new OpMod();
            default:
                // Impossible
                throw new UnsupportedOperationException();
        }
    }

    static public ComparisonOperation getComparisonOperation(String s) throws UnsupportedOperationException {
        switch (s) {
            case "<":
                return new OpLt();
            case ">":
                return new OpGt();
            case "=":
                return new OpEq();
            case "<=":
                return new OpLe();
            case ">=":
                return new OpGe();
            case "!=":
                return new OpNe();
            default:
                // Impossible
                throw new UnsupportedOperationException();
        }
    }
}
