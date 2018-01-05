import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MuaTest {
    private final String ASSETS_DIR = "src/test/assets/";
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    String DIRECTORY;

    String[] runTest(String filename) throws IOException {
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

        // Read result
        String ans = new String(Files.readAllBytes(Paths.get(ansFilename))).
                replaceAll("\\r\\n", "\n");
        String out = outStream.toString().replaceAll("\\r\\n", "\n");

        String[] ret = new String[2];
        ret[0] = ans;
        ret[1] = out;
        return ret;
    }

    void strcmpTest(String filename) {
        try {
            String[] ret = runTest(filename);
            assertEquals(ret[0], ret[1]);
        } catch (IOException e) {
            fail("Can't read file " + filename);
        }
    }

    void floatTest(String filename, double eps) {
        try {
            String[] ret = runTest(filename);

            String[] ans = ret[0].split("\\n");
            String[] out = ret[1].split("\\n");
            assertEquals(
                    ans.length, out.length,
                    String.format(
                            "Expecting %d lines of output, but found %d. Output:\n%s", ans.length, out.length, ret[1]
                    )
            );

            for (int i = 0; i < ans.length; i++) {
                double a = Double.parseDouble(ans[i]);
                double o = Double.parseDouble(out[i]);
                assertEquals(
                        Math.abs(a - o) <= eps, true,
                        String.format("Error too large! eps = %.9f, ans = %.9f, out = %.9f", eps, a, o)
                );
            }
        } catch (IOException e) {
            fail("Can't read file " + filename);
        }
    }
}
