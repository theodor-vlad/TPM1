import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Pot {

    private final int capacity;
    private int servings;
    ReentrantLock lock = new ReentrantLock();
    Condition empty = lock.newCondition();
    Condition can_eat = lock.newCondition();

    public Pot(int capacity) {
        this.capacity = capacity;
        this.servings = capacity;
    }

    public void takeOne() {
        lock.lock();
        try {
            while (servings == 0) {
                empty.signal();
                can_eat.await();
            }
            servings--;
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
            if (servings > 0) {
                result = empty.await(5, TimeUnit.SECONDS);
                if (!result) {
                    System.out.println("All eaters have dined.");
                }
            }

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
}
