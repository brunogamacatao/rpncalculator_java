package br.brunocatao.rpncalculator;

import br.brunocatao.rpncalculator.exceptions.InvalidCommandException;
import br.brunocatao.rpncalculator.exceptions.RpnCalculatorException;
import br.brunocatao.rpncalculator.exceptions.StoppedInterpreterException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.math.NumberUtils.isNumber;

/**
 * This class implements the RPN Calculator's protocol. It supports both serial
 * command execution and streammed execution.
 * 
 * @author Bruno Catao
 */
public class Interpreter {
    private static final String QUIT_COMMAND = "q";
    
    private final RpnCalculator calculator;
    private final Map<String, Runnable> operators;
    private boolean running;
    
    public Interpreter() {
        calculator = new RpnCalculator();
        operators  = new HashMap();
        running    = true;
        
        initOperators();
    }
    
    /**
     * This method executes one command at the interpreter.
     * 
     * @param command The command to be executed.
     * @return The result of the command execution.
     * @throws StoppedInterpreterException if the interpreter is stopped
     * @throws InvalidCommandException if an invalid command is supplied
     */
    public String execute(String command) {
        if (!isRunning()) {
            throw new StoppedInterpreterException();
        }
        
        command = command.trim(); // The command is trimmed by default
        
        if (isNumber(command)) {
            calculator.push(new BigDecimal(command));
        } else if (isOperator(command)) {
            operators.get(command).run();
        } else if (isQuitCommand(command)) {
            running = false;
        } else if (isEmpty(command)) {
            // An empty command is simply ignored
        } else {
            throw new InvalidCommandException();
        }
        
        return calculator.getCurrentValue().toString();
    }
    
    /**
     * This is the streammed version of the execute method. Any execution errors
     * will be pushed to output stream insted of raising an exception.
     * 
     * @param input The input stream for the interpreter.
     * @param output The output stream where results will be pushed.
     * @throws IOException This exception will be only thrown if the input 
     * output streams are not functional.
     */
    public void execute(BufferedReader input, PrintStream output) throws IOException {
        String line;
        while ((line = input.readLine()) != null) {
            try {
                String result = execute(line);
                
                /* It breaks the execution, in case of interpreter stop, before
                 * appending an output. */
                if (!isRunning()) break;
                
                output.println(result);
            } catch (RpnCalculatorException ex) {
                // Exceptions are pushed to output stream insted of being thrown
                output.println(ex.getLocalizedMessage());
            }
        }
    }
    
    // Below are simple utility methods
    public boolean isRunning() {
        return running;
    }
    
    private boolean isOperator(String command) {
        return operators.containsKey(command);
    }
    
    private boolean isQuitCommand(String command) {
        return QUIT_COMMAND.equals(command);
    }
    
    // This method initializes an operator map with their respective functions
    private void initOperators() {
        operators.put("+", () -> calculator.add());
        operators.put("-", () -> calculator.subtract());
        operators.put("*", () -> calculator.multiply());
        operators.put("/", () -> calculator.divide());
    }
}
