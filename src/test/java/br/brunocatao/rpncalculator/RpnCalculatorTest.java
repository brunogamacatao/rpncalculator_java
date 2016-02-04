package br.brunocatao.rpncalculator;

import java.math.BigDecimal;
import br.brunocatao.rpncalculator.exceptions.EmptyStackException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Bruno Catao
 */
public class RpnCalculatorTest {
    private RpnCalculator calc;
    
    @Before
    public void setUp() {
        calc = new RpnCalculator();
    }

    @Test
    public void testIfTheCurrentValueInitiallyIsZero() {
        assertEquals(BigDecimal.ZERO, calc.getCurrentValue());
    }
    
    @Test
    public void itShouldPushAValueIntoStackAndUpdateTheCurrentValue() {
        calc.push(BigDecimal.valueOf(32));
        assertEquals(BigDecimal.valueOf(32), calc.getCurrentValue());
        calc.push(BigDecimal.valueOf(9));
        assertEquals(BigDecimal.valueOf(9), calc.getCurrentValue());
    }
    
    @Test
    public void itShouldAcceptIntegerLiterals() {
        calc.push(8);
    }
    
    @Test
    public void itShouldAcceptFloatingPointLiterals() {
        calc.push(3.14);
    }
    
    @Test
    public void itShouldAddTwoValuesAndUpdateTheCurrentValue() {
        calc.push(3);
        calc.push(8);
        assertEquals(BigDecimal.valueOf(3 + 8), calc.add());
        assertEquals(BigDecimal.valueOf(3 + 8), calc.getCurrentValue());
    }
    
    @Test(expected = EmptyStackException.class)
    public void itShouldNotPerformOperationOverAnEmptyStackCalculator() {
        calc.add();
    }
    
    @Test
    public void itShouldSubtractTwoValuesAndUpdateTheCurrentValue() {
        calc.push(42);
        calc.push(40);
        assertEquals(BigDecimal.valueOf(42 - 40), calc.subtract());
        assertEquals(BigDecimal.valueOf(42 - 40), calc.getCurrentValue());
    }
    
    @Test
    public void itShouldMultiplyTwoValuesAndUpdateTheCurrentValue() {
        calc.push(7);
        calc.push(8);
        assertEquals(BigDecimal.valueOf(7 * 8), calc.multiply());
        assertEquals(BigDecimal.valueOf(7 * 8), calc.getCurrentValue());
    }
    
    @Test
    public void itShouldDiviceTwoValuesAndUpdateTheCurrentValue() {
        calc.push(3);
        calc.push(2);
        assertEquals(BigDecimal.valueOf(3.0 / 2.0), calc.divide());
        assertEquals(BigDecimal.valueOf(3.0 / 2.0), calc.getCurrentValue());
    }
    
    @Test
    public void itShouldAddThreeOrMoreValues() {
        calc.push(1);
        calc.push(2);
        calc.push(3);
        assertEquals(BigDecimal.valueOf(1 + 2 + 3), calc.add());
        calc.push(4);
        calc.push(5);
        calc.push(6);
        assertEquals(BigDecimal.valueOf(6 + 4 + 5 + 6), calc.add());
    }
    
    @Test
    public void itShouldAllowMultipleOperations() {
        calc.push(-3);
        calc.push(-2);
        assertEquals(BigDecimal.valueOf(6), calc.multiply());
        calc.push(5);
        assertEquals(BigDecimal.valueOf(11), calc.add());
    }
}
