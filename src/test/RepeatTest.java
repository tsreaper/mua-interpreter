import org.junit.jupiter.api.Test;

class RepeatTest extends MuaTest {
    RepeatTest() {
        DIRECTORY = "repeat/";
    }

    @Test
    void BasicRepeat() {
        runTest("BasicRepeat");
    }

    @Test
    void NestedRepeat() {
        runTest("NestedRepeat");
    }

    @Test
    void OperandNumberExceptionRepeat() {
        runTest("OperandNumberExceptionRepeat");
    }
}
