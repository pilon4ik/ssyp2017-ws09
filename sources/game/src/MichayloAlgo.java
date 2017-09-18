import Algorithms.Algorithm;
import Game.Bike;

public class MichayloAlgo extends Algorithm {


    int timer = 0;
    boolean flag = false;

    private void rec() {
        rec();
    }

    @Override
    public void doSomething(Bike bike) {
        bike.accelerate(true);
        if (!flag) {
            bike.rotate(true);
            flag = true;
        }
    }
}
