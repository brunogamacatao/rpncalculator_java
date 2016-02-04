package br.brunocatao.rpncalculator.exceptions;

/**
 * This exception is thown allways that a user tries to execute an invalid 
 * command.
 * 
 * @author Bruno Catao
 */
public class InvalidCommandException extends RpnCalculatorException {
    public InvalidCommandException() {
        super("error.invalid_command");
    }
}
