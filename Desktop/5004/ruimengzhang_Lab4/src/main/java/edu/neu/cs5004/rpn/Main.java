package edu.neu.cs5004.rpn;

import edu.neu.cs5004.rpn.exceptions.IllegalOperationException;
import edu.neu.cs5004.rpn.exceptions.NotEnoughArgumentsException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IllegalOperationException, NotEnoughArgumentsException {
        RPNCalculator calculator = new RPNCalculator();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter operand or operator");
            String input = scanner.nextLine();
            System.out.println(input + ": " + input.getClass());
            calculator.processInput(input);
            System.out.println(calculator.toString());
        }
    }
}
