import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kyunney on 27.07.2017.
 */
public class Loader extends Button {
    ArrayList<Wall> walls;
    Camera camera;
    ButtonStatus status;
    boolean ifLoaded;
    ArrayList<Point> spawnPoints;

    Loader(Point cor, float size, ArrayList<Wall> walls, ArrayList<Point> spawnPoints, Camera camera, ButtonStatus status) throws SlickException {
        super(Color.lightGray, cor, size, status);
        this.walls = walls;
        this.camera = camera;
        this.status = status;
        this.spawnPoints = spawnPoints;
    }

    public boolean ifLoaded() {
        return ifLoaded;
    }


    public void draw(GameContainer gc, Graphics graphics) throws IOException, SlickException {
        graphics.drawImage(new Image("res/load.png"), cor.getX(), cor.getY());
        if (this.status == ButtonStatus.Howel || this.status == ButtonStatus.Clicked) {
            graphics.drawImage(new Image("res/load2.png"), cor.getX(), cor.getY());
        }
    }


    public void choose(GameContainer gc, Boolean ifMousePressed, ArrayList<BorderDrawer> borderDrawers) throws IOException {
        Point click = new Point(gc.getInput().getMouseX(), gc.getInput().getMouseY());
        Rectangle rectangle = new Rectangle(this.cor.getX(), this.cor.getY(), this.size, this.size / 2);
        if (rectangle.contains(new Point(click.getX(), click.getY()))) {
            if (ifMousePressed) {
                this.status = ButtonStatus.Clicked;
                this.open(borderDrawers);
                ifLoaded = true;
            } else {
                this.status = ButtonStatus.Howel;
                ifLoaded = false;
            }
        } else {
            this.status = ButtonStatus.Nothing;
            ifLoaded = false;
        }
    }

    private void open(ArrayList<BorderDrawer> borderDrawers) throws IOException {
        int i = 0;
        if (this.status == ButtonStatus.Clicked) {
            JFileChooser fc = new JFileChooser();
            int size = walls.size();
            for (i = 0; i < size; i++) {
                this.walls.remove(0);
            }
            FileChooser component = new FileChooser();
            int returnVal = fc.showOpenDialog(component);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                BufferedReader input = new BufferedReader(new FileReader(fc.getSelectedFile()));
                ArrayList<String> strWalls = new ArrayList<String>();
                while (input.read() != -1) {
                    strWalls.add(input.readLine());
                }
                for (i = 0; i < strWalls.size(); i++) {
                    if (!(strWalls.get(i).equals("spawn"))) {
                        int begin;
                        begin = 0;
                        float beginX, beginY, endX, endY, red, green, blue;
                        beginX = nextFloat(strWalls.get(i), begin);
                        begin = nextValueEnd(strWalls.get(i), begin) + 1;
                        beginY = nextFloat(strWalls.get(i), begin);
                        begin = nextValueEnd(strWalls.get(i), begin) + 1;
                        endX = nextFloat(strWalls.get(i), begin);
                        begin = nextValueEnd(strWalls.get(i), begin) + 1;
                        endY = nextFloat(strWalls.get(i), begin);
                        begin = nextValueEnd(strWalls.get(i), begin) + 1;
                        red = nextFloat(strWalls.get(i), begin);
                        begin = nextValueEnd(strWalls.get(i), begin) + 1;
                        green = nextFloat(strWalls.get(i), begin);
                        begin = nextValueEnd(strWalls.get(i), begin) + 1;
                        blue = nextFloat(strWalls.get(i), begin);
                        Color color = new Color(red, green, blue);
                        this.walls.add(new Wall(new Point(beginX, beginY), new Point(endX, endY), color));
                    } else {
                        i++;
                        break;
                    }
                }
                for (int j = i; j < strWalls.size(); j++) {
                    int begin;
                    begin = 0;
                    float x, y;
                    x = nextFloat(strWalls.get(j), begin);
                    begin = nextValueEnd(strWalls.get(j), begin) + 1;
                    y = nextFloat(strWalls.get(j), begin);
                    this.spawnPoints.add(new Point(x, y));
                }
            }
            this.status = ButtonStatus.Nothing;
            for (BorderDrawer borderDrawer : borderDrawers) {
                borderDrawer.status = ButtonStatus.Nothing;
            }
        }
    }

    private int nextValueEnd(String string, int begin) {
        int end = begin;
        while ((!(string.substring(end, end + 1).equals(" "))) && end < (string.length() - 1)) {
            end++;
        }
        return end;
    }

    private float nextFloat(String string, int begin) {
        int end = nextValueEnd(string, begin);
        return Float.valueOf(string.substring(begin, end));
    }
}
