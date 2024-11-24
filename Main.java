
import java.util.Scanner;



class Main{

// Function f(x) = (x^2 + 1) % n
public static long f(long x, long n) {
    return (x * x + 1) % n;
}

// gcd function to compute the greatest common divisor
public static long gcd(long a, long b) {
    while (b != 0) {
        long temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}

// Pollard's Rho algorithm to find a non-trivial factor of n
public static long pollardsRho(long n) {    
    // Initialize x and y, d
    long x = 2;
    long y = 2;
    long d = 1;

    // Repeat until we find a non-trivial factor
    while (d == 1 || d == n) {
        // Calculate the next x and y values
        x = f(x, n); // x = f(x)
        y = f(f(y, n), n); // y = f(f(y))

        // Calculate the gcd of the absolute difference between x and y, and n
        d = gcd(Math.abs(x - y), n); // d = gcd(|x - y|, n)

        // If d is a non-trivial factor, return it
        if (d != 1 && d != n) {
            return d;
        }
    }
    return 1; // In case no factor is found (shouldn't happen in theory)
}

public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    long input;
    do{
    System.out.println("******************* Integer factorization program *******************");
    System.out.println("Enter Number to factorize \nEnter 0 to exit");
    System.out.print("Choice: ");
    input = in.nextLong(); 
    if (input==0){
    System.out.println("Program shut down");
    break;
    }
    System.out.println("Factorization of " + input + ":");

    // Try to find a factor using Pollard's Rho
    long factor = pollardsRho(input);
    if (factor == 1) {
        System.out.println("No factor found.");
    } else {
        System.out.println("A non-trivial factor: " + factor);
        System.out.println("Other factor: " + (input / factor));
        System.out.println();
    }
    } while(true);
}

   
}


