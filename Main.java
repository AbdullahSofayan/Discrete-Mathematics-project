import java.util.Scanner;

class Main {

    // Function f(x) = (x^2 + 1) % n
    public static long f(long x, long n) {
        return (x * x + 1) % n; // Returns the result of the polynomial modulo n
    }

    // gcd function to compute the greatest common divisor
    public static long gcd(long a, long b) {
        // Using the Euclidean algorithm to find gcd
        while (b != 0) { 
            long temp = b; // Store b in a temporary variable
            b = a % b;     // Update b to the remainder of a divided by b
            a = temp;      // Update a to the value of b
        }
        return a; 
    }

    // Helper function to check if a number is prime
    public static boolean isPrime(long n) {
        if (n <= 1) return false; // Numbers <= 1 are not prime
        // Check divisibility from 2 up to the square root of n
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false; // If divisible, it's not prime
        }
        return true; // Return true if no divisors are found
    }

    // Pollard's Rho algorithm to find a non-trivial factor of n
    public static long pollardsRho(long n) {    
        if (isPrime(n)) // If n is prime, return 1 as no factorization is needed
            return 1;

        // Initialize variables x, y, and d
        long x = 2; // Starting value for x
        long y = 2; // Starting value for y
        long d = 1; // Initial gcd value

        // Loop until a non-trivial factor is found
        while (d == 1 || d == n) {
            // Update x using the function f
            x = f(x, n); 
            // Update y twice using f (to ensure faster divergence)
            y = f(f(y, n), n); 

            // Calculate gcd of |x - y| and n
            d = gcd(Math.abs(x - y), n); 

            // If a non-trivial factor is found, return it
            if (d != 1 && d != n) {
                return d;
            }
        }
        return 1; // Return 1 if no factor is found (unlikely in practice)
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // Create a Scanner object for user input
        long input; // Variable to store the user's input

        // Loop to repeatedly ask for user input
        do {
            // Print program menu
            System.out.println();
            System.out.println("******************* Integer factorization program *******************");
            System.out.println("Enter Number to factorize \nEnter 0 to exit");
            System.out.print("Number: ");
            input = in.nextLong(); // Read the user's Number

            // Exit the program if input is 0
            if (input == 0) {
                System.out.println("\nProgram Ended");
                break;
            }

            // Try to find a factor using Pollard's Rho
            long factor = pollardsRho(input);
            if (factor == 1) {
                // If no factor is found, print a message
                System.out.println("\nNo factor found.");
            } else {
                // Print the factorization result
                System.out.println("\nFactorization of " + input + ":");
                System.out.println("A non-trivial factor: " + factor);
                System.out.println("Other factor: " + (input / factor));
            }
        } while (true); // Keep looping until the user chooses to exit
    }
}
