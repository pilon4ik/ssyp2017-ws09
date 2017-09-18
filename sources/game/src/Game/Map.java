package Game;

import Game.Bike;


import GUI.FileChooser;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Map {
    public ArrayList<Wall> getWalls() {
        return new ArrayList<Wall>(walls);
    }

    ArrayList<Wall> walls = new ArrayList<Wall>();

    public Map() {
    }

    Map(Map map) {
        this.walls = map.walls;
    }




    Wall collision(Bike bike) {
        for (Wall wall : walls) {
            if (wall.collides(bike)) {
                return wall;
            }
        }
        return null;
    }

    void draw(Graphics graphics) {
        for (Wall wall : walls) {
            wall.draw(graphics);
        }
    }


}
