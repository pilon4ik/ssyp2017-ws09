package Main;

import org.newdawn.slick.*;

public class MainClass extends BasicGame {

    Window window = new Window();

    public MainClass(String title) {
        super(title);
    }

    public static void main(String[] args) {
            try {
                AppGameContainer apgC;

                apgC = new AppGameContainer(new MainClass("TRON"));
                apgC.setUpdateOnlyWhenVisible(true);
                apgC.setDisplayMode(1280, 720, false);
                apgC.setShowFPS(false);
                apgC.start();
            } catch (SlickException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        window.init(gameContainer);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        window.update(gameContainer, i);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        window.render(gameContainer, graphics);
    }
}
