package Main;

import Algorithms.Algorithm;
import Game.*;
import GUI.ImageLoader;
import GUI.Legacy;
import GUI.Menu;
import Game.Game;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;

import java.util.ArrayList;

public class Window {
    //TODO: Кароче, на самом деле все очень хорошо. Вы все большие молодцы(особенно Денис).
    //TODO: Но багов много. Артем с Мишей - займитесь их ловлей и отладкой. Постарайтесь найти как можно больше и пофиксить.
    //TODO: Миша, не надо кричать что багов нет - дайте мне писать призентацию. Они есть, ищи.
    //TODO: Артем, тоже самое, баги есть, я сам постоянно нарываюсь на них. Если ты их совсем не находишь уже пол часа
    //TODO: то присойденяйся к Илье и Денису.
    //TODO: Денис и Илья - пишите свои алгоритмы. Их нужно написать хорошо, что бы потестить возможности нашего проекта.Это очень важно.
    //TODO: Предлагайте свои фичи Мише. Миша, записывай их и реализуй по возможности. Лично от меня: увеличивай скорость камеры со скейлом.
    //TODO: До Ужина вы занимайтесь вот этим. После ужина - пилим призентацию.
    //TODO: Коменты для Кюнней в ее проекте.
    private Condition condition = Condition.Menu;
    Menu menu = new Menu(this);
    public Legacy legacy = new Legacy(this);
    Game game = new Game(this);
    GameContainer gc;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition newCondition) {
        condition = newCondition;
        switch (newCondition) {
            case Menu:
                menu.init(gc);
                break;
            case Game:
                game.init(gc);
                break;
            case Legacy:
                legacy.init(gc);
                break;
        }
    }

    public void init(GameContainer gameContainer) throws SlickException {
        gc = gameContainer;
        ImageLoader.loadImage();
        legacy.init(gameContainer);

        menu.init(gameContainer);
    }

    public void update(GameContainer gameContainer, int i) throws SlickException {
        gc = gameContainer;
        switch (condition) {
            case Menu:
                menu.update(gc);
                break;
            case Game:
                game.update(gc);
                break;
            case Legacy:
                legacy.update(gc);
                break;
        }
    }

    public void render(GameContainer gameContainer, Graphics g) throws SlickException {
        switch (condition) {
            case Menu:
                menu.render(gc, g);
                break;
            case Game:
                game.render(gc, g);
                break;
            case Legacy:
                legacy.render(gc, g);
                break;
        }
    }
}
