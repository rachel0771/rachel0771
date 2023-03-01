package edu.neu.cs5004.rpn;

import edu.neu.cs5004.rpn.exceptions.IllegalOperationException;
import edu.neu.cs5004.rpn.exceptions.NotEnoughArgumentsException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Stack;

public class RPNCalculator {

    private static final List<String> operators = List.of("+", "-","*", "/", "%", "sin", "cos", "tan", "abs", "==", "&&", "||", "!");
    private static final List<String> needSingleValue = List.of("sin", "cos", "tan", "abs", "!");
    private static final List<String> needTwoValues = List.of("+", "*", "/", "%", "==", "&&", "||");
    private static final List<String> needOneOrTwo = List.of("-");
    private static final List<String> literals = List.of("True", "False", "true", "false");
    private double result;
    private Stack<Double> stack = new Stack<>();
    public void processInput(String input) throws NotEnoughArgumentsException, IllegalOperationException {
        // step 1: check if input is an operator
        if(operators.contains(input)){
            if(needTwoValues.contains(input) ){
                if(stack.size() > 1) {
                    calculateTwo(input);
                }
                else{
                    throw new NotEnoughArgumentsException();
                }
            }
            else if (needOneOrTwo.contains(input)) {
                if(stack.size() == 1){
                    calculateSingle(input);
                }
                else if (stack.size()>1) {
                    calculateTwo(input);

                }else
                    throw new NotEnoughArgumentsException();

            }
            // check if stack size is valid and perform operation, throw exception if missing required values
            else if(needSingleValue.contains(input)){
                if(stack.size() > 0){
                    calculateSingle(input);
                }
                else throw new NotEnoughArgumentsException();
                }
            }
        // step 2: check if input is a literal
        else if(literals.contains(input)){
            processLiterals(input);// convert string literal to 0 : 1 and push to stack
        }
        else{  // step 3: check if input is a valid double
            try{// try block: try to convert to Double
                stack.push(Double.parseDouble(input));
            }catch (NumberFormatException e){
                throw new IllegalOperationException();
            }// try catch, convert input to double and push onto stack
        }
    }

    public Double pop() {
        return stack.pop();
    }

    public Double peekItemAt(int index) {
        return stack.elementAt(index);
    }


    private double calculateTwo(String input){
        double secondOperand = stack.pop();
        double firstOperand = stack.pop();
        if(input.equals("+")) {
            result = firstOperand + secondOperand;
        }
        else if(input.equals("-"))
            result = firstOperand - secondOperand;
        else if(input.equals("*"))
            result = firstOperand * secondOperand;
        else if(input.equals("/"))
            result = firstOperand / secondOperand;
        else if(input.equals("abs"))
            if(firstOperand >= 0 && secondOperand >= 0) {
                result = firstOperand + secondOperand;
            }
            else if (firstOperand < 0 && secondOperand > 0) {
                result = secondOperand - firstOperand;
            } else if (firstOperand > 0 && secondOperand < 0) {
                result = firstOperand - secondOperand;
            }else {
                result = -(firstOperand + secondOperand);
            }
        else if(input.equals("mod"))
            result = firstOperand % secondOperand;
        else if(input.equals("==")){
            if(firstOperand == secondOperand)
                // true
                result = 1;
            else{
                // not true
                result = 0;
            }
        }
        else if (input.equals(" &&")) {
            if(firstOperand != secondOperand)
                result = 1;
            else {
                result = 0;
            }
        }
        else if (input.equals("||")) {
            if((firstOperand != 0.0 && firstOperand != 1.0) || (secondOperand != 0.0 && secondOperand != 1.0)) {
                throw new IllegalArgumentException();
            }
            result = (firstOperand != 0.0) || (secondOperand != 0.0) ? 1.0 : 0.0;

        }
        else if (input.equals("!")) {
            if((firstOperand != 0.0 && firstOperand != 1.0) || (secondOperand != 0.0 && secondOperand != 1.0)){
                throw new IllegalArgumentException();
            }
            result = (firstOperand != 0.0) && (secondOperand != 0.0) ? 1.0 : 0.0;

        }
        // add back to stack and return result:
        //System.out.println("Result2: "+result);
        stack.push(result);
        return result;
    }

    private double calculateSingle(String symbol){
        double operand = stack.pop();
        if(symbol.equals("sin"))
            result = Math.sin(operand);
        else if(symbol.equals("cos"))
            result = Math.cos(operand);
        else if(symbol.equals("tan"))
            result = Math.tan(operand);
        else if(symbol.equals( "-"))
            result = -operand;
        stack.push(result);
        return result;
    }

    private double processLiterals(String literal){
        // "True", "False", "true", "false"
        if(literal.toLowerCase().equals("true")){
            stack.push(1.0);
            return 1.0;
        }
        stack.push(0.0);
        return 0.0;
    }

    public void clearStack(){
        stack.clear();
    }

    @Override
    public String toString() {
        return "RPNCalculator{" +
                "stack=" + stack +
                '}';
    }
}
