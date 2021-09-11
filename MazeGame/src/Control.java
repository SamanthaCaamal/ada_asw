
import javax.swing.*;

public class Control {

    String[][] updatedMatrix;
    boolean level;
    int WallXCord;
    int WallYCord;
    int diamondsCollected = 0;
    int totalDiamonds = 0;

    public void setExit(int x, int y) {
        WallXCord = x;
        WallYCord = y;
    }

    public boolean getLevel() {
        return level;
    }

    public int getDimonds() {
        return totalDiamonds - diamondsCollected;
    }

    public String[][] getUpdatedMatrix() {
        return updatedMatrix;
    }

    public void playerMove(int xScale, int yScale, String[][] currentMatrix, int dimonds) {
        int x = 0;
        int y = 0;

        totalDiamonds = dimonds;
        nextLevel(false);

        for (int i = 0; i < currentMatrix.length; i++) {
            for (int j = 0; j < currentMatrix[i].length; j++) {
                //posicionMatriz == "P"
                boolean currentPosition = currentMatrix[i][j].equals("P");
                if (currentPosition) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        updatedMatrix = validateItems(currentMatrix, x, y, xScale, yScale, dimonds);
    }

    public String[][] validateItems(String[][] currentMatrix, int x, int y, int xScale, int yScale, int totalDimonds) {

        String character = currentMatrix[x + xScale][y + yScale];

        switch (character) {
            case "H":
                reassign(currentMatrix, x, y, xScale, yScale);
                diamondsCollected += 1;
                break;
            case "D":
                reassign(currentMatrix, x, y, xScale, yScale);
                diamondsCollected += 1;
                break;
            case "M":
                availableSpace(currentMatrix, x, y, xScale, yScale);
                break;
            case "N":
                reassign(currentMatrix, x, y, xScale, yScale);
                break;
            case "E":
                reassign(currentMatrix, x, y, xScale, yScale);
                nextLevel(true);
                break;
        }

        fullDiamond(totalDimonds);

        return currentMatrix;
    }

    public void availableSpace(String[][] currentMatrix, int x, int y, int xScale, int yScale) {
        if (currentMatrix[x + (xScale * 2)][y + (yScale * 2)].equals("N")) {
            reassign(currentMatrix, x, y, xScale, yScale);
            currentMatrix[x + (xScale * 2)][y + (yScale * 2)] = "M";
        }
    }

    public void fullDiamond(int totalDimonds) {
        if (diamondsCollected == totalDimonds) {
            showWall();
        }
    }

    public void reassign(String[][] sampleMtrx, int x, int y, int xShifted, int yShifted) {
        sampleMtrx[x][y] = "N";
        sampleMtrx[x + xShifted][y + yShifted] = "P";
    }

    public void showWall() {
        updatedMatrix[WallXCord][WallYCord] = "E";
    }

    public void nextLevel(boolean tOrF) {
        level = tOrF;
    }
}
