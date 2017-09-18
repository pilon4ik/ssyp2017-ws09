import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;

/**
 * Created by Kyunney on 24.07.2017.
 */
public class WallBuilder {
    ArrayList<Wall> walls;
    Point begin;
    boolean ifBuildBeginned = false;
    int i, lastElementIndex;
    int index;

    WallBuilder(ArrayList<Wall> walls) {
        this.walls = walls;
    }

    public void build(GameContainer gc, ArrayList<Button> buttons, Camera camera, Boolean ifMousePressed) {
        Input input = gc.getInput();
        for (i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).status == ButtonStatus.Clicked) {
                break;
            }
        }
        float x, y;
        if (ifMousePressed) {
            if (!ifBuildBeginned) {

                x = input.getMouseX() + camera.cor.getX();
                y = (input.getMouseY() + camera.cor.getY());
                begin = new Point(x, y);
                ifBuildBeginned = true;
                if (input.isKeyDown(Input.KEY_LCONTROL)) {
                    if (this.walls.size() != 0) {
                        x = setup(walls, new Point(x, y)).getX();
                        y = setup(walls, new Point(x, y)).getY();
                    }
                }
                walls.add(new Wall(new Point(x, y), new Point(x, y), buttons.get(i).color));
                lastElementIndex = walls.size() - 1;
            } else {
                ifBuildBeginned = false;
                x = input.getMouseX() + camera.cor.getX();
                y = input.getMouseY() + camera.cor.getY();
                if (input.isKeyDown(Input.KEY_LCONTROL)) {
                    if (this.walls.size() != 0) {
                        x = punchline(walls, new Point(x, y)).getX();
                        y = punchline(walls, new Point(x, y)).getY();
                    }
                }
                walls.get(lastElementIndex).end.setX(x);
                walls.get(lastElementIndex).end.setY(y);
                if (input.isKeyDown(Input.KEY_LSHIFT)) {
                    straighten(walls.get(walls.size() - 1));
                }
            }
        }
    }

    public void preAdd(GameContainer gc, Camera camera) {
        if (ifBuildBeginned) {
            float x, y;
            Input input = gc.getInput();
            x = input.getMouseX() + camera.cor.getX();
            y = input.getMouseY() + camera.cor.getY();
            walls.get(lastElementIndex).end.setX(x);
            walls.get(lastElementIndex).end.setY(y);
            if (input.isKeyDown(Input.KEY_LSHIFT)) {
                straighten(walls.get(lastElementIndex));
            }
        }
    }

    private void straighten(Wall wall) {
        float x, y;
        x = Math.abs(wall.end.getX() - wall.begin.getX());
        y = Math.abs(wall.begin.getY() - wall.end.getY());
        if (x >= y) {
            wall.end.setY(wall.begin.getY());
        } else wall.end.setX(wall.begin.getX());
    }

    public void addBorders(float borderCor) {

        walls.add(new Wall(new Point(-borderCor, -borderCor), new Point(borderCor, -borderCor), Color.white));
        index = walls.size() - 1;
        walls.add(new Wall(new Point(borderCor, -borderCor), new Point(borderCor, borderCor), Color.white));
        walls.add(new Wall(new Point(borderCor, borderCor), new Point(-borderCor, borderCor), Color.white));
        walls.add(new Wall(new Point(-borderCor, borderCor), new Point(-borderCor, -borderCor), Color.white));
    }

    public boolean ifBuildBeginned() {
        return ifBuildBeginned;
    }

    private Point setup(ArrayList<Wall> walls, Point newPoint) {
        float minDistance = distance(walls.get(0).begin, newPoint);
        Point closestPoint = walls.get(0).begin;
        for (Wall wall : walls) {
            if (distance(wall.begin, newPoint) < minDistance) {
                minDistance = distance(wall.begin, newPoint);
                closestPoint = wall.begin;
            }
            if (distance(wall.end, newPoint) < minDistance) {
                minDistance = distance(wall.end, newPoint);
                closestPoint = wall.end;
            }
        }
        return closestPoint;
    }

    private Point punchline(ArrayList<Wall> walls, Point newPoint) {
        float minDistance = distance(walls.get(0).begin, newPoint);
        Point closestPoint = walls.get(0).begin;
        for (int i = 0; i < walls.size() - 1; i++) {
            if (distance(walls.get(i).begin, newPoint) < minDistance) {
                minDistance = distance(walls.get(i).begin, newPoint);
                closestPoint = walls.get(i).begin;
            }
            if (distance(walls.get(i).end, newPoint) < minDistance) {
                minDistance = distance(walls.get(i).end, newPoint);
                closestPoint = walls.get(i).end;
            }
        }
        return closestPoint;
    }

    private float distance(Point p1, Point p2) {
        Vector2f vect = new Vector2f((p1.getX() - p2.getX()), (p1.getY() - p2.getY()));
        float dist = vect.length();
        return dist;
    }

}
