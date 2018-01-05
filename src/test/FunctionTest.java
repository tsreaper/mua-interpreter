import org.junit.jupiter.api.Test;

class FunctionTest extends MuaTest {
    FunctionTest() {
        DIRECTORY = "function/";
    }

    @Test
    void BasicFunction() {
        strcmpTest("BasicFunction");
    }

    @Test
    void CallFunctionInAnother() {
        strcmpTest("CallFunctionInAnother");
    }

    @Test
    void FunctionClosure() {
        strcmpTest("FunctionClosure");
        strcmpTest("FunctionClosure2");
    }
}
