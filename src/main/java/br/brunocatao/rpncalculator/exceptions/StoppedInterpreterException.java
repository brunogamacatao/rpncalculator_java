package br.brunocatao.rpncalculator.exceptions;

/**
 *
 * @author Bruno Catao
 */
public class StoppedInterpreterException extends RpnCalculatorException {
    public StoppedInterpreterException() {
        super("error.stopped_interpreter");
    }
}
