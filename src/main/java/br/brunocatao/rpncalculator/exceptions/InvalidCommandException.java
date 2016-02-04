package br.brunocatao.rpncalculator.exceptions;

/**
 * @author Bruno Catao
 */
public class InvalidCommandException extends RpnCalculatorException {
    public InvalidCommandException() {
        super("error.invalid_command");
    }
}
