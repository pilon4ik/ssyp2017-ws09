package Game;

import GUI.*;
import Main.Serialize;
import Main.Window;
import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

import static Game.Camera.pos;

public class Bike {
    private float deathTimer = -1;
    public static final int height = 45;
    public static final int width = 12;
    private boolean isDead = false;
    private float velocity = 0;
    boolean hasAxel = false;
    private Wall trace;
    private int direction = 0;
    private Stack<Wall> wallsToDelete = new Stack<>();
    //MAP//////////////////////////////////////////
    private float rotateAngle = 90;
    private float sin = (float) Math.sin(Math.toRadians(rotateAngle));
    private float cos = (float) Math.cos(Math.toRadians(rotateAngle));
    private Point position;
    private Vector2f vector = new Vector2f(0, 0);
    private Image imageRight;
    private Color color;
    int score = 0;
    private int index;
    private Map map;
    private ArrayList<Bike> bikes;
    private long respawnTimer;
    private Color green = new Color(56, 159, 22),
            blue = new Color(0, 26, 255),
            red = new Color(111, 0, 0),
            yellow = new Color(210, 103, 11);
    private GameContainer gc;
    private long afkTimer = -1;
    private long prevTickTime = -1;
    boolean hasRotated = false;
    private Point defaultPos;

    public int getDirection() {
        return direction;
    }

    public Vector2f getVector() {

        return new Vector2f(vector);
    }

    public Point getPosition() {
        return new Point(position.getX(), position.getY());
    }

    public long getRespawnTimer() {
        return respawnTimer;
    }

    public float getVelocity() {

        return velocity;
    }

    public Color getColor() {
        return color;
    }

    public boolean isDead() {
        return isDead;
    }

    public int getIndex() {
        return index;
    }

    public ArrayList<Bike> getBikes() {
        ArrayList<Bike> newBikes = new ArrayList<Bike>(bikes);
        newBikes.remove(index);
        return newBikes;
    }


    public ArrayList<Bike> getAllBikes() {
        return new ArrayList<Bike>(bikes);
    }

    public Map getMap() {
        return new Map(map);
    }


    public Bike getCopy() {
        return new Bike(this);
    }


    Bike(GameContainer gc, int index, Map map, ArrayList<Bike> bikes, Point pos) {
        imageRight = ImageLoader.getBike(index);
        vector = new Vector2f(0, 0);
        this.gc = gc;
        velocity = 1;
        direction = 0;
        vector.y = -1;
        switch (index) {
            case 0:
                color = red;
                break;
            case 1:
                color = blue;
                break;
            case 2:
                color = yellow;
                break;
            case 3:
                color = green;
                break;
        }
        this.index = index;
        this.map = map;
        this.bikes = bikes;
        this.position = new Point(pos.getX(), pos.getY());
        this.defaultPos = new Point(pos.getX(), pos.getY());
        hasAxel = false;
    }

    private Bike(Point pos, Bike bike) {
        this(bike.gc, bike.index, bike.map, bike.bikes,  new Point(pos.getX(), pos.getY()));
        direction = bike.direction;
        hasAxel = false;
    }


    private Bike(Point pos, int dir, Bike bike) {
        this(bike.gc, bike.index, bike.getMap(), bike.getAllBikes(), new Point(pos.getX(), pos.getY()));
        direction = dir;
        hasAxel = false;
        switch (dir) {
            case 0:
                vector.x = 0;
                vector.y = -1;
                break;
            case 90:
                vector.x = 1;
                vector.y = 0;
                break;
            case 180:
                vector.x = 0;
                vector.y = 1;
                break;
            case 270:
                vector.x = -1;
                vector.y = 0;
                break;
        }
    }

    private Bike(Bike bike) {
        this(bike.gc, bike.index, bike.getMap(), bike.getBikes(), new Point(bike.position.getX(), bike.position.getY()));
        this.direction = bike.direction;
        this.vector = bike.vector;
        this.velocity = bike.velocity;
        hasAxel = false;
    }

