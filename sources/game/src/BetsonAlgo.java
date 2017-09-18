import Algorithms.Algorithm;
import Game.Bike;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

public class BetsonAlgo extends Algorithm {
    long time = System.currentTimeMillis() / 1000;
    Bike bClone;
    int sideL = 0, sideR = 0;
    float length = 0;
    boolean isRaiding = false;
    boolean forceToRotate = false;
    boolean keepCalm = false;

    @Override
    public void doSomething(Bike bike) {
        long time2 = System.currentTimeMillis() / 1000;
        float futX, futY, curX, curY;
        Vector2f rightV = bike.getVector().getPerpendicular();
        Vector2f leftV = bike.getVector().getPerpendicular().negate();
        futX = bike.getVector().getX() * 3 + bike.getPosition().getX();
        futY = bike.getVector().getY() * 3 + bike.getPosition().getY();
        curX = bike.getPosition().getX();
        curY = bike.getPosition().getY();
        bike.accelerate(true);
        if (bike.checkPos(new Point(rightV.getX() * sideR + bike.getPosition().getX(),
                rightV.getY() * sideR + bike.getPosition().getY()))) {
            sideR += 4;
        }
        if (bike.checkPos(new Point(leftV.getX() * sideL + bike.getPosition().getX(),
                leftV.getY() * sideL + bike.getPosition().getY()))) {
            sideL += 4;
        }
        if (!bike.checkPos(new Point(futX, futY), bike.getDirection())) {
            forceToRotate = true;
            if (bike.checkRotation(false) && bike.checkRotation(true)) {
                if (sideR < sideL) {
                    bike.rotate(false);
                } else {
                    bike.rotate(true);
                }
            } else if (bike.checkRotation(true))
                bike.rotate(true);
            else if (bike.checkRotation(false))
                bike.rotate(false);
            sideR = 0;
            sideL = 0;

        }
        if (bike.checkPos(new Point(futX, futY), bike.getDirection())) {
            forceToRotate = false;
        }
        for (int i = 0; i < bike.getBikes().size(); i++) {
            Bike bikeEnemy = bike.getBikes().get(i);
            if (bikeEnemy != bike && bikeEnemy.getVector() == bike.getVector().negate() &&
                    !bike.checkPos(new Point(bike.getVector().getX() * 2 + bike.getPosition().getX(),
                            bike.getVector().getY() * 2 + bike.getPosition().getY()), bike.getDirection())) {
                forceToRotate = true;
                if (bike.checkRotation(false) && bike.checkRotation(true)) {
                    if (sideR < sideL) {
                        bike.rotate(false);
                    } else {
                        bike.rotate(true);
                    }
                } else if (bike.checkRotation(true))
                    bike.rotate(true);
                else if (bike.checkRotation(false))
                    bike.rotate(false);
                sideR = 0;
                sideL = 0;
            }
            if (bikeEnemy != bike && isNear(bikeEnemy, curX, curY) && !forceToRotate && !keepCalm) {
                bike.accelerate(true);
                if (bike.getDirection() != bikeEnemy.getDirection() && isNearToEnd(bike, bikeEnemy) && !isRaiding &&
                        sideL > 125 && sideR > 125) {
                    if (bike.checkRotation(false) && bike.checkRotation(true)) {
                        if (sideR < sideL)
                            bike.rotate(false);
                        else
                            bike.rotate(true);
                    } else if (bike.checkRotation(true))
                        bike.rotate(true);
                    else if (bike.checkRotation(false))
                        bike.rotate(false);
                    sideR = 0;
                    sideL = 0;
                }
                if (length > 500 && bike.getDirection() == bikeEnemy.getDirection()) {
                    bike.rotate(whereToRotate(bike, bikeEnemy));
                    bike.accelerate(true);
                    isRaiding = true;
                }
                if (length < 300 && bike.getDirection() == bikeEnemy.getDirection()) {
                    keepCalm = true;
                }
            }
        }
    }

    boolean isNear(Bike bike, float xUs, float yUs) {
        boolean result;
        float xEn, yEn;
        xEn = bike.getPosition().getX();
        yEn = bike.getPosition().getY();
        if (xEn > xUs - 150 && xEn < xUs + 150 && yEn > yUs - 150 && yEn < yUs + 150) {
            keepCalm = false;
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    boolean isNearToEnd(Bike bikeOur, Bike bike2) {
        boolean result = false;
        switch (bikeOur.getDirection()) {
            case 0:
                if (bikeOur.getPosition().getY() < bike2.getPosition().getY())
                    result = true;
                length = Math.abs(bikeOur.getPosition().getY() - bike2.getPosition().getY());
                break;
            case 90:
                if (bikeOur.getPosition().getX() > bike2.getPosition().getX())
                    result = true;
                length = Math.abs(bikeOur.getPosition().getX() - bike2.getPosition().getX());
                break;
            case 180:
                if (bikeOur.getPosition().getY() > bike2.getPosition().getY())
                    result = true;
                length = Math.abs(bikeOur.getPosition().getY() - bike2.getPosition().getY());
                break;
            case 270:
                if (bikeOur.getPosition().getX() < bike2.getPosition().getX())
                    result = true;
                length = Math.abs(bikeOur.getPosition().getX() - bike2.getPosition().getX());
                break;
        }
        return result;
    }

    boolean whereToRotate(Bike us, Bike enemy) {
        boolean result = false;
        switch (us.getDirection()) {
            case 0:
                if (enemy.getPosition().getX() > us.getPosition().getX())
                    result = true;
                break;
            case 90:
                if (enemy.getPosition().getY() > us.getPosition().getY())
                    result = true;
                break;
            case 180:
                if (enemy.getPosition().getX() < us.getPosition().getX())
                    result = true;
                break;
            case 270:
                if (enemy.getPosition().getY() < us.getPosition().getY())
                    result = true;
                break;
        }
        return result;
    }
}
