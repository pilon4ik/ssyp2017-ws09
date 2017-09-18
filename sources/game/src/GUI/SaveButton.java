package GUI;

import Main.*;
import org.newdawn.slick.Image;

import java.util.ArrayList;

public class SaveButton extends Button {
    SaveButton(float posX, float posY, Image imgButton, Image imgButton2, Window window) {
        super(posX, posY, imgButton, imgButton2,window);
    }

    @Override
    void onClick() {
        ArrayList<AlgInfo> isParticipate = window.legacy.getIsParticipate();

        Serialize.fillAlgoInNewGameFile(isParticipate, "algorithmsInNewGame.txt");

        if((isParticipate.size() != 0) && (window.legacy.getPathToMap() != null)) {
            isParticipate.clear();
            window.setCondition(Condition.Menu);
        }
    }
}
