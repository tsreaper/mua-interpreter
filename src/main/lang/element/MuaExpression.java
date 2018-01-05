package lang.element;

import exception.MuaException;
import exception.element.InvalidExpressionException;
import interpreter.MuaRunner;
import interpreter.Tokenizer;
import lang.operation.OperationUtil;
import lang.operation.arithmetic.ArithmeticOperation;
import lang.operation.comparison.ComparisonOperation;

import java.util.ArrayList;

public class MuaExpression extends MuaElement {
    private StringBuilder value;
    private int bracketCount;

    public MuaExpression() {
        value = new StringBuilder();
        bracketCount = 1;
    }

    @Override
    public String getValue() {
        return "(" + value.toString() + ")";
    }

    public void appendString(String s) throws InvalidExpressionException {
        if (value.length() > 0) {
            value.append(' ');
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                bracketCount++;
            } else if (s.charAt(i) == ')') {
                bracketCount--;
            }

            if (bracketCount == 0) {
                if (i != s.length() - 1) {
                    throw new InvalidExpressionException(
                            "Expecting expression to end. But `" + s.substring(i + 1) + "` follows."
                    );
                }
                value.append(s.substring(0, s.length() - 1));
                return;
            }
        }

        value.append(s);
    }

    @Override
    public boolean canExecute() {
        return bracketCount == 0;
    }

    @Override
    public MuaElement execute() throws MuaException {
        if (value.length() == 0) {
            throw new InvalidExpressionException("Empty expression.");
        }
        return calcExpression(value.toString());
    }

    private MuaElement calcExpression(String exp) throws MuaException {
        if (exp.length() == 0) {
            return new MuaNumber("0");
        }
        checkExpression(exp);

        MuaElement result;
        // Find <, >, =, <=, >=, !=
        result = findComparison(exp);
        if (result != null) {
            return result;
        }
        // Find +, -
        result = findOperation("+-", exp, true);
        if (result != null) {
            return result;
        }
        // Find *, /, %
        result = findOperation("*/%", exp, false);
        if (result != null) {
            return result;
        }

        // +-*/% not found. Run raw mua
        Tokenizer tokenizer = new Tokenizer(false);
        MuaRunner runner = new MuaRunner(false, false);
        tokenizer.tokenize(exp);
        while (tokenizer.hasNext()) {
            runner.add(tokenizer.getElement());
        }
        ArrayList<MuaElement> runnerResult = runner.run();
        if (runnerResult.size() != 1) {
            throw new InvalidExpressionException("Too many return values.");
        }
        return runnerResult.get(0);
    }

    private MuaBool findComparison(String exp) throws MuaException {
        ArrayList<String> ops = new ArrayList<>();
        ArrayList<MuaElement> operands = new ArrayList<>();

        int count = 0, last = 0;
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '(') {
                count++;
            } else if (exp.charAt(i) == ')') {
                count--;
            } else if (count == 0) {
                boolean split = false;

                // <=, >=, !=
                if (i < exp.length() - 1 && exp.charAt(i + 1) == '=') {
                    if (exp.charAt(i) == '<') {
                        ops.add("<=");
                        split = true;
                    } else if (exp.charAt(i) == '>') {
                        ops.add(">=");
                        split = true;
                    } else if (exp.charAt(i) == '!') {
                        ops.add("!=");
                        split = true;
                    }
                    if (split) {
                        operands.add(calcExpression(exp.substring(last, i)));
                        last = i + 2;
                        i++;
                        continue;
                    }
                }

                // <, >, =
                if (exp.charAt(i) == '<') {
                    ops.add("<");
                    split = true;
                } else if (exp.charAt(i) == '>') {
                    ops.add(">");
                    split = true;
                } else if (exp.charAt(i) == '=') {
                    ops.add("=");
                    split = true;
                }
                if (split) {
                    operands.add(calcExpression(exp.substring(last, i)));
                    last = i + 1;
                }
            }
        }

        // No comparison found
        if (ops.size() == 0) {
            return null;
        }

        // Comparison found
        operands.add(calcExpression(exp.substring(last, exp.length())));
        for (int i = 0; i < ops.size(); i++) {
            ComparisonOperation op = OperationUtil.getComparisonOperation(ops.get(i));
            op.addOperand(operands.get(i));
            op.addOperand(operands.get(i + 1));
            if (op.execute().getValue().equals("false")) {
                return new MuaBool(false);
            }
        }
        return new MuaBool(true);
    }

    private MuaElement findOperation(String ops, String exp, boolean canBeUnary) throws MuaException {
        int count = 0, index = -1;

        int i;
        for (i = exp.length() - 1; i >= 0; i--) {
            if (exp.charAt(i) == '(') {
                count++;
            } else if (exp.charAt(i) == ')') {
                count--;
            } else if (count == 0) {
                if (canBeUnary) {
                    // Check if op is unary
                    boolean isUnary = true;
                    for (int j = i - 1; j >= 0; j--) {
                        if (exp.charAt(j) != ' ') {
                            if ("+-*/%".indexOf(exp.charAt(j)) < 0) {
                                isUnary = false;
                            }
                            break;
                        }
                    }
                    if (isUnary) {
                        continue;
                    }
                }

                // Not unary. Split
                index = ops.indexOf(exp.charAt(i));
                if (index >= 0) {
                    break;
                }
            }
        }

        if (index < 0) {
            // Not found
            return null;
        }

        // Found
        ArithmeticOperation op = OperationUtil.getArithmeticOperation(ops.charAt(index));
        op.addOperand(calcExpression(exp.substring(0, i)));
        op.addOperand(calcExpression(exp.substring(i + 1, exp.length())));
        return op.execute();
    }

    private void checkExpression(String exp) throws InvalidExpressionException {
        // Can't start with */%<>=
        final String INVALID_START = "*/%<>=";
        if (INVALID_START.indexOf(exp.charAt(0)) >= 0) {
            throw new InvalidExpressionException("`" + exp.charAt(0) + "` is not a unary operator.");
        }

        // Can't end with +-*/%<>=
        final String INVALID_END = "+-*/%<>=";
        if (INVALID_END.indexOf(exp.charAt(exp.length() - 1)) >= 0) {
            throw new InvalidExpressionException(
                    "Expression can't end with operator `" + exp.charAt(exp.length() - 1) + "`."
            );
        }

        // Check if brackets are matched
        int count = 0;
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '(') {
                count++;
            } else if (exp.charAt(i) == ')') {
                count--;
            }
            if (count < 0) {
                throw new InvalidExpressionException("Unmatched brackets.");
            }
        }
        if (count != 0) {
            throw new InvalidExpressionException("Unmatched brackets.");
        }
    }
}
