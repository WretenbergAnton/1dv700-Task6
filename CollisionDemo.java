import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CollisionDemo {

  /**
   * Used the same hash function that i used in part (a) and (b)
   * It sums the charachters value of the input string
   * and reduces the result to 8 bit (0-255)
   */

  public static int hash8(String line) {
    int sum = 0;

    // Add the numeric value of each character
    for (char c : line.toCharArray()) {
      sum += (int) c;
    }

    // Limit the hash value to 8 bits
    return sum % 256;
  }

  /**
   * Generates a random string of fixed length.
   * This is used to create many different inputs
   * in order to find a hash collision
   */
  public static String randomString(Random rnd, int length) {
    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder sb = new StringBuilder();

    // Build the string character by charaacter
    for (int i = 0; i < length; i++) {
      sb.append(chars.charAt(rnd.nextInt(chars.length())));
    }
    return sb.toString();
  }

  public static void main(String[] args) {

    // Map that stores prviously seen hash values
    // Key = hash value (0-255)
    // Value = first string that produced this hash
    Map<Integer, String> seen = new HashMap<>();

    // Random number generator for creating test strings
    Random rnd = new Random();

    // Keep generating strings until a collision is found
    while (true) {

      // Generate a random input string
      String s = randomString(rnd, 20);

      // Compute the hash using the same hash function as in a/b
      int h = hash8(s);

      // check if this hash value has already been seen
      if (seen.containsKey(h) && !seen.get(h).equals(s)) {

        // Collision found: two different strings have the same hash
        System.out.println("Collision found");
        System.out.println("Hash value: " + h);
        System.out.println("String A: " + seen.get(h));
        System.out.println("String B: " + s);
        break;
      }

      // Store the hash value and its corresponding string
      seen.put(h, s);
    }

  }
}
