package lang.namespace;

import exception.operation.InvalidNameException;
import lang.element.MuaElement;

import java.util.ArrayList;
import java.util.HashMap;

public class Namespace {
    MuaElement returnValue;
    private HashMap<String, MuaElement> wordMap;
    private Namespace parent;

    Namespace(Namespace parent) {
        returnValue = null;
        wordMap = new HashMap<>();
        this.parent = parent;
    }

    MuaElement getBoundedElement(String key) throws InvalidNameException {
        if (isName(key)) {
            return wordMap.get(key);
        }
        throw new InvalidNameException(key);
    }

    ArrayList<String> getKeys() {
        return new ArrayList<>(wordMap.keySet());
    }

    void bindElement(String key, MuaElement element) {
        wordMap.put(key, element);
    }

    boolean eraseBoundedElement(String key) {
        if (isName(key)) {
            wordMap.remove(key);
            return true;
        }
        return false;
    }

    void eraseAll() {
        wordMap.clear();
    }

    boolean isName(String key) {
        return wordMap.containsKey(key);
    }

    Namespace getParent() {
        return parent;
    }
}
