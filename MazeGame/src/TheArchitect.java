/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegamesource;

import javax.swing.*;

/**
 *
 * @author shirl
 */
public class TheArchitect extends JFrame {

    int foundPlayer = 0;
    String[][] updatedMatrix;
    int WallXCord;
    int WallYCord;
    int collected = 0;
    boolean level;
    int globalTotalDimonds = 0;

    private static final String BLOCK_HIDDEN = "H";
    private static final String BLOCK_DIAMOND = "D";
    private static final String BLOCK_MOVEABLE = "M";
    private static final String BLOCK_NORMAL = "N";
    private static final String BLOCK_EXIT = "E";
    private static final String BLOCK_PLAYER = "P";

    public void setExit(int x, int y) {
        WallXCord = x;
        WallYCord = y;
    }

    public void showExit() {
        updatedMatrix[WallXCord][WallYCord] = "E";
    }

    public boolean getLevel() {
        return level;
    }

    public int getDimondsLeft() {
        return globalTotalDimonds - collected;
    }

    public void nextLevel(boolean continueTo) {
        level = continueTo;
    }

    public String[][] getUpdatedMatrix() {
        return updatedMatrix;
    }

    public void playerMove(int xScale, int yScale, String[][] currentMatrix, int totalDimonds) {
        int[] coordsPlayer = findPlayer(currentMatrix);
        int x = coordsPlayer[0];
        int y = coordsPlayer[1];
        globalTotalDimonds = totalDimonds;
        assert totalDimonds > 0 : "NOT DIAMONDS FOUND";
        nextLevel(false);

        if (collected == totalDimonds) {
            showExit();
        }

        assert updatedMatrix != updateMatrix(xScale, yScale, currentMatrix, x, y) : "Problemas con el nivel";
        updatedMatrix = updateMatrix(xScale, yScale, currentMatrix, x, y);
    }

    public int[] findPlayer(String[][] currentMatrix) {
        int coordX = 0, coordY = 0;

        for (int i = 0; i < currentMatrix.length; i++) {
            for (int j = 0; j < currentMatrix[i].length; j++) {
                if (isPlayer(currentMatrix[i][j])) {
                    coordX = i;
                    coordY = j;
                    break;
                }
            }
        }

        assert coordY >= 0 : false;
        return new int[]{coordX, coordY};
    }

    private boolean isPlayer(String block) {
        assert block != null : false;
        return block.equals(BLOCK_PLAYER);
    }

    private String[][] updateMatrix(int xScale, int yScale, String[][] currentMatrix, int x, int y) throws youHitWall {
        String nextBlock = currentMatrix[x + xScale][y + yScale];
        assert nextBlock != null : false;

        switch (nextBlock) {
            case BLOCK_HIDDEN:
            case BLOCK_DIAMOND:
                collected++;
                break;
            case BLOCK_EXIT:
                assert !level : false;
                nextLevel(true);
                break;
            case BLOCK_MOVEABLE:
                if (currentMatrix[x + (xScale * 2)][y + (yScale * 2)].equals(BLOCK_NORMAL)) {
                    currentMatrix[x + (xScale * 2)][y + (yScale * 2)] = BLOCK_MOVEABLE;
                } else {
                    throw new youHitWall();
                }
                break;
            default:
                if (!nextBlock.equals(BLOCK_NORMAL)) {
                    throw new youHitWall();
                }
        }

        currentMatrix[x][y] = BLOCK_NORMAL;
        currentMatrix[x + xScale][y + yScale] = BLOCK_PLAYER;
        return currentMatrix;
    }

    private class youHitWall extends RuntimeException {

        public youHitWall() {
            JFrame frame = new JFrame("Warning");
            JOptionPane.showMessageDialog(frame, "You hit the wall");
        }
    }

}
