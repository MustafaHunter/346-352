/* Alawadi Mustafa
 * 40217764
 * Comp352
 * To: Professor Eric Chan
 * 24/05/2024
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OddonacciCalculator {

    public static void main(String[] args) {
    	
        // Testing the function with n=10
        System.out.println("Tail Recursive Oddonacci(10): " + tail(10));

        // Run the tests for the multiple and linear recursive methods
        MultiRecursiveOddonacci.testMultiRecursive();
        LinearRecursiveOddonacci.testLinear();

        // Run the recursion methods
        MultiRecursiveOddonacci.multipleRecursion();
        LinearRecursiveOddonacci.linearRecursion();
    }

    // Write the time to the file
    public static void writeTimeToFile(long time, String name) {
    	
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(name + "Odoo.txt", true))) {
        	
            bw.write("Time to execute " + name + ": " + time + " nanoseconds\n");
            
        } 
        
        catch (IOException e) {
        	
            System.out.println("Error can't write.");
            e.printStackTrace();
        }
    }

    // The tail recursive method for calculating oddonacci numbers
    public static int tail(int n) {
    	
    	// Inner class for calculating oddonacci numbers
        class Oddonacci {
        	
            int oddo(int n, int one, int two, int three) {
            	
                if (n == 1)
                    return one;
                if (n == 2)
                    return two;
                if (n == 3)
                    return three;
                
                // Tail recursive call
                return oddo(n - 1, two, three, one + two + three);
            }
        }
        
        // Creating instance of the oddonacci class
        Oddonacci compute = new Oddonacci();
        
        return compute.oddo(n, 1, 1, 1);
    }
}
