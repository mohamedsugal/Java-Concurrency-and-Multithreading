import java.util.concurrent.Semaphore;

class Foo {
    private final Semaphore[] semaphores;

    public Foo() {
        semaphores = new Semaphore[2]; // Create an array for two semaphores
        semaphores[0] = new Semaphore(0); // Semaphore for controlling the second action
        semaphores[1] = new Semaphore(0); // Semaphore for controlling the third action
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        semaphores[0].release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        semaphores[0].acquire();
        printSecond.run();
        semaphores[1].release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        semaphores[1].acquire();
        printThird.run();
    }
}
