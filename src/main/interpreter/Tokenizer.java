package interpreter;

import exception.element.UnclosedListException;
import lang.element.*;
import util.Util;

import java.util.ArrayList;

public class Tokenizer {
    private boolean flowInput;
    private ArrayList<MuaElement> elements;
    private ArrayList<MuaList> muaLists;

    public Tokenizer(boolean flowInput) {
        this.flowInput = flowInput;
        elements = new ArrayList<>();
        muaLists = new ArrayList<>();
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
        return elements.size() == 0 && muaLists.size() == 0;
    }

    public void clear() {
        elements.clear();
        muaLists.clear();
    }

    public void tokenize(String line) throws UnclosedListException {
        // Split string
        String[] strArr = Util.splitStringToArray(line);

        for (String token : strArr) {
            // Check if the lang.element is a comment
            if (token.length() >= 2 && token.substring(0, 2).equals("//")) {
                break;
            }

            // Check if the lang.element is a list
            int closedList = 0;
            while (token.length() > 0 && token.charAt(0) == '[') {
                muaLists.add(new MuaList());
                token = token.substring(1);
            }
            while (token.length() > 0 && token.charAt(token.length() - 1) == ']' && token.charAt(0) != ':') {
                closedList++;
                token = token.substring(0, token.length() - 1);
            }

            // Append elements
            if (token.length() > 0) {
                if ((token.charAt(0) >= '0' && token.charAt(0) <= '9') || token.charAt(0) == '-') {
                    addElement(new MuaNumber(token));
                } else if (token.charAt(0) == '"') {
                    addElement(new MuaWord(token.substring(1)));
                } else if (token.equals("true") || token.equals("false")) {
                    addElement(new MuaBool(Boolean.valueOf(token)));
                } else {
                    addElement(new MuaOperation(token));
                }
            }

            // Close current list
            for (; closedList > 0; closedList--) {
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

        // Check if lists are closed if not flow input
        if (!flowInput && muaLists.size() > 0) {
            throw new UnclosedListException();
        }
    }

    private void addElement(MuaElement element) {
        if (muaLists.size() > 0) {
            muaLists.get(muaLists.size() - 1).appendElement(element);
        } else {
            elements.add(element);
        }
    }
}
