public class Eater implements Runnable {

    private final int number;
    private final Pot pot;

    public Eater(Pot pot, int number) {
        this.pot = pot;
        this.number = number;
    }

    @Override
    public void run() {
        try {
            pot.takeOne();
            System.out.println("Eater " + this.number + " has eaten.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
