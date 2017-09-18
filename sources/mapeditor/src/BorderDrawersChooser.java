import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

/**
 * Created by Kyunney on 28.07.2017.
 */
public class BorderDrawersChooser {
    ArrayList<BorderDrawer> borderDrawers = new ArrayList<>();
    boolean ifButtonChoosed = false;
    float spaceBtwButtons = 60;
    float borderIndent = 30;
    float buttonSize = 40;


    BorderDrawersChooser(ArrayList<BorderDrawer> borderDrawers) {
        this.borderDrawers = borderDrawers;
    }

    public void init(GameContainer gc) {
        borderDrawers.add(new BorderDrawer(new Point(gc.getWidth() - 8 * spaceBtwButtons, borderIndent), buttonSize, 1000, "1k", ButtonStatus.Nothing));
        borderDrawers.add(new BorderDrawer(new Point(gc.getWidth() - 7 * spaceBtwButtons, borderIndent), buttonSize, 2000, "2k", ButtonStatus.Nothing));
        borderDrawers.add(new BorderDrawer(new Point(gc.getWidth() - 6 * spaceBtwButtons, borderIndent), buttonSize, 3000, "3k", ButtonStatus.Nothing));
    }


    public boolean choose(GameContainer gc, ArrayList<BorderDrawer> borderDrawers, boolean ifMousePressed) {
        Point click = new Point(gc.getInput().getMouseX(), gc.getInput().getMouseY());
        Rectangle rectangle;
        int i;
        for (i = 0; i< borderDrawers.size(); i++) {
            rectangle = new Rectangle(borderDrawers.get(i).cor.getX(), borderDrawers.get(i).cor.getY(), borderDrawers.get(i).size, borderDrawers.get(i).size);
            if (rectangle.contains(click)) {
                if (ifMousePressed) {
                    ifButtonChoosed = true;
                } else{
                    if(borderDrawers.get(i).status != ButtonStatus.Clicked) {
                        borderDrawers.get(i).status = ButtonStatus.Howel;
                        ifButtonChoosed = false;
                    }
                }
            }
            else{
                if(borderDrawers.get(i).status != ButtonStatus.Clicked) {
                    borderDrawers.get(i).status = ButtonStatus.Nothing;
                }
                ifButtonChoosed = false;
            }
            if(ifButtonChoosed){
                if(borderDrawers.get(i).status != ButtonStatus.Clicked) {
                    borderDrawers.get(i).status = ButtonStatus.Clicked;
                    break;
                }
                else borderDrawers.get(i).status = ButtonStatus.Nothing;
            }
        }
        return ifButtonChoosed;
    }
}
