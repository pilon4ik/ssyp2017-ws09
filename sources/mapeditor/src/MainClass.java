import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kyunney on 24.07.2017.
 */
public class MainClass extends BasicGame {
    float spaceBtwButtons = 60;
    float borderIndent = 30;
    float buttonSize = 40;
    Image backGround;
    ArrayList<Wall> walls = new ArrayList<>();
    WallBuilder wallbuilder = new WallBuilder(walls);
    int i = 0;
    ArrayList<Button> colorButtons = new ArrayList<>();
    ButtonChooser colorChooser = new ButtonChooser(colorButtons);
    ArrayList<BorderDrawer> borderDrawers = new ArrayList<>();
    BorderDrawersChooser borderDrawersChooser = new BorderDrawersChooser(borderDrawers);
    ArrayList<Point> spawnPoints = new ArrayList<>();
    Eraser eraser;
    Camera camera = new Camera(new Point(0, 0));
    Saver saver;
    Loader loader;
    MiniMap minimap;
    Spawn spawn;

    public MainClass(String title) throws SlickException {
        super(title);
    }


    @Override
    public void init(GameContainer gc) throws SlickException {
        spawn = new Spawn(new Point(gc.getWidth() - (float)9.5 * spaceBtwButtons, borderIndent), buttonSize*2, ButtonStatus.Nothing);
        borderDrawersChooser.init(gc);
        colorChooser.init(gc);
        backGround = new Image("res/gameGround.png");
        loader = new Loader(new Point(borderIndent, gc.getHeight() - spaceBtwButtons), buttonSize * 2, walls, spawnPoints, camera, ButtonStatus.Nothing);
        saver = new Saver(new Point(borderIndent, gc.getHeight() - 2 * spaceBtwButtons), buttonSize * 2, ButtonStatus.Nothing, walls, spawnPoints);
        eraser = new Eraser(new Point(gc.getWidth() - spaceBtwButtons, borderIndent), buttonSize, ButtonStatus.Nothing);
        minimap = new MiniMap(gc, walls, spawnPoints);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        Boolean ifMousePressed = gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON);
        camera.moveCam(gc);
        if (wallbuilder.ifBuildBeginned()) {
            wallbuilder.preAdd(gc, camera);
            wallbuilder.build(gc, colorButtons, camera,  ifMousePressed);
        } else {

            wallbuilder.preAdd(gc, camera);
            camera.toCentre(gc);

            try {
                loader.choose(gc, ifMousePressed, borderDrawers);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                saver.choose(gc, ifMousePressed);
            } catch (IOException e) {
                e.printStackTrace();
            }
            eraser.choose(gc, ifMousePressed, colorButtons, spawn);
            eraser.reset(gc, walls, spawnPoints, camera,  ifMousePressed);
            if(!saver.ifSaved && !loader.ifLoaded()) {
                spawn.spawn(gc, spawnPoints, camera,  ifMousePressed);
            }
            spawn.choose(gc, colorButtons, spawnPoints, eraser, ifMousePressed);
            colorChooser.choose(gc, colorButtons, ifMousePressed, eraser, spawn);
            if (!colorChooser.ifButtonChoosed()) {
                if (!borderDrawersChooser.choose(gc, borderDrawers, ifMousePressed) && eraser.status == ButtonStatus.Nothing && !loader.ifLoaded() && !saver.ifSaved && spawn.status == ButtonStatus.Nothing) {
                    wallbuilder.build(gc, colorButtons, camera, ifMousePressed);
                }
            }

            for (BorderDrawer borderDrawer : borderDrawers) {
                if (borderDrawer.status == ButtonStatus.Clicked) {
                    wallbuilder.addBorders(borderDrawer.borderCor);
                    borderDrawer.status = ButtonStatus.Nothing;
                }
            }


        }
    }

    @Override
    public void render(GameContainer gc, Graphics graphics) throws SlickException {
        graphics.drawImage(backGround, 0, 0);
        camera.draw(graphics);
        eraser.draw(gc, graphics);
        spawn.draw(gc, graphics);
        spawn.draw(graphics, camera, spawnPoints);
        for (i = 0; i < walls.size(); i++) {
            walls.get(i).draw(graphics, camera);
        }
        for (Button button :
                colorButtons) {
            try {
                button.draw(gc, graphics);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (BorderDrawer borderDrawer : borderDrawers) {
            borderDrawer.draw(gc, graphics);
        }
        try {
            saver.draw(gc, graphics);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            loader.draw(gc, graphics);
        } catch (IOException e) {
            e.printStackTrace();
        }
        minimap.draw(gc, graphics, camera);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new MainClass("build"));
            appgc.setDisplayMode(1280, 720, false);
            appgc.setShowFPS(false);
            appgc.start();

        } catch (SlickException e) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
