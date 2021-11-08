public class Timer implements Runnable {

    private Pot pot;

    public Timer(Pot pot) {
        this.pot = pot;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        while (!pot.allHaveEaten()) {}
        long stopTime = System.nanoTime();
        float elapsed_seconds = stopTime - startTime;
        elapsed_seconds /= 1_000_000_000.0;
        System.out.println("All eaters have dined once in " + elapsed_seconds + " seconds.");
    }
}
