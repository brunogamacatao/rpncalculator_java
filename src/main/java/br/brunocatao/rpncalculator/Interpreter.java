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
    
    public String execute(String command) {
        if (!isRunning()) {
            throw new StoppedInterpreterException();
        }
        
        command = command.trim();
        
        if (isNumber(command)) {
            calculator.push(new BigDecimal(command));
        } else if (isOperator(command)) {
            operators.get(command).run();
        } else if (isQuitCommand(command)) {
            running = false;
        } else if (isEmpty(command)) {
            // Simply ignore
        } else {
            throw new InvalidCommandException();
        }
        
        return calculator.getCurrentValue().toString();
    }
    
    public void execute(BufferedReader input, PrintStream output) throws IOException {
        String line;
        while ((line = input.readLine()) != null) {
            try {
                String result = execute(line);
                if (!isRunning()) break;
                output.println(result);
            } catch (RpnCalculatorException ex) {
                output.println(ex.getLocalizedMessage());
            }
        }
    }
    
    public boolean isRunning() {
        return running;
    }
    
    private boolean isOperator(String command) {
        return operators.containsKey(command);
    }
    
    private boolean isQuitCommand(String command) {
        return QUIT_COMMAND.equals(command);
    }
    
    private void initOperators() {
        operators.put("+", () -> calculator.add());
        operators.put("-", () -> calculator.subtract());
        operators.put("*", () -> calculator.multiply());
        operators.put("/", () -> calculator.divide());
    }
}
