import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalcModelTest {
    @Test
    public void testSimpleSubtraction() {
        CalcModel model = new CalcModel();
        model.updateQuery(4);
        model.updateQuery(Operation.SUB);
        model.updateQuery(3);
        String result = model.displayResult();
        assertEquals("1.0", result);
    }

    @Test
    public void testSimpleAddition() {
        CalcModel model = new CalcModel();
        model.updateQuery(4);
        model.updateQuery(Operation.ADD);
        model.updateQuery(3);
        String result = model.displayResult();
        assertEquals("7.0", result);
    }

    @Test
    public void testSimpleMultiplication() {
        CalcModel model = new CalcModel();
        model.updateQuery(4);
        model.updateQuery(Operation.MULT);
        model.updateQuery(3);
        String result = model.displayResult();
        assertEquals("12.0", result);
    }

    @Test
    public void testSimpleDivision() {
        CalcModel model = new CalcModel();
        model.updateQuery(4);
        model.updateQuery(Operation.DIV);
        model.updateQuery(3);
        String result = model.displayResult();
        //TODO this might give a rounding error?
        assertEquals(Float.toString((float) 4/3), result);
    }

    @Test
    public void testSimpleModulo() {
        CalcModel model = new CalcModel();
        model.updateQuery(4);
        model.updateQuery(Operation.MOD);
        model.updateQuery(3);
        String result = model.displayResult();
        assertEquals("1.0", result);
    }

    @Test
    public void testPointBeforeLine() {
        CalcModel model = new CalcModel();
        model.updateQuery(4);
        model.updateQuery(Operation.ADD);
        model.updateQuery(3);
        model.updateQuery(Operation.MULT);
        model.updateQuery(2);
        String result = model.displayResult();
        assertEquals("10.0", result);
    }

    @Test
    public void testInvalidQuery() {
        CalcModel model = new CalcModel();
        model.updateQuery(4);
        model.updateQuery(Operation.ADD);
        model.updateQuery(Operation.MULT);
        model.updateQuery(2);
        String result = model.displayResult();
        assertEquals("Invalid query", result);
    }
}
