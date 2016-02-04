package br.brunocatao.rpncalculator;

import br.brunocatao.rpncalculator.exceptions.InvalidCommandException;
import br.brunocatao.rpncalculator.exceptions.StoppedInterpreterException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Locale;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Those are the unit tests for Interpreter class.
 * 
 * @author Bruno Catao
 */
public class InterpreterTest {
    private Interpreter interpreter;
    
    @Before
    public void setUp() {
        interpreter = new Interpreter();
    }
    
    @Test
    public void itShouldReturnTheSameNumberWhenItsPassedAsACommand() {
        assertEquals("2", interpreter.execute("2"));
        assertEquals("3", interpreter.execute("3"));
    }
    
    @Test
    public void itShouldPerformTheFourOperations() {
        interpreter.execute("20");
        interpreter.execute("13");
        assertEquals("7", interpreter.execute("-"));
        interpreter.execute("2");
        assertEquals("3.5", interpreter.execute("/"));
    }
    
    @Test(expected = InvalidCommandException.class)
    public void itShouldNotAllowInvalidCommands() {
        interpreter.execute("9,3");
    }
    
    @Test
    public void itShouldStopExecutingWhenQCommandIsSupplied() {
        assertTrue(interpreter.isRunning());
        interpreter.execute("q");
        assertFalse(interpreter.isRunning());
    }
    
    @Test(expected = StoppedInterpreterException.class)
    public void itShouldNotAllowAdditionalCommandsAfterStopped() {
        interpreter.execute("q");
        interpreter.execute("2");
    }
    
    @Test
    public void itShouldIgnoreAnEmptyLine() {
        assertEquals("2", interpreter.execute("2"));
        assertEquals("2", interpreter.execute(""));
        assertEquals("3", interpreter.execute("3"));
    }
    
    @Test
    public void itShouldTrimTheCommands() {
        assertEquals("2", interpreter.execute("  2"));
        assertEquals("3", interpreter.execute("3    "));
        assertEquals("5", interpreter.execute(" +  "));
    }
    
    @Test
    public void itShoulAlsoWorkWithIOStreams() throws IOException {
        String input  = "5\n8\n+";
        String output = "5\n8\n13\n";
        
        assertEquals(output, runInterpreterWithStreams(input));
    }
    
    @Test
    public void itShouldStopReadingTheStreamWhenAQuitCommandIsFound() throws IOException {
        String input  = "5\n8\n+\n9\nq\n10";
        String output = "5\n8\n13\n9\n";
        
        assertEquals(output, runInterpreterWithStreams(input));
    }
    
    @Test
    public void itShouldDisplayErrorsInstedOfStoppingTheInterpreter() throws IOException {
        Locale.setDefault(Locale.US);
        
        String invalidCommandInput  = "5\n8\nb";
        assertEquals("5\n8\nSyntax error. You have type an unknown command\n", runInterpreterWithStreams(invalidCommandInput));
    }
    
    private String runInterpreterWithStreams(String input) throws IOException {
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
        PrintStream out   = new PrintStream(outputBuffer, true);
        
        interpreter.execute(in, out);
        
        return outputBuffer.toString();
    }
}
