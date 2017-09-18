import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kyunney on 27.07.2017.
 */
public class Saver extends Button {
    ArrayList<Wall> walls;
    boolean ifSaved = false;
    ArrayList<Point> spawnPoints;

    Saver(Point cor, float size, ButtonStatus status, ArrayList<Wall> walls, ArrayList<Point> spawnPoints) {
        super(Color.lightGray, cor, size, status);
        this.walls = walls;
        this.spawnPoints = spawnPoints;
    }

    public void draw(GameContainer gc, Graphics graphics) throws IOException, SlickException {
        if(this.status == ButtonStatus.Nothing) {
            graphics.drawImage(new Image("res/save.png"), cor.getX(), cor.getY());
        }
        else{
            graphics.drawImage(new Image("res/save2.png"), cor.getX(), cor.getY());
        }
    }
    public boolean ifSaved(){
        return ifSaved;
    }


    public void choose(GameContainer gc, Boolean ifButtonPressed) throws IOException {
        Point click = new Point(gc.getInput().getMouseX(), gc.getInput().getMouseY());
        Rectangle rectangle = new Rectangle(this.cor.getX(), this.cor.getY(), this.size, this.size / 2);
        if (rectangle.contains(new Point(click.getX(), click.getY()))) {
            if(ifButtonPressed) {
                this.status = ButtonStatus.Clicked;
                ifSaved = true;
                this.save();
            }
            else{
                this.status = ButtonStatus.Howel;
                ifSaved = false;
            }
        }
        else{
            this.status = ButtonStatus.Nothing;
            ifSaved = false;
        }
    }

    private void save() throws IOException {
        if (this.status == ButtonStatus.Clicked) {
            JFileChooser fc = new JFileChooser();
            FileChooser component = new FileChooser();
            int returnVal = fc.showSaveDialog(component);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                BufferedWriter output = new BufferedWriter(new FileWriter(fc.getSelectedFile()));
                for (Wall wall : this.walls) {
                    output.append(" ");
                    output.append(Float.toString(wall.begin.getX()));
                    output.append(" ");
                    output.append(Float.toString(wall.begin.getY()));
                    output.append(" ");
                    output.append(Float.toString(wall.end.getX()));
                    output.append(" ");
                    output.append(Float.toString(wall.end.getY()));
                    output.append(" ");
                    output.append(Float.toString(wall.color.getRed()));
                    output.append(" ");
                    output.append(Float.toString(wall.color.getGreen()));
                    output.append(" ");
                    output.append(Float.toString(wall.color.getBlue()));
                    output.newLine();
                }
                output.append(" ");
                output.append("spawn");
                output.newLine();
                for (Point spawnPoint : spawnPoints){
                    output.append(" ");
                    output.append(Float.toString(spawnPoint.getX()));
                    output.append(" ");
                    output.append(Float.toString(spawnPoint.getY()));
                    output.newLine();
                }
                output.flush();
            }
            this.status = ButtonStatus.Nothing;
        }
    }
}
