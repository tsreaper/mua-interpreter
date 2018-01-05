import org.junit.jupiter.api.Test;

class ExpressionTest extends MuaTest {
    ExpressionTest() {
        DIRECTORY = "expression/";
    }

    @Test
    void ArithmeticExpression() {
        strcmpTest("ArithmeticExpression");
    }

    @Test
    void ComparisonExpression() {
        strcmpTest("ComparisonExpression");
    }

    @Test
    void ExpressionAndFunction() {
        strcmpTest("ExpressionAndFunction");
        strcmpTest("ExpressionAndFunction2");
    }
}
