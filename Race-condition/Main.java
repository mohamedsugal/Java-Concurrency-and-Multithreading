import java.util.*;

public class Main {
    private static int BALANCE = 500;

    public static void main(String[] args) throws InterruptedException {
        /**
         * There are 2 threads here that are called 10 times, the purpose here is
         * to see how they compete for resources and how a race condition could occur
         */
        final Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Thread thread1 = new Thread(new NewDecrementer(random.nextInt(400)));
            Thread thread2 = new Thread(new NewDecrementer(random.nextInt(200)));
            thread1.start();
            thread2.start();

        }
    }

    /**
     * This method is called by multiple threads and there's a sleep condition
     * to create a window for race condition; however, if we add the word
     * "synchronized" into the method signature, the method will no longer
     * suffer from race condition.
     *
     * @param amount value to subtract from balance
     * @return the balance
     */
    public static int withdraw(int amount) {
        if (amount <= BALANCE) {
            // Introduce a delay to create a window for a race condition
            try {
                Thread.sleep(100); // sleep for 100 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return BALANCE;
            }
            BALANCE -= amount;
        }
        return BALANCE;
    }

    /**
     * This NewDecrementer class is used called by multiple thread to
     * call the withdraw method and pass in a random value
     */
    public static class NewDecrementer implements Runnable {
        private final int amount;
        private static final Random random = new Random();

        public NewDecrementer(int amount) {
            this.amount = amount;
        }

        @Override
        public void run() {
            try {
                // Sleep for a random time between 0 and 1000 milliseconds
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            int balance = withdraw(amount);
            System.out.println("Balance: " + balance + " amount: " + amount);
        }
    }
}