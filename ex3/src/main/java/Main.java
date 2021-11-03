import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int no_of_servings = 5;
        int no_of_eaters = 8;
        Pot pot = new Pot(no_of_servings);
        Cook cook = new Cook(pot);
        Thread cook_thd = new Thread(cook);
        List<Thread> eaters = new ArrayList<>();

//        // punctul A
//        for (int i = 0; i < no_of_eaters; i++) {
//            eaters.add(new Thread(new Eater(pot, i + 1)));
//        }
//
//        cook_thd.start();
//
//        for (var e : eaters) {
//            e.start();
//        }
//
//        for (var e : eaters) {
//            e.join();
//        }
//
//        cook.kill();

        // punctul B
        for (int i = 0; i < no_of_eaters; i++) {
            eaters.add(new Thread(new InfiniteEater(pot, i + 1)));
        }

        cook_thd.start();

        for (var e : eaters) {
            e.start();
        }
    }
}
