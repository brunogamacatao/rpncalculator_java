package br.brunocatao.rpncalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is RPN Calculator's entry point. This class should be executed without
 * any command line parameters. It will use the standard input and output to
 * gather and publish information, respectively.
 * 
 * @author Bruno Catao
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Interpreter interpreter = new Interpreter();
        interpreter.execute(new BufferedReader(new InputStreamReader(System.in)), System.out);
    }
}
