package edu.neu.cs5004.rpn;

import edu.neu.cs5004.rpn.exceptions.IllegalOperationException;
import edu.neu.cs5004.rpn.exceptions.NotEnoughArgumentsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RPNCalculatorTest {

    private RPNCalculator testCalculator;

    @Test
    void testArithmetic() throws IllegalOperationException, NotEnoughArgumentsException {
        // +
        testCalculator = new RPNCalculator();
        testCalculator.processInput("90");
        testCalculator.processInput("10");
        testCalculator.processInput("100");
        assertEquals(testCalculator.pop(), 100.0);
        testCalculator.clearStack();
        // -
        testCalculator = new RPNCalculator();
        testCalculator.processInput("90");
        testCalculator.processInput("10");
        testCalculator.processInput("-");
        assertEquals(testCalculator.pop(), 80.0);
        testCalculator.clearStack();
        //*
        testCalculator = new RPNCalculator();
        testCalculator.processInput("90");
        testCalculator.processInput("10");
        testCalculator.processInput("*");
        assertEquals(testCalculator.pop(), 900.0);
        testCalculator.clearStack();
        //  /
        testCalculator = new RPNCalculator();
        testCalculator.processInput("90");
        testCalculator.processInput("10");
        testCalculator.processInput("/");
        assertEquals(testCalculator.pop(), 9.0);
        testCalculator.clearStack();
        // -(single)
        testCalculator = new RPNCalculator();
        testCalculator.processInput("90");
        testCalculator.processInput("-");
        assertEquals(testCalculator.pop(), -90.0);
        testCalculator.clearStack();
        //  %
        testCalculator = new RPNCalculator();
        testCalculator.processInput("90");
        testCalculator.processInput("10");
        testCalculator.processInput("mod");
        assertEquals(testCalculator.pop(), 0.0);
        testCalculator.clearStack();

    }

    @Test
    void testTriangle() throws IllegalOperationException, NotEnoughArgumentsException {
        // sin
        testCalculator = new RPNCalculator();
        testCalculator.processInput("90");
        testCalculator.processInput("sin");
        assertEquals(testCalculator.pop(), Math.sin(90));
        testCalculator.clearStack();
        //cos
        testCalculator = new RPNCalculator();
        testCalculator.processInput("90");
        testCalculator.processInput("cos");
        assertEquals(testCalculator.peekItemAt(0), Math.cos(90));
        testCalculator.clearStack();
        //tan
        testCalculator = new RPNCalculator();
        testCalculator.processInput("90");
        testCalculator.processInput("tan");
        assertEquals(testCalculator.pop(), Math.tan(90));
        testCalculator.clearStack();

    }

    @Test
    void testLiterals() throws IllegalOperationException, NotEnoughArgumentsException {
        //true
        testCalculator.clearStack();
        testCalculator = new RPNCalculator();
        testCalculator.processInput("true");
        assertEquals(testCalculator.pop(), 1.0);
        testCalculator.clearStack();
        // false
        testCalculator.clearStack();
        testCalculator = new RPNCalculator();
        testCalculator.processInput("false");
        assertEquals(testCalculator.pop(), 0.0);
        testCalculator.clearStack();


    }

    @Test
    void testBoolean() throws IllegalOperationException, NotEnoughArgumentsException {

        testCalculator = new RPNCalculator();
        testCalculator.processInput("0");
        testCalculator.processInput("0");
        testCalculator.processInput("!");
        assertEquals(testCalculator.pop(), 0.0);

        testCalculator.clearStack();
        testCalculator = new RPNCalculator();
        testCalculator.processInput("0");
        testCalculator.processInput("0");
        testCalculator.processInput("||");
        assertEquals(testCalculator.pop(), 0.0);
        testCalculator.clearStack();

        testCalculator.clearStack();
        testCalculator = new RPNCalculator();
        testCalculator.processInput("0");
        testCalculator.processInput("1");
        testCalculator.processInput("==");
        assertEquals(testCalculator.pop(), 0.0);
        testCalculator.clearStack();


    }
}