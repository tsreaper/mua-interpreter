package util;

import exception.element.InvalidExpressionException;

import java.util.ArrayList;
import java.util.Arrays;

public class Util {
    private Util() {
    }

    static public ArrayList<String> splitStringToArrayList(String str) {
        ArrayList<String> tmp = new ArrayList<>(Arrays.asList(str.split("\\s+")));
        while (tmp.remove("")) ;
        return tmp;
    }

    static public String[] splitStringToArray(String str) {
        ArrayList<String> tmp = splitStringToArrayList(str);
        return tmp.toArray(new String[tmp.size()]);
    }

    static public double calcExpression(String exp) throws InvalidExpressionException {
        if (exp.length() == 0) {
            return 0;
        }

        // Try if exp is already a number
        try {
            return Double.valueOf(exp);
        } catch (NumberFormatException e) {
        }

        // Check validity
        final String CHARSET = "0123456789.+-*/%()";
        int count;

        if (exp.charAt(0) == '*' || exp.charAt(0) == '/' || exp.charAt(0) == '%') {
            throw new InvalidExpressionException(exp);
        }

        count = 0;
        for (int i = 0; i < exp.length(); i++) {
            if (CHARSET.indexOf(exp.charAt(i)) < 0) {
                throw new InvalidExpressionException(exp);
            }
            if (exp.charAt(i) == '(') {
                count++;
            } else if (exp.charAt(i) == ')') {
                count--;
            }
            if (count < 0) {
                throw new InvalidExpressionException(exp);
            }
        }
        if (count != 0) {
            throw new InvalidExpressionException(exp);
        }

        // Find + or -
        count = 0;
        for (int i = exp.length() - 1; i >= 0; i--) {
            if (exp.charAt(i) == '+' && count == 0) {
                return calcExpression(exp.substring(0, i)) + calcExpression(exp.substring(i + 1, exp.length()));
            } else if (exp.charAt(i) == '-' && count == 0) {
                return calcExpression(exp.substring(0, i)) - calcExpression(exp.substring(i + 1, exp.length()));
            } else if (exp.charAt(i) == '(') {
                count++;
            } else if (exp.charAt(i) == ')') {
                count--;
            }
        }

        // Find * or / or %
        count = 0;
        for (int i = exp.length() - 1; i >= 0; i--) {
            if (exp.charAt(i) == '*' && count == 0) {
                return calcExpression(exp.substring(0, i)) * calcExpression(exp.substring(i + 1, exp.length()));
            } else if (exp.charAt(i) == '/' && count == 0) {
                return calcExpression(exp.substring(0, i)) / calcExpression(exp.substring(i + 1, exp.length()));
            } else if (exp.charAt(i) == '%' && count == 0) {
                return calcExpression(exp.substring(0, i)) % calcExpression(exp.substring(i + 1, exp.length()));
            } else if (exp.charAt(i) == '(') {
                count++;
            } else if (exp.charAt(i) == ')') {
                count--;
            }
        }

        // Must be in parentheses
        if (exp.charAt(0) == '(' && exp.charAt(exp.length() - 1) == ')') {
            return calcExpression(exp.substring(1, exp.length() - 1));
        }

        // Impossible. Invalid expression
        throw new InvalidExpressionException(exp);
    }
}
