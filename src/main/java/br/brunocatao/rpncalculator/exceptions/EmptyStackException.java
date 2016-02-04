package br.brunocatao.rpncalculator.exceptions;

/**
 * @author Bruno Catao
 */
public class EmptyStackException extends RpnCalculatorException {
    public EmptyStackException() {
        super("error.empty_stack");
    }
}
