import org.junit.jupiter.api.Test;

class BasicTest extends MuaTest {
    BasicTest() {
        DIRECTORY = "basic/";
    }

    @Test
    void BasicOperation() {
        strcmpTest("BasicOperation");
    }
}
