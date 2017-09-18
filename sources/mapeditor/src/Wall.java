import org.newdawn.slick.*;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;

import java.util.ArrayList;

/**
 * Created by Kyunney on 24.07.2017.
 */
public class Wall {
    Point begin, end;
    Color color;

    Wall(Point begin, Point end, Color color) {
        this.begin = begin;
        this.end = end;
        this.color = color;
    }


    public void draw(Graphics graphics, Camera camera) {
        float beginX, beginY, endX, endY;
        graphics.setLineWidth(7);
        graphics.setColor(color);
        beginX = this.begin.getX()  - camera.cor.getX();
        beginY = this.begin.getY()  - camera.cor.getY();
        endX = this.end.getX() - camera.cor.getX();
        endY = this.end.getY()  - camera.cor.getY();
        Line line = new Line(beginX, beginY, endX, endY);
        graphics.draw(line);
    }
}
