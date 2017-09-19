package GUI;

import org.newdawn.slick.Graphics;

public class Font {

    public static void makeLine(String line, float xPos, float yPos) {
        String[] lineAr = line.split("");
        float width = 0;
        float scale = 1.5f;
        for (int i = 0; i < lineAr.length; i++) {

            switch (lineAr[i]) {
                case "A":
                    ImageLoader.getA().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getA().getWidth() * scale;
                    break;
                case "B":
                    ImageLoader.getB().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getB().getWidth() * scale;
                    break;
                case "C":
                    ImageLoader.getC().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getC().getWidth() * scale;
                    break;
                case "D":
                    ImageLoader.getD().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getD().getWidth() * scale;
                    break;
                case "E":
                    ImageLoader.getE().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getE().getWidth() * scale;
                    break;
                case "F":
                    ImageLoader.getF().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getF().getWidth() * scale;
                    break;
                case "G":
                    ImageLoader.getG().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getG().getWidth() * scale;
                    break;
                case "H":
                    ImageLoader.getH().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getH().getWidth() * scale;
                    break;
                case "I":
                    ImageLoader.getI().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getI().getWidth() * scale;
                    break;
                case "J":
                    ImageLoader.getJ().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getJ().getWidth() * scale;
                    break;
                case "K":
                    ImageLoader.getK().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getK().getWidth() * scale;
                    break;
                case "L":
                    ImageLoader.getL().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getL().getWidth() * scale;
                    break;
                case "M":
                    ImageLoader.getM().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getM().getWidth() * scale;
                    break;
                case "N":
                    ImageLoader.getN().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getN().getWidth() * scale;
                    break;
                case "O":
                    ImageLoader.getO().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getO().getWidth() * scale;
                    break;
                case "P":
                    ImageLoader.getP().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getP().getWidth() * scale;
                    break;
                case "Q":
                    ImageLoader.getQ().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getQ().getWidth() * scale;
                    break;
                case "R":
                    ImageLoader.getR().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getR().getWidth() * scale;
                    break;
                case "S":
                    ImageLoader.getS().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getS().getWidth() * scale;
                    break;
                case "T":
                    ImageLoader.getT().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getT().getWidth() * scale;
                    break;
                case "U":
                    ImageLoader.getU().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getU().getWidth() * scale;
                    break;
                case "V":
                    ImageLoader.getV().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getV().getWidth() * scale;
                    break;
                case "W":
                    ImageLoader.getW().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getW().getWidth() * scale;
                    break;
                case "X":
                    ImageLoader.getX().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getX().getWidth() * scale;
                    break;
                case "Y":
                    ImageLoader.getY().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getY().getWidth() * scale;
                    break;
                case "Z":
                    ImageLoader.getZ().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getZ().getWidth() * scale;
                    break;
                case "0":
                    ImageLoader.getN0().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getN0().getWidth() * scale;
                    break;
                case "1":
                    ImageLoader.getN1().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getN1().getWidth() * scale;
                    break;
                case "2":
                    ImageLoader.getN2().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getN2().getWidth() * scale;
                    break;
                case "3":
                    ImageLoader.getN3().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getN3().getWidth() * scale;
                    break;
                case "4":
                    ImageLoader.getN4().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getN4().getWidth() * scale;
                    break;
                case "5":
                    ImageLoader.getN5().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getN5().getWidth() * scale;
                    break;
                case "6":
                    ImageLoader.getN6().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getN6().getWidth() * scale;
                    break;
                case "7":
                    ImageLoader.getN7().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getN7().getWidth() * scale;
                    break;
                case "8":
                    ImageLoader.getN8().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getN8().getWidth() * scale;
                    break;
                case "9":
                    ImageLoader.getN9().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getN9().getWidth() * scale;
                    break;
                case "#":
                    ImageLoader.getHash().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getHash().getWidth() * scale;
                    break;
                case "-":
                    ImageLoader.getLine().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getLine().getWidth() * scale;
                    break;
                case ":":
                    ImageLoader.getDoublePoint().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getDoublePoint().getWidth() * scale;
                    break;
                case ",":
                    ImageLoader.getComma().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getComma().getWidth() * scale;
                    break;
                case ".":
                    ImageLoader.getDot().draw(xPos + width, yPos, scale);
                    width += ImageLoader.getDot().getWidth() * scale;
                    break;
                default:
                    width += 15;

            }
        }
    }
}
