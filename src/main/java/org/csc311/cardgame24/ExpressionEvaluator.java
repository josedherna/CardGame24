package org.csc311.cardgame24;

import java.util.Stack;

import static java.lang.Character.isDigit;

public class ExpressionEvaluator {
    private Stack<Character> toPostfix;
    private Stack<Double> evalStack;

    ExpressionEvaluator() {
        toPostfix = new Stack<>();
        evalStack = new Stack<>();
    }

    private int getPriority(char ch) {
        return switch (ch) {
            //Order of operations
            case '^' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> -1;
        };
    }

    private boolean isOperator(char ch) {
        return (ch == '^' || ch == '!' || ch == '*' || ch == '/' || ch == '+' || ch == '-');
    }

    private String convertToPostFix(String expression) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < expression.length(); ++i) {
            char ch = expression.charAt(i);

            if (isDigit(ch)) {
                output.append(ch);
                //Checks the next character and adds a space to output string if it's not a digit.
                if (i + 1 < expression.length() && !isDigit(expression.charAt(i + 1))) {
                    output.append(" ");
                }
                //Adds a space when the last digit is at the end of expression.
                else if (i == expression.length() - 1) {
                    output.append(" ");
                }
            }
            else if (ch == '(') {
                toPostfix.push(ch);
            }
            else if (ch == ')') {
                while (!toPostfix.isEmpty() && toPostfix.peek() != '(') {
                    output.append(toPostfix.pop()).append(" ");
                }
                toPostfix.pop();
            }
            else {
                while (!toPostfix.isEmpty() && getPriority(ch) <= getPriority(toPostfix.peek()) && isOperator(ch)) {
                    output.append(toPostfix.pop()).append(" ");
                }
                toPostfix.push(ch);
            }
        }

        while (!toPostfix.isEmpty()) {
            if (toPostfix.peek() == '(') {
                return "";
            }
            output.append(toPostfix.pop()).append(" ");
        }

        return output.toString();
    }

    public double evaluate(String expression) {
        StringBuilder digits = new StringBuilder();
        String postfix = convertToPostFix(expression);
        double result = 0.0;
        double operand1;
        double operand2;

        for (int i = 0; i < postfix.length(); ++i) {
            char ch = postfix.charAt(i);
            if (isDigit(ch)) {
                digits.append(ch);
                //Number is complete if the condition is true, pushes the number to evalStack.
                if (i + 1 < postfix.length() && !isDigit(postfix.charAt(i + 1)) && !isOperator(postfix.charAt(i + 1))) {
                    evalStack.push(Double.parseDouble(digits.toString()));
                    digits.setLength(0);
                }
            }
            else if (isOperator(ch)) {
                operand2 = evalStack.pop();
                operand1 = evalStack.pop();
                result = performOperation(operand1, operand2, ch);
                evalStack.push(result);
            }
        }
        return result;
    }

    private double performOperation(double operand1, double operand2, char operator) {
        return switch (operator) {
            case '^' -> Math.pow(operand1, operand2);
            case '*' -> operand1 * operand2;
            case '/' -> operand1 / operand2;
            case '+' -> operand1 + operand2;
            case '-' -> operand1 - operand2;
            default -> -1;
        };
    }
}
