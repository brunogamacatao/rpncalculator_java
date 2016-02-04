package br.brunocatao.rpncalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Bruno Catao
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Interpreter interpreter = new Interpreter();
        interpreter.execute(new BufferedReader(new InputStreamReader(System.in)), System.out);
    }
}
