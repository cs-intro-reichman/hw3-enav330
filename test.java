// Computes the periodical payment necessary to pay a given loan.
public class test {

    static double epsilon = 0.001;  // Approximation accuracy
    static int iterationCounter;    // Number of iterations 

    // Gets the loan data and computes the periodical payment.
    public static void main(String[] args) {        
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);
        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        // Computes the periodical payment using brute force search
        System.out.print("\nPeriodical payment, using brute force: ");
        System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);

        // Computes the periodical payment using bisection search
        System.out.print("\nPeriodical payment, using bi-section search: ");
        System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);
    }

    // Computes ending balance after n payments of "payment"
    private static double endBalance(double loan, double rate, int n, double payment) {
        double balance = loan;
        for (int i = 0; i < n; i++) {
            balance = (balance - payment) * (1+rate/100);
        }
        return balance;
    }

    // Brute force search for payment
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;

        // Start with loan/n as instructed (definitely too small)
        double g = loan / n;

        // Increase g until ending balance becomes <= 0
        while (endBalance(loan, rate, n, g) > 0) {
            g += epsilon;
            iterationCounter++;
        }
        return g;
    }

    // Bisection search for payment
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        iterationCounter = 0;

        // lo gives positive ending balance (too low payment)
        double lo = loan / n;

        // hi must give negative ending balance: choose something big
        double hi = loan;  // paying full loan every period is definitely too high

        // Ensure hi is indeed high enough
        while (endBalance(loan, rate, n, hi) > 0) {
            hi *= 2;  // expand high bound if needed
        }

        double mid = (lo + hi) / 2;

        while ((hi - lo) > epsilon) {
            iterationCounter++;

            double fmid = endBalance(loan, rate, n, mid);
            double flo = endBalance(loan, rate, n, lo);

            // If f(mid) and f(lo) have same sign, root is in [mid, hi]
            if (fmid * flo > 0) {
                lo = mid;
            } else {
                hi = mid;
            }

            mid = (lo + hi) / 2;
        }

        return mid;
    }
}
