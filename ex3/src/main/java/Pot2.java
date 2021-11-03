public class Pot2 {

    private final int capacity;
    private int servings;
    Bakery lock;

    public Pot2(int capacity, int eaters) {
        this.capacity = capacity;
        this.servings = capacity;
        lock = new Bakery(eaters);
    }

    public void takeOne(int i) {
        lock.lock(i);
        try {
            if (servings == 0)
                servings = capacity;
            servings--;
            if (servings < 0)
                throw new Exception("Bad");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock(i);
        }
    }
}
