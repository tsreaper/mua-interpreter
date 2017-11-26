import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MuaTest {
    private final String ASSETS_DIR = "src/test/assets/";
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    @TestFactory
    Stream<DynamicTest> muaTests() {
        HashSet<String> tests = new HashSet<>();
        for (File file : new File(ASSETS_DIR).listFiles()) {
            if (file.isFile()) {
                tests.add(file.getName().split("\\.")[0]);
            }
        }

        return new ArrayList<>(tests).stream().map(
                (filename) -> DynamicTest.dynamicTest(
                        filename,
                        () -> {
                            String muaFilename = ASSETS_DIR + filename + ".mua";
                            String inputFilename = ASSETS_DIR + filename + ".in";
                            String ansFilename = ASSETS_DIR + filename + ".ans";

                            // Redirect IO
                            System.setIn(new ByteArrayInputStream(Files.readAllBytes(Paths.get(inputFilename))));
                            outStream.reset();
                            System.setOut(new PrintStream(outStream));

                            // Run mua
                            Mua.main(new String[]{muaFilename});

                            // Compare
                            String ans = new String(Files.readAllBytes(Paths.get(ansFilename)));
                            assertEquals(ans, outStream.toString().replaceAll("\\r\\n", "\n"));
                        }
                )
        );
    }
}
