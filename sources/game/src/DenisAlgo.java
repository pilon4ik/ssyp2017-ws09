import Game.Algorithm;
import Game.Bike;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;

import java.util.ArrayList;

public class DenisAlgo extends Algorithm {
    int timer = -1;
    int round = 0;

    @Override
    public void doSomething(Bike bike) {
        //speedUp(bike,10);
        // ArrayList<Bike> bikes = bike.getBikes();
        // checkout(bike);
        timer++;
        if (timer == 99 || round == 3) {
            bike.rotate(false);
            return;
        }
        if (timer == 100) {
            round += 1;
            timer = 0;
            return;
        }
        if (timer % 4 == 0) {
            bike.rotate(false);
            // checkout(bike);
            return;
        }
        if (timer % 4 == 1) {
            bike.rotate(false);
            //   checkout(bike);
            return;
        }

        if (timer % 4 == 2) {
            bike.rotate(true);
            //     checkout(bike);
            return;
        }
        if (timer % 4 == 3) {
            bike.rotate(true);
            //           checkout(bike);
            return;
        }
        return;
    }
  /*
    public void speedUp(Bike bike,float limit) {

        while (bike.getVelocity() != limit) {
         if (bike.getVelocity()<limit)bike.accelerate(true);
            if (bike.getVelocity()>limit) bike.accelerate(false);}}
*/
/*
    public void stop(Bike bike) {
        while (bike.getVelocity() != 0) {
            if (bike.getVelocity() > 0) bike.accelerate(false);
            else bike.accelerate(true);
        }
    }

    private void checkout(Bike bike) {
        if (bike.checkPos(new Point(bike.getPosition().getX() + bike.getVector().getX() * bike.getVelocity(),
                (float) bike.getPosition().getY() + bike.getVector().getY() * bike.getVelocity())) == false) {
            stop(bike);
            bike.rotate(true);
            if (bike.checkPos(new Point(bike.getPosition().getX() + bike.getVector().getX() * bike.getVelocity(),
                    (float) bike.getPosition().getY() + bike.getVector().getY() * bike.getVelocity())) == false) {
            bike.rotate(false);
            bike.rotate(false);
            }  else return;
        } else return;
    }

*/
}


