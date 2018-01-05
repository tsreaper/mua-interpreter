import org.junit.jupiter.api.Test;

class ControlTest extends MuaTest {
    ControlTest() {
        DIRECTORY = "control/";
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
    void NestedRun() {
        runTest("NestedRun");
    }

    @Test
    void OperandNumberExceptionRepeat() {
        runTest("OperandNumberExceptionRepeat");
    }
}
