package GUI;

import Main.Window;
import org.newdawn.slick.Image;

public class TimeUp  extends Button{
    TimeUp(float posX, float posY, Image imgButton, Image imgButton2, Window window) {
        super(posX, posY, imgButton, imgButton2, window);
    }

    @Override
    void onClick() {
        int time = window.legacy.getRoundTime();
        if(time < 600)
            time += 10;
        window.legacy.setRoundTime(time);
    }
}
