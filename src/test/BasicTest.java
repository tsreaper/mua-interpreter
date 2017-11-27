import org.junit.jupiter.api.Test;

class BasicTest extends MuaTest {
    private final String DIRECTORY = "basic/";

    @Test
    void BasicOperation() {
        runTest(DIRECTORY + "BasicOperation");
    }
}
