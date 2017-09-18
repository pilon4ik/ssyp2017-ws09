import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;

import java.util.ArrayList;

/**
 * Created by Kyunney on 28.07.2017.
 */
public class MiniMap {
    ArrayList<Wall> walls;
    float size = 200;
    float borderCor = 3000;
    Point cor;
    float bikeWidth = 12;
    ArrayList<Point> spawnPoints;

    MiniMap(GameContainer gc, ArrayList<Wall> walls, ArrayList<Point> spawnPoints) {
        this.walls = walls;
        this.cor = new Point(gc.getWidth() - size - 20, gc.getHeight() - size - 20);
        this.spawnPoints = spawnPoints;
    }

    public void draw(GameContainer gc, Graphics graphics, Camera camera) {
        float scale = size / (borderCor * 2);
        float beginX, beginY, endX, endY, camX, camY;
        graphics.setColor(Color.black);
        graphics.fillRect(this.cor.getX(), this.cor.getY(), size, size);
        graphics.setColor(Color.gray);
        graphics.setLineWidth(2);
        camX = this.cor.getX() + (camera.cor.getX() + borderCor) * scale;
        camY = this.cor.getY() + (camera.cor.getY() + borderCor) * scale;
        graphics.drawRect(camX, camY, gc.getWidth() * scale , gc.getHeight() * scale );
        for (Wall wall : walls) {
            graphics.setLineWidth(7 * scale);
            graphics.setColor(wall.color);
            beginX = this.cor.getX() + (wall.begin.getX() + borderCor) * scale;
            beginY = this.cor.getY() + (wall.begin.getY() + borderCor) * scale;
            endX = this.cor.getX() + (wall.end.getX() + borderCor) * scale;
            endY = this.cor.getY() + (wall.end.getY() + borderCor) * scale;
            Line line = new Line(beginX, beginY, endX, endY);
            graphics.draw(line);
        }
        for (int i = 0; i < spawnPoints.size() ; i++) {
            graphics.setLineWidth(2);
            graphics.setColor(Color.white);
            beginX = this.cor.getX() + (spawnPoints.get(i).getX() - bikeWidth / 2 + borderCor) * scale;
            beginY = this.cor.getY() + (spawnPoints.get(i).getY() - bikeWidth / 2 + borderCor) * scale;
            graphics.drawRect(beginX, beginY, bikeWidth*scale, bikeWidth*scale);
        }

    }
}
