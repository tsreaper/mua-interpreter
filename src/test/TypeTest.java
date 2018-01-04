import org.junit.jupiter.api.Test;

class TypeTest extends MuaTest {
    TypeTest() {
        DIRECTORY = "type/";
    }

    @Test
    void IsType() {
        runTest("IsType");
    }

    @Test
    void Concat() {
        runTest("Concat");
    }

    @Test
    void FirstLast() {
        runTest("FirstLast");
    }
}
