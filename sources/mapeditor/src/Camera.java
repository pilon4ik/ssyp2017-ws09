import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Kyunney on 26.07.2017.
 */
public class Camera {
    Point cor;
    Vector2f vel;
    int frame;
    float borderCor = 3000;

    Camera(Point cor) {
        this.cor = cor;
        this.vel = new Vector2f(1, 1);
        this.frame = 25;
    }

    public void moveCam(GameContainer gc) {
        if ((gc.getInput().getMouseX() < frame) || gc.getInput().isKeyDown(Input.KEY_A)) {
            if (this.cor.getX() < -borderCor - 100) {
                this.cor.setX(this.cor.getX() + this.vel.x);
            } else this.cor.setX(this.cor.getX() - this.vel.x);
        }
        if ((gc.getInput().getMouseX() > gc.getWidth() - frame) || gc.getInput().isKeyDown(Input.KEY_D)) {
            if (this.cor.getX() > borderCor + 100) {
                this.cor.setX(this.cor.getX() - this.vel.x);
            } else this.cor.setX(this.cor.getX() + this.vel.x);
        }
        if ((gc.getInput().getMouseY() < frame) || gc.getInput().isKeyDown(Input.KEY_W)) {
            if (this.cor.getY() < -borderCor - 100) {
                this.cor.setY(this.cor.getY() + this.vel.y);
            } else this.cor.setY(this.cor.getY() - this.vel.y);
        }
        if ((gc.getInput().getMouseY() > gc.getHeight() - frame) || gc.getInput().isKeyDown(Input.KEY_S)) {
            if (this.cor.getY() > borderCor + 100) {
                this.cor.setY(this.cor.getY() - this.vel.y);
            }
            this.cor.setY(this.cor.getY() + this.vel.y);
        }
    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.white);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("X: ");
        stringBuilder.append(this.cor.getX());
        stringBuilder.append(", Y: ");
        stringBuilder.append(this.cor.getY());
        graphics.drawString(stringBuilder.toString(), 90, 0);
    }

    public void toCentre(GameContainer gc) {
        if (gc.getInput().isKeyDown(Input.KEY_SPACE)) {
            this.cor.setX(0);
            this.cor.setY(0);
        }
    }
}
