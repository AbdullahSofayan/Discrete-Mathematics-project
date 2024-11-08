import java.math.BigInteger;

public class Main1 {
    public static BigInteger pollardsRho(BigInteger n) {
        // If n is even, immediately return 2 as a factor
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return BigInteger.TWO;
        }

        BigInteger x = BigInteger.TWO;
        BigInteger y = BigInteger.TWO;
        BigInteger d = BigInteger.ONE;
        BigInteger C = BigInteger.ONE;

        // Try different values of C if necessary
        while (d.equals(BigInteger.ONE)) {
            // Update x and y using the function f(x) = (x^2 + C) % n
            x = f(x, C, n);
            y = f(f(y, C, n), C, n);

            // Calculate gcd(|x - y|, n)
            d = (x.subtract(y)).abs().gcd(n);

            // If d is a non-trivial factor, return it
            if (!d.equals(BigInteger.ONE) && !d.equals(n)) {
                return d;
            }

            // If no factor found, try a new constant C
            C = C.add(BigInteger.ONE);
        }

        // If no factor is found, return null
        return null;
    }

    // Function f(x) = (x^2 + C) % n
    private static BigInteger f(BigInteger x, BigInteger C, BigInteger n) {
        return x.multiply(x).add(C).mod(n);
    }

    public static void main(String[] args) {
        BigInteger n = BigInteger.valueOf(8051);
        BigInteger factor = pollardsRho(n);

        if (factor != null) {
            System.out.println("A non-trivial factor of " + n + " is: " + factor);
        } else {
            System.out.println("No factor found for " + n);
        }
    }
}
