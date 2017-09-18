import Algorithms.Algorithm;
import Game.Bike;
import org.newdawn.slick.geom.Point;

public class ArtyomAnalDestroyer extends Algorithm {

    @Override
    public void doSomething(Bike bike) {
        Point bikePos = bike.getPosition();
        int divide = 35;
        switch (bike.getDirection()) {
            case 0:
                if(!bike.checkPos(new Point(bikePos.getX(), bikePos.getY() - divide)))
                    if(bike.checkRotation(true))
                        bike.rotate(true);
                    else
                        bike.rotate(false);
                break;
            case 90:
                if(!bike.checkPos(new Point(bikePos.getX() + divide, bikePos.getY())))
                    if(bike.checkRotation(true))
                        bike.rotate(true);
                    else
                        bike.rotate(false);
                break;
            case 180:
                if(!bike.checkPos(new Point(bikePos.getX(), bikePos.getY() + divide)))
                    if(bike.checkRotation(true))
                        bike.rotate(true);
                    else
                        bike.rotate(false);
                break;
            case 270:
                if(!bike.checkPos(new Point(bikePos.getX() - divide, bikePos.getY())))
                    if(bike.checkRotation(true))
                        bike.rotate(true);
                    else
                        bike.rotate(false);
                break;
        }
        bike.accelerate(true);
    }
}
