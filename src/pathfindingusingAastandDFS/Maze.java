/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfindingusingAastandDFS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

/**
 *
 * @author Sam
 */
public class Maze {
        private char outArr[][];
    private String file;
    List<Node> pathList;
    private int totalF = 0;
    private int mazeHeight;
    private int mazeWidth;

    public Maze(String file) {
        this.file = file;
        this.mazeHeight = computeMazeHeight();
        this.mazeWidth = computeMazeWidth();
        outArr = new char[mazeWidth][mazeHeight];
        readMazeFromFile();

    }

    private int computeMazeWidth() {
        int mW = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = br.readLine();
            mW = s.length();

        } catch (Exception e) {
        }
        return mW;
    }

    private int computeMazeHeight() {
        int mH = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;

            while ((s = br.readLine()) != null) {
                mH = mH + 1;
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return mH;
    }

    public int getMazeHeight() {
        return mazeHeight;
    }

    public int getMazeWidth() {
        return mazeWidth;
    }

    private void readMazeFromFile() {

        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String s;
                int temp = 0;
                while ((s = br.readLine()) != null) {
                    char[] tempArr = s.toCharArray();
                    for (int i = 0; i < mazeWidth; i++) {
                        switch (tempArr[i]) {
                            case '%':
                                outArr[temp][i] = '%';

                                break;
                            case ' ':
                                outArr[temp][i] = ' ';

                                break;
                            case 'P':
                                outArr[temp][i] = 'P';

                                break;
                            case '.':
                                outArr[temp][i] = '.';

                                break;
                            default:
                                break;
                        }
                    }
                    temp++;

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void putPathOnMaze(List<Node> pathList) {
        this.pathList = pathList;
        for (int i = 0; i < pathList.size(); i++) {
            Node n = pathList.get(i);
            int xPlace = n.getXPlace();
            int yPlace = n.getYPlace();
            if (i == 0) {
                outArr[xPlace][yPlace] = 'P';
            } else {
                outArr[xPlace][yPlace] = '.';
            }
            totalF = n.getF() + totalF;

        }
    }

    public char[][] getOutArr() {
        return outArr;
    }

    public void printMaze() {
        for (char[] outArr1 : outArr) {
            for (int j = 0; j < outArr.length; j++) {
                System.out.print(outArr1[j] + " ");
            }
            System.out.println();
        }
        System.out.println("PathCost(No of Steps/G(n)): " + (pathList.size() - 1));
        System.out.println("F(n): " + totalF);

    }

    public void printMazeWithoutF() {
        for (char[] outArr1 : outArr) {
            for (int j = 0; j < outArr.length; j++) {
                System.out.print(outArr1[j] + " ");
            }
            System.out.println();
        }
        System.out.println("PathCost(No of Steps/G(n)): " + (pathList.size() - 1));

    }

}
