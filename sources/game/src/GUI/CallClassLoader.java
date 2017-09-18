package GUI;

import Algorithms.*;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class CallClassLoader {
    static JFileChooser fc = new JFileChooser();

    public static void classLoader() {
        FileChooser component = new FileChooser();
        int returnVal = fc.showOpenDialog(component);
        String nameClass = null;
        String pathClass = null;

        File file;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            nameClass = file.getName();
            int nameClassLength = nameClass.length();
            nameClass = nameClass.substring(0, nameClassLength - 6);
            pathClass = file.getPath().substring(0, file.getPath().length() - nameClassLength);
        }
        file = new File(pathClass);

        try {
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};
            ClassLoader folderClassLoader = new URLClassLoader(urls);

            Class<Algorithm> cls = (Class<Algorithm>) folderClassLoader.loadClass(nameClass);
            Constructor<Algorithm> ctor = cls.getDeclaredConstructor();

            Algorithm p = ctor.newInstance();
//            p.doSomething();

        } catch (MalformedURLException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | NullPointerException | NoSuchMethodException e) {
            System.err.println("ADD CLASS, DEAR USER");
//            e.printStackTrace();
        }
    }
}
