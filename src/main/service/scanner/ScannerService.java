package service.scanner;

public class ScannerService {
    static private IScannerServiceProvider service;

    private ScannerService() {
    }

    static public IScannerServiceProvider getService() {
        return service;
    }

    static public void provide(IScannerServiceProvider provider) {
        service = provider;
    }
}