    boolean collidesWithBike(Bike bike) {
        if (bike.index != index) {
            for (Line hitBoxEdge1 :
                    getBikeHitBox()) {
                for (Line hitBoxEdge2 :
                        bike.getBikeHitBox()) {
                    if (Wall.crosses(hitBoxEdge1, hitBoxEdge2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkPos(Point newPos, int newDir) {
        Bike hypotheticalBike = new Bike(newPos, newDir, this);
        for (Bike bike : bikes) {
            if (hypotheticalBike.collidesWithBike(bike)) {
                return false;
            }
        }
        return map.collision(hypotheticalBike) == null;
    }

    public boolean checkRotation(boolean clockwise) {
        Bike bike = new Bike(this);
        bike.rotate(clockwise);
        return bike.checkPos(bike.position);
    }


    public boolean checkPos(Point newPos, GameContainer gc) {
        Bike hypotheticalBike = new Bike(newPos, this);
        for (Bike bike : bikes) {
            if (hypotheticalBike.collidesWithBike(bike)) {
                return false;
            }
        }
        return map.collision(hypotheticalBike) == null;
    }


    ArrayList<Line> getBikeHitBox() {
        ArrayList<Point> bikePoints = new ArrayList<>();
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                Vector2f dir = vector.copy().normalise().scale(height / 2);
                Vector2f perDir = vector.getPerpendicular().normalise().scale(width / 2);
                Vector2f vec = dir.add(perDir);
                bikePoints.add(new Point(position.getX() + vec.x * i, position.getY() + vec.y * j));
            }
        }
        ArrayList<Line> bikeHitBox = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            bikeHitBox.add(new Line(bikePoints.get(i).getX(), bikePoints.get(i).getY(),
                    bikePoints.get((i + 1) % 4).getX(), bikePoints.get((i + 1) % 4).getY()));
        }
        return bikeHitBox;
    }


    void die() {
        isDead = true;
        while (!wallsToDelete.empty()) {
            map.walls.remove(wallsToDelete.peek());
            wallsToDelete.pop();
        }
        deathTimer = 5;
        respawnTimer = System.currentTimeMillis();
    }

    public boolean checkPos(Point pos) {
        Bike hypotheticalBike = new Bike(pos, this);
        for (Bike bike : bikes) {
            if (hypotheticalBike.collidesWithBike(bike)) {
                return false;
            }
        }
        return map.collision(hypotheticalBike) == null;
    }

    void respawn(GameContainer gc) {
        vector = new Vector2f(0, 0);
        velocity = 1;
        position = new Point(defaultPos.getX(), defaultPos.getY());
        direction = 0;
        vector.y = -1;
        isDead = false;
        Wall collidedWall = map.collision(this);
        if (collidedWall != null) {
            map.walls.remove(collidedWall);
        }
        trace = null;
    }

    void move() {
        if (prevTickTime == -1) {
            prevTickTime = System.currentTimeMillis();
        }
        Vector2f vecNow = vector.copy().scale(velocity / 1000 * (System.currentTimeMillis() - prevTickTime));
        prevTickTime = System.currentTimeMillis();
        position.setX(position.getX() + vecNow.x);
        position.setY(position.getY() + vecNow.y);
        float w = width / 2f;
        float h = height / 2f;
        Vector2f dir = vector.copy().scale(h);
        Point back = new Point(position.getX() - dir.getX() - vector.x * Wall.thickness / 2f,
                position.getY() - dir.getY() - vector.y * Wall.thickness / 2f);
        if (trace == null) {
            trace = new Wall(new Line(back.getX(), back.getY(), back.getX(), back.getY()), color, this);
            map.walls.add(trace);
            wallsToDelete.add(trace);
        } else {
            trace.wallSpine.set(trace.wallSpine.getX1(), trace.wallSpine.getY1(), back.getX(), back.getY());
        }
        if (velocity == 0 && afkTimer == -1) {
            afkTimer = System.currentTimeMillis();
        }

        Random r = new Random();
        if (System.currentTimeMillis() - afkTimer >= ((r.nextFloat() * 4f) + 1f) * 1000 && afkTimer != -1) {
            die();
            score -= 4;
            afkTimer = -1;
        }
        if (afkTimer != -1 && velocity > 0) {
            afkTimer = -1;
        }
    }

    public void rotate(Boolean rotation) {
        if (!hasRotated) {
            // false <--- rotate ----> true
            float w = width;
            float h = height / 2 + 1;
            Vector2f dir;
            Vector2f perDir;
            Vector2f nvec = new Vector2f(0, 0);
            Point forward;
            if (rotation) {
                nvec.x = vector.x * cos - vector.y * sin;
                nvec.y = vector.x * sin + vector.y * cos;
                vector = nvec.normalise();
                dir = vector.copy().scale(h);
                perDir = vector.copy().getPerpendicular().scale(w);
                forward = new Point(position.getX() + dir.getX() + perDir.getX(),
                        position.getY() + dir.getY() + perDir.getY());
                position = forward;
                direction += rotateAngle;

                if (direction >= 360) {
                    direction -= 360;
                }
            } else {
                nvec.x = vector.x * cos + vector.y * sin;
                nvec.y = -vector.x * sin + vector.y * cos;
                vector = nvec.normalise();
                dir = vector.copy().scale(h);
                perDir = vector.copy().getPerpendicular().scale(w);
                forward = new Point(position.getX() + dir.getX() - perDir.getX(),
                        position.getY() + dir.getY() - perDir.getY());
                position = forward;
                direction -= rotateAngle;
                if (direction < 0) {
                    direction += 360;
                }
            }
            dir = vector.copy().scale(h);

            Point back = new Point(position.getX() - dir.getX(),
                    position.getY() - dir.getY());
            if (trace != null) {
                trace.wallSpine.set(trace.wallSpine.getX1(), trace.wallSpine.getY1(), back.getX(), back.getY());
            }
            trace = null;
            position.setX(position.getX() + vector.x * Wall.thickness / 2f);
            position.setY(position.getY() + vector.y * Wall.thickness / 2f);
            hasRotated = true;
        }
    }

    public void accelerate(Boolean speedUp) {
        if(!hasAxel) {
            if (speedUp) {
                velocity += 1;
            } else {
                velocity -= 1;
            }
            if (velocity < 0) {
                velocity = 0;
            } else if (velocity > 150f) {
                velocity = 150f;
            }
            hasAxel = true;
        }
    }

    void draw(Graphics g, Window window) {
        float posX = (this.position.getX() - height / 2f - Camera.pos.getX()) * Camera.scale,
                posY = (this.position.getY() - width / 2f - Camera.pos.getY()) * Camera.scale;

        if (!isDead) {
            Image imgCopy;
            imgCopy = imageRight.getScaledCopy(Camera.scale);
            imgCopy.rotate(direction - 90);
            g.drawImage(imgCopy, posX, posY);
        } else {
            if (deathTimer >= 1) {
                switch (Math.round(deathTimer)) {
                    case 5:
                        Image imgCopy = ImageLoader.getExplosion1().getScaledCopy(Camera.scale);
                        imgCopy.rotate(direction - 90);
                        g.drawImage(imgCopy, posX, posY);
                        deathTimer -= 0.025f;
                        break;
                    case 4:
                        imgCopy = ImageLoader.getExplosion2().getScaledCopy(Camera.scale);
                        imgCopy.rotate(direction - 90);
                        g.drawImage(imgCopy, posX, posY);
                        deathTimer -= 0.025f;
                        break;
                    case 3:
                        imgCopy = ImageLoader.getExplosion3().getScaledCopy(Camera.scale);
                        imgCopy.rotate(direction - 90);
                        g.drawImage(imgCopy, posX, posY);
                        deathTimer -= 0.025f;
                        break;
                    case 2:
                        imgCopy = ImageLoader.getExplosion4().getScaledCopy(Camera.scale);
                        imgCopy.rotate(direction - 90);
                        g.drawImage(imgCopy, posX, posY);
                        deathTimer -= 0.025f;
                        deathTimer -= 0.025f;
                        break;
                    case 1:
                        imgCopy = ImageLoader.getExplosion5().getScaledCopy(Camera.scale);
                        imgCopy.rotate(direction - 90);
                        g.drawImage(imgCopy, posX, posY);
                        deathTimer -= 0.025f;
                        deathTimer -= 0.025f;
                        break;
                }
            }
        }
        String algoName = window.legacy.getPathAndName(Serialize.readFile("algorithmsInNewGame.txt").get(index).getPath()).get("name");
        if (algoName.length() > 10) {
            algoName = algoName.substring(0, 9) + ",,,";
        }
        String output = algoName + " SCORE: " + score;
        GUI.Font.makeLine(output.toUpperCase(), gc.getWidth() - 300, index * 30 + 25);
    }
}
