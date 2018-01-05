import org.junit.jupiter.api.Test;

class TypeTest extends MuaTest {
    TypeTest() {
        DIRECTORY = "type/";
    }

    @Test
    void IsType() {
        strcmpTest("IsType");
    }

    @Test
    void Concat() {
        strcmpTest("Concat");
    }

    @Test
    void FirstLast() {
        strcmpTest("FirstLast");
    }
}
