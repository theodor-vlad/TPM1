public class InfiniteEater implements Runnable {

    private final int number;
    private final Pot pot;
    private int times_eaten = 0;

    public InfiniteEater(Pot pot, int number) {
        this.pot = pot;
        this.number = number;
    }

    @Override
    public void run() {
        while (true) {
            try {
                pot.takeOne(number, ++times_eaten);
                Thread.sleep(500); // wait for others to eat
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}