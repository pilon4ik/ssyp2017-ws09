package GUI;

import Algorithms.Algorithm;
import Main.AlgInfo;
import Main.Condition;
import Main.Serialize;
import Main.Window;
import org.newdawn.slick.*;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Legacy {
    private AddAlgButton addAlgButton;
    private DelButton delButton;
    private SaveButton saveBut;
    private LoadButton loadButton;
    private ScrollUp scrollUp;
    private ScrollDown scrollDown;
    private TimeUp timeUp;
    private TimeDown timeDown;

    private Image backGround;
    private Image textPlace;

    private Input input;
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private ArrayList<Image> scrollList = new ArrayList<>();

    private String pathToMap;
    public float generalHeight;
    private int roundTime;

    private ArrayList<AlgInfo> allAlgInfos = new ArrayList<>();
    private ArrayList<AlgInfo> isParticipate = new ArrayList<>();
    private Window window;


    public Legacy(Window window) {
        this.window = window;
    }

    public void init(GameContainer gameContainer) {
        ImageLoader.loadImage();
        addAlgButton = new AddAlgButton(890, 240, ImageLoader.getAddAlg(), ImageLoader.getAddAlg2(), window);
        delButton = new DelButton(890, 300, ImageLoader.getDel(), ImageLoader.getDel2(), window);
        saveBut = new SaveButton(980, 300, ImageLoader.getSave(), ImageLoader.getSave2(), window);
        loadButton = new LoadButton(980, 240, ImageLoader.getLoad(), ImageLoader.getLoad2(), window);
        scrollUp = new ScrollUp(300, 280, ImageLoader.getScrollUp(), ImageLoader.getScrollUp(), window);
        scrollDown = new ScrollDown(287, 330, ImageLoader.getScrollDown(), ImageLoader.getScrollDown(), window);
        timeUp = new TimeUp(100, 280, ImageLoader.getScrollUp(), ImageLoader.getScrollUp(), window);
        timeDown = new TimeDown(87, 330, ImageLoader.getScrollDown(), ImageLoader.getScrollDown(), window);

        backGround = ImageLoader.getBackGround();
        textPlace = ImageLoader.getTextPlace();
        roundTime = 60;

        initAllAlgInfos();


        input = gameContainer.getInput();
    }

    public void update(GameContainer gameContainer) {

        if (input.isKeyPressed(Input.KEY_ESCAPE))
            window.setCondition(Condition.Menu);
        else {
            callOnClick(addAlgButton, gameContainer);
            callOnClick(delButton, gameContainer);
            callOnClick(saveBut, gameContainer);
            callOnClick(loadButton, gameContainer);
            callOnClick(scrollUp, gameContainer);
            callOnClick(scrollDown, gameContainer);
            callOnClickPressed(timeUp, gameContainer);
            callOnClickPressed(timeDown, gameContainer);

            for (int i = 0; i < checkBoxes.size(); i++) {
                CheckBox nowCheckbox = checkBoxes.get(i);
                if (nowCheckbox.checkCLick(gameContainer) &&
                        input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    if (nowCheckbox.isChanged()) {
                        nowCheckbox.changeImg();
                        isParticipate.remove(allAlgInfos.get(i));
                    } else {
                        if (isParticipate.size() < 4) {
                            nowCheckbox.changeImg();
                            isParticipate.add(allAlgInfos.get(i));
                        }
                    }
                }
            }
        }
    }

    public void render(GameContainer gameContainer, Graphics graphics) {
        backGround.draw(0, 0);
        addAlgButton.drawButton(graphics);
        delButton.drawButton(graphics);
        saveBut.drawButton(graphics);
        loadButton.drawButton(graphics);
        scrollUp.drawButton(graphics);
        scrollDown.drawButton(graphics);
        timeUp.drawButton(graphics);
        timeDown.drawButton(graphics);

        for (int i = 0; i < allAlgInfos.size(); i++) {
            scrollList.get(i).draw(400, generalHeight + 200 + i * 68, 1.5f);
            checkBoxes.get(i).drawButton(graphics);
            HashMap<String, String> kek = getPathAndName(allAlgInfos.get(i).getPath());
            if (kek.get("name").length() > 15)
                Font.makeLine(kek.get("name").substring(0, 13).toUpperCase() + "...", 600, generalHeight + 210 + i * 68);
            else
                Font.makeLine(kek.get("name").toUpperCase(), 600, generalHeight + 210 + i * 68);
            Font.makeLine("HIGHSCORE: " + allAlgInfos.get(i).getHighScore(), 600, generalHeight + 240 + i * 68);
            ImageLoader.getGrayBike().draw(480, generalHeight + 220 + i * 68, 2.2f);
            for (AlgInfo item : isParticipate) {
                if (item.getId() == allAlgInfos.get(i).getId()) {
                    int ind = isParticipate.indexOf(item);
                    ImageLoader.getBike(ind).draw(480, generalHeight + 220 + i * 68, 2.2f);
                }
            }
        }

        textPlace.draw(380, 0, 1.6f);
        Font.makeLine("ADD - ADD ALGORITHM, DEL - DEL ALGORITHM.", 400, 25);
        Font.makeLine("LOAD - LOAD MAP, SAVE - SAVE SETTINGS IN NEW GAME", 400, 65);
        Font.makeLine("TIME", 170, 250);
        Font.makeLine(String.valueOf(roundTime), 170, 280);
    }

    public void initAllAlgInfos() {
        checkBoxes = new ArrayList<>();
        scrollList = new ArrayList<>();
        allAlgInfos = Serialize.readFile("allAlgorithms.txt");
        for (int i = 0; i < allAlgInfos.size(); i++) {
            CheckBox chBx = new CheckBox(418, generalHeight + 218 + i * 68, ImageLoader.getCheckBox(), ImageLoader.getCheckBox2(), window);
            scrollList.add(ImageLoader.getCheckList());
            checkBoxes.add(chBx);
            checkBoxes.get(i).setOriginalBut();
            isParticipate.clear();
            ImageLoader.getGrayBike().draw(480, generalHeight + 220 + i * 68, 2.2f);
        }
    }

    public ArrayList<Algorithm> getAlgorithmsInNewGame() {
        ArrayList<AlgInfo> algInfosInNewGame = Serialize.readFile("algorithmsInNewGame.txt");
        ArrayList<Algorithm> algorithms = new ArrayList<>();

        for (int i = 0; i < algInfosInNewGame.size(); i++) {
            HashMap<String, String> kek = getPathAndName(algInfosInNewGame.get(i).getPath());
            File fileClass = new File(kek.get("path"));
            String nameClass = kek.get("name");

            try {
                URL url = fileClass.toURI().toURL();
                URL[] urls = new URL[]{url};
                ClassLoader folderClassLoader = new URLClassLoader(urls);
                Class<Algorithm> cls = (Class<Algorithm>) folderClassLoader.loadClass(nameClass);
                Constructor<Algorithm> ctor = cls.getDeclaredConstructor();
                Algorithm p = ctor.newInstance();
                algorithms.add(p);
            } catch (MalformedURLException | InstantiationException | InvocationTargetException
                    | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return algorithms;
    }

    public static HashMap<String, String> getPathAndName(String fullPath) {
        HashMap<String, String> result = new HashMap<>();
        String nameClass = "";
        for (int i = fullPath.length() - 1; i > -1; i--) {
            String symbol = String.copyValueOf(new char[]{fullPath.charAt(i)});
            if (!Objects.equals(symbol, "\\"))
                nameClass = symbol + nameClass;
            else
                break;
        }
        String pathClass = fullPath.substring(0, fullPath.length() - nameClass.length());
        result.put("path", pathClass);
        result.put("name", nameClass);

        return result;
    }

    public void callOnClick(Button button, GameContainer gameContainer) {
        if (button.checkCLick(gameContainer) &&
                input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
            button.onClick();
    }

    public void callOnClickPressed(Button button, GameContainer gameContainer) {
        if (button.checkCLick(gameContainer) &&
                input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
            button.onClick();
    }

    public String getPathToMap() {
        return this.pathToMap;
    }

    public void setPathToMap(String pathToMap) {
        this.pathToMap = pathToMap;
    }

    public int getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(int roundTime) {
        this.roundTime = roundTime;
    }

    public void setCheckBoxes(ArrayList<CheckBox> checkBoxes) {
        this.checkBoxes = checkBoxes;
    }

    public ArrayList<CheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    public ArrayList<AlgInfo> getIsParticipate() {
        return isParticipate;
    }

    public void setIsParticipate(ArrayList<AlgInfo> isParticipate) {
        this.isParticipate = isParticipate;
    }

    public ArrayList<AlgInfo> getAllAlgInfos() {
        return allAlgInfos;
    }
}
