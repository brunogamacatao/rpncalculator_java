package br.brunocatao.rpncalculator;

import br.brunocatao.rpncalculator.exceptions.EmptyStackException;
import java.math.BigDecimal;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.BiFunction;

/**
 * @author Bruno Catao
 */
public class RpnCalculator {
    private final Deque<BigDecimal> values;
    
    public RpnCalculator() {
        values = new LinkedList<>();
    }
    
    public BigDecimal getCurrentValue() {
        return values.isEmpty() ? BigDecimal.ZERO : values.getLast();
    }
    
    public void push(BigDecimal value) {
        values.add(value);
    }
    
    public void push(long value) {
        push(BigDecimal.valueOf(value));
    }
    
    public void push(double value) {
        push(BigDecimal.valueOf(value));
    }
    
    public BigDecimal add() {
        return operate((BigDecimal a, BigDecimal b) -> a.add(b));
    }
    
    public BigDecimal subtract() {
        return operate((BigDecimal a, BigDecimal b) -> a.subtract(b));
    }
    
    public BigDecimal multiply() {
        return operate((BigDecimal a, BigDecimal b) -> a.multiply(b));
    }
    
    public BigDecimal divide() {
        return operate((BigDecimal a, BigDecimal b) -> a.divide(b));
    }
    
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
