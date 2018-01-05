import org.junit.jupiter.api.Test;

class SyntaxTest extends MuaTest {
    SyntaxTest() {
        DIRECTORY = "syntax/";
    }

    @Test
    void UnfriendlySyntax() {
        strcmpTest("UnfriendlySyntax");
    }

    @Test
    void FlowInput() {
        strcmpTest("FlowInput");
    }

    @Test
    void ExpressionAndList() {
        strcmpTest("ExpressionAndList");
    }
}
