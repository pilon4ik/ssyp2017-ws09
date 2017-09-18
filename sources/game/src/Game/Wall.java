package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.*;

import java.util.ArrayList;

public class Wall {
    public Color color;
    public Line wallSpine;
    public static final int thickness = 7;
    public Bike owner;

    public Wall(Line wallSpine, Color color, Bike owner) {
        this.wallSpine = wallSpine;
        this.color = color;
        this.owner = owner;
    }

    public boolean collides(Bike bike) {
        ArrayList<Line> bikeHitBox = bike.getBikeHitBox();
        ArrayList<Line> wallHitBox = new ArrayList<>();
        float x1 = wallSpine.getX1(), x2 = wallSpine.getX2(), y1 = wallSpine.getY1(), y2 = wallSpine.getY2();
        float t = thickness / 2;
        if (x1 == x2) {
            wallHitBox.add(new Line(x1 - t, y1 - t, x1 + t, y1 - t));
            wallHitBox.add(new Line(x1 - t, y1 - t, x1 - t, y2 + t));
            wallHitBox.add(new Line(x2 - t, y2 + t, x2 + t, y2 + t));
            wallHitBox.add(new Line(x1 + t, y1 - t, x2 + t, y2 + t));
        } else {
            wallHitBox.add(new Line(x1 - t, y1 - t, x1 - t, y1 + t));
            wallHitBox.add(new Line(x1 - t, y1 - t, x2 + t, y2 - t));
            wallHitBox.add(new Line(x2 + t, y2 - t, x2 + t, y2 + t));
            wallHitBox.add(new Line(x1 - t, y1 + t, x2 + t, y2 + t));
        }
        for (Line wallHitBoxEdge : wallHitBox) {
            for (Line bikeHitBoxEdge : bikeHitBox) {
                if (crosses(wallHitBoxEdge, bikeHitBoxEdge)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean crosses(Line l1, Line l2) {
        float l1X1 = l1.getX1(), l1X2 = l1.getX2(), l1Y1 = l1.getY1(), l1Y2 = l1.getY2();
        float l2X1 = l2.getX1(), l2X2 = l2.getX2(), l2Y1 = l2.getY1(), l2Y2 = l2.getY2();
        if (l1X1 == l1X2 || l1Y1 == l1Y2) { //l1 : -- or |
            if (l1X1 == l1X2) { //l1 : --
                if (l2X1 == l2X2) { //l1 & l2: --
                    return (l1X1 == l2X1) && ((l1Y1 <= l2.getMaxY() && l1Y1 >= l2.getMinY())
                            || (l1Y2 <= l2.getMaxY() && l1Y2 >= l2.getMinY()));
                } else { //l1 : --, l2 : |
                    return l1X1 <= l2.getMaxX() && l1X1 >= l2.getMinX() && l2Y1 <= l1.getMaxY() && l2Y1 >= l1.getMinY();
                }
            } else { //l1 : |
                if (l2Y1 == l2Y2) { //l1 & l2 : |
                    return (l1Y1 == l2Y1) && ((l1X1 <= l2.getMaxX() && l1X1 >= l2.getMinX())
                            || (l1X2 <= l2.getMaxX() && l1X2 >= l2.getMinX()));
                } else { //l1 : |, l2 : --
                    return l1Y1 <= l2.getMaxY() && l1Y1 >= l2.getMinY() && l2X1 <= l1.getMaxX() && l2X1 >= l1.getMinX();
                }
            }
        } else { //l1 : /
            float k1 = (l1Y1 - l1Y2) / (l1X1 - l1X2);
            float b1 = k1 * -1 * l1X1 + l1Y1;
            if (l2X1 == l2X2) { //l2 : --
                float y = l2X1 * k1 + b1;
                return y <= l1.getMaxY() && y >= l1.getMinY() && y <= l2.getMaxY() && y >= l2.getMinY();
            } else { //l2 : |
                float x = (l2Y2 - b1) / k1;
                return x <= l1.getMaxX() && x >= l1.getMinX() && x <= l2.getMaxX() && x >= l2.getMinX();
            }
        }
    }

    public void draw(Graphics graphics) {
        graphics.setLineWidth(thickness * Camera.scale);
        graphics.setColor(color);
        graphics.drawLine((wallSpine.getX1() - Camera.pos.getX()) * Camera.scale,
                (wallSpine.getY1() - Camera.pos.getY()) * Camera.scale,
                (wallSpine.getX2() - Camera.pos.getX()) * Camera.scale,
                (wallSpine.getY2() - Camera.pos.getY()) * Camera.scale);
    }

}
