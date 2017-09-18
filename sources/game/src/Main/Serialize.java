package Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Serialize {
    public Serialize() {
    }

    public static void fillAllAlgoFile(AlgInfo algInfo) {
        try {
            ArrayList<AlgInfo> algInfos = readFile("allAlgorithms.txt");
            BufferedWriter bf = new BufferedWriter(new FileWriter("allAlgorithms.txt"));
            int size = algInfos.size();

            for (int i = 0; i < size; i++) {
                AlgInfo alg = algInfos.get(i);
                bf.append("id<" + i + ">path<" + alg.getPath() + ">totalScore<" +
                        alg.getTotalScore() + ">highScore<" + alg.getHighScore() + ">\r\n");
            }

            bf.append("id<" + size + ">path<" + algInfo.getPath() + ">totalScore<" + algInfo.getTotalScore() + ">highScore<" + algInfo.getHighScore() + ">\r\n");

            bf.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fillAlgoInNewGameFile(ArrayList<AlgInfo> algInfos, String fileName) {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(fileName));

            for (int i = 0; i < algInfos.size(); i++) {
                AlgInfo alg = algInfos.get(i);
                bf.append("id<" + i + ">path<" + alg.getPath() + ">totalScore<" +
                        alg.getTotalScore() + ">highScore<" + alg.getHighScore() + ">\r\n");
            }

            bf.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<AlgInfo> readFile(String fileName) {
        try {
            boolean start = false;
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            ArrayList<AlgInfo> algInfos = new ArrayList<>();

            while (line != "\r\n") {
                if (line == null || Objects.equals(line, ""))
                    break;
                int iCount = 0;
                String id = "";
                String path = "";
                String totalScore = "";
                String highScore = "";
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '<') {
                        iCount++;
                        start = true;
                        i++;
                    }
                    if (line.charAt(i) == '>')
                        start = false;
                    if (start && iCount == 1) {
                        id += line.charAt(i);
                    }
                    if (start && iCount == 2) {
                        path += line.charAt(i);
                    }
                    if (start && iCount == 3) {
                        totalScore += line.charAt(i);
                    }
                    if (start && iCount == 4) {
                        highScore += line.charAt(i);
                    }
                }
                algInfos.add(new AlgInfo(Integer.parseInt(id), path, Integer.parseInt(totalScore), Integer.parseInt(highScore)));
                line = br.readLine();
            }
            return algInfos;
        } catch (FileNotFoundException e) {
            try {
                BufferedWriter bf = new BufferedWriter(new FileWriter(fileName));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
