package lang.namespace;

import lang.element.MuaElement;
import exception.operation.InvalidNameException;

import java.util.ArrayList;

public class NamespaceManager {
    static private ArrayList<Namespace> nsList = new ArrayList<>();

    static public void addNamespace() {
        nsList.add(new Namespace());
    }

    static public void removeNamespace() {
        nsList.remove(nsList.size() - 1);
    }

    static public MuaElement getBoundedElement(String key) throws InvalidNameException {
        for (int i = nsList.size() - 1; i >= 0; i--) {
            try {
                return nsList.get(i).getBoundedElement(key);
            } catch (InvalidNameException e) {
            }
        }
        throw new InvalidNameException(key);
    }

    static public void bindElement(String key, MuaElement element) {
        nsList.get(nsList.size() - 1).bindElement(key, element);
    }

    static public boolean eraseBoundedElement(String key) {
        for (int i = nsList.size() - 1; i >= 0; i--) {
            if (nsList.get(i).eraseBoundedElement(key)) {
                return true;
            }
        }
        return false;
    }

    static public boolean isName(String key) {
        for (int i = nsList.size() - 1; i >= 0; i--) {
            if (nsList.get(i).isName(key)) {
                return true;
            }
        }
        return false;
    }
}
