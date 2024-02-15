import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(1000000L, 7L, 3435L, 2377L, 4659L, 23L, 5556L);
        List<FactorialThread> threads = new ArrayList<>();

        for (long n : inputNumbers) {
            threads.add(new FactorialThread(n));
        }

        for (Thread thread : threads) {
            // Here we need to set the Daemon to true to end the program
            // gracefully even if it takes longer than 2000millis
            thread.setDaemon(true);
            thread.start();
        }

        for (Thread thread : threads) {
            // The 2000millis indicate that we won't tolerate waiting more than that
            // for each thread's computation. However, the program will still hang.
            // so we need to interrupt it by using thread.setDaemon(true) above before
            // starting the thread in the previous for loop
            thread.join(2000);
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread thread = threads.get(i);
            if (thread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is: " + thread.getResult());
            } else {
                System.out.println("The calculation for " + inputNumbers.get(i) + " is in progress...");
            }
        }
    }

    public static class FactorialThread extends Thread {
        private final long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long n) {
            BigInteger tempResult = BigInteger.ONE;
            for (long i = n; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public BigInteger getResult() {
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }
    }
}
