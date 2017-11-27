import org.junit.jupiter.api.Test;

class RepeatTest extends MuaTest {
    private final String DIRECTORY = "repeat/";

    @Test
    void BasicRepeat() {
        runTest(DIRECTORY + "BasicRepeat");
    }
}
