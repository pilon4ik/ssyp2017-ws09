package GUI;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageLoader {
    private static Image newGame, newGame2, addAlg, addAlg2, legacyButton, legacyButton2, backGround, checkList,
            scrollDown, scrollUp,
            A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, checkBox, checkBox2,
            n0, n1, n2, n3, n4, n5, n6, n7, n8, n9, gameGround, hash,
            explosion1, explosion2, explosion3, explosion4, explosion5,
            yellowBike, redBike, blueBike, greenBike, line, doublePoint,
            save, save2, load, load2, del, del2, comma, textPlace, grayBike, spawn, spawn2;


    public static void loadImage() {
        try {
            spawn = new Image("res\\spawn.png");
        } catch (SlickException e) {
            e.printStackTrace();

        }
        try {
            spawn2 = new Image("res\\spawn2.png");
        } catch (SlickException e) {
            e.printStackTrace();

        }
        try {
            grayBike = new Image("res\\grayBike.png");
        } catch (SlickException e) {
            e.printStackTrace();

        }
        try {
            textPlace = new Image("res\\textPlace.png");
        } catch (SlickException e) {
            e.printStackTrace();

        }
        try {
            gameGround = new Image("res\\gameGround.png");
        } catch (SlickException e) {
            e.printStackTrace();

        }
        try {
            comma = new Image("res\\Alphabet\\comma.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            del = new Image("res\\delAlg.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            del2 = new Image("res\\delAlg2.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            del = new Image("res\\delAlg.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            del2 = new Image("res\\delAlg2.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            load2 = new Image("res\\load2.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            load = new Image("res\\load.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            save2 = new Image("res\\save2.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            save = new Image("res\\save.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            yellowBike = new Image("res\\yellowBikeN.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            redBike = new Image("res\\redBikeN.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            greenBike = new Image("res\\greenBikeN.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            blueBike = new Image("res\\blueBikeN.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        try {
            explosion1 = new Image("res\\explosion1.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        try {
            explosion2 = new Image("res\\explosion2.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        try {
            explosion3 = new Image("res\\explosion3.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        try {
            explosion4 = new Image("res\\explosion4.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            explosion5 = new Image("res\\explosion5.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            doublePoint = new Image("res\\Alphabet\\double.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            line = new Image("res\\Alphabet\\line.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            hash = new Image("res\\Alphabet\\Numb.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            gameGround = new Image("res\\gameGround.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            scrollDown = new Image("res\\ScrollList.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            scrollUp = new Image("res\\ScrollList.png");
            scrollUp.rotate(180);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            newGame = new Image("res\\newGame.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            newGame2 = new Image("res\\newGame2.png");
            newGame2.getScaledCopy(2);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            addAlg = new Image("res\\addAlg.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            addAlg2 = new Image("res\\addAlg2.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            legacyButton = new Image("res\\Legacy.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            legacyButton2 = new Image("res\\Legacy2.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            backGround = new Image("res\\backGround.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            checkList = new Image("res\\checkList.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            checkBox = new Image("res\\checkBox.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            checkBox2 = new Image("res\\checkBox2.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            A = new Image("res\\Alphabet\\A.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            B = new Image("res\\Alphabet\\B.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            C = new Image("res\\Alphabet\\C.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            D = new Image("res\\Alphabet\\D.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            E = new Image("res\\Alphabet\\E.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            F = new Image("res\\Alphabet\\F.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            G = new Image("res\\Alphabet\\G.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            H = new Image("res\\Alphabet\\H.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            I = new Image("res\\Alphabet\\I.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            J = new Image("res\\Alphabet\\J.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            K = new Image("res\\Alphabet\\K.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            L = new Image("res\\Alphabet\\L.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            M = new Image("res\\Alphabet\\M.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            N = new Image("res\\Alphabet\\N.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            O = new Image("res\\Alphabet\\O.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            P = new Image("res\\Alphabet\\P.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            Q = new Image("res\\Alphabet\\Q.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            R = new Image("res\\Alphabet\\R.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            S = new Image("res\\Alphabet\\S.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            T = new Image("res\\Alphabet\\T.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            U = new Image("res\\Alphabet\\U.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            V = new Image("res\\Alphabet\\V.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            W = new Image("res\\Alphabet\\W.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            X = new Image("res\\Alphabet\\X.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            Y = new Image("res\\Alphabet\\Y.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            Z = new Image("res\\Alphabet\\Z.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            n0 = new Image("res\\Alphabet\\0.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            n1 = new Image("res\\Alphabet\\1.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            n2 = new Image("res\\Alphabet\\2.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            n3 = new Image("res\\Alphabet\\3.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            n4 = new Image("res\\Alphabet\\4.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            n5 = new Image("res\\Alphabet\\5.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            n6 = new Image("res\\Alphabet\\6.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            n7 = new Image("res\\Alphabet\\7.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            n8 = new Image("res\\Alphabet\\8.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            n9 = new Image("res\\Alphabet\\9.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    public static Image getExplosion1() {
        return explosion1;
    }

    public static Image getExplosion2() {
        return explosion2;
    }

    public static Image getExplosion3() {
        return explosion3;
    }

    public static Image getExplosion4() {
        return explosion4;
    }

    public static Image getExplosion5() {
        return explosion5;
    }

    public static Image getScrollDown() {
        return scrollDown;
    }


    public static Image getGameGround() {
        return gameGround;
    }

    public static Image getScrollUp() {
        return scrollUp;
    }

    public static Image getCheckBox() {
        return checkBox;
    }

    public static Image getCheckBox2() {
        return checkBox2;
    }

    static Image getCheckList() {
        return checkList;
    }

    public static Image getBackGround() {
        return backGround;
    }

    public static Image getBike(int index) {
        switch (index) {
            case 0:
                return redBike;
            case 1:
                return blueBike;
            case 2:
                return yellowBike;
            case 3:
                return greenBike;
            default:
                return redBike;

        }
    }

    public static Image getNewGame() {
        return newGame;
    }

    public static Image getAddAlg() {
        return addAlg;
    }

    public static Image getAddAlg2() {
        return addAlg2;
    }

    public static Image getLegacyButton() {
        return legacyButton;
    }

    public static Image getNewGame2() {
        return newGame2;
    }

    public static Image getLegacyButton2() {
        return legacyButton2;
    }

    public static Image getA() {
        return A;
    }

    public static Image getB() {
        return B;
    }

    public static Image getC() {
        return C;
    }

    public static Image getD() {
        return D;
    }

    public static Image getE() {
        return E;
    }

    public static Image getF() {
        return F;
    }

    public static Image getG() {
        return G;
    }

    public static Image getH() {
        return H;
    }

    public static Image getI() {
        return I;
    }

    public static Image getJ() {
        return J;
    }

    public static Image getK() {
        return K;
    }

    public static Image getL() {
        return L;
    }

    public static Image getM() {
        return M;
    }

    public static Image getN() {
        return N;
    }

    public static Image getO() {
        return O;
    }

    public static Image getP() {
        return P;
    }

    public static Image getQ() {
        return Q;
    }

    public static Image getR() {
        return R;
    }

    public static Image getS() {
        return S;
    }

    public static Image getT() {
        return T;
    }

    public static Image getU() {
        return U;
    }

    public static Image getV() {
        return V;
    }

    public static Image getW() {
        return W;
    }

    public static Image getX() {
        return X;
    }

    public static Image getY() {
        return Y;
    }

    public static Image getZ() {
        return Z;
    }

    public static Image getN0() {
        return n0;
    }

    public static Image getN1() {
        return n1;
    }

    public static Image getN2() {
        return n2;
    }

    public static Image getN3() {
        return n3;
    }

    public static Image getN4() {
        return n4;
    }

    public static Image getN5() {
        return n5;
    }

    public static Image getN6() {
        return n6;
    }

    public static Image getN7() {
        return n7;
    }

    public static Image getN8() {
        return n8;
    }

    public static Image getN9() {
        return n9;
    }

    public static Image getHash() {
        return hash;
    }

    public static Image getLine() {
        return line;
    }

    public static Image getSave() {
        return save;
    }

    public static Image getSave2() {
        return save2;
    }

    public static Image getLoad() {
        return load;
    }

    public static Image getLoad2() {
        return load2;
    }

    public static Image getDoublePoint() {
        return doublePoint;
    }

    public static Image getDel() {
        return del;
    }

    public static Image getDel2() {
        return del2;
    }

    public static Image getComma() {
        return comma;
    }

    public static Image getTextPlace() {
        return textPlace;
    }

    public static Image getGrayBike() {
        return grayBike;
    }

    public static Image getSpawn() {
        return spawn;
    }

    public static Image getSpawn2() {
        return spawn2;
    }
}
