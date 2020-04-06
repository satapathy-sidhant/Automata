package basics;

/**
 * great common divisor
 */
public class GCD {

    public static void main(String[] args) {
        System.out.println(GCD.gcd(191, 55));
    }

    private static int gcd(int dividend, int divisor) {
        if (divisor == 0) return dividend;
        int remainder = dividend % divisor;
        return gcd(divisor, remainder);
    }
}
