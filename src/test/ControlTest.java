import org.junit.jupiter.api.Test;

class ControlTest extends MuaTest {
    ControlTest() {
        DIRECTORY = "control/";
    }

    @Test
    void BasicRepeat() {
        strcmpTest("BasicRepeat");
    }

    @Test
    void NestedRepeat() {
        strcmpTest("NestedRepeat");
    }

    @Test
    void NestedRun() {
        strcmpTest("NestedRun");
    }

    @Test
    void NestedIf() {
        strcmpTest("NestedIf");
    }

    @Test
    void StopRepeatInIf() {
        strcmpTest("StopRepeatInIf");
    }

    @Test
    void OperandNumberExceptionRepeat() {
        strcmpTest("OperandNumberExceptionRepeat");
    }
}
