import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static java.util.Collections.max;

public class Bakery implements Lock {
    List<Boolean> flag = new ArrayList<>();
    List<Integer> label = new ArrayList<>();

    public Bakery (int n) {
        for (int i = 0; i < n; i++) {
            flag.add(false);
            label.add(0);
        }
    }

    public void lock(int i) {
        flag.set(i, true);
        label.set(i, max(label) + 1);
        for (int j = 0; j < label.size(); j++) {
            while (j != i && flag.get(j) == true && (label.get(i) > label.get(j) || (label.get(i) == label.get(j) && i > j))) {
                // wait
            }
        }
    }

    public void unlock(int i) {
        flag.set(i, false);
    }

    public void lock() { }

    public void lockInterruptibly() throws InterruptedException { }

    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() { }

    public Condition newCondition() {
        return null;
    }
}