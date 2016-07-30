package com.huge.canvas;

/**
 *
 * @author Julian
 */
public class CanvasUtil {
    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    public static boolean isOutOfRange(int x, int y, int sizeX, int sizeY){
        if(x < 1 || x > sizeX || y < 1 || y > sizeY) {
            return true;
        }
        return false;
    }

    /**
     * Returns if a position is already drawn
     * @param canvas
     * @param x
     * @param y
     * @return  true if the position is drawn 
     */
    public static boolean isPositionDrawn(char [][] canvas, int x, int y) {
        return canvas[x][y] != ' ';
    }
}
