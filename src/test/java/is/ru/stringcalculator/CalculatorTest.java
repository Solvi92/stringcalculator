package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

public class CalculatorTest {

	public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    }

	@Test
	public void testEmptyString() throws Calculator.NegativeException{
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber() throws Calculator.NegativeException{
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void testTwoNumbers() throws Calculator.NegativeException{
		assertEquals(3, Calculator.add("1,2"));
	}	

	@Test
    	public void testMultipleNumbers() throws Calculator.NegativeException{
    		assertEquals(6, Calculator.add("1,2,3"));
    	}
	@Test
	public void testNewLine() throws Calculator.NegativeException{
		assertEquals(6, Calculator.add("1\n2,3"));
	}
	@Test
	public void testDelimiter() throws Calculator.NegativeException{
		assertEquals(3, Calculator.add("//;\n1;2"));
	}
	@Rule
	public ExpectedException exception = ExpectedException.none();
	@Test
	public void testNegative() throws Calculator.NegativeException{
		exception.expect(Calculator.NegativeExeption.class);
		exception.expectMessage("Negatives not allowed: -2, -4");
		Calculator.add("1, -2,3,-4");
	}
	
}
