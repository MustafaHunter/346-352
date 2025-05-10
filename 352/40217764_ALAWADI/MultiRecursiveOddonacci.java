/* Alawadi Mustafa
 * 40217764
 * Comp352
 * To: Professor Eric Chan
 * 24/05/2024
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MultiRecursiveOddonacci {

    public static void main(String[] args) {
    	
        // Testing for multiple Oddonacci n=10 
        System.out.println("Multiple Recursive Oddonacci(10): " + multipleOddonacci(10));
        
        // Call the test method
        testMultiRecursive();
        multipleRecursion();
    }

    // Method to calculate and write the Oddonacci values from 5 to 200 by 5
    public static void multipleRecursion() {
    	
    	
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("OddoOutM.txt"))) {
        	
        	
            for (int n = 5; n <= 200; n += 5) {
            	
                long timeStart = System.nanoTime();
                long observation = multipleOddonacci(n);
                long timeEnd = System.nanoTime();
                long fullTime = timeEnd - timeStart;
                
                // Write to file
                bw.write("Oddonacci(" + n + ") = " + observation + " Time: " + fullTime + " nanoseconds\n");
                
            }
            
        } 
        
        // Catch if there was a problem writing to file
        catch (IOException e) {
        	
            System.err.println("Error while writing file");
            e.printStackTrace();
        }
    }

    // Test the performance
    public static void testMultiRecursive() {
    	
    	// Start time
        long start = System.nanoTime();
        
        for (int n = 5; n <= 100; n += 5) {
        	
        	// Calculate the oddonacci value
            multipleOddonacci(n);
        }
        
        // End time
        long end = System.nanoTime();
        
        // Total time taken for the test
        OddonacciCalculator.writeTimeToFile(end - start, "Multiple");
    }

    // Calculate oddonacci using multiple recursion
    public static int multipleOddonacci(int n) {
    	
    	// Base case 
        if (n <= 3) {
        	
            return 1;
        }
        
        // Recursive case 
        return multipleOddonacci(n - 3) + multipleOddonacci(n - 2) + multipleOddonacci(n - 1);
    }
}
