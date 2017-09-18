package GUI;

import Main.Window;
import org.newdawn.slick.Image;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class LoadButton extends Button {
    LoadButton(float posX, float posY, Image imgButton, Image imgButton2, Window window) {
        super(posX, posY, imgButton, imgButton2,window);
    }
    static JFileChooser fc = new JFileChooser();

    @Override
    void onClick() {
        FileChooser component = new FileChooser();
        int returnVal = fc.showOpenDialog(component);

        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File fileClass = fc.getSelectedFile();
            window.legacy.setPathToMap(fileClass.getAbsolutePath());
        }
    }
}
