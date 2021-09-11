
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Maze {

    private int exitX = 0;
    private int exitY = 0;
    public String[][] GameMatrix;
    private int column;
    private int row;

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public String[][] getGameMatrix() {
        int exitCount = 0;
        int i1 = 0;
        int j1 = 0;
        int playerCount = 0;

        for (int i = 0; i < GameMatrix.length; i++) {
            for (int j = 0; j < GameMatrix[i].length; j++) {
                if (GameMatrix[i][j].equals("P")) {
                    playerCount += 1;

                } else if (GameMatrix[i][j].equals("E")) {
                    exitCount += 1;
                    i1 = i;
                    j1 = j;
                }
            }
        }
        if (playerCount > 1 || exitCount > 1) {
            throw new GameError();
        } else {
            GameMatrix[i1][j1] = "W";
        }

        return GameMatrix;
    }

    public void createMaze(String name) {
        ArrayList<String> datos = new ArrayList<String>();
        File file = new File();

        datos = file.readFile(name);

        for (int i = 0; i < datos.size(); i++) {
            String text = "";
            text = datos.get(i);

            MatrixLoader(text, i);
        }
    }

    public void MatrixLoader(String textLine, int numberLine) {
        try {
            int sum = 0;

            if (numberLine == 0) {
                numberColumnsAndRows(textLine, sum);

            } else {
                addElementsMaze(textLine, numberLine);
            }
        } catch (GameError ex) {
            ex.GameError("El laberinto tiene más de un jugador o una salida");
        }
    }

    public void addElementsMaze(String text, int numberLine) {
        char textVar;

        for (int i = 0; i < text.length(); i++) {
            textVar = text.charAt(i);
            if (textVar == '.') {
                textVar = 'N';
            }
            String textVar1 = "" + textVar;
            if (textVar == 'E') {

                exitX = numberLine - 1;
                exitY = i;
                textVar1 = "" + textVar;
            }
            GameMatrix[numberLine - 1][i] = textVar1;
        }
    }

    public void numberColumnsAndRows(String text, int sum) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                sum += 1;
            }
        }

        int locationOfSpace = text.indexOf(" ");
        String c1 = text.substring(0, locationOfSpace);
        String r1 = text.substring(locationOfSpace + sum, text.length());
        column = Integer.parseInt(c1);
        row = Integer.parseInt(r1);
        GameMatrix = new String[row][column];
    }

    public int diamondCounter() {
        int totalDimonds = 0;
        for (int i = 0; i < GameMatrix.length; i++) {
            for (int j = 0; j < GameMatrix[i].length; j++) {
                totalDimonds += sumDiamond(i, j);
            }
        }
        return totalDimonds;
    }

    public int sumDiamond(int i, int j) {
        int dimonds = 0;
        boolean isDiamond = GameMatrix[i][j].equals("D");
        boolean isHiddenDiamond = GameMatrix[i][j].equals("H");

        dimonds += (GameMatrix[i][j].equals("D") || GameMatrix[i][j].equals("H")) ? 1 : 0;
        return dimonds;
    }
}
