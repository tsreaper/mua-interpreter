import org.junit.jupiter.api.Test;

class FunctionTest extends MuaTest {
    FunctionTest() {
        DIRECTORY = "function/";
    }

    @Test
    void BasicFunction() {
        runTest("BasicFunction");
    }

    @Test
    void CallFunctionInAnother() {
        runTest("CallFunctionInAnother");
    }

    @Test
    void FunctionClosure() {
        runTest("FunctionClosure");
    }
}
