import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

/**
 * Created by Kyunney on 30.07.2017.
 */
public class Spawn extends Button {
    Point cor;
    ButtonStatus status;
    float bikeWidth = 12;

    Spawn(Point cor, Float size, ButtonStatus status) {
        super(Color.lightGray, cor, size, status);
        this.cor = cor;
        this.size = size;
        this.status = status;
    }

    public void draw(GameContainer gc, Graphics graphics) throws SlickException {
        graphics.drawImage(new Image("res/spawn.png"), this.cor.getX(), this.cor.getY());
        if ((this.status == ButtonStatus.Howel)||(this.status == ButtonStatus.Clicked)) {
            graphics.drawImage(new Image("res/spawn2.png"), this.cor.getX(), this.cor.getY());
        }
    }

    public void choose(GameContainer gc, ArrayList<Button> buttons, ArrayList<Point> spawnPoints, Eraser eraser, boolean ifMousePressed) {
        Point click = new Point(gc.getInput().getMouseX(), gc.getInput().getMouseY());
        Rectangle rectangle = new Rectangle(this.cor.getX(), this.cor.getY(), this.size, this.size/2);
        if (rectangle.contains(new Point(click.getX(), click.getY()))) {
            if (ifMousePressed) {
                if (this.status != ButtonStatus.Clicked) {
                    if (spawnPoints.size() <= 4) {
                        this.status = ButtonStatus.Clicked;
                        for (Button button : buttons) {
                            button.status = ButtonStatus.Nothing;
                        }
                        eraser.status = ButtonStatus.Nothing;
                    } else {
                        this.status = ButtonStatus.Nothing;
                    }
                }
            } else {
                if (this.status != ButtonStatus.Clicked) {
                    this.status = ButtonStatus.Howel;
                }
            }
        } else {
            if (this.status != ButtonStatus.Clicked) {
                this.status = ButtonStatus.Nothing;
            }
        }
    }

    public void spawn(GameContainer gc, ArrayList<Point> spawnPoints, Camera camera, boolean ifMousePressed) {
        Point click = new Point(gc.getInput().getMouseX(), gc.getInput().getMouseY());
        if (this.status == ButtonStatus.Clicked) {
            if (ifMousePressed) {
                if (spawnPoints.size() < 4) {
                    spawnPoints.add(new Point(click.getX() + camera.cor.getX(), click.getY() + camera.cor.getY()));
                }
            }
        }
    }

    public void draw(Graphics graphics, Camera camera, ArrayList<Point> spawnPoints) {
        for (Point spawnPoint : spawnPoints) {
            graphics.setColor(Color.white);
            graphics.setLineWidth(2);
            graphics.drawRect(spawnPoint.getX() - (bikeWidth / 2) - camera.cor.getX(), spawnPoint.getY() - (bikeWidth / 2) - camera.cor.getY(), bikeWidth, bikeWidth);
        }
    }
}
