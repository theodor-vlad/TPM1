public class Eater2 implements Runnable {

    private final int number;
    private final Pot2 pot;
    private int times_eaten = 0;

    public Eater2(Pot2 pot, int number) {
        this.pot = pot;
        this.number = number;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pot.takeOne(number);
            System.out.println("Eater " + this.number + " has eaten " + (++times_eaten) + " time(s).");
        }
    }
}
