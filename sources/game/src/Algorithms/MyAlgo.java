import Algorithms.Algorithm;
import Game.Bike;

public class MyAlgo extends Algorithm {

    @Override
    public void doSomething(Bike bike) {
        long time = System.currentTimeMillis() / 1000;
        if (time == 0) {
            bike.accelerate(true);
            bike.rotate(true);
        }
        if (time == 5)
            bike.rotate(true);
        if (time == 7) {
            bike.rotate(true);
            bike.accelerate(false);
        }
        if(time >= 8 && bike.getVelocity() > 0){
            bike.accelerate(false);
        }

    }
}
