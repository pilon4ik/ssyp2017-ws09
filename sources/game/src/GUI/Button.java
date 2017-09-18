package GUI;

import Main.Window;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Button {
    private float posX, posY;
    private Image imgButton;
    private Image imgButton2, originalBut;
    Window window;

    abstract void onClick();

    void setOriginalBut() {
        originalBut = this.getImgButton();
    }

    Button(float posX, float posY, Image imgButton, Image imgButton2, Window window) {
        this.posX = posX;
        this.posY = posY;
        this.imgButton = imgButton;
        this.imgButton2 = imgButton2;
        this.window = window;
    }

    void drawButton(Graphics graphics) {
        this.getImgButton().draw(this.getPosX(), this.getPosY(), 1.5f);
    }

    void changeImg() {

        Image imgAdd;
        imgAdd = this.getImgButton();
        this.setImgButton(this.getImgButton2());
        this.setImgButton2(imgAdd);

    }

    boolean isChanged() {
        boolean result = false;
        if (this.getImgButton2() == originalBut) {
            result = true;
        }
        return result;
    }

    boolean checkCLick(GameContainer gameContainer) {
        float mouseX, mouseY;
        boolean result = false;
        float width = this.getImgButton().getWidth() * 1.5f;
        float height = this.getImgButton().getHeight() * 1.5f;
        mouseX = gameContainer.getInput().getMouseX();
        mouseY = gameContainer.getInput().getMouseY();
        if (mouseX > this.getPosX() && mouseX < width + this.getPosX()
                && mouseY > this.getPosY() && mouseY < height + this.getPosY()) {
            result = true;
        }
        return result;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    private Image getImgButton() {
        return imgButton;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    void setImgButton(Image imgButton) {
        this.imgButton = imgButton;
    }

    public void setImgButton2(Image imgButton2) {
        this.imgButton2 = imgButton2;
    }

    public Image getImgButton2() {
        return imgButton2;
    }
}

