import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class HashB {

  /**
   * 
   * Very simple hashg function
   * Sums the character values of the input string
   * and reduces the result to 8 bits (0-255)
   */
  public static int hash8(String line) {
    int sum = 0;

    // Loop through each character in the string
    for (char c : line.toCharArray()) {
      sum += (int) c;
    }

    // Reduce to 8 bit (256 possible values)
    return sum % 256;
  }

  public static void main(String[] args) throws  IOException {

    // Check that a filename was provided
    if (args.length != 1) {
      System.err.println("Usage: java HashA <input-file>");
      return;
    }

    String filename = args[0];

    // Buckets for uniformity test (0...255)
    int[] buckets = new int[256];

    // Track how many lines we hashed
    int totalLines = 0;

    // Open a text file for reading (UTF-8)
    try (BufferedReader reader = 
              Files.newBufferedReader(Path.of(filename), StandardCharsets.UTF_8)) {

      
      String line;
      int lineNumber = 1;
      
      // Read the file line by line
      while ((line = reader.readLine()) != null) {
        int hash = hash8(line);

        // Count how often each hash value occurs
        buckets[hash]++;

        // Increase total line counter
        totalLines++;
        
        System.out.printf("%6d hash=%3d line=%s%n", lineNumber, hash, line);
        lineNumber++;
        
      }
    }
  }
}
