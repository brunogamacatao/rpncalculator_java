package br.brunocatao.rpncalculator;

import br.brunocatao.rpncalculator.exceptions.EmptyStackException;
import java.math.BigDecimal;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.BiFunction;

/**
 * This class implements the core of an RPN Calculator.
 * 
 * @author Bruno Catao
 */
public class RpnCalculator {
    /* In reverse polish notation, data is represented in a queue, new values
     * are pushed at the end, and all operations must be performed from the 
     * first to last stored value. */
    private final Deque<BigDecimal> values;
    
    public RpnCalculator() {
        values = new LinkedList<>();
    }
    
    /**
     * Returns the last pushed value.
     * @return The last pushed value
     */
    public BigDecimal getCurrentValue() {
        return values.isEmpty() ? BigDecimal.ZERO : values.getLast();
    }
    
    /**
     * This method adds a value to the end of calculator's values queue.
     * @param value The value to be added.
     */
    public void push(BigDecimal value) {
        values.add(value);
    }
    
    /**
     * Utility method that receive a long value and pushes it to calculator's
     * values.
     * @param value The value to be added.
     */
    public void push(long value) {
        push(BigDecimal.valueOf(value));
    }
    
    /**
     * Utility method that receive a double value and pushes it to calculator's
     * values.
     * @param value The value to be added.
     */
    public void push(double value) {
        push(BigDecimal.valueOf(value));
    }
    
    /**
     * Performs the add operation over the values' queue, removing them, and 
     * storing the result at the queue's head.
     * @return The result of the add operation.
     */
    public BigDecimal add() {
        return operate((BigDecimal a, BigDecimal b) -> a.add(b));
    }
    
    /**
     * Performs the subtract operation over the values' queue, removing them, 
     * and storing the result at the queue's head.
     * @return The result of the subtract operation.
     */
    public BigDecimal subtract() {
        return operate((BigDecimal a, BigDecimal b) -> a.subtract(b));
    }
    
    /**
     * Performs the multiply operation over the values' queue, removing them, 
     * and storing the result at the queue's head.
     * @return The result of the multiply operation.
     */
    public BigDecimal multiply() {
        return operate((BigDecimal a, BigDecimal b) -> a.multiply(b));
    }
    
    /**
     * Performs the divide operation over the values' queue, removing them, and 
     * storing the result at the queue's head.
     * @return The result of the divide operation.
     */
    public BigDecimal divide() {
        return operate((BigDecimal a, BigDecimal b) -> a.divide(b));
    }
    
    /* This method encapsulates the common behavior shared by operations methods */
    private BigDecimal operate(BiFunction<BigDecimal, BigDecimal, BigDecimal> op) {
        if (values.isEmpty()) {
            throw new EmptyStackException();
        }
        
        BigDecimal result = values.remove();
        
        while(!values.isEmpty()) {
            result = op.apply(result, values.remove());
        }
        
        values.add(result);
        
        return result;
    }
}
