package br.brunocatao.rpncalculator.exceptions;

/**
 * This exception is thown if the user tries to execute a command on a stopped
 * interpreter.
 * 
 * @author Bruno Catao
 */
public class StoppedInterpreterException extends RpnCalculatorException {
    public StoppedInterpreterException() {
        super("error.stopped_interpreter");
    }
}
