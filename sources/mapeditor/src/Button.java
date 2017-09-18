import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import java.io.IOException;

public class Button {
    Color color;
    Point cor;
    float size;
    ButtonStatus status;

    Button(Color color, Point cor, float size, ButtonStatus status) {
        this.color = color;
        this.cor = cor;
        this.size = size;
        this.status = status;
    }

    public void draw(GameContainer gc, Graphics graphics) throws IOException, SlickException {
        graphics.setColor(this.color);
        graphics.fillRect(this.cor.getX(), this.cor.getY(), this.size, this.size);
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
}
