public class Cook implements Runnable {

    private final Pot pot;
    private boolean running = true;

    public Cook(Pot pot) {
        this.pot = pot;
    }

    public void run() {
        while (running) {
            pot.refill();
        }
    }

    public void kill() {
        running = false;
    }
}
