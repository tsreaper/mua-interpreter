import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class NamespaceTest extends MuaTest {
    NamespaceTest() {
        DIRECTORY = "namespace/";
    }

    @Test
    void CopyNamespace() throws IOException {
        runTest("CopyNamespace");
        Files.delete(Paths.get("test_ns.mns"));
    }

    @Test
    void EraseAndExport() {
        runTest("EraseAndExport");
    }
}
