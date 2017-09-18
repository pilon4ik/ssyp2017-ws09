package GUI;

import Main.Condition;
import Main.Window;
import org.newdawn.slick.*;

public class Menu {
    private Button newGame, addAlg, Legacy, scrollUp, scrollDown;
    Font font;
    int repeat = 0;
    float choose = 0;
    float scroller = 0;
    Input input;
    Window window;

    public Menu(Window window) {
        this.window = window;
    }

    private void drawLine(Graphics graphics, GameContainer gameContainer) {
        repeat += 5;
        if (repeat > gameContainer.getWidth() / 2) {
            repeat = 0;
            choose = (float) Math.random();
        }
        if (choose >= 0.5f)
            graphics.drawLine((repeat - 70) / 1.15f + 50, gameContainer.getHeight() - (repeat - 70) / 2,
                    repeat / 1.15f + 50,
                    gameContainer.getHeight() - repeat / 2);
        if (choose < 0.5f)
            graphics.drawLine(gameContainer.getWidth() - (repeat - 70) / 1.13f - 50,
                    gameContainer.getHeight() - (repeat - 70) / 2, gameContainer.getWidth() - repeat / 1.13f - 50,
                    gameContainer.getHeight() - repeat / 2);

    }

    public void init(GameContainer gameContainer) {
        ImageLoader.loadImage();
        font = new Font();
        input = gameContainer.getInput();
        newGame = new ScrollDown(30, 100, ImageLoader.getNewGame(), ImageLoader.getNewGame2(), window);
        Legacy = new LegacyButton(30, 150, ImageLoader.getLegacyButton(), ImageLoader.getLegacyButton2(), window);
        scrollDown = new ScrollUp(1000, 550, ImageLoader.getScrollDown(), ImageLoader.getScrollDown(), window);
        scrollUp = new ScrollUp(1010, 500, ImageLoader.getScrollUp(), ImageLoader.getScrollUp(), window);
        newGame.setOriginalBut();
        Legacy.setOriginalBut();
    }

    public void update(GameContainer gameContainer) {
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (newGame.checkCLick(gameContainer)) {
                newGame.changeImg();
            }

            if (Legacy.checkCLick(gameContainer)) {

                Legacy.changeImg();
            }
            if (scrollUp.checkCLick(gameContainer) &&
                    scroller > -50) {
                scroller--;
            }
            if (scrollDown.checkCLick(gameContainer) &&
                    scroller < 1000) {
                scroller++;
            }
        }
        if (!input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && newGame.isChanged()) {
            newGame.changeImg();
            if (newGame.checkCLick(gameContainer))
                window.setCondition(Condition.Game);
        }
        if (!input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && Legacy.isChanged()) {
            Legacy.changeImg();
            if (Legacy.checkCLick(gameContainer))
                window.setCondition(Condition.Legacy);
        }
    }


    public void render(GameContainer gameContainer, Graphics graphics) {
        graphics.drawImage(ImageLoader.getBackGround(), 0, 0);
        graphics.setColor(Color.blue);
        drawLine(graphics, gameContainer);
        Font.makeLine("-ALL RIGHTS RESERVED-", 10, gameContainer.getHeight() - 30);
        newGame.drawButton(graphics);
        Legacy.drawButton(graphics);

    }

}
