/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegamesource;

import java.io.*;
import javax.swing.*;

/**
 *
 * @author shirl
 */
public class Loader {

    private int exitXCord = 0;
    private int exitYCord = 0;
    private String[][] GameMatrix;
    private int column;
    private int row;
    private final String WALL = "W";

    public int getNumOfColumns() {
        return column;
    }

    public void setNumOfColumns(int column) {
        this.column = column;
    }

    public int getNumOfRows() {
        return row;
    }

    public void setNumOfRows(int row) {
        this.row = row;
    }

    public int ExitXCord() {
        return exitXCord;
    }

    public void setExitXCord(int exitXCord) {
        this.exitXCord = exitXCord;
    }

    public int ExitYCord() {
        return exitYCord;
    }

    public void setExitYCord(int exitYCord) {
        this.exitYCord = exitYCord;
    }

    public void loadFile(String FileName) {

        try {
            BufferedReader ObjReader = new BufferedReader(new FileReader(FileName));

            String CurrentLine;
            int LineNum = 0;

            while ((CurrentLine = ObjReader.readLine()) != null) {
                MatrixLoader(CurrentLine, LineNum);
                LineNum++;
            }

        } catch (IOException e) {
            JFrame frame = new JFrame("Alert");
            JOptionPane.showMessageDialog(frame, "The game file is invalid, try it again." + e.getMessage());
        }

    }

    public void MatrixLoader(String CurrentLine, int lineNum) throws gameFileError {
        if (lineNum == 0) {
            createMatrixSize(CurrentLine);
        } else {
            loadMatrixWithValues(CurrentLine, lineNum);
        }
    }

    public void createMatrixSize(String CurrentLine) {
        int sumSpace = 0;

        for (int i = 0; i < CurrentLine.length(); i++) {
            if (CurrentLine.charAt(i) == ' ') {
                sumSpace += 1;
            }
        }

        int locationOfSpace = CurrentLine.indexOf(" ");

        int numColumnsRead = Integer.parseInt(CurrentLine.substring(0, locationOfSpace));
        int numRowsRead = Integer.parseInt(CurrentLine.substring(locationOfSpace + sumSpace, CurrentLine.length()));

        setNumOfColumns(numColumnsRead);
        setNumOfRows(numRowsRead);

        GameMatrix = new String[row][column];
    }

    public void loadMatrixWithValues(String CurrentLine, int lineNum) {
        char value = 0;

        for (int i = 0; i < CurrentLine.length(); i++) {
            value = CurrentLine.charAt(i);
            if (value == 'E') {
                setExitXCord(lineNum - 1);
                setExitYCord(i);
            }
            String value1 = "" + value;
            GameMatrix[lineNum - 1][i] = value1;
        }
    }

    public String[][] getGameMatrix() {
        for (int i = 0; i < GameMatrix.length; i++) {
            GameMatrix = playerAndExitCount(i);
        }
        return GameMatrix;
    }

    public String[][] playerAndExitCount(int i) {
        int exitCount = 0;
        int playerCount = 0;
        int newValI = 0;
        int newValJ = 0;

        for (int j = 0; j < GameMatrix[i].length; j++) {

            boolean isPlayer = GameMatrix[i][j].equals("P");
            boolean isExit = GameMatrix[i][j].equals("E");

            if (isPlayer) {
                playerCount += 1;
            } else if (isExit) {
                exitCount += 1;
                newValI = i;
                newValJ = j;
            }
        }

        GameMatrix = ValidationMatrix(exitCount, playerCount, newValI, newValJ);

        return GameMatrix;
    }

    public String[][] ValidationMatrix(int exitCount, int playerCount, int newValI, int newValJ) {
        if (playerCount > 1 || exitCount > 1) {
            throw new gameFileError();
        } else {
            GameMatrix[newValI][newValJ] = WALL;
        }

        return GameMatrix;
    }

    public int dimondCount() {
        int totalDimonds = 0;
        for (int i = 0; i < GameMatrix.length; i++) {
            totalDimonds = getDimonds(i);
        }
        return totalDimonds;
    }

    public int getDimonds(int i) {
        int totalDimonds = 0;
        for (int j = 0; j < GameMatrix[i].length; j++) {
            totalDimonds += isDimond(GameMatrix[i][j]);
        }
        return totalDimonds;
    }

    public int isDimond(String dimond) {
        if (dimond.equals("D") || dimond.equals("H")) {
            return 1;
        }
        return 0;
    }
}
