package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

/**
 * Created by miha7 on 28-Jul-17.
 */
public class Minimap {
    Map map;
    ArrayList<Bike> bikes;
    float size = 200;

    public Minimap(Map map, ArrayList<Bike> bikes) {
        this.map = map;
        this.bikes = bikes;
    }

    //TODO: Как же я люблю захардкоденные константы
    public void render(GameContainer gc, Graphics g) {
        g.setColor(Color.white);
        g.setLineWidth(6);
        g.drawRect(gc.getWidth() - size - 13, gc.getHeight() - size - 13, size + 3, size + 3);
        g.setColor(Color.black);
        g.fillRect(gc.getWidth() - size - 10, gc.getHeight() - size - 10, size - 3, size - 3);
        g.setLineWidth(3);
        float scale = size / (Camera.mapSize * 2f);
        float movementX = gc.getWidth() - size / 2 - 10, movementY = gc.getHeight() - size / 2 - 10;
        for (Bike bike : bikes) {
            if (!bike.isDead()) {
                g.setColor(bike.getColor());
                float x = (bike.getPosition().getX() * scale + movementX),
                        y = (bike.getPosition().getY() * scale + movementY);
                g.drawLine(x - bike.getVector().x * Bike.height / 2 * scale,
                        y - bike.getVector().y * Bike.height / 2 * scale,
                        x + bike.getVector().x * Bike.height / 2 * scale,
                        y + bike.getVector().y * Bike.height / 2 * scale);
            }
        }
        g.setLineWidth(1);
        for (Wall wall : map.walls) {
            g.setColor(wall.color);
            float x1 = wall.wallSpine.getX1() * scale + movementX, y1 = wall.wallSpine.getY1() * scale + movementY,
                    x2 = wall.wallSpine.getX2() * scale + movementX, y2 = wall.wallSpine.getY2() * scale + movementY;
            g.drawLine(x1, y1, x2, y2);
        }
        g.setColor(Color.white);
        g.drawRect(Camera.pos.getX() * scale + movementX - 1, Camera.pos.getY() * scale + movementY - 1,
                (gc.getWidth() * scale / Camera.scale) + 2, gc.getHeight() * scale / Camera.scale + 2);
    }
}
