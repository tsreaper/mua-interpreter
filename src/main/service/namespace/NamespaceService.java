package service.namespace;

public class NamespaceService {
    static private INamespaceServiceProvider service;

    private NamespaceService() {
    }

    static public INamespaceServiceProvider getService() {
        return service;
    }

    static public void provide(INamespaceServiceProvider provider) {
        service = provider;
    }
}
