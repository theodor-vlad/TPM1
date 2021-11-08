import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Pot {

    private final int capacity;
    private int servings;
    ReentrantLock lock = new ReentrantLock();
    Condition empty = lock.newCondition();
    Condition can_eat = lock.newCondition();
    volatile boolean[] has_eaten;

    public Pot(int capacity, int eaters) {
        this.capacity = capacity;
        this.servings = capacity;
        has_eaten = new boolean[eaters];
    }

    public void takeOne(int index) {
        lock.lock();
        try {
            if (servings == 0) {
                empty.signal();
                can_eat.await();
            }
            servings--;
            has_eaten[index] = true;
            if (servings < 0)
                throw new Exception("Can't eat, pot is empty.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void refill() {
        lock.lock();
        try {
            boolean result = true;
            if (servings > 0)
                result = empty.await(3, TimeUnit.SECONDS);

            if (result) {
                servings += capacity;
                can_eat.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public boolean allHaveEaten() {
        for (var b : has_eaten)
            if (!b)
                return false;
        return true;
    }
}
