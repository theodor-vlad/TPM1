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
                servings = capacity;
            servings--;
            if (servings < 0)
                throw new Exception("Bad");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
