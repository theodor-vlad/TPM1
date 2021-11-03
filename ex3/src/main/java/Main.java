import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int no_of_servings = 5;
        int no_of_eaters = 8;

        // punctul A
        Pot pot = new Pot(no_of_servings);
        List<Thread> eaters = new ArrayList<>();
        for (int i = 0; i < no_of_eaters; i++) {
            eaters.add(new Thread(new Eater(pot, i + 1)));
        }

        for (var e : eaters) {
            e.start();
        }

        for (var e : eaters) {
            e.join();
        }

        System.out.println("All eaters have eaten.");

//        // punctul B
//        Pot2 pot = new Pot2(no_of_servings, no_of_eaters);
//        List<Thread> eaters = new ArrayList<>();
//        for (int i = 0; i < no_of_eaters; i++) {
//            eaters.add(new Thread(new Eater2(pot, i)));
//        }
//
//        for (var e : eaters) {
//            e.start();
//        }
    }
}
