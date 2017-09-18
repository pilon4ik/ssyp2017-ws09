package Game;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

public class Camera {

    static final float velocity = 2;
    static Point pos = new Point(-640, -360);
    static final int frame = 25;
    static float scale = 1;
    static final int mapSize = 3000;
    static void moveCam(GameContainer gameContainer) {
        Input inp = gameContainer.getInput();
        if (inp.getMouseX() - frame <= 0 || inp.isKeyDown(Input.KEY_A)) {
            pos.setX(pos.getX() - velocity / scale);
        }

        if (inp.getMouseY() - frame <= 0 || inp.isKeyDown(Input.KEY_W)) {
            pos.setY(pos.getY() - velocity / scale);
        }

        if (inp.getMouseX() + frame >= gameContainer.getWidth() || inp.isKeyDown(Input.KEY_D)) {
            pos.setX(pos.getX() + velocity / scale);
        }

        if (inp.getMouseY() + frame >= gameContainer.getHeight() || inp.isKeyDown(Input.KEY_S)) {
            pos.setY(pos.getY() + velocity / scale);
        }
        if (inp.isKeyPressed(Input.KEY_SPACE)) {
            pos.setX(gameContainer.getWidth() / -2 / scale);
            pos.setY(gameContainer.getHeight() / -2 / scale);
        }
        //TODO: Как же я люблю захардкоденные константы
        if (pos.getX() <= -mapSize - Wall.thickness / 2) {
            pos.setX(-mapSize);
        }
        if (pos.getX() >= mapSize - gameContainer.getWidth() / scale + Wall.thickness / 2) {
            pos.setX(mapSize - gameContainer.getWidth() / scale + Wall.thickness / 2);
        }
        if (pos.getY() <= -mapSize - Wall.thickness / 2) {
            pos.setY(-mapSize - Wall.thickness / 2);
        }
        if (pos.getY() >= mapSize - gameContainer.getHeight() / scale + Wall.thickness / 2) {
            pos.setY(mapSize - gameContainer.getHeight() / scale + Wall.thickness / 2);
        }
        if (inp.isKeyPressed(Input.KEY_EQUALS) && scale <= 1.7f) {
            float x = pos.getX() + gameContainer.getWidth() / 2f / scale;
            float y = pos.getY() + gameContainer.getHeight() / 2f / scale;
            scale += 0.1f;
            pos.setX(x - gameContainer.getWidth() / 2f / scale);
            pos.setY(y - gameContainer.getHeight() / 2f / scale);
        }
        if (inp.isKeyPressed(Input.KEY_MINUS) && scale >= 0.3f) {
            float x = pos.getX() + gameContainer.getWidth() / 2f / scale;
            float y = pos.getY() + gameContainer.getHeight() / 2f / scale;
            scale -= 0.1f;
            pos.setX(x - gameContainer.getWidth() / 2f / scale);
            pos.setY(y - gameContainer.getHeight() / 2f / scale);
        }
    }

}
