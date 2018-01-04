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

    public void addNamespace(Namespace parent) {
        nsList.add(new Namespace(parent));
    }

    public MuaElement removeNamespace() {
        return nsList.remove(nsList.size() - 1).returnValue;
    }

    public Namespace getNamespaceByKey(String key) throws InvalidNameException {
        Namespace ns = nsList.get(nsList.size() - 1);
        while (ns != null && !ns.isName(key)) {
            ns = ns.getParent();
        }
        if (ns != null) {
            return ns;
        }
        throw new InvalidNameException(key);
    }

    public MuaElement getBoundedElement(String key) throws InvalidNameException {
        return getNamespaceByKey(key).getBoundedElement(key);
    }

    public ArrayList<String> getKeys() {
        return nsList.get(nsList.size() - 1).getKeys();
    }

    public ArrayList<String> getAllNamespaceKeys() {
        ArrayList<String> ret = new ArrayList<>();
        Namespace ns = nsList.get(nsList.size() - 1);
        while (ns != null) {
            ret.addAll(ns.getKeys());
            ns = ns.getParent();
        }
        return ret;
    }

    public void bindElement(String key, MuaElement element) {
        nsList.get(nsList.size() - 1).bindElement(key, element);
    }

    public boolean eraseBoundedElement(String key) {
        try {
            Namespace ns = getNamespaceByKey(key);
            ns.eraseBoundedElement(key);
            return true;
        } catch (InvalidNameException e) {
            return false;
        }
    }

    public void eraseAll() {
        nsList.get(nsList.size() - 1).eraseAll();
    }

    public boolean isName(String key) {
        try {
            getNamespaceByKey(key);
            return true;
        } catch (InvalidNameException e) {
            return false;
        }
    }

    public void setReturnValue(MuaElement element) {
        nsList.get(nsList.size() - 1).returnValue = element;
    }

    public boolean isGlobal() {
        return nsList.get(nsList.size() - 1).getParent() == null;
    }

    public void export() {
        assert !isGlobal();

        for (String key : getKeys()) {
            nsList.get(0).bindElement(key, nsList.get(nsList.size() - 1).getBoundedElement(key));
        }
        eraseAll();
    }
}
