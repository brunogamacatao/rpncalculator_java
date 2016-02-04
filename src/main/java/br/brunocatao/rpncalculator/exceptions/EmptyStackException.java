package br.brunocatao.rpncalculator.exceptions;

/**
 * This exception is thrown whenever the user tries to perform an operation
 * before informing any values to calculator.
 * 
 * @author Bruno Catao
 */
public class EmptyStackException extends RpnCalculatorException {
    public EmptyStackException() {
        super("error.empty_stack");
    }
}
