package interpreter;

import exception.element.InvalidExpressionException;
import exception.element.InvalidNumberException;
import exception.element.UnclosedListException;
import lang.element.*;
import util.Util;

import java.util.ArrayList;

public class Tokenizer {
    private boolean flowInput;

    private ArrayList<MuaElement> elements;
    private ArrayList<MuaList> muaLists;
    private MuaExpression muaExpression;

    public Tokenizer(boolean flowInput) {
        this.flowInput = flowInput;

        elements = new ArrayList<>();
        muaLists = new ArrayList<>();
        muaExpression = null;
    }

    public MuaElement getElement() {
        if (elements.size() > 0) {
            return elements.remove(0);
        }
        return null;
    }

    public boolean hasNext() {
        return elements.size() > 0;
    }

    public boolean finished() {
        return elements.size() == 0 && muaLists.size() == 0 && muaExpression == null;
    }

    public void clear() {
        elements.clear();
        muaLists.clear();
        muaExpression = null;
    }

    public void tokenize(String line) throws UnclosedListException, InvalidExpressionException {
        // Split string
        ArrayList<String> strArr = Util.splitString(line);

        for (int i = 0; i < strArr.size(); i++) {
            String token = strArr.get(i);

            // Check if the element is a comment
            if (token.length() >= 2 && token.substring(0, 2).equals("//")) {
                break;
            }

            // Check []
            token = splitList(strArr, i);

            if (muaExpression == null) {
                // Not in expression. Tokenize normally
                parseToken(token);
            } else {
                // In expression
                appendToExpression(token);
            }
        }

        // Check if lists are closed if not flow input
        if (!flowInput && muaLists.size() > 0) {
            throw new UnclosedListException();
        }

        // Check if expression is closed if not flow input
        if (!flowInput && muaExpression != null) {
            throw new InvalidExpressionException("Unmatched brackets.");
        }
    }

    private void parseToken(String token) throws InvalidExpressionException {
        assert token.length() > 0;

        if (token.equals("[")) {
            muaLists.add(new MuaList());
        } else if (token.equals("]")) {
            closeList();
        } else {
            appendElement(token);
        }
    }

    private void appendElement(String token) throws InvalidExpressionException {
        if (token.charAt(0) == '(') {
            // Expression begins
            muaExpression = new MuaExpression();
            appendToExpression(token.substring(1));
        } else {
            // Not in expression
            try {
                addElement(new MuaNumber(token));
            } catch (InvalidNumberException e1) {
                if (token.charAt(0) == '"') {
                    addElement(new MuaWord(token.substring(1)));
                } else if (token.equals("true") || token.equals("false")) {
                    addElement(new MuaBool(Boolean.valueOf(token)));
                } else {
                    addElement(new MuaOperation(token));
                }
            }
        }
    }

    private void appendToExpression(String s) throws InvalidExpressionException {
        assert muaExpression != null;

        muaExpression.appendString(s);
        if (muaExpression.canExecute()) {
            addElement(muaExpression);
            muaExpression = null;
        }
    }

    private void addElement(MuaElement element) {
        if (muaLists.size() > 0) {
            muaLists.get(muaLists.size() - 1).appendElement(element);
        } else {
            elements.add(element);
        }
    }

    private String splitList(ArrayList<String> strArr, int index) {
        String token = strArr.get(index);
        ArrayList<String> splitArr = new ArrayList<>();

        // Split [
        while (token.length() > 1 && token.charAt(0) == '[') {
            splitArr.add("[");
            token = token.substring(1);
        }

        // Split ]
        int count = 0;
        while (token.length() > 1 && token.charAt(token.length() - 1) == ']' && token.charAt(0) != ':') {
            count++;
            token = token.substring(0, token.length() - 1);
        }
        splitArr.add(token);
        for (; count > 0; count--) {
            splitArr.add("]");
        }

        // Insert split tokens into strArr
        token = splitArr.remove(0);
        if (splitArr.size() > 0) {
            strArr.addAll(index + 1, splitArr);
        }
        return token;
    }

    private void closeList() {
        if (muaLists.size() == 0) {
            throw new UnclosedListException();
        }

        MuaList currentList = muaLists.remove(muaLists.size() - 1);
        if (muaLists.size() > 0) {
            muaLists.get(muaLists.size() - 1).appendElement(currentList);
        } else {
            elements.add(currentList);
        }
    }
}
