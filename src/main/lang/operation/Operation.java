package lang.operation;

import exception.MuaException;
import exception.operation.OperandNumberException;
import exception.operation.OperandTypeException;
import lang.element.*;

import java.util.ArrayList;

public abstract class Operation implements ICanExecute {
    protected int operandNum;
    protected String name;
    private ArrayList<MuaElement> operands;

    public Operation() {
        operands = new ArrayList<>();
    }

    public void addOperand(ICanExecute operand) throws MuaException {
        assert operands.size() < operandNum;
        operands.add(operand.execute());
    }

    public boolean canExecute() {
        return operands.size() == operandNum;
    }

    public abstract MuaElement execute();

    protected MuaElement getOperand(int index, String types) throws OperandTypeException {
        MuaElement operand = operands.get(index);
        if (operand == null) {
            throw new OperandTypeException(name, types, "null");
        }

        // Check operand type
        boolean typeCheck = false;
        for (String type : types.split("\\|")) {
            switch (type) {
                case "number":
                    typeCheck |= operand instanceof MuaNumber;
                    break;
                case "word":
                    typeCheck |= operand instanceof MuaWord;
                    break;
                case "bool":
                    typeCheck |= operand instanceof MuaBool;
                    break;
                case "list":
                    typeCheck |= operand instanceof MuaList;
                    break;
                case "?":
                    typeCheck |= true;
                    break;
                default:
                    break;
            }
        }

        if (typeCheck) {
            return operand;
        }
        throw new OperandTypeException(name, types, operand.getValue());
    }

    protected void checkOperandNum() throws OperandNumberException {
        if (!canExecute()) {
            throw new OperandNumberException(name, operandNum, operands.size());
        }
    }
}
