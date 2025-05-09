import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Calculator {
    
    // Stack for storing numbers 
    private final MyStack<Double> number = new MyStack<>();
    // Stack for storing operators 
    private final MyStack<String> operation = new MyStack<>();
    // List to store parsed elements of expression
    private ArrayList<String> space;

   
    public double evaluateExpression(String str) {
        
        // This removes all whitespace and add () around the expression
        space = new ArrayList<>();
        String removeSpace = "(" + str.replaceAll("\\s", "") + ")";
        String num = "";
        int count = 0;

        // Parse the expression to numbers and operators
        for (int i = 0; i < removeSpace.length(); i++) {
            
            char c = removeSpace.charAt(i);
            
            // Include the decimal number for floating numbers
            if (isNumber(c) || c == '.') { 
                count = 0;
                num += c;
                continue;
            }
            
            // Add the parsed number to list
            if (count == 0) {
            	
                if (!num.isEmpty()) {
                    space.add(num);
                    num = "";
                }
                
                count = 1;
            }

            if (!isNumber(c)) {
                
                if (i < removeSpace.length() - 1 && ((c == '=' && removeSpace.charAt(i + 1) == '=') || (c == '<' && removeSpace.charAt(i + 1) == '=') || (c == '>' && removeSpace.charAt(i + 1) == '=') || (c == '!' && removeSpace.charAt(i + 1) == '='))) {
                    // For two-character operators
                    String op = "" + c + removeSpace.charAt(i + 1);
                    space.add(op);
                    i++;
                    continue;
                }
                
                // Add single character operators and ()
                space.add(Character.toString(c));
            }
        }

        // Evaluate parsed expression 
        for (int i = 0; i < space.size(); i++) {
            
            String s = space.get(i);
            
            // When encountering closing ()
            if (s.equals(")")) {
                
                while (!operation.isEmpty() && !operation.peek().equals("(")) {
                    operate();
                }
                
                // Remove the open parenthesis
                operation.pop();
            } 
            
            // When encountering an operator
            else if (isOperator(s)) {
                
                while (!operation.isEmpty() && stringOperator(s) <= stringOperator(operation.peek())) {
                	
                    // Performing operations based on operator precedence
                    operate();
                }
                
                // Push current operator to stack
                operation.push(s);
            } 
            
            // When encountering open parenthesis
            else if (s.equals("(")) {
                operation.push(s);
            } 
            
            // When encountering a number
            else {
                // Push it to the stack
                number.push(Double.parseDouble(s));
            }
        }
        
        // Perform remaining operations
        while (!operation.isEmpty()) {
            operate();
        }

        // Return result
        return number.pop();
    }

    // Perform the operation at the very top of the operator stack
    private void operate() {
    	
        // Get operator
        String operator = operation.pop();
        // Get the next operand
        double b = number.pop();
        // Get the first operand
        double a = number.pop();
        
        double result = 0;
        
        // Switch case for operation based on the operator
        switch (operator) {
        
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "^":
                result = Math.pow(a, b);
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            case ">":
                result = (a > b ? 1 : 0);
                break;
            case ">=":
                result = (a >= b ? 1 : 0);
                break;
            case "<":
                result = (a < b ? 1 : 0);
                break;
            case "<=":
                result = (a <= b ? 1 : 0);
                break;
            case "==":
                result = (a == b ? 1 : 0);
                break;
            case "!=":
                result = (a != b ? 1 : 0);
                break;
        }
        
        // Push the result to the stack
        number.push(result); 
    }

    // Method for checking if character is a digit
    public static boolean isNumber(char c) {
    	
        return (c >= '0' && c <= '9') || c == '.';
    }

    private boolean isOperator(String s) {
    	
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^") ||s.equals(">") || s.equals(">=") || s.equals("<") || s.equals("<=") ||s.equals("==") || s.equals("!=");
    }

    // Method for checking if a string is an operator
    private int stringOperator(String operator) {
    	
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            case ">":
            case ">=":
            case "<":
            case "<=":
                return 4;
            case "==":
            case "!=":
                return 5;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
    	
        // Input file name
        String fileName = "input.txt";
        String line;

        // Read expressions from input file and write to output file the result
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter("Output.txt"))) {

            Calculator calculator = new Calculator();
            
            while ((line = bufferedReader.readLine()) != null) {
                
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Evaluate the expression
                double result = calculator.evaluateExpression(line);
                writer.write(line + " = " + result + "\n");
            }
        } 
        // Exception in case the file is not found and for all other exceptions
        catch (FileNotFoundException e) {
        	
            System.out.println("Can't open file'" + fileName);
        } 
        catch (IOException e) {
        	
            System.out.println("Error");
        }
    }
}
