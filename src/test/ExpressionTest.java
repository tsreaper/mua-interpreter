import org.junit.jupiter.api.Test;

class ExpressionTest extends MuaTest {
    ExpressionTest() {
        DIRECTORY = "expression/";
    }

    @Test
    void ArithmeticExpression() {
        runTest("ArithmeticExpression");
    }

    @Test
    void ExpressionAndFunction() {
        runTest("ExpressionAndFunction");
        runTest("ExpressionAndFunction2");
    }
}
