import org.junit.jupiter.api.Test;

class SyntaxTest extends MuaTest {
    SyntaxTest() {
        DIRECTORY = "syntax/";
    }

    @Test
    void UnfriendlySyntax() {
        runTest("UnfriendlySyntax");
    }

    @Test
    void FlowInput() {
        runTest("FlowInput");
    }
}
