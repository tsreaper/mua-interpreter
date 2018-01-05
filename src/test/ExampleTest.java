import org.junit.jupiter.api.Test;

class ExampleTest extends MuaTest {
    ExampleTest() {
        DIRECTORY = "example/";
    }

    @Test
    void SqrtNewton() {
        floatTest("SqrtNewton", 1e-3);
        floatTest("SqrtNewton2", 1e-3);
    }

    @Test
    void Factorial() {
        floatTest("Factorial", 1e-6);
    }

    @Test
    void Exp() {
        floatTest("Exp", 1e-6);
    }

    @Test
    void Pi() {
        floatTest("Pi", 1e-2);
    }

    @Test
    void PiMC() {
        floatTest("PiMC", 1e-1);
    }

    @Test
    void BsRoot() {
        floatTest("BsRoot", 1e-3);
    }
}
