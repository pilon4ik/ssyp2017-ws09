package GUI;

import Algorithms.Algorithm;
import Main.AlgInfo;
import Main.Serialize;
import Main.Window;
import org.newdawn.slick.Image;

import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Objects;

/**
 * Button has added new Algo.Algorithm in program
 */
public class AddAlgButton extends Button {
    static JFileChooser fc = new JFileChooser();

    AddAlgButton(float posX, float posY, Image imgButton, Image imgButton2, Window window) {
        super(posX, posY, imgButton, imgButton2, window);
    }

    @Override
    void onClick() {

        FileChooser component = new FileChooser();
        int returnVal = fc.showOpenDialog(component);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fileClass = fc.getSelectedFile();
            String nameClass = fileClass.getName();
            int nameClassLength = nameClass.length();
            String lol = "";
            for (int i = nameClassLength - 1; i > -1; i--) {
                String symbol = String.copyValueOf(new char[]{nameClass.charAt(i)});
                if (!Objects.equals(symbol, "."))
                    lol = symbol + lol;
                else
                    break;
            }
            if (Objects.equals(lol, "class")) {
                nameClass = nameClass.substring(0, nameClassLength - 6);
                String pathClass = fileClass.getPath().substring(0, fileClass.getPath().length() - nameClassLength);

                try {
                    URL url = fileClass.toURI().toURL();
                    URL[] urls = new URL[]{url};
                    ClassLoader folderClassLoader = new URLClassLoader(urls);
                    Class<Algorithm> cls = (Class<Algorithm>) folderClassLoader.loadClass(nameClass);
                } catch (MalformedURLException | NoClassDefFoundError | ClassNotFoundException e) {
                    window.legacy.initAllAlgInfos();
                    return;
                }

                Serialize.fillAllAlgoFile(new AlgInfo(window.legacy.getCheckBoxes().size(), pathClass + nameClass, 0, 0));
                window.legacy.initAllAlgInfos();
            }
        }
    }
}
