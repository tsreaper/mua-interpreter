package lang.namespace;

import exception.operation.InvalidNameException;
import lang.element.MuaElement;
import service.namespace.INamespaceServiceProvider;

import java.util.ArrayList;

public class NamespaceManager implements INamespaceServiceProvider {
    private ArrayList<Namespace> nsList;

    public NamespaceManager() {
        nsList = new ArrayList<>();
    }

    public void addNamespace() {
        nsList.add(new Namespace());
    }

    public MuaElement removeNamespace() {
        return nsList.remove(nsList.size() - 1).returnValue;
    }

    public MuaElement getBoundedElement(String key) throws InvalidNameException {
        for (int i = nsList.size() - 1; i >= 0; i--) {
            try {
                return nsList.get(i).getBoundedElement(key);
            } catch (InvalidNameException e) {
            }
        }
        throw new InvalidNameException(key);
    }

    public void bindElement(String key, MuaElement element) {
        nsList.get(nsList.size() - 1).bindElement(key, element);
    }

    public boolean eraseBoundedElement(String key) {
        for (int i = nsList.size() - 1; i >= 0; i--) {
            if (nsList.get(i).eraseBoundedElement(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean isName(String key) {
        for (int i = nsList.size() - 1; i >= 0; i--) {
            if (nsList.get(i).isName(key)) {
                return true;
            }
        }
        return false;
    }

    public void setReturnValue(MuaElement element) {
        nsList.get(nsList.size() - 1).returnValue = element;
    }
}
