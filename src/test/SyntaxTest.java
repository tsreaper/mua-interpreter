import org.junit.jupiter.api.Test;

class SyntaxTest extends MuaTest {
    private final String DIRECTORY = "syntax/";

    @Test
    void UnfriendlySyntax() {
        runTest(DIRECTORY + "UnfriendlySyntax");
    }
}
