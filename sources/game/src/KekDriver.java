import Algorithms.Algorithm;
import Game.Bike;
import Game.Map;
import org.newdawn.slick.BigImage;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

import java.util.Random;

public class KekDriver extends Algorithm {

    Random r = new Random();

    @Override
    public void doSomething(Bike bike) {
        if(bike.getVelocity() < 20)
            bike.accelerate(true);
        float futX, futY, curX, curY;
        futX = bike.getVector().getX() + bike.getPosition().getX();
        futY = bike.getVector().getY() + bike.getPosition().getY();
        curX = bike.getPosition().getX();
        curY = bike.getPosition().getY();
        bike.accelerate(true);
        if (!bike.checkPos(new Point(futX, futY), bike.getDirection())) {
            if (bike.checkRotation(true))
                bike.rotate(true);
            else
                bike.rotate(false);
        } else if(r.nextInt(15000) == 1) {
            Bike copy = bike.getCopy();
            boolean dir = false;
            if (r.nextInt(2) == 1) {
                dir = true;
            }
            copy.rotate(dir);
            Point p = copy.getPosition();
            Point p2 = copy.getPosition();
            Vector2f l = copy.getVector().scale(copy.getVelocity());
            p2.setX(l.getX() + p.getX());
            p2.setX(l.getY() + p.getY());
            if (copy.checkPos(p,copy.getDirection()) && copy.checkPos(p2,copy.getDirection())) {
                bike.rotate(dir);
            } else {
                copy = bike.getCopy();
                copy.rotate(!dir);
                p = copy.getPosition();
                p2 = copy.getPosition();
                l = copy.getVector().scale(copy.getVelocity());
                p2.setX(l.getX() + p.getX());
                p2.setX(l.getY() + p.getY());
                if (copy.checkPos(p,copy.getDirection()) && copy.checkPos(p2,copy.getDirection())) {
                    bike.rotate(!dir);
                } else {
                    bike.accelerate(false);
                }
            }
        }

    }
}
