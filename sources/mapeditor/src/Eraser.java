import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

/**
 * Created by Kyunney on 26.07.2017.
 */
public class Eraser extends Button {
    float bikeWidth = 12;

    Eraser(Point cor, float size, ButtonStatus status) {
        super(Color.lightGray, cor, size, status);
        this.cor = cor;
        this.size = size;
    }

    public void draw(GameContainer gc, Graphics graphics) {
        graphics.setColor(this.color);
        graphics.fillRect(this.cor.getX(), this.cor.getY(), this.size, this.size);
        graphics.setColor(Color.black);
        graphics.drawLine(this.cor.getX(), this.cor.getY(), this.cor.getX() + size, this.cor.getY() + size);
        graphics.drawLine(this.cor.getX() + size, this.cor.getY(), this.cor.getX(), this.cor.getY() + size);
        if (this.status == ButtonStatus.Howel) {
            graphics.setLineWidth(2);
            graphics.setColor(Color.pink);
            graphics.drawRect(cor.getX(), cor.getY(), size, size);
        }
        if (this.status == ButtonStatus.Clicked) {
            graphics.setLineWidth(2);
            graphics.setColor(Color.white);
            graphics.drawRect(cor.getX(), cor.getY(), size, size);
        }
    }


    public void choose(GameContainer gc, boolean ifMousePressed, ArrayList<Button> buttons, Spawn spawn) {
        Point click = new Point(gc.getInput().getMouseX(), gc.getInput().getMouseY());
        Rectangle rectangle = new Rectangle(this.cor.getX(), this.cor.getY(), this.size, this.size);
        if (rectangle.contains(new Point(click.getX(), click.getY()))) {
            if (ifMousePressed) {
                this.status = ButtonStatus.Clicked;
                for (Button button : buttons) {
                    button.status = ButtonStatus.Nothing;
                }
                spawn.status = ButtonStatus.Nothing;
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

    public void reset(GameContainer gc, ArrayList<Wall> walls, ArrayList<Point> spawnPoints, Camera camera, boolean ifMousePressed) {
        Input input = gc.getInput();
        float clickX, clickY;
        if (ifMousePressed) {
            if (this.status == ButtonStatus.Clicked) {
                clickX = input.getMouseX()  + camera.cor.getX();
                clickY = input.getMouseY()  + camera.cor.getY();
                for (int i = 0; i < walls.size(); i++) {
                    if ((clickX - 10 >= walls.get(i).begin.getX() && clickX + 10 <= walls.get(i).end.getX())) {
                        if ((clickY - 10 >= walls.get(i).begin.getY() && clickY + 10 <= walls.get(i).end.getY())) {
                            walls.remove(i);
                        } else {
                            if (clickY - 10 <= walls.get(i).begin.getY() && clickY + 10 >= walls.get(i).end.getY()) {
                                walls.remove(i);
                            }
                        }
                    } else {
                        if ((clickX - 10 <= walls.get(i).begin.getX() && clickX + 10 >= walls.get(i).end.getX())) {
                            if ((clickY - 10 >= walls.get(i).begin.getY() && clickY + 10 <= walls.get(i).end.getY())) {
                                walls.remove(i);
                            } else {
                                if (clickY - 10 <= walls.get(i).begin.getY() && clickY + 10 >= walls.get(i).end.getY()) {
                                    walls.remove(i);
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < spawnPoints.size(); i++) {
                    Rectangle rectangle = new Rectangle(spawnPoints.get(i).getX() - bikeWidth / 2, spawnPoints.get(i).getY() - bikeWidth / 2, bikeWidth, bikeWidth);
                    clickX = input.getMouseX() + camera.cor.getX();
                    clickY = input.getMouseY() + camera.cor.getY();
                    if (rectangle.contains(clickX, clickY)) {
                        spawnPoints.remove(i);
                    }
                }
            }
        }
    }
}


