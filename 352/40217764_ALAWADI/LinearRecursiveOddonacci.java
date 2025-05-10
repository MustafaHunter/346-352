/* Alawadi Mustafa
 * 40217764
 * Comp352
 * To: Professor Eric Chan
 * 24/05/2024
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LinearRecursiveOddonacci {

    public static void main(String[] args) {
    	
    	// Calculate 10nth oddonacci and print it
        long result = linearOddonacci(10)[0];
        
        System.out.println("Linear Recursive Oddonacci(10): " + result);
        
        // Call the test linear function
        testLinear();
        linearRecursion();
    }

    // Method to calculate and write the Oddonacci values from 5 to 200 by 5
    public static void linearRecursion() {
    	
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("OddoOutL.txt"))) {
        	
            for (int n = 5; n <= 200; n += 5) {
            	
                long timeStart = System.nanoTime();
                long observation = linearOddonacci(n)[0];
                long timeEnd = System.nanoTime();
                long fullTime = timeEnd - timeStart;

                // Write to file
                bw.write("Oddonacci(" + n + ") = " + observation + " Time: " + fullTime + " nanoseconds\n");
            }
            
        } 
        
        // Catch if there is a problem when writing to file
        catch (IOException e) {
        	
            System.err.println("Error while writing file");
            e.printStackTrace();
        }
    }

    // Test performance of function
    public static void testLinear() {
    	
    	// Start time
        long start = System.nanoTime();
        
        for (int n = 5; n <= 100; n += 5) {
        	
        	// The value
            linearOddonacci(n);
        }
        
        // End time
        long end = System.nanoTime();
        
        // Total time taken for test
        OddonacciCalculator.writeTimeToFile(end - start, "Linear");
    }

    // Calculate oddonacci with linear recursion
    public static long[] linearOddonacci(int n) {
    	
    	// Base case 
        if (n <= 3) {
        	
            long[] answer = {1, 1, 1};
            
            return answer;
            
        } 
        
        else {
        	
        	// Recursive step
            long[] temp = linearOddonacci(n - 1);
            long[] value = {temp[0] + temp[1] + temp[2], temp[0], temp[1]};
            
            
            return value;
        }
    }
}
