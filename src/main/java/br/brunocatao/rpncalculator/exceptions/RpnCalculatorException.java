package br.brunocatao.rpncalculator.exceptions;

import java.util.ResourceBundle;

/**
 * @author Bruno Catao
 */
public class RpnCalculatorException extends RuntimeException {
    private static final String MESSAGES_PATH = "br.brunocatao.rpncalculator.messages.errors";
    private static ResourceBundle resourceBundle;
    
    public RpnCalculatorException(String messageKey) {
        super(messageKey);
    }
    
    @Override
    public String getLocalizedMessage() {
        return getResourceBundle().getString(getMessage());
    }
    
    private static ResourceBundle getResourceBundle() {
        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle(MESSAGES_PATH);
        }
        
        return resourceBundle;
    }
}
