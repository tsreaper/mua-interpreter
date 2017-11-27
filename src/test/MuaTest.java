import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MuaTest {
    String DIRECTORY;

    private final String ASSETS_DIR = "src/test/assets/";
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    void runTest(String filename) {
        filename = DIRECTORY + filename;
        String muaFilename = ASSETS_DIR + filename + ".mua";
        String inputFilename = ASSETS_DIR + filename + ".in";
        String ansFilename = ASSETS_DIR + filename + ".ans";

        // Redirect IO
        try {
            System.setIn(new ByteArrayInputStream(Files.readAllBytes(Paths.get(inputFilename))));
        } catch (IOException e) {
            fail("Can't read file " + inputFilename);
        }
        outStream.reset();
        System.setOut(new PrintStream(outStream));

        // Run mua
        Mua.main(new String[]{muaFilename});

        // Compare
        try {
            String ans = new String(Files.readAllBytes(Paths.get(ansFilename)));
            assertEquals(ans, outStream.toString().replaceAll("\\r\\n", "\n"));
        } catch (IOException e) {
            fail("Can't read file " + inputFilename);
        }
    }
}
