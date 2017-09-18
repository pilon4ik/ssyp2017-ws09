package GUI;

import Main.Window;
import org.newdawn.slick.Image;

public class LegacyButton extends Button {


    LegacyButton(float posX, float posY, Image imgButton, Image imgButton2, Window window) {
        super(posX, posY, imgButton, imgButton2,window);
    }

    @Override
    void onClick() {

    }
}
