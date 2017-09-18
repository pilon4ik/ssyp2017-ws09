package GUI;

import Main.AlgInfo;
import Main.Serialize;
import Main.Window;
import org.newdawn.slick.Image;

import java.util.ArrayList;
import java.util.Comparator;

public class DelButton extends Button {


    DelButton(float posX, float posY, Image imgButton, Image imgButton2, Window window) {
        super(posX, posY, imgButton, imgButton2, window);
    }

    @Override
    void onClick() {

        ArrayList<AlgInfo> isParticipate = window.legacy.getIsParticipate();
        ArrayList<AlgInfo> algoInfos = Serialize.readFile("allAlgorithms.txt");
        ArrayList<AlgInfo> alive = new ArrayList<>();
        try {
            for (AlgInfo item : isParticipate)
                algoInfos.set(item.getId(), new AlgInfo(-1, "", 0, 0));
        } catch (IndexOutOfBoundsException e) {}

        for (AlgInfo item : algoInfos)
            if (item.getId() != -1)
                alive.add(item);

        Serialize.fillAlgoInNewGameFile(alive, "allAlgorithms.txt");

        window.legacy.initAllAlgInfos();
    }
}
