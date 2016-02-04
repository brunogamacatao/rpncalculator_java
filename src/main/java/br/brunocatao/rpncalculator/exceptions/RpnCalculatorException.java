package br.brunocatao.rpncalculator.exceptions;

import java.util.ResourceBundle;

/**
 * This is the root of rpncalculator project exceptions. This exception 
 * implements a mechanism to display localized messages. Those messages are
 * stored on resource bundles (property files) that are loaded according to
 * JVM's current locale.
 * 
 * @author Bruno Catao
 */
public class RpnCalculatorException extends RuntimeException {
    // The location of the root message resource bundle
    private static final String MESSAGES_PATH = "br.brunocatao.rpncalculator.messages.errors";
    private static ResourceBundle resourceBundle;
    
    /**
     * An exception must be created informing it's localized message key, thus
     * the correct message can be displayed to users.
     * 
     * @param messageKey The exception message key at resource bundle.
     */
    public RpnCalculatorException(String messageKey) {
        super(messageKey);
    }
    
    @Override
    public String getLocalizedMessage() {
        return getResourceBundle().getString(getMessage());
    }
    
    /* There's no need to have a resource bundle for each instance, so it is static */
    private static ResourceBundle getResourceBundle() {
        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle(MESSAGES_PATH);
        }
        
        return resourceBundle;
    }
}
