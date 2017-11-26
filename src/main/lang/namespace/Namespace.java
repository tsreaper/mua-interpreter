package lang.namespace;

import lang.element.MuaElement;
import exception.operation.InvalidNameException;

import java.util.HashMap;

class Namespace {
    private HashMap<String, MuaElement> wordMap;

    Namespace() {
        wordMap = new HashMap<>();
    }

    MuaElement getBoundedElement(String key) throws InvalidNameException {
        if (isName(key)) {
            return wordMap.get(key);
        }
        throw new InvalidNameException(key);
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

    boolean isName(String key) {
        return wordMap.containsKey(key);
    }
}
