import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

/**
 * Created by Kyunney on 28.07.2017.
 */
public class BorderDrawer extends Button {
    String borderName;
    float borderCor;


    BorderDrawer(Point cor, float size, float borderCor, String borderName, ButtonStatus status) {
        super(Color.white, cor, size, status);
        this.borderCor = borderCor;
        this.borderName = borderName;
    }



    public void draw(GameContainer gc, Graphics graphics) {
        graphics.setColor(this.color);
        graphics.fillRect(this.cor.getX(), this.cor.getY(), this.size, this.size);
        graphics.setColor(Color.black);
        graphics.drawString(this.borderName, this.cor.getX(), this.cor.getY());
        if(this.status == ButtonStatus.Howel){
            graphics.setLineWidth(2);
            graphics.setColor(Color.pink);
            graphics.drawRect(cor.getX(), cor.getY(), size, size);
        }
        if (this.status==ButtonStatus.Clicked) {
            graphics.setLineWidth(2);
            graphics.setColor(Color.blue);
            graphics.drawRect(cor.getX(), cor.getY(), size, size);
        }
    }

}
