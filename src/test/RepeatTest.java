import org.junit.jupiter.api.Test;

class RepeatTest extends MuaTest {
    private final String DIRECTORY = "repeat/";

    @Test
    void BasicRepeat() {
        runTest(DIRECTORY + "BasicRepeat");
    }

    @Test
    void NestedRepeat() {
        runTest(DIRECTORY + "NestedRepeat");
    }

    @Test
    void OperandNumberExceptionRepeat() {
        runTest(DIRECTORY + "OperandNumberExceptionRepeat");
    }
}
