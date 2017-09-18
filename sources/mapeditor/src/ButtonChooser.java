import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Kyunney on 26.07.2017.
 */
public class ButtonChooser {
    ArrayList<Button> buttons;
    float spaceBtwButtons = 60;
    float borderIndent = 30;
    float buttonSize = 40;
    boolean ifButtonChoosed = false;
    int i;
    int pressedButtonIndex;

    ButtonChooser(ArrayList<Button> buttons) {
        this.buttons = buttons;
    }

    public void init(GameContainer gc) {
        buttons.add(new Button(Color.green, new Point(gc.getWidth() - 5 * spaceBtwButtons, borderIndent), buttonSize, ButtonStatus.Clicked));
        buttons.add(new Button(Color.red, new Point(gc.getWidth() - 4 * spaceBtwButtons, borderIndent), buttonSize, ButtonStatus.Nothing));
        buttons.add(new Button(Color.blue, new Point(gc.getWidth() - 3 * spaceBtwButtons, borderIndent), buttonSize, ButtonStatus.Nothing));
        buttons.add(new Button(Color.yellow, new Point(gc.getWidth() - 2 * spaceBtwButtons, borderIndent), buttonSize, ButtonStatus.Nothing));
    }

    public void choose(GameContainer gc, ArrayList<Button> buttons, boolean ifMousePressed, Eraser eraser, Spawn spawn) {
        Point click = new Point(gc.getInput().getMouseX(), gc.getInput().getMouseY());
        Rectangle rectangle;
        boolean ifAnyButtonIsOnClick = false;
        int onClickIndex = 0;
        for (i = 0; i < buttons.size(); i++) {
            rectangle = new Rectangle(buttons.get(i).cor.getX(), buttons.get(i).cor.getY(), buttons.get(i).size, buttons.get(i).size);
            if (rectangle.contains(click)) {
                ifAnyButtonIsOnClick = true;
                onClickIndex = i;
            }
        }
        for(i = 0; i < buttons.size(); i++){
            if(buttons.get(i).status == ButtonStatus.Clicked){
                pressedButtonIndex = i;
            }
        }
        if (ifAnyButtonIsOnClick) {
            if(ifMousePressed) {
                ifButtonChoosed = true;
                if (pressedButtonIndex == buttons.size()) {
                    buttons.get(onClickIndex).status = ButtonStatus.Clicked;
                    eraser.status = ButtonStatus.Nothing;
                    spawn.status = ButtonStatus.Nothing;
                }
                else{
                    buttons.get(pressedButtonIndex).status = ButtonStatus.Nothing;
                    buttons.get(onClickIndex).status = ButtonStatus.Clicked;
                    eraser.status = ButtonStatus.Nothing;
                    spawn.status = ButtonStatus.Nothing;
                }
            }
            else{
                ifButtonChoosed = false;
                if(onClickIndex!=pressedButtonIndex) {
                    buttons.get(onClickIndex).status = ButtonStatus.Howel;
                }
            }

        }
        else{
            ifButtonChoosed = false;
            for(i = 0; i< buttons.size(); i++){
                if(i!=pressedButtonIndex) {
                    buttons.get(i).status = ButtonStatus.Nothing;
                }
            }
        }
    }

    public boolean ifButtonChoosed(){
        return ifButtonChoosed;
    }

}

