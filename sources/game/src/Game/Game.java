
package Game;

import Algorithms.Algorithm;
import GUI.*;
import GUI.Font;
import Main.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Game {

    ArrayList<Bike> bikes;
    Map map = new Map();
    ArrayList<Algorithm> algorithms;
    private ArrayList<Bike> winners;
    private long roundTimer;
    private String winnerNames;
    private long gameEndTimer = -1;
    private long roundTimeSecs;
    private long crashTimer = -1;
    private int crasherIndex = -1;
    Window window;
    Minimap minimap;

    public Game(Window window) {

        this.window = window;

    }


    public void init(GameContainer gc) {
        if (window.legacy.getAlgorithmsInNewGame().size() == 0 || window.legacy.getPathToMap() == null) {
            window.setCondition(Condition.Menu);
        } else {
            crashTimer = -1;
            gameEndTimer = -1;
            winners = new ArrayList<Bike>();
            bikes = new ArrayList<Bike>();
            crasherIndex = -1;
            String path = window.legacy.getPathToMap();
            roundTimeSecs = window.legacy.getRoundTime();
            algorithms = window.legacy.getAlgorithmsInNewGame();
            ArrayList<Point> bikeCoords = new ArrayList<>();
            try {
                open(path, bikeCoords, map);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            for (int i = 0; i < algorithms.size(); i++) {
                bikes.add(new Bike(gc, i, map, bikes, bikeCoords.get(i)));
            }
            roundTimer = System.currentTimeMillis();
            minimap = new Minimap(map, bikes);
            Camera.pos.setX(gc.getWidth() / -2);
            Camera.pos.setY(gc.getHeight() / -2);
            Camera.scale = 1;
        }
    }

    private void open(String path, ArrayList<Point> coords, Map map) throws IOException {
        map.walls.clear();
        coords.clear();
        if (path != null) {
            BufferedReader input = new BufferedReader(new FileReader(path));
            ArrayList<String> strWalls = new ArrayList<String>();
            while (input.read() != -1) {
                strWalls.add(input.readLine());
            }
            try {
                boolean hasChanged = false;
                String[] numbers;
                for (int i = 0; i < strWalls.size(); i++) {
                    if (!strWalls.get(i).equals("spawn") && !hasChanged) {
                        numbers = strWalls.get(i).split(" ");
                        float beginX, beginY, endX, endY, red, green, blue;
                        beginX = Float.valueOf(numbers[0]);
                        beginY = Float.valueOf(numbers[1]);
                        endX = Float.valueOf(numbers[2]);
                        endY = Float.valueOf(numbers[3]);
                        red = Float.valueOf(numbers[4]);
                        green = Float.valueOf(numbers[5]);
                        blue = Float.valueOf(numbers[6]);
                        Color color = new Color(red, green, blue);
                        map.walls.add(new Wall(new Line(beginX, beginY, endX, endY), color, null));
                    } else {
                        if (!hasChanged) {
                            i++;
                        }
                        hasChanged = true;
                        numbers = strWalls.get(i).split(" ");
                        if (strWalls.size() < map.walls.size() + algorithms.size() + 1) {
                            throw new IOException();
                        }
                        if (i - map.walls.size() <= algorithms.size()) {
                            coords.add(new Point(Float.valueOf(numbers[0]), Float.valueOf(numbers[1])));
                        } else {
                            break;
                        }

                    }

                }
                if (coords.isEmpty()) {
                    throw new IOException();
                }
            } catch (NumberFormatException | IOException ex) {
                open("maps/default.txt", coords, map);
            }
        }
    }


    public void update(GameContainer gameContainer) {
        Camera.moveCam(gameContainer);
        if (winners.isEmpty()) {
            for (int i = 0; i < algorithms.size(); i++) {
                if (!bikes.get(i).isDead()) {
                    try {
                        algorithms.get(i).doSomething(bikes.get(i));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        crasherIndex = bikes.get(i).getIndex() + 1;
                        crashTimer = System.currentTimeMillis();
                    }
                    bikes.get(i).hasRotated = false;
                    bikes.get(i).hasAxel = false;
                    Wall collidedWall = map.collision(bikes.get(i));
                    if (collidedWall == null) {
                        bikes.get(i).move();
                    } else {
                        bikes.get(i).die();
                        bikes.get(i).score -= 1;
                        if (collidedWall.owner != null) {
                            if (collidedWall.owner.getIndex() != bikes.get(i).getIndex()) {
                                collidedWall.owner.score += 2;
                            }
                        }

                    }
                    for (Bike bike2 :
                            bikes) {
                        if (bikes.get(i).collidesWithBike(bike2) && !bike2.isDead() && bikes.get(i).getIndex() != bike2.getIndex()) {

                            bikes.get(i).die();
                            bike2.die();
                        }
                    }
                } else {
                    if (System.currentTimeMillis() - bikes.get(i).getRespawnTimer() >= 5000) {
                        bikes.get(i).respawn(gameContainer);
                    }
                }
            }
        }
        if (System.currentTimeMillis() - roundTimer >= roundTimeSecs * 1000 && gameEndTimer == -1) {
            int maxScore = 0;
            for (Bike bike : bikes) {
                if (bike.score >= maxScore || winners.isEmpty()) {
                    if (bike.score > maxScore) {
                        winners.clear();
                    }
                    maxScore = bike.score;
                    winners.add(bike);
                }
            }
            StringBuilder winnerIndexes = new StringBuilder();

            for (int i = 0; i < winners.size(); i++) {
                String algoName = window.legacy.getPathAndName(Serialize.readFile("algorithmsInNewGame.txt").get(bikes.get(winners.get(i).getIndex()).getIndex()).getPath()).get("name");
                winnerIndexes.append(algoName.toUpperCase());
                if (i != winners.size() - 1) {
                    winnerIndexes.append(", ");
                }
            }
            winnerNames = winnerIndexes.toString();
            gameEndTimer = System.currentTimeMillis();

        }
        if ((System.currentTimeMillis() - gameEndTimer > 3000 && gameEndTimer != -1)
                || (System.currentTimeMillis() - crashTimer > 3000 && crashTimer != -1)
                || gameContainer.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            if (System.currentTimeMillis() - gameEndTimer > 3000) {
                ArrayList<AlgInfo> algInfos = Serialize.readFile("algorithmsInNewGame.txt");
                for (int i = 0; i < algInfos.size(); i++) {
                    int allAlgInfosInd = algInfos.get(i).getId();
                    int newTotalScoreValue = algInfos.get(i).getTotalScore() + bikes.get(i).score;
                    window.legacy.getAllAlgInfos().get(allAlgInfosInd).setTotalScore(newTotalScoreValue);
                    if (algInfos.get(i).getHighScore() < bikes.get(i).score) {
                        window.legacy.getAllAlgInfos().get(allAlgInfosInd).setHighScore(bikes.get(i).score);
                    }
                }
                Serialize.fillAlgoInNewGameFile(window.legacy.getAllAlgInfos(), "allAlgorithms.txt");
                window.legacy.initAllAlgInfos();
            }
            window.setCondition(Condition.Menu);
        }
    }


    public void render(GameContainer gameContainer, Graphics graphics) {
        if (crasherIndex != -1) {
            String output = "BIKE #" + crasherIndex + " JUST CRASHED THE GAME";
            Font.makeLine(output, gameContainer.getWidth() / 2 - output.length() * 6,
                    gameContainer.getHeight() / 2 - 6);
        } else {
            graphics.drawImage(ImageLoader.getGameGround(), 0, 0);
            if (winners.isEmpty()) {
                map.draw(graphics);
                for (Bike bike : bikes) {
                    bike.draw(graphics, window);
                }
                Font.makeLine("X: " + (int) Camera.pos.getX() + ", Y: " + (int) Camera.pos.getY(),
                        25, gameContainer.getHeight() - 50);
                long systCurTime = System.currentTimeMillis();
                long curTime = (systCurTime - roundTimer) / 1000;
                Font.makeLine("TIME: " + (curTime / 60 == 0 ? "00" : curTime / 60) +
                        ":" + (curTime % 60 < 10 ? "0" : "") + curTime % 60, 25, 40);
                minimap.render(gameContainer, graphics);
            } else {
                boolean isOne = winnerNames.length() == 1;
                String output = winnerNames + " WIN" + (isOne ? "S" : "");
                Font.makeLine(output, gameContainer.getWidth() / 2 - output.length() * 6,
                        gameContainer.getHeight() / 2 - 6);
                for (Bike bike : bikes) {
                    String algoName = window.legacy.getPathAndName(Serialize.readFile("algorithmsInNewGame.txt").get(bike.getIndex()).getPath()).get("name");
                    if (algoName.length() > 10) {
                        algoName = algoName.substring(0, 9) + ",,,";
                    }
                    output = algoName.toUpperCase() + " SCORE: " + bike.score;
                    Font.makeLine(output,
                            gameContainer.getWidth() / 2 - output.length() * 6,
                            gameContainer.getHeight() / 2 + 60 + bike.getIndex() * 30);
                }

            }
        }
    }
}