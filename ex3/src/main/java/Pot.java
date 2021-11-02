import java.util.concurrent.locks.ReentrantLock;

public class Pot {

    private final int capacity;
    private int servings;
    ReentrantLock lock = new ReentrantLock();

    public Pot(int capacity) {
        this.capacity = capacity;
        this.servings = capacity;
    }

    public void takeOne() {
        lock.lock();
        try {
            if (servings == 0)
                servings += capacity;
            servings--;
        } finally {
            lock.unlock();
        }
    }
}
