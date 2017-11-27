package service.namespace;

import lang.element.MuaElement;

public interface INamespaceServiceProvider {
    void addNamespace();

    MuaElement removeNamespace();

    MuaElement getBoundedElement(String key);

    void bindElement(String key, MuaElement element);

    boolean eraseBoundedElement(String key);

    boolean isName(String key);

    void setReturnValue(MuaElement element);
}
